package org

import scala.util.matching.Regex

case class Category(categoryName: String, regex: String) {
  override def equals(c: Any) = c match {
    case that: Category => this.categoryName == that.categoryName && this.regex == that.regex
    case _ => false
  }
}

case class CategoryMapper(filename: String) {
  val source = scala.io.Source.fromFile(filename)

  def buildCategories(lines: List[String]):List[Category] = {
    if (lines.isEmpty) Nil
    else {
      val items = lines.head.split("\\|\\|")
      println(lines.head)
      val (cat,regex) = (items.head, items.tail.head)
      Category(cat, regex) :: buildCategories(lines.tail)
    }
  }

  val categories: List[Category] = buildCategories(source.getLines().toList) ::: Category("Needs Category", ".*") :: Nil

  def findCategory(desc: String):Category = {
    val match2 = categories.dropWhile((x: Category) => !desc.matches(x.regex))
    match2.head
  }

}
