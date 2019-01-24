package com.test.chat;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer {
    public static void main(String[] args) throws IOException {
        System.out.println("--------Server-------");
        ServerSocket server = new ServerSocket(8888);
        while (true) {
            Socket client = server.accept();
            System.out.println("一个客户端已经连接");
            ChatChannel channel = new ChatChannel(client);
            ChatTools.addClient(channel);
            new Thread(channel).start();
        }
    }
}
