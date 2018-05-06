# IO

###### **[全面掌握IO(输入/输出流)](http://www.cnblogs.com/shenliang123/archive/2012/05/11/2496606.html)**

**[java回忆录—输入输出流详细讲解（入门经典）](https://blog.csdn.net/qq_22063697/article/details/52137369)**

**[java回忆录—I/O流详解补充](https://blog.csdn.net/qq_22063697/article/details/52154916)**

* 字节流

* 字符流

* 节点流

* 处理流

* 输入流

* 输出流

* 转换流

* 缓冲流

* 数据流

* Print流

  > 输出不会抛异常，还有自动flush功能。

* Object流

  > transient关键字：
  >
  > * 透明的，即序列化的时候不考虑
  >
  > * 一个静态变量不管是否被transient修饰，均不能被序列化
  >
  > * 只能修饰变量，不能修饰方法和类
  >
  >   [Java transient关键字使用小记](http://www.cnblogs.com/lanxuezaipiao/p/3369962.html)
  >
  > Serializable接口：序列化。

### 四个抽象类

|   输入流    |    输出流    |
| :---------: | :----------: |
| InputStream | OutputStream |
|   Reader    |    Writer    |

_输入和输出的区别：_

两种：文件角度，程序角度。

我们一般的输入、输出都是指从程序角度看

从文件中读数据，程序角度看为输入流，_Reader_

由文件往外写数据，程序角度看为输出流。_Writer_

```java
package io;


import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class StreamExample {

	
	public static void main(String[] args) throws IOException {
	
		//源文件
		String source = "D:\\example\\stream.txt";
		//目标文件
		String dist = "D:\\example\\streamCopy.txt";
		//开始
		Long stratTime = System.currentTimeMillis();
		//调用方法
		//method1(source,dist);
		//method2(source,dist);
		//method3(source,dist);
		listDir("D:\\example");
		method4(source,dist);
		//结束时间
		Long endTime = System.currentTimeMillis();
		System.out.println("所用时间"+(endTime-stratTime)+"毫秒");
	}
	
	//基本字节流读一个字节
	public static void method1(String source,String dist) throws IOException {
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(source);
			fos = new FileOutputStream(dist);
			int c = 0;
			while((c=fis.read()) != -1) {
				fos.write(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (fis != null) {
				fis.close();
			}
			if (fos != null) {
				fos.close();
			}
		}
	}
	
	//基本字节流读一组字节
	public static void method2(String source,String dist) throws IOException {
		
		FileInputStream fis = null;
		FileOutputStream fos = null;
		try {
			fis = new FileInputStream(source);
			fos = new FileOutputStream(dist);
			//定义字节数组
			byte[] buf = new byte[1024];
			//定义实际读取字节数
			int hasRead = 0;
			while((hasRead = fis.read(buf)) != -1) {
				fos.write(buf, 0, hasRead);
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (fis != null) {
				fis.close();
			}
			if (fos != null) {
				fos.close();
			}
		}
		
	}
	
	//处理流读取一个字节
	public static void method3(String source,String dist) throws IOException {
		
		FileInputStream fis = null;
		FileOutputStream fos = null;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		try {
			fis = new FileInputStream(source);
			//处理流
			bis = new BufferedInputStream(fis);
			fos = new FileOutputStream(dist);
			//处理流
			bos = new BufferedOutputStream(fos);
			int c = 0;
			while((c = bis.read()) != -1) {
				bos.write(c);
			}
			bos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (bis != null) {
				bis.close();
			}
			if (bos != null) {
				bos.close();
			}
		}
		
	}
	
	//处理流读取一个数组
	public static void method4(String source,String dist) throws IOException {
		
		FileInputStream fis = null;
		FileOutputStream fos = null;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		
		try {
			fis = new FileInputStream(source);
			//处理流
			bis = new BufferedInputStream(fis);
			fos = new FileOutputStream(dist);
			//处理流
			bos = new BufferedOutputStream(fos);
			int hasRead = 0;
			byte[] buf = new byte[1024];
			while ((hasRead = bis.read(buf)) != -1) {
				bos.write(buf, 0, hasRead);
			}
			bos.flush();
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			if (bis != null) {
				bis.close();
			}
			if (bos != null) {
				bos.close();
			}
		}
		
	}
	
	//定义读取文件的文件名
	public static void listDir(String dir) throws IOException {
		
		File file = new File(dir);
		//判断是否为文件目录
		if (!file.isDirectory()) {
			//不是，抛出异常
			throw new IOException(dir + "不是目录");
		}
		//定义文件名数组
		File[] files = file.listFiles();
		for (File f : files) {
			//可能是一个多级目录，使用递归
			if (f.isDirectory()) {
				listDir(f.getAbsolutePath());
			}else {
				System.out.println(f.getAbsolutePath());
			}
		}
		
	}

}

```

