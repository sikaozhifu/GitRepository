```java
//UdpTalkClient
package com.test.talk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.*;

/**
 * 发送端
 */
public class UdpTalkClient implements Runnable{
    private DatagramSocket client;
    private int port;
    private String name;
    public UdpTalkClient(int port,String name) throws SocketException {
        this.port = port;
        this.name = name;
        client = new DatagramSocket();
    }
    @Override
    public void run() {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        try {
            while (true){
                //发送数据
                String data = input.readLine();
                byte[] bufSend = null;
                if (data!=null){
                    //也可以在接收端直接以常量形式写入
                    bufSend = (name + " : "+ data).getBytes();
                }
                //发送的时候需要指定目标的IP地址和端口
                DatagramPacket packetSend = new DatagramPacket(bufSend, 0, bufSend.length, new InetSocketAddress("localhost",port));
                client.send(packetSend);
                //接收数据
                byte[]  bufReceive= new byte[1024];
                DatagramPacket packetReceive = new DatagramPacket(bufReceive, bufReceive.length);
                client.receive(packetReceive);
                byte[] bytes = packetReceive.getData();
                String text = new String(bytes, 0, bytes.length, "UTF-8");
                if (text.equalsIgnoreCase("bye")){
                    System.out.println(text);
                    break;
                }else {
                    System.out.println(text);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            client.close();
        }
    }
}
```

