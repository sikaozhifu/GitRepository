#### Java8对接口的改变：

 * 增加了default方法和static方法，这两种方法完全可以有方法体。
 * default方法属于实例，static方法属于类（接口）
 * 接口里面的静态方法不会被继承。静态变量会被继承下来。
 * 如果一个类实现了多个接口，并且这些接口互相之间没有继承关系，同时存在相同的默认方法，会报错：提示不相关默认值。如果多个接口有继承关系，默认方法会被子接口覆盖。
 * 如果遇到有多个接口，并且有相同的默认方法，实现类可以通过特殊语法指定要访问哪个接口的方法。即在实现类或者子接口中重写默认方法，在方法里面写：<接口>.super.<方法名>([参数]);
 * 如果一个接口只有一个抽象方法（包括继承的），该接口是一个函数式接口，可以使用Lambda表达式实现。
 * 如果接口使用FunctionalInterface注解限定，则接口里面只能有一个抽象方法。

#### Lambda表达式：

 * 语法：

   ()	：表示参数列表，不需要指定参数的类型，会自动推断

   ->	：连接符

   {}	：表示方法体

* 注意点：

  + 如果形参列表是空的，只需保留()即可
  + 如果没有返回值，只需要在{}写执行语句即可
  + 如果接口的抽象方法只有一个形参，()可以省略，只需要参数的名称即可。
  + 形参列表的数据类型会自动推断，只要参数名称
  + 和匿名内部类一样，如果访问局部变量，要求局部变量必须是final的

```java
package com.test.lambda;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class TestLambda {
    public static void main(String[] args) {
        String[] atp = {"Rafael Nadal", "Novak Djokovic",
                "Stanislas Wawrinka",
                "David Ferrer","Roger Federer",
                "Andy Murray","Tomas Berdych",
                "Juan Martin Del Potro"};
        List<String> list = Arrays.asList(atp);
        //before
        for (String player : list) {
            System.out.println(player + "; ");
        }
        System.out.println("----------------");
        //lambda
        list.forEach((player) -> System.out.println(player + ";"));
        // 在 Java 8 中使用双冒号操作符(double colon operator)
        //list.forEach(System.out::println);
        System.out.println("--------------------------");
        //排序
        Arrays.sort(atp, new Comparator<String>() {
            @Override
            public int compare(String s1, String s2) {
                return (s1.compareTo(s2));
            }
        });
        for (int i = 0; i < atp.length; i++) {
            System.out.println(atp[i]);
        }
        System.out.println("--------------------------");
        //使用Lambda表达式
        Comparator<String> sortByName = (String s1,String s2) -> (s1.compareTo(s2));
        Arrays.sort(atp,sortByName);
        for (int i = 0; i < atp.length; i++) {
            System.out.println(atp[i]);
        }
    }
}

```



