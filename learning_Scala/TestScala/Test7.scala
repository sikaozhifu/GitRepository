import java.io._
//类和对象
class Point(val xc: Int, val yc: Int){
	var x: Int = xc;
	var y: Int = yc;
	def move(dx: Int,dy: Int){
		x = x + dx;
		y = y + dy;
		println("x的坐标点: " + x);
		println("y的坐标点: " + y);
	}
}
class Location(override val xc: Int,override val yc: Int,val zc: Int) extends Point(xc,yc){
	var z: Int = zc;
	def move(dx: Int, dy: Int, dz: Int){
		x = x + dx;
		y = y + dy;
		z = z + dz;
		println("x的坐标点: " + x);
		println("y的坐标点: " + y);
		println("z的坐标点: " + z);
	}
}
object Test7{
	def main(args: Array[String]){
		val pt = new Point(10,20);
		pt.move(10,10);
		val loc = new Location(10,20,15);
		loc.move(10,10,5);
	}
}