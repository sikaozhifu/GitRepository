```java
//StudentTest
package com.test.talk;

import java.net.SocketException;

public class StudentTest {
    public static void main(String[] args) throws SocketException {
        System.out.println("Student上线...");
        new Thread(new UdpTalkClient(8888,"Student")).start();
    }
}

```

