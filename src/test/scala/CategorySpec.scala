import org.{CategoryMapper, Category}
import org.scalatest.{Matchers, FlatSpec}

class CategorySpec extends FlatSpec with Matchers {
    "A Category " should "have a name" in {
      val cat = Category("a name", "a regex")
      cat.categoryName should be ("a name")
      cat.regex should be ("a regex")
    }

    "A Category Mapper" should "read a regex file" in {
      val catMapper = CategoryMapper("src/test/resources/test_regexfile.txt")
      catMapper.categories shouldBe a [List[_]]
      catMapper.categories should contain (Category("Kitty", "(petsmart|petco).*"))
      catMapper.findCategory("starbucks i25 & tamarac").categoryName shouldBe ("Coffee")
    }
}

