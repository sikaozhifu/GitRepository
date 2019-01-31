package com.test.nio;

import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.Pipe;

public class TestPipe {
    public static void main(String[] args) throws IOException {
        //获取管道
        Pipe pipe = Pipe.open();
        //将缓冲区的数据写入管道
        ByteBuffer buf = ByteBuffer.allocate(1024);
        Pipe.SinkChannel sinkChannel = pipe.sink();
        buf.put("通过单向管道发送数据".getBytes());
        buf.flip();
        sinkChannel.write(buf);
        //读取数据
        Pipe.SourceChannel sourceChannel = pipe.source();
        buf.flip();
        sourceChannel.read(buf);
        System.out.println(new String(buf.array(),0,buf.limit()));

        sourceChannel.close();
        sinkChannel.close();
    }
}
