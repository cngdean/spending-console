package org

object Analyze {
  def main(args: Array[String]): Unit = {
    println("Reading in QFX files ")

    def sum(txns: List[Transaction]) = {
      txns.map( (x: Transaction) => x.amount).sum
    }

    val filename = System.getProperty("user.home") + "/qfx/" + "CreditCard5.qfx"
    val filename2 = System.getProperty("user.home") + "/qfx/" + "Checking1.qfx"
    var txns = ProcessFiles.process(filename) ::: ProcessFiles.process(filename2)

    println("Spending:" +  sum(txns.filter(_.amount < 0)))

    val after = new java.util.Date("09/01/2014")
    //    txns = txns.filter( _.date.after(after) )

    println(TransactionSummary.spendingByMonth(txns))

    println(TransactionSummary.spendingByMonth(txns))
    println("Spending:" +  sum(txns.filter(_.amount < 0)))

    println("Spending by category\n" )
    println(TransactionSummary.spendingByCategory(txns))

    println( for (m <- TransactionSummary.splitByMonth(txns)) yield (m._1, TransactionSummary.spendingByCategory(m._2) ))

    println(txns.head)

  }
}
