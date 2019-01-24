package com.test.chat;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;

public class SendMessage implements Runnable {
    private DataOutputStream dos;
    private BufferedReader input;
    private Socket client;
    private boolean isRunning;
    private String name;
    public SendMessage(Socket client,String name) {
        this.client = client;
        this.isRunning = true;
        this.name = name;
        try {
            dos = new DataOutputStream(client.getOutputStream());
            send(name);
            input = new BufferedReader(new InputStreamReader(System.in));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {
        String msg = "";
        while (isRunning){
            try {
                msg = input.readLine();
                send(this.name+ ":" + msg);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    //发送
    public void send(String msg){
        try {
            dos.writeUTF(msg);
            dos.flush();
        } catch (IOException e) {
            e.printStackTrace();
            release();
        }
    }

    //释放资源
    public void release(){
        isRunning = false;
        ChatTools.release(dos,client);
    }
}
