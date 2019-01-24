package com.test.chat;

import java.io.Closeable;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * 聊天工具类
 */
public class ChatTools {
    //容器
    private static CopyOnWriteArrayList<ChatChannel> list = new CopyOnWriteArrayList<>();

    private ChatTools() {
    }

    //释放资源
    public static void release(Closeable... targets) {
        for (Closeable target : targets) {
            if (null != target) {
                try {
                    target.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    //群发消息
    public static void sendOthers(ChatChannel chatChannel, String msg) {
        for (ChatChannel channel : list) {
            if (!channel.equals(chatChannel)) {
                channel.send(msg);
            }
        }
    }

    //将客户端添加到容器里面
    public static void addClient(ChatChannel channel) {
        list.add(channel);
    }

    public static void removeClient(ChatChannel channel){
        list.remove(channel);
    }
}
