```java
//Client.java客户端
package com.test.tcp;

import java.io.*;
import java.net.Socket;

public class Client {
    private static DataOutputStream dos;
    private static DataInputStream dis;
    private static BufferedReader input;
    public Client(Socket client) {
        try {
            dos = new DataOutputStream(client.getOutputStream());
            dis = new DataInputStream(client.getInputStream());
            input = new BufferedReader(new InputStreamReader(System.in));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        System.out.println("---------Client----------");
        Socket client = new Socket("localhost", 8888);
        new Client(client);
        //发送数据
        String data = input.readLine();
        dos.writeUTF(data);
        dos.flush();
        //接收数据
        String result = dis.readUTF();
        System.out.println("服务端发来的消息" + result);
        if (client != null) {
            client.close();
        }
        release();
    }

    public static void release() {
        try {
            if (dis != null) {
                dis.close();
            }
            if (dos != null) {
                dos.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

```

