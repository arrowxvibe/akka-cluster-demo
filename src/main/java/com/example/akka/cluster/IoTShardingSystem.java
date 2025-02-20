package com.example.akka.cluster;

import akka.actor.typed.ActorSystem;
import akka.cluster.sharding.typed.javadsl.ClusterSharding;
import akka.cluster.sharding.typed.javadsl.Entity;
import akka.cluster.sharding.typed.javadsl.EntityTypeKey;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component
public class IoTShardingSystem {

    private final ActorSystem<?> system;
    public static final EntityTypeKey<IoTEntity.Command> ENTITY_KEY =
            EntityTypeKey.create(IoTEntity.Command.class, "IoTEntity");

    public IoTShardingSystem(ActorSystem<?> system) {
        this.system = system;
    }

    @PostConstruct
    public void initSharding() {
        ClusterSharding sharding = ClusterSharding.get(system);

        sharding.init(Entity.of(
                ENTITY_KEY,
                entityContext -> IoTEntity.create(entityContext.getEntityId())
        ));

        System.out.println("✅ IoT Sharding Initialized: " + ENTITY_KEY.name());
    }
}