package com.test.actor;

import akka.actor.UntypedActor;
//消息接收者
public class AngryFoalActor extends UntypedActor {
    @Override
    public void onReceive(Object message) throws Exception {
        System.out.println("AngryFoalActor receive message: " + message);

        getSender().tell("Hello,LazyFoalActor. I am AngelFoalActor！ ", getSelf());
    }
}
