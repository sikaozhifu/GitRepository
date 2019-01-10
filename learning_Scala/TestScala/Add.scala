object Add{
	def main(args: Array[String]) {
		println("Returned Value : " + addInt(5,7));
		println(printMe());
	}
	def addInt(a:Int, b:Int): Int = {
		var sum: Int = 0;
		sum = a + b;
		return sum;
	}
	def printMe(): Unit = {
		println("Hello, Scala!");
	}
}