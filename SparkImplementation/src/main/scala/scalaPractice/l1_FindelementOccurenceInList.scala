package scalaPractice

object l1_FindelementOccurenceInList {

  def main(args:Array[String]):Unit={
    val listOfString=List("Ram", "Sita", "Ram", "Lakshman", "Hari", "Lakshman", "Hanuman", "Hanuman", "Ram", "Sita", "Sita")
    println(m1_FindelementOccurenceInList(listOfString).sorted)
    println(m2_FindelementOccurenceInList(listOfString).sorted)
    println(m3_FindelementOccurenceInList(listOfString).sorted)
  }

// using groupBy
  def m1_FindelementOccurenceInList(listOfString: List[String]):List[(String,Int)]={
    listOfString.groupBy(x => x).map(x => (x._1, x._2.size)).toList
  }

  // Using foldLeft
  def m2_FindelementOccurenceInList(listOfString: List[String]): List[(String,Int)] = {
    listOfString.foldLeft(Map.empty[String, Int]) {
      (map, elem) => map + ((elem, map.getOrElse(elem, 0) + 1))
    }.toList
  }

  //Using map and Count
  def m3_FindelementOccurenceInList(listOfString: List[String]): List[(String,Int)] = {
    listOfString.map(x => (x, listOfString.count( _ == x))).distinct

  }
}
