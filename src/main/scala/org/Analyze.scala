package org

import net.sf.ofx4j.io.OFXHandler
import net.sf.ofx4j.io.nanoxml.NanoXMLOFXReader

object Analyze {
  def main(args: Array[String]): Unit = {
    println("Reading in QFX files ")

    val filename = System.getProperty("user.home") + "/qfx/" + "CreditCard5.qfx"
    val txns = ProcessFiles.process(filename)


    println("Total:" + txns.map( (x: Transaction) => x.amount).sum )

  }
}
