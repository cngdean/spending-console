package org

/**
 * Created by carmen on 12/1/14.
 */
case class Transaction(id: String, note: String, memo: String, amount: BigDecimal, date: java.util.Date) {

  val format = new java.text.SimpleDateFormat("yyyy-MM")

  def month = format.format(date)


}