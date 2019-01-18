有了 gRPC， 我们可以一次性的在一个 .proto 文件中定义服务并使用任何支持它的语言去实现客户端和服务器，反过来，它们可以在各种环境中，从Google的服务器到你自己的平板电脑—— gRPC 帮你解决了不同语言及环境间通信的复杂性。使用 protocol buffers 还能获得其他好处，包括高效的序列号，简单的 IDL 以及容易进行接口更新。

添加Maven依赖：

```shell
<?xml version="1.0" encoding="UTF-8"?>
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
    <modelVersion>4.0.0</modelVersion>

    <groupId>com</groupId>
    <artifactId>example</artifactId>
    <version>1.0-SNAPSHOT</version>
    <dependencies>
        <dependency>
            <groupId>io.grpc</groupId>
            <artifactId>grpc-netty-shaded</artifactId>
            <version>1.14.0</version>
        </dependency>
        <dependency>
            <groupId>io.grpc</groupId>
            <artifactId>grpc-protobuf</artifactId>
            <version>1.14.0</version>
        </dependency>
        <dependency>
            <groupId>io.grpc</groupId>
            <artifactId>grpc-stub</artifactId>
            <version>1.14.0</version>
        </dependency>
    </dependencies>

    <build>
        <extensions>
            <extension>
                <groupId>kr.motd.maven</groupId>
                <artifactId>os-maven-plugin</artifactId>
                <version>1.5.0.Final</version>
            </extension>
        </extensions>
        <plugins>
            <plugin>
                <groupId>org.xolstice.maven.plugins</groupId>
                <artifactId>protobuf-maven-plugin</artifactId>
                <version>0.5.0</version>
                <configuration>
                    <protocArtifact>com.google.protobuf:protoc:3.0.0:exe:${os.detected.classifier}</protocArtifact>
                    <pluginId>grpc-java</pluginId>
                    <pluginArtifact>io.grpc:protoc-gen-grpc-java:1.0.0:exe:${os.detected.classifier}</pluginArtifact>
                </configuration>
                <executions>
                    <execution>
                        <goals>
                            <goal>compile</goal>
                            <goal>compile-custom</goal>
                        </goals>
                    </execution>
                </executions>
            </plugin>
        </plugins>
    </build>

</project>
```

编写proto文件   helloworld.proto

proto文件放在src/main/proto文件夹中

```protobuf
syntax = "proto3";

option java_outer_classname = "HelloWorldProto";
option java_package = "com.example.helloworld";


service Greeter{
    rpc SayHello(HelloRequest) returns (HelloReply){}
}
message HelloRequest{
    string name = 1;
}

message HelloReply{
    string message = 1;
}
```

编写服务端代码HelloWorldServer.java

```java
package com.example.helloworld;


import com.example.helloworld.HelloWorldProto.HelloReply;
import com.example.helloworld.HelloWorldProto.HelloRequest;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.stub.StreamObserver;

import java.io.IOException;

public class HelloWorldServer {
    private int port = 50051;
    private Server server;
    private void start() throws IOException {
        server = ServerBuilder.forPort(port)
                .addService(new GreeterImpl())
                .build()
                .start();
        System.out.println("service start...");
        Runtime.getRuntime().addShutdownHook(new Thread(){
            @Override
            public void run() {
                System.err.println("shutting down gRPC server since JVM is shutting down");
                HelloWorldServer.this.stop();
                System.err.println("server shut down");
            }
        });
    }
    private void stop(){
        if (server != null){
            server.shutdown();
        }
    }
    private void blockUntilShutDown() throws InterruptedException{
        if (server != null){
            server.awaitTermination();
        }
    }

    private class GreeterImpl extends GreeterGrpc.GreeterImplBase {
        public void sayHello(HelloRequest request, StreamObserver<HelloReply> responseObserver){
            System.out.println("service:" + request.getName());
            HelloReply reply = HelloReply.newBuilder().setMessage("Hello: " + request.getName()).build();
            responseObserver.onNext(reply);
            responseObserver.onCompleted();
        }
    }

    public static void main(String[] args) throws InterruptedException, IOException {
        final HelloWorldServer server = new HelloWorldServer();
        server.start();
        server.blockUntilShutDown();
    }
}

```

编写客户端代码HelloWorldClient.java

```java
package com.example.helloworld;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

import java.util.concurrent.TimeUnit;

import static com.example.helloworld.HelloWorldProto.*;

public class HelloWorldClient {
    private final ManagedChannel channel;
    private final GreeterGrpc.GreeterBlockingStub blockingStub;

    public HelloWorldClient(String host,int port){
        channel = ManagedChannelBuilder.forAddress(host, port)
                .usePlaintext()
                .build();
        blockingStub = GreeterGrpc.newBlockingStub(channel);
    }
    public void shutdown() throws InterruptedException{
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }
    public void greet(String name){
        HelloRequest request = HelloRequest.newBuilder().setName(name).build();
        HelloReply response = blockingStub.sayHello(request);
        System.out.println(response.getMessage());
    }

    public static void main(String[] args) {
        HelloWorldClient client = new HelloWorldClient("127.0.0.1",50051);
        for (int i = 0; i < 5; i++) {
            client.greet("world:" + i);
        }
    }
}

```

