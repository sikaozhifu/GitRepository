package com.test.chat;

import java.io.Closeable;
import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
/**
 * 聊天工具类
 */
public class ChatTools {
    //容器
    private static Map<String,ChatChannel> map = new ConcurrentHashMap<>();

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
        for (ChatChannel channel : map.values()) {
            if (!channel.equals(chatChannel)) {
                channel.send(msg);
            }
        }
    }

    //将客户端添加到容器里面
    public static void addClient(ChatChannel channel) {
        map.put(channel.getName(), channel);
    }

    public static void removeClient(ChatChannel channel){
        Iterator<ChatChannel> iterator = map.values().iterator();
        while (iterator.hasNext()){
            if (iterator.next().equals(channel)){
                iterator.remove();
                break;
            }
        }
    }

    //从容器中根据key获取Channel
    public static ChatChannel getChannelByName(String name){
        Iterator<String> iterator = map.keySet().iterator();
        while (iterator.hasNext()){
            if (iterator.next().equals(name)){
                return map.get(name);
            }
        }
        return null;
    }
}
