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
    private String name;
    public ChatChannel(String name,Socket client) {
        this.client = client;
        this.isRunning = true;
        this.name = name;
        try {
            dos = new DataOutputStream(client.getOutputStream());
            dis = new DataInputStream(client.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getName() {
        return name;
    }

    @Override
    public void run() {
        while (isRunning){
            String msg = receive();
            //msg    ->    name:@targetName:msg
            //私聊协议以@xxx:开头
            if (msg.contains("@")){//私聊
                int startIndex = msg.indexOf("@");
                String name = msg.substring(0, startIndex-1);
                msg = msg.substring(startIndex+1);
                int endIndex = msg.indexOf(":");
                String targetName = msg.substring(0,endIndex);
                msg = msg.substring(endIndex+1);
                ChatChannel channel = ChatTools.getChannelByName(targetName);
                channel.send(name + "悄悄地对您说:" + msg);
            }else {
                //群聊
                ChatTools.sendOthers(this, msg);
            }
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
