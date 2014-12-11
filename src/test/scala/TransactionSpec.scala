import org.scalatest.{FlatSpec, Matchers}
import org.{Transaction, ProcessFiles, Category, CategoryMapper}

class TransactionSpec extends FlatSpec with Matchers {
    "A Transaction Processor " should "have a transaction" in {
      val tp = ProcessFiles.process("src/test/resources/test_qfxFile.qfx")
      tp shouldBe a [List[_]]
      tp.head shouldBe a [Transaction]
      val t = tp.head
      t.amount shouldBe -90.00
      tp.tail.head.amount shouldBe -29.31
    }

  "A Transaction Processor " should "have a transaction in directories" in {
    val tp = ProcessFiles.processDirectory("src/test/resources/")
    tp shouldBe a [List[_]]
    tp.head shouldBe a [Transaction]
    val t = tp.head
    t.amount shouldBe -90.00
    tp.tail.head.amount shouldBe -29.31
  }

}

