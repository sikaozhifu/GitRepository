import scala.util.matching.Regex
//正则表达式
object Test8{
	def main(args: Array[String]){
		val pattern = new Regex("(S|s)cala");
		val str = "Scala is scalable and cool";
		println((pattern findAllIn str).mkString(","));
	}
}