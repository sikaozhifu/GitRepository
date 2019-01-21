```java
//TeacherTest
package com.test.talk;

import java.net.SocketException;

public class TeacherTest {
    public static void main(String[] args) throws SocketException {
        System.out.println("Teacher上线...");
        new Thread(new UdpTalkServer(8888, "Teacher")).start();
    }
}
```

