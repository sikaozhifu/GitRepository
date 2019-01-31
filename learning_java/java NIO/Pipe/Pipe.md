Java NIO 管道是2个线程之间的单向数据连接。

Pipe有一个source通道和一个sink通道。数据会被写到sink通道，从source通道读取。

![](assets/20170308154828966.png)

