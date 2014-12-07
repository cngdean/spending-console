import org.Category
import org.scalatest.{Matchers, FlatSpec}

class CategorySpec extends FlatSpec with Matchers {
    "A Category " should "have a name" in {
      val cat = Category("a name")
      cat.categoryName should be ("a name")
    }


}

