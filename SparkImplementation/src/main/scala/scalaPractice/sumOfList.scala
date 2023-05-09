package scalaPractice

object sumOfList {

  def main(args: Array[String]):Unit={
    val list=List.tabulate(10)(x => x +1)
    println("usingSumMethod "+ ": "+usingSumMethod(list))
    println("usingfoldLeftMethod "+ ": "+usingfoldLeftMethod(list))
    println("usingReduceMethod "+ ": "+usingReduceMethod(list))
  }

  def usingSumMethod(list: List[Int]):Int={
    list.sum
  }
  def usingfoldLeftMethod(list: List[Int]): Int = {
    list.foldLeft(0)(_ + _)

    /*
    list.foldLeft(0){
      (acc, x) => acc + x
    }
     */
  }

  def usingReduceMethod(list: List[Int]):Int={
    list.reduce(_ + _)
  }

}
