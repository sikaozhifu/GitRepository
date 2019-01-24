package com.test.chat;

import java.io.*;
import java.net.Socket;

public class Client {
    public static void main(String[] args) throws IOException {
        Socket client = new Socket("localhost", 8888);
        System.out.println("--------Client-------");
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("请输入名字：");
        String name = input.readLine();
        new Thread(new SendMessage(client,name)).start();
        new Thread(new ReceiveMessage(client)).start();
    }
}
