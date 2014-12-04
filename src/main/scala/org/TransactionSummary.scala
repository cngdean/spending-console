package org

/**
 * Created by carmen on 12/4/14.
 */
object TransactionSummary {
  def spendingByMonth(txns: List[Transaction]): Map[String,BigDecimal] = {
    for ((month,txns) <- txns.groupBy(_.month)) yield (month, txns.foldLeft(BigDecimal(0))((acc, x) => acc + x.amount))
  }



}
