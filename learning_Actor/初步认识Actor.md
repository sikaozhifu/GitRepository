#### Actor模型的介绍：

在使用Java进行并发编程时需要特别的关注锁和内存原子性等一系列线程问题，而Actor模型内部的状态由它自己维护即它内部数据只能由它自己修改(通过消息传递来进行状态修改)，所以使用Actors模型进行并发编程可以很好地避免这些问题，Actor由状态(state)、行为(Behavior)和邮箱(mailBox)三部分组成。

1. 状态(state)：Actor中的状态指的是Actor对象的变量信息，状态由Actor自己管理，避免了并发环境下的锁和内存原子性等问题；
2. 行为(Behavior)：行为指定的是Actor中计算逻辑，通过Actor接收到消息来改变Actor的状态；
3. 邮箱(mailBox)：邮箱是Actor和Actor之间的通信桥梁，邮箱内部通过FIFO消息队列来存储发送方Actor消息，接受方Actor从邮箱队列中获取消息；

[Actor入门第一篇](http://ifeve.com/introducing-actors-akka-notes-part-1/)

[Actor入门第二篇](http://ifeve.com/akka-notes-actor-messaging-1/)

[Actor入门简单介绍](https://www.cnblogs.com/MOBIN/p/7236893.html)

[当多线程并发遇到Actor](当多线程并发遇到Actor)----讲解非常通俗易懂

[java中的Actor模式 Akka实例](https://blog.csdn.net/cyduo/article/details/50403352)

