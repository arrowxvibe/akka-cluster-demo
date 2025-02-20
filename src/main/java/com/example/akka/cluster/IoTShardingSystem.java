package com.example.akka.cluster;

import akka.actor.typed.ActorSystem;
import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.Behaviors;
import akka.cluster.sharding.typed.javadsl.ClusterSharding;
import akka.cluster.sharding.typed.javadsl.Entity;
import akka.cluster.sharding.typed.javadsl.EntityTypeKey;

public class IoTShardingSystem {

    public static final EntityTypeKey<IoTEntity.Command> ENTITY_KEY =
            EntityTypeKey.create(IoTEntity.Command.class, "IoTEntity");

    public static Behavior<Void> create() {
        return Behaviors.setup(context -> {
            ClusterSharding sharding = ClusterSharding.get(context.getSystem());

            sharding.init(Entity.of(
                    ENTITY_KEY,
                    entityContext -> IoTEntity.create(entityContext.getEntityId())
            ));

            return Behaviors.empty();
        });
    }

    public static void main(String[] args) {
        ActorSystem<Void> system = ActorSystem.create(IoTShardingSystem.create(), "ClusterSystem");
    }
}
