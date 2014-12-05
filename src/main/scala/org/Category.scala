package org

case class Category(categoryName: String)

object CategoryMapper {
  val source = scala.io.Source.fromFile(System.getProperty("user.home") + "/qfx/" + "regexconfig.txt")

  def buildCategories(lines: List[String]):List[(String,String)] = {
    if (lines.isEmpty) Nil
    else {
      val items = lines.head.split("\t")
      val (cat,regex) = (items.head, items.tail.head)
      (cat, regex) :: buildCategories(lines.tail)
    }
  }

  val categories = buildCategories(source.getLines().toList) ::: ("Needs Category", ".*") :: Nil

  def findCategory(desc: String):Category = {
    val match2 = categories.dropWhile((x) => !desc.matches(x._2))
    if (!match2.isEmpty) Category(match2.head._1)
    else Category("Unknown")
  }

}
