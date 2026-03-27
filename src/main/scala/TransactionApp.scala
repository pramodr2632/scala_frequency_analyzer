case class Transaction(id: String, userId: String, amount: Double)

object TransactionApp extends App {

  def highValueUsers(transactions: List[Transaction]): Map[String, Double] = {

    val totals = scala.collection.mutable.Map.empty[String, Double]

    transactions.foreach { txn =>
      val current = totals.getOrElse(txn.userId, 0.0)
      totals.update(txn.userId, current + txn.amount)
    }

    totals.filter { case (_, total) => total > 1000 }.toMap
  }

  // test data
  val data = List(
    Transaction("1", "u1", 500),
    Transaction("2", "u1", 700),
    Transaction("3", "u2", 200)
  )

  println(highValueUsers(data))
}