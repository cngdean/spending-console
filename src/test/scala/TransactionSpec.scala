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

    "A Category Mapper" should "read a regex file" in {
      val catMapper = CategoryMapper("src/test/resources/test_regexfile.txt")
      catMapper.categories shouldBe a [List[_]]
      catMapper.categories should contain (Category("Kitty", "(petsmart|petco).*"))
      catMapper.findCategory("starbucks i25 & tamarac").categoryName shouldBe ("Coffee")
    }
}

