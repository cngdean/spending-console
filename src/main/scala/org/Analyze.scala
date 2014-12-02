package org

object Analyze {
  def main(args: Array[String]): Unit = {
    println("Reading in QFX files ")

    def sum(txns: List[Transaction]) = {
      txns.map( (x: Transaction) => x.amount).sum
    }

    val filename = System.getProperty("user.home") + "/qfx/" + "CreditCard5.qfx"
    val txns = ProcessFiles.process(filename)


    println("Spending:" +  sum(txns.filter(_.amount < 0)))

    val source = scala.io.Source.fromFile(System.getProperty("user.home") + "/qfx/" + "regexconfig.txt")
    for (l <- source.getLines()) {
      val l2 = l.split("\t")
      val (name, regex) = (l2.head, l2.tail.head)
      println(name + ":\t" + sum(txns.filter( _.note.matches(regex))))
    }

  }
}
