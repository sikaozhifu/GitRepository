原子性：互斥访问

synchronized：

修饰代码块：大括号括起来的代码，作用于调用对象

修饰方法：整个方法，作用于调用对象

修饰静态方法：整个静态方法，作用于所有对象

修饰类：括号括起来的部分，作用于所有对象

原子性对比：

synchronized：不可中断锁，适合竞争不激烈，可读性好

lock：可中断锁，多样化同步，竞争激烈时能维持常态

atomic：竞争激烈时能维持常态，比lock性能好，但是只能同步一个值

可见性：

导致共享变量在线程间不可见的原因：

1、线程交叉执行；

2、重排序结合线程交叉执行；

3、共享变量更新后的值没有在工作内存与主存间及时更新

volatile：不具有原子性，适合作为状态标记量，双重检测（双重同步锁）

volatile加双重检测机制来实现禁止指令重排

通过加入内存屏障和禁止重排序优化来实现

1、对volatile变量写操作时，会在写操作之后加入一条store屏蔽指令，将本地内存中的共享变量值刷新到主内存

2、对volatile变量读操作时，会在读操作之前加入一条load屏蔽指令，从主内存中读取共享变量

有序性：

happens--before原则：

1、程序次序规则

2、锁定规则：一个unlock操作先行发生于lock操作

3、volatile变量规则：写操作先行发生于读操作

4、传递规则：A先行于B，B先行于C，则A先行于C

5、线程启动规则：start方法

6、线程中断规则：interrupt方法

7、线程终结规则

8、对象终结规则

安全发布对象：

1、在静态初始化函数中初始化一个对象的引用；

2、将对象的引用保存到volatile类型域或者AtomicReference对象中；

3、将对象的引用保存到某个正确构造对象的final类型域中；

4、将对象的引用保存到一个由锁保护的域中。

实现：

单例模式：懒汉模式（volatile加双重同步锁机制）、饿汉模式、枚举模式

线程封闭：

Ad-hoc线程封闭：程序控制实现，最糟糕；

堆栈封闭：局部变量，无并发问题；

ThreadLocal线程封闭：特别好的封闭方法。

线程不安全类与写法：

1、StringBuilder(线程不安全，在使用局部变量[堆栈封闭，线程安全]的时候效率快)和StringBuffer(线程安全，append方法使用synchronized修饰)

2、SimpleDateFormat(线程不安全，每次必须重新创建实例)和JodaTime(线程安全，尽量使用，外部包)

3、ArrayList，HashList，HashMap等Collections都是线程不安全的

4、先检查再执行：if( condition(a) ) { handle(a) }，如果a是共享的，则是线程不安全的，atomic底层使用cas实现

线程安全-同步容器（同步容器不一定是线程安全的）

1、ArrayList->Vector，Stack(内部方法使用synchronized修饰)

在集合遍历的时候需要做remove/update等更新操作的时候，使用for循环，若使用foreach或者iterator时，会抛出ConcurrentModificationException异常

2、HashMap->HashTable(key，Value不能为null)

3、Collections.synchronizedXXX(List、Set、Map)

线程安全-并发容器J.U.C

1、ArrayList->CopyOnWriteArrayList，尽量读多写少，底层使用锁实现

2、HashSet、TreeSet->CopyOnWriteArraySet、ConcurrentSkipListSet，底层使用CopyOnWriteArrayList实现

3、HashMap、TreeMap->ConcurrentHashMap[聊聊并发（四）深入分析ConcurrentHashMap](http://ifeve.com/concurrenthashmap/#more-269)、ConcurrentSkipListMap，并发面试，跳表。性能好

安全共享对象策略：

1、线程限制：一个被线程限制的对象，由线程独占，并且只能被占有它的线程修改

2、共享只读：一个共享只读的对象，在没有额外同步的情况下，可以被多个线程并发访问，但是任何线程都不能修改它

3、线程安全对象：一个线程安全的对象或者容器，在内部通过同步机制来保证线程安全，所以其他线程无需做额外同步就可以通过公共接口随意访问它

4、被守护对象：被守护的对象只能通过获取特定的锁来访问

AQS(AbstractQueuedSynchronizer)：

CountDownLatch：关键方法countDown，wait

CountDownLatch很适合用来将一个任务分为n个独立的部分，等这些部分都完成后继续接下来的任务， 

CountDownLatch只能出发一次，计数值不能被重置。  

基于CountDownLatch的模拟项目，一个项目可以分为多个模块，只有但这些模块都完成后才可以继续下一步的工作。 

countDown方法，当前线程调用此方法，则计数减一

await方法，调用此方法会一直阻塞当前线程，直到计时器的值为0

```java
package com.concurrent.example;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class CountDownLatchExample {

	private final static int threadCount = 200;
	
	public static void main(String[] args) throws Exception{
		//定义线程池
		ExecutorService exec = Executors.newCachedThreadPool();
		
		final CountDownLatch countDownLatch = new CountDownLatch(threadCount);
		
		for(int i = 0;i < threadCount;i++) {
			final int threadNum = i;
			exec.execute(new Runnable() {
				
				@Override
				public void run() {
					try {
						test(threadNum);
					} catch (Exception e) {
						System.out.println(e);
					}finally {
						countDownLatch.countDown();
					}
				}
			});
			
		}
		countDownLatch.await(10,TimeUnit.MILLISECONDS);
		System.out.println("finish");
	}
	
	public static void test(int threadNum) throws Exception{
		Thread.sleep(100);
		System.out.println(threadNum);
	}
}

```



Semaphore：获取许可acquire，释放许可release

获取多个许可acquire(int permits)，尝试获取许可tryAcquire()，尝试获取多个许可(int permits，TimeUnit unit)

CyclicBarrier：初始的时候需要传入屏障数final CyclicBarrier barrier = new CyclicBarrier(int parties)，await()方法，还可以在初始化的时候，传递一个Runnable，这样在线程到达屏障的时候，优先执行Runnable里面的代码。

一个同步辅助类，它允许一组线程互相等待，直到到达某个公共屏障点(common barrier point)。 

在涉及一组固定大小的线程的程序中，这些线程必须不时地互相等待，此时CyclicBarrier 很有用。 

因为该 barrier在释放等待线程后可以重用，所以称它为循环 的 barrier。 

CyclicBarrier可以多次重复使用 

区别：[多线程CountDownLatch和CyclicBarrier的区别以及举例](https://blog.csdn.net/paul342/article/details/49661991)

CountDownLatch:一个线程(或者多个)，等待另外N个线程完成某个事情之后才能执行。   

CyclicBarrier:N个线程相互等待，任何一个线程完成之前，所有的线程都必须等待。

对于CountDownLatch来说，重点是那个“一个线程”, 是它在等待，而另外那N的线程在把“某个事情”做完之后可以继续等待，可以终止。

而对于CyclicBarrier来说，重点是那N个线程，他们之间任何一个没有完成，所有的线程都必须等待。

CountDownLatch是计数器,线程完成一个就记一个,就像报数一样,只不过是递减的。

而CyclicBarrier更像一个水闸,线程执行就想水流,在水闸处都会堵住,等到水满(线程到齐)了,才开始泄流。

CyclicBarrier举例：几个工程队修地铁的基础设施，只有当都完成之后，技术检验人员检验合格了（这个期间工程队就歇着），工程队接着进行下一步的铁轨铺设。

CountDownLatch举例：我在一个地方等着队友开发进度，只有我的队友到达了一个预定进度，然后，我才能开始开发。当所有队友到达了一个预定进度，我开始开发了，队友也不管我继续他们的开发。

ReentrantLock：有两种实现，公平锁和非公平锁[Java并发之ReentrantLock详解](https://blog.csdn.net/lipeng_bigdata/article/details/52154637#commentBox)

[轻松学习java可重入锁(ReentrantLock)的实现原理](https://blog.csdn.net/yanyan19880509/article/details/52345422)

Condition：配合ReentrantLock使用。synchronized是jvm级别提供的同步原语，它的实现机制隐藏在jvm实现中。作为Lock系列功能中的Condition，就是用来实现类似Object.wait和Object.notify 对应功能的。

[java并发等待条件的实现原理(Condition)](https://blog.csdn.net/yanyan19880509/article/details/52502882)

ReentrantReadWriteLock：读写分离，适用于读多写少；读读共享，写写互斥、读写互斥[轻松掌握java读写锁(ReentrantReadWriteLock)的实现原理](https://blog.csdn.net/yanyan19880509/article/details/52435135)

FutureTask：除了实现了Future接口外，还实现了Runnable接口，因此可以直接提交给Executor执行。当然也可以调用线程直接执行FutureTask.run()。

Future接口的功能：[Java多线程编程：Callable、Future和FutureTask浅析（多线程编程之四）](https://blog.csdn.net/javazejian/article/details/50896505)

1、能够中断执行中的任务；

2、判断任务是否已经执行完成；

3、获取任务执行完成后的结果

Fork/Join：[聊聊并发（八）——Fork/Join框架介绍](http://ifeve.com/talk-concurrency-forkjoin/)

用来执行并行任务的框架，里面使用了工作窃取算法(使用双端队列，被窃取任务线程永远从双端队列的头部拿任务执行，而窃取任务的线程永远从双端队列的尾部拿任务执行。)，大任务可以拆成小任务，小任务还可以继续拆成更小的任务，最后把任务的结果汇总合并，得到最终结果，这种模型就是Fork/Join模型。

需要继承RecursiveTask，通过RecursiveTask这个类就可以方便地实现Fork/Join模式。

开始时先拆分成两个队列，即双端队列，然后各自执行fork，之后再合并在一起join。

BlockingQueue：有四个具体的实现类,根据不同需求,选择不同的实现类 [并发队列](http://ifeve.com/?x=0&y=0&s=BlockingQueue)

1、ArrayBlockingQueue：一个由数组支持的有界阻塞队列，规定大小的BlockingQueue,其构造函数必须带一个int参数来指明其大小.其所含的对象是以FIFO(先入先出)顺序排序的。

2、LinkedBlockingQueue：大小不定的BlockingQueue,若其构造函数带一个规定大小的参数,生成的BlockingQueue有大小限制,若不带大小参数,所生成的BlockingQueue的大小由Integer.MAX_VALUE来决定.其所含的对象是以FIFO(先入先出)顺序排序的。

补充：

Integer.MIN_VALUE，即-2147483648，二进制位如下：

1000 0000 0000 00000000 0000 0000 0000

在计算机的运算中，“-”（前缀）运算表示各二制位取反再加1，也就是说b = -a 在计算机内部是 b = ~a + 1 这样处理的。

所以上面的位就变成了： 

1000 0000 0000 00000000 0000 0000 0000 Integer.MIN_VALUE 

取反 0111 1111 1111 11111111 1111 1111 1111 （取反之后变成了Integer.MAX_VALUE） 

加１ 1000 0000 0000 00000000 0000 0000 0000 -Integer.MIN_VALUE（与原来的结果一样）

3、PriorityBlockingQueue：类似于LinkedBlockQueue,但其所含对象的排序不是FIFO,而是依据对象的自然排序顺序或者是构造函数的Comparator决定的顺序。

4、SynchronousQueue：特殊的BlockingQueue,对其的操作必须是放和取交替完成的。

注意：

LinkedBlockingQueue可以指定容量，也可以不指定，不指定的话，默认最大是Integer.MAX_VALUE,其中主要用到put和take方法，put方法在队列满的时候会阻塞直到有队列成员被消费，take方法在队列空的时候会阻塞，直到有队列成员被放进来。

LinkedBlockingQueue和ArrayBlockingQueue比较起来,它们背后所用的数据结构不一样,导致LinkedBlockingQueue的数据吞吐量要大于ArrayBlockingQueue,但在线程数量很大时其性能的可预见性低于ArrayBlockingQueue.

线程池：[聊聊并发（三）Java线程池的分析和使用](http://ifeve.com/java-threadpool/)

死锁：

发生死锁的必要条件：

1、互斥性；

2、请求保持；

3、不剥夺；

4、环路等待；

预防死锁：

1.避免一个线程同时获取多个锁；

2.避免在一个资源内占用多个资源，尽量保证每个锁只占用一个资源；

3.尝试使用定时锁，使用tryLock(timeout)来代替使用内部锁机制-----设置加锁时间；

4.对于数据库锁，加锁和解锁必须在一个数据库连接里，否则会出现解锁失败的情况；

5.避免同步嵌套的发生-----加锁顺序；

6.死锁检测；

多线程并发实践：

1、使用本地变量；

2、使用不可变类；

3、最小化锁的作用范围：S=1/（1-a+a/n）；

4、使用线程池的Executor，而不是直接new Thread执行；

5、宁可使用线程同步也不要使用线程的wait和notify，如CountDownLatch、Semaphore、CyclicBarrier等同步工具 ；

6、使用BlockingQueue实现生产-消费模式

7、使用并发集合而不是加锁的同步集合；

8、使用Semaphore创建有界的访问；

9、宁可使用同步代码块，也不使用同步的方法，主要针对synchronized，同步代码块锁定一个对象；

10、避免使用静态变量，如果使用，优先使用final变量，集合使用只读集合；

```java
public ThreadPoolExecutor(
			     int corePoolSize,  
                              int maximumPoolSize,  
                              long keepAliveTime,  
                              TimeUnit unit,  
                              BlockingQueue<Runnable> workQueue,  
                              ThreadFactory threadFactory) {  
        this(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue,  
             threadFactory, defaultHandler);  
    } 
```

ThreadPoolExecutor执行任务时的大致规则：

（1）如果线程池的数量还未达到核心线程的数量，那么会直接启动一个核心线程来执行任务

（2）如果线程池中的线程数量已经达到或者超出核心线程的数量，那么任务会被插入到任务队列中排队等待执行。

（3）如果在步骤（2）中无法将任务插入到任务队列中，这往往是由于任务队列已满，这个时候如果线程数量未达到线程池规定的最大值，那么会立刻启动一个非核心线程来执行任务。

（4）如果在步骤（3）中线程数量已经达到线程池规定的最大值，那么就会拒绝执行此任务，ThreadPoolExecutor会调用RejectExecutionHandler的rejectExecution方法来通知调用者。