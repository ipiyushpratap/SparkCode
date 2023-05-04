import org.apache.log4j.{Level, Logger}
import org.apache.spark.sql.SparkSession
import org.apache.spark.sql.functions._

object MaxElementAmong3Columns {

  def maxElementAmong3Columns():Unit={
    val p = List(1, 2, 3, 4)

    val marks = Seq(
      (101, 90, 95, 93),
      (102, 91, 93, 99),
      (103, 92, 95, 93),
      (104, 93, 93, 94),
      (105, 94, 92, 90),
    )
    ("Student_id", "English", "Science", "Maths") //
    Logger.getLogger("org.apache").setLevel(Level.ERROR)
    val spark = SparkSession.builder().appName("ffggee")
      .master("local[*]")
      //    .config("spark.serializer", "org.apache.spark.serializer.KryoSerializer")
      //    .config("spark.kryoserializer.buffer", "1024k")
      //    .config("spark.kryoserializer.buffer.max", "1024m")
      //    .config("spark.kryo.registrationRequired", "true")
      .getOrCreate()
    val sc = spark.sparkContext

    import spark.implicits._
    val marksDf = sc.parallelize(marks).toDF("Student_id", "English", "Science", "Maths")
    marksDf.show()
    /*

    val df2 = df.withColumn("new_gender", when(col("gender") === "M","Male")
          .when(col("gender") === "F","Female")
          .otherwise("Unknown"))

     */
    val df1 = marksDf.withColumn("Fav Subject",
      when(col("English") > col("Science") && col("English") > col("Maths"), "English")
        .when(col("Science") > col("English") && col("Science") > col("Maths"), "Science")
        .when(col("Maths") > col("English") && col("Maths") > col("Science"), "Maths")
    )
      .select("Student_id", "Fav Subject")


    //  val df2=marksDf.withColumn("Fav Subject",
    //    expr("case when 'English' > 'Science' && 'English' > 'Maths' then 'English' "))
    //    .select("Student_id","Fav Subject")
    //  df2.show()

    val col_name_except_sid = marksDf.columns.tail
    marksDf.withColumn("max_val", greatest(col_name_except_sid.head, col_name_except_sid.tail: _*))
      .withColumn("Fav Subject",
        when(col("max_val") === col("English"), "English")
          .when(col("max_val") === col("Science"), "Science")
          .when(col("max_val") === col("Maths"), "Maths"))
      .show()


    marksDf.withColumn("MaxValue", array_max(array(col_name_except_sid.map(col): _*))).show()

  }

}
