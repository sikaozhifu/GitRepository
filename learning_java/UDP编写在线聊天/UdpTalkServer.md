```java
//UdpTalkServer
package com.test.talk;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.SocketException;

/**
 * 接收端
 */
public class UdpTalkServer implements Runnable {
    private DatagramSocket server;
    private int port;
    private String name;
    public UdpTalkServer(int port,String name) throws SocketException {
        this.port = port;
        this.server = new DatagramSocket(port);
        this.name = name;
    }
    @Override
    public void run() {
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        try {
            while (true){
                //接收数据
                byte[]  bufReceive= new byte[1024];
                DatagramPacket packetReceive = new DatagramPacket(bufReceive, bufReceive.length);

                server.receive(packetReceive);
                byte[] buf = packetReceive.getData();
                String text = new String(buf, 0, buf.length, "UTF-8");
                if (text.equalsIgnoreCase("bye")){
                    System.out.println(text);
                    break;
                }else {
                    System.out.println(text);
                }
                //发送数据
                String data = input.readLine();
                byte[] bufSend = null;
                if (data!=null){
                    //也可以在接收端直接以常量形式写入
                    bufSend = (name + " : " +data).getBytes();
                }
                //发送的时候需要指定目标的IP地址和端口
                DatagramPacket packetSend = new DatagramPacket(bufSend, 0,bufSend.length ,packetReceive.getAddress(),packetReceive.getPort());
                server.send(packetSend);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            server.close();
        }
    }
}
```

