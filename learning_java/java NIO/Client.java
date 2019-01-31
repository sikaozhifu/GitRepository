package com.test.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.SocketChannel;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;

public class Client {
    public static void main(String[] args) throws IOException {
        SocketChannel sChannel = SocketChannel.open(new InetSocketAddress("127.0.0.1", 8888));
        FileChannel inChannel = FileChannel.open(Paths.get("D:/1.jpg"),StandardOpenOption.READ);
        ByteBuffer buf = ByteBuffer.allocate(1024);
        while (inChannel.read(buf) != -1){
            buf.flip();
            sChannel.write(buf);
            buf.clear();
        }
        sChannel.shutdownOutput();//关闭发送通道，表明发送完毕

        //接收服务端反馈
        while (sChannel.read(buf) != -1){
            buf.flip();
            byte[] bytes = new byte[buf.limit()];
            buf.get(bytes);
            System.out.println(new String(bytes));
            buf.clear();
        }
        inChannel.close();
        sChannel.close();
    }
}
