package com.test.chat;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class ChatChannel implements Runnable {
    private DataOutputStream dos;
    private DataInputStream dis;
    private Socket client;
    private boolean isRunning;
    public ChatChannel(Socket client) {
        this.client = client;
        this.isRunning = true;
        try {
            dos = new DataOutputStream(client.getOutputStream());
            dis = new DataInputStream(client.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        while (isRunning){
            String msg = receive();
            ChatTools.sendOthers(this, msg);
        }
    }

    //发送消息
    public void send(String msg) {
        try {
            if (!msg.equals("")){
                dos.writeUTF(msg);
                dos.flush();
            }

        } catch (IOException e) {
            //System.out.println("发送消息发生错误...");
            release();
            ChatTools.removeClient(this);
        }
    }

    //接受消息
    public String receive(){
        try {
            return dis.readUTF();
        } catch (IOException e) {
            //e.printStackTrace();
            release();
            ChatTools.removeClient(this);
        }
        return "";
    }

    //释放资源
    public void release(){
        isRunning = false;
        ChatTools.release(dis,dos,client);
    }

}
