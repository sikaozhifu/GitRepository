import java.io._
import scala.io._
object Test12{
	//文件I/O
	def main(args: Array[String]){
		// val writer = new PrintWriter(new File("test12.txt"));
		// writer.write("Hello World");
		// writer.close();

		// print("please input your name:");
		// val line = StdIn.readLine();
		// println("your name is " + line);
		println("文件内容为： ");
		Source.fromFile("test12.txt").foreach{
			print
		}
	}
}