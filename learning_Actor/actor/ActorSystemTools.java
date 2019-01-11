package com.test.actor;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;

public class ActorSystemTools {
    private static ActorSystem actorSystem = null;

    public static void start() {
        System.out.println("start actorSystem");
        actorSystem = ActorSystem.create();
    }

    public static ActorRef actorOf(Class clazz) {
        return actorSystem.actorOf(Props.create(clazz));
    }
    public static void shutdown(){
        System.out.println("shutdown actorSystem...");
        actorSystem.shutdown();
    }
}
