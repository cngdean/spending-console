package org

import net.sf.ofx4j.io.OFXHandler
import net.sf.ofx4j.io.nanoxml.NanoXMLOFXReader

/**
 * Created by carmen on 12/1/14.
 */
object ProcessFiles {

  def process(filename: String):List[Transaction] = {
    val reader = new NanoXMLOFXReader

    val txns = scala.collection.mutable.ListBuffer.empty[Transaction]

    var (id, note, memo, amount) = ("", "", "", 0.toFloat)

    val handle = new OFXHandler {
      override def onElement(s: String, v: String): Unit = {
        s match {
          case ("TRNAMT") => amount = v.toFloat
          case ("NAME") => note = v
          case ("FITID") => id = v
          case ("MEMO") => memo = v
          case _ =>
        }
      }

      override def endAggregate(s: String): Unit = {
        if ("STMTTRN" == s) {
          txns += Transaction(id, note, memo, amount)
        }
      }

      override def onHeader(s: String, s1: String): Unit = println("header" + s)

      override def startAggregate(s: String): Unit = {
        if ("STMTTRN" == s) {
          note = ""
          id = ""
          amount = 0.toFloat
          memo = ""
        }
      }
    }

    reader.setContentHandler(handle)
    reader.parse(new java.io.FileInputStream(filename))
    // dedupe by txn id
    txns.sortBy( _.id ).groupBy{_.id}.map{_._2.head}.toList
  }

}
