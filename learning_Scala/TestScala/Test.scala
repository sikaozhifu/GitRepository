import scala.util.control._
object Test{
	def main(args: Array[String]){
		var x = 30;
		if(x < 20){
			println("x < 20");
		}else{
			println("x > 20");
		}
		val numList = List(1,2,3,4,5,6,7,8,9,10);
		val loop = new Breaks;
		loop.breakable{
			for(a <- numList){
				println("Value of a: " + a);
				if(a == 4){
					loop.break;
				}
			}
		}
		println("After the loop");
	}
}