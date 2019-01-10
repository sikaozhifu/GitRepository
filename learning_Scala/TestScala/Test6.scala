object Test6{
	def main(args:Array[String]){
		val it = Iterator("Baidu","Google","Taobao");
		while (it.hasNext){
			println(it.next());
		}
	}
}