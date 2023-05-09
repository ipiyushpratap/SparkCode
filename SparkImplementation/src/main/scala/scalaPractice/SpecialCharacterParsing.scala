package scalaPractice

object SpecialCharacterParsing {

  def main(args:Array[String]):Unit={
    val str=" \" Hello World\". - abc " +
      " \" Hello1 World\". - def " +
      " \" Hello World1\". - ghi "
    println(str)

    val parsedStr=str.replaceAll("\"|-|\\.", "")
    println(parsedStr)

    //parsedStr.split(" ").filter(_.nonEmpty).groupBy(x => x).map(x => (x._1,x._2.length))

    val splitStr=parsedStr.split("\\s+") // split based on one or more white space

    val st=splitStr.filterNot(_ =="")  //splitStr.filter(_.nonEmpty)

    val abc=st.groupBy(x => x).map(x => (x._1,x._2.length))
    println(abc)

  }

}
