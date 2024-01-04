object KafkaStreams {

  // Daniel uses this method as opposed to data in case classes for some reason
  object Domain {
    // this is the Domain outline for an online store
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
    val Discounts = "disocunts"
    val Orders = "orders"
    val PaidOrders = "paid-orders"
  }

  def main(args: Array[String]): Unit = {
    // Rather than manually writing out the Kafka command for each of the topics, the below is a better option
    List(
      "orders-by-user" ,
      "discount-profiles-by-user" ,
      "discounts" ,
      "orders" ,
      "payments" ,
      "paid-orders" ,
    ).foreach { topic =>
      println("")
    }
  }

}
