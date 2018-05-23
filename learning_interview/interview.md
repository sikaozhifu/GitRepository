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

* java中的集合

  Collection和Map

  List是有序的，可以重复；

  Set是无序的，不可以重复；根据equals和hashcode判断。

* ArrayList和LinkedList的区别：

  ArrayList使用数组，LinkedList使用的是链表。

  数组查询比较快（有索引），插入和修改比较慢，数组在内存中是一块连续的内存，如果插入或删除，需要移动内存。

  链表查询比较慢（需要从头部开始），插入和修改比较快（只需改变引用指向即可），链表不要求内存连续。

  ArrayList使用在查询比较多，而插入和删除比较少。而LinkedList相反。

* HashMap和HashTable的区别：

  HashMap和HashTable都使用哈希表来存储键值对。在数据结构上是基本相同的，都创建了一个继承自Map.Entry的私有的内部类Entry，每一个Entry对象表示存储在哈希表中的一个键值对。

  Entry对象唯一表示一个键值对，有四个属性：

  -K key 键对象
  -V value 值对象
  -int hash 键对象的hash值
  -Entry entry 指向链表中下一个Entry对象，可为null，表示当前Entry对象在链表尾部

  初始容量大小和每次扩充容量大小的不同：

  ```java
  以下代码及注释来自java.util.HashTable
   
  // 哈希表默认初始大小为11
  public Hashtable() {
      this(11, 0.75f);
  }
   
  protected void rehash() {
      int oldCapacity = table.length;
      Entry<K,V>[] oldMap = table;
   
      // 每次扩容为原来的2n+1
      int newCapacity = (oldCapacity << 1) + 1;
      // ...
  }
   
   
  以下代码及注释来自java.util.HashMap
   
  // 哈希表默认初始大小为2^4=16
  static final int DEFAULT_INITIAL_CAPACITY = 1 << 4; // aka 16
   
  void addEntry(int hash, K key, V value, int bucketIndex) {
      // 每次扩充为原来的2n 
      if ((size >= threshold) && (null != table[bucketIndex])) {
         resize(2 * table.length);
  }
  ```

  HashTable默认的初始大小为11，之后每次扩充为原来的2n+1。HashMap默认的初始化大小为16，之后每次扩充为原来的2倍。

  如果在创建时给定了初始化大小，那么HashTable会直接使用你给定的大小，而HashMap会将其扩充为2的幂次方大小。

  ​

  HashMap可以把null作为key或者value，而HashTable是不可以的；

  ```java
  以下代码及注释来自java.util.HashTable
   
  public synchronized V put(K key, V value) {
   
      // 如果value为null，抛出NullPointerException
      if (value == null) {
          throw new NullPointerException();
      }
   
      // 如果key为null，在调用key.hashCode()时抛出NullPointerException
   
      // ...
  }
   
   
  以下代码及注释来自java.util.HasMap
   
  public V put(K key, V value) {
      if (table == EMPTY_TABLE) {
          inflateTable(threshold);
      }
      // 当key为null时，调用putForNullKey特殊处理
      if (key == null)
          return putForNullKey(value);
      // ...
  }
   
  private V putForNullKey(V value) {
      // key为null时，放到table[0]也就是第0个bucket中
      for (Entry<K,V> e = table[0]; e != null; e = e.next) {
          if (e.key == null) {
              V oldValue = e.value;
              e.value = value;
              e.recordAccess(this);
              return oldValue;
          }
      }
      modCount++;
      addEntry(0, null, value, 0);
      return null;
  }
  ```

  HashMap是线程不安全的，效率较高，HashTable是线程安全的，效率较低；

  ```java
  以下代码及注释来自java.util.HashTable
   
  public synchronized V get(Object key) {
      Entry tab[] = table;
      int hash = hash(key);
      int index = (hash & 0x7FFFFFFF) % tab.length;
      for (Entry<K,V> e = tab[index] ; e != null ; e = e.next) {
          if ((e.hash == hash) && e.key.equals(key)) {
              return e.value;
          }
      }
      return null;
  }
   
  public Set<K> keySet() {
      if (keySet == null)
          keySet = Collections.synchronizedSet(new KeySet(), this);
      return keySet;
  }
  ```

* ​