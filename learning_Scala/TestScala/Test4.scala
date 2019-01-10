import Array._
object Test4{
	//处理数组
	def main(args:Array[String]){
		var z:Array[String] = new Array[String](3);
		z(0) = "Hello";
		z(1) = "World";
		z(2) = "Scala";
		//输出数组中所有元素
		for (x <- z){
			println( x );
		}
		var total = 0.0;
		var myList = Array(1.9,2.9,3.4,3.5);
		//计算数组中的所有元素的和
		for(i <- 0 to (myList.length - 1)){
			total += myList(i);
		}
		println("总和为: " + total);
		var max = myList(0);
		//计算最大值
		for( i <- 1 to (myList.length - 1)){
			if(myList(i) > max){
				max = myList(i);
			}
		}
		println("最大值为: " + max);

		//二维数组
		var myMatrix = ofDim[Int](3,3);
		for(i <- 0 to myMatrix.length - 1){
			for(j <- 0 to myMatrix(0).length - 1){
				myMatrix(i)(j) = j;
			}
		}
		//打印
		for(i <- 0 to 2){
			for(j <- 0 to 2){
				print(" " + myMatrix(i)(j));
			}
			println();
		}
	}
	
}