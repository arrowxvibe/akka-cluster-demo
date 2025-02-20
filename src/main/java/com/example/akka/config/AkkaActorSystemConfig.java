package com.example.akka.config;

import akka.actor.typed.ActorSystem;
import akka.actor.typed.javadsl.Behaviors;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class AkkaActorSystemConfig {

    @Bean
    public ActorSystem<Void> actorSystem() {
        ActorSystem<Void> system = ActorSystem.create(Behaviors.empty(), "ClusterSystem");


        return system;
    }
}