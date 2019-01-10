object Test2 {
	//闭包
	def main(args: Array[String]){
		println( "muliplier(1) value = " + muliplier(1) );
		println( "muliplier(2) value = " + muliplier(2) );
	}
	var factor = 3;
	val muliplier = (i:Int) => i * factor;
}