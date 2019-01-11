package com.test.actor;

import akka.actor.ActorRef;

public class MainTest {
    public static void main(String[] args) {
        ActorSystemTools.start();//创建ActorSystem
        ActorRef angryFoal = ActorSystemTools.actorOf(AngryFoalActor.class);
        ActorRef lazyFoal = ActorSystemTools.actorOf(LazyFoalActor.class);
        //发送者是LazyFoal
        angryFoal.tell("Hello,AngryFoalActor. I am LazyFoalActor!", lazyFoal);
        ActorSystemTools.shutdown();
    }
}
