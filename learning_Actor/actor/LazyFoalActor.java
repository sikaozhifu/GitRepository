package com.test.actor;

import akka.actor.UntypedActor;
//消息发送者
public class LazyFoalActor extends UntypedActor {
    @Override
    public void onReceive(Object message) throws Exception {
        System.out.println("LazyFoalActor receive message: " + message);
    }
}
