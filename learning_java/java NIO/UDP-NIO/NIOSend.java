package com.test.nio;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.DatagramChannel;

public class NIOSend {
    public static void main(String[] args) throws IOException {
        DatagramChannel dChannel = DatagramChannel.open();
        dChannel.configureBlocking(false);
        ByteBuffer buf = ByteBuffer.allocate(1024);
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        while (true){
            String data = input.readLine();
            buf.put(data.getBytes());
            buf.flip();
            dChannel.send(buf, new InetSocketAddress("127.0.0.1", 8888));
            buf.clear();
        }

    }
}
