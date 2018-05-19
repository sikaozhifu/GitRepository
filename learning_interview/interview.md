### 面试问题：

* java跨平台的原理：

  java通过不同的系统、不同版本、不同位数的java虚拟机，来屏蔽不同指令集差异而对外提供统一的接口。

* java中int占几个字节？

  4个，32位

* 面向对象的特征有哪些方面？

  封装：将对象封装成一个高度自治和相对封闭的个体

  抽象：找出一些事物的相似之处和共性之处

  继承：可以在一个已经存在的类的基础之上来进行，例如遗产的继承

  多态：父类或接口定义的引用变量指向子类或具体实现的类，程序在调用时才动态绑定。

* 有了基本的数据类型，为什么还需要包装类型？

  装箱：在编译时会调用valueOf()方法来装箱

  拆箱：把包装类型转换为基本数据类型，调用xxxValue();

  java是一种面向对象的语言，而基本的数据类型，不具备面向对象的特性，比如null值。

* "=="和equals方法的区别：

  ==用来判断两个变量之间的值是否相等。基本数据类型直接比较值（包装类型会触发自动拆箱的过程），引用数据类型要比较对应的引用的内存首地址（对于包装类型，equals方法并不会进行类型转换）；

  equals用来比较两个对象的某些特征是否一样。举例：两个Person对象的名字(name)是否一样，若一样，则相等。

  ```java
  public static void main(String[] args) {

  		Integer a = 1;
  		Integer b = 2;
  		Integer c = 3;
  		Integer d = 3;
  		Integer e = 321;
  		Integer f = 321;
  		Long g = 3L;
  		Long h = 2L;

  		System.out.println(c == d);
  		System.out.println(e == f);
  		System.out.println(c == (a + b));
  		System.out.println(c.equals(a + b));
  		System.out.println(g == (a + b));
  		System.out.println(g.equals(a + b));
  		System.out.println(g.equals(a + h));
  	}
  ```

  ```java
  //输出结果
  true
  false
  true
  true
  true
  false
  true
  ```

* String和StringBuilder的区别，StringBuffer和StringBuilder的区别

  String是内容不可变的字符串，底层使用了一个不可变的字符数组（private final char []）

  而StringBuilder和StringBuffer采用可变字符数组（char [] value）

  拼接字符串：

  String进行拼接，String c= "a" + "b";

  StringBuilder或者StringBuffer，StringBuilder sb = new StringBuilder();

  sb.append("a");

  StringBuilder是线程不安全的，效率较高。而StringBuffer是线程安全的，效率较低。（内部采用synchronized来修饰append方法）

* ​