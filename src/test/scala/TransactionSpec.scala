import org.scalatest.{FlatSpec, Matchers}
import org.{Transaction, ProcessFiles, Category, CategoryMapper}

class TransactionSpec extends FlatSpec with Matchers {
    "A Transaction Processor " should "have a transaction" in {
      val tp = ProcessFiles.process("src/test/resources/test_qfxFile.qfx", "src/test/resources/test_regexfile.txt")
      tp shouldBe a [List[_]]
      tp.head shouldBe a [Transaction]
      val t = tp.head
      t.amount shouldBe -90.00
      tp.tail.head.amount shouldBe -29.31
    }

  "A Transaction Processor " should "have a transaction in directories" in {
    val tp = ProcessFiles.processDirectory("src/test/resources/", "src/test/resources/test_regexfile.txt")
    tp shouldBe a [List[_]]
    tp.head shouldBe a [Transaction]
    val t = tp.head
    t.amount shouldBe -90.00
    tp.tail.head.amount shouldBe -29.31
  }

  "A Transaction Processor " should "ignore excluded category transactions" in {
    val tp = ProcessFiles.processDirectory("src/test/resources/", "src/test/resources/test_regexfile.txt")
    tp.find( _.note.equals("PETCO PET MARKET")) should not be empty
    tp.find( _.note.equals("ExcludeMe")) shouldBe empty
  }
}

