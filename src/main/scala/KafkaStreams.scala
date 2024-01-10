import io.circe.{Decoder, Encoder}
import io.circe.generic.auto._
import io.circe.parser._
import io.circe.syntax._
import org.apache.kafka.common.serialization.Serde
import org.apache.kafka.streams.kstream.{GlobalKTable, KStream, KTable}
import org.apache.kafka.streams.scala.StreamsBuilder
import org.apache.kafka.streams.scala.serialization.Serdes
import org.apache.kafka.streams.scala.ImplicitConversions._

object KafkaStreams {

  // Daniel uses this method as opposed to data in case classes for some reason
  object Domain {
    // this is the Domain outline for an online store
    // The IDE were telling you to ensure that the below we privately accessed
    type UserId = String
    type Profile = String
    type Product = String
    type OrderId = String

    case class Order(orderId: OrderId, userId: UserId, products: List[Product], amount: Double)
    case class Discount(profile: Profile, amount: Double)
    case class Payment(orderId: OrderId, status: String)
  }

  object Topics {
    val OrdersByUser = "orders-by-user"
    val DiscountProfileByUser = "discount-profiles-by-user"
    val Discounts = "discounts"
    val Orders = "orders"
    val PaidOrders = "paid-orders"
  }

  /*
    Three primary entities within Kafka (parts of a stream processor)
      1. source = emits elements
      2. flow = transforms elements along the way (e.g. map)
      3. sink = "ingests' elements
   */
  import Domain._
  // Note that below we are using the inbuilt 'Serde' in Kafka wherein we have to explicitly state both a Serializer and Deserializer
  // Remember that with a deserializer, you have to alter an object into bytes -> the function was altered from an implicit val towards an implicit def also and this needs to be understood
  import Topics._
  import Domain._

  implicit def serdeOrder[A >: Null : Decoder : Encoder ]: Serde[A] = {
    val serializer = (a: A) => a.asJson.noSpaces.getBytes()
    val deserializer = (bytes: Array[Byte]) => {
      val string = new String(bytes)
      decode[A](string).toOption
    }

    Serdes.fromFn[A](serializer, deserializer)
  }

  // topology
  private val builder = new StreamsBuilder()

  // KStream
  val usersOrdersStream: KStream[UserId, Order] = builder.stream[UserId, Order](OrdersByUser)

  // KTable - is distributed
  val usersProfilesTable: KTable[UserId, Profile] = builder.table[UserId, Profile](DiscountProfileByUser)

  // GlobalKTable - copied to all the nodes
  val discountProfilesGTable: GlobalKTable[Profile, Discount] = builder.globalTable[Profile, Discount](Discounts)

  builder.build()


  def main(args: Array[String]): Unit = {
    // The below will be printed for the topics such that there is a broker in the bash
    List(
      "orders-by-user" ,
      "discount-profiles-by-user" ,
      "discounts" ,
      "orders" ,
      "payments" ,
      "paid-orders" ,
    ).foreach { topic =>
      println(s"kafka-topics -- bootstrap-server localhost:9092 --topic ${topic} --create")
    }
  }

}
