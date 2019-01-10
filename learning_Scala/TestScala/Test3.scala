object Test3{
	//字符串
	val greating: String = "Hello World!";
	def main(args: Array[String]){
		//println( greating );
		var str1 = "hello Scala!";
		val buf = new StringBuilder;
		buf += 'a';
		buf ++= "bcdef";
		var len = buf.length();
		var str = greating.concat(str1);
		println( "buf is " + buf.toString );
		println( "buf length is: " + len);
		println("str is: " + str);
	}
}