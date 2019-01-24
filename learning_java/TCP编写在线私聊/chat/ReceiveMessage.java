package com.test.chat;

import java.io.DataInputStream;
import java.io.IOException;
import java.net.Socket;

public class ReceiveMessage implements Runnable {
    private DataInputStream dis;
    private Socket client;
    private boolean isRunning;
    public ReceiveMessage(Socket client){
        try {
            this.client = client;
            this.isRunning = true;
            dis = new DataInputStream(client.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    @Override
    public void run() {
        String msg = "";
        while (isRunning){
            msg = receive();
            System.out.println(msg);
        }
    }

    //接收数据
    public String receive(){
        try {
            return dis.readUTF();
        } catch (IOException e) {
            e.printStackTrace();
            release();
        }
        return "";
    }

    //释放资源
    public void release(){
        isRunning = false;
        ChatTools.release(dis,client);
    }
}
