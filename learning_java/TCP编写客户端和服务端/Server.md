```java
//Server.java服务端
package com.test.tcp;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    public static void main(String[] args) throws IOException {
        System.out.println("-----------Server---------");
        //创建服务端
        ServerSocket server = new ServerSocket(8888);
        //接收客户端数据
        //建立管道
        while (true){
            Socket client = server.accept();
            System.out.println("一个客户端已连接...");
            new Thread(new ServerChannel(client)).start();
        }
    }
}

```

