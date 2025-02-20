package com.example.akka;


import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.Behaviors;

public class Guardian {
    public static Behavior<Void> create() {
        return Behaviors.setup(context -> {
            System.out.println("✅ Guardian Actor Started");
            return Behaviors.empty();
        });
    }
}