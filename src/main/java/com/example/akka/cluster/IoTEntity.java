package com.example.akka.cluster;

import akka.actor.typed.Behavior;
import akka.actor.typed.javadsl.*;

public class IoTEntity extends AbstractBehavior<IoTEntity.Command> {

    public interface Command {}

    public static final class SensorData implements Command {
        public final String containerId;
        public final String deviceType;
        public final String readings;

        public SensorData(String containerId, String deviceType, String readings) {
            this.containerId = containerId;
            this.deviceType = deviceType;
            this.readings = readings;
        }
    }

    private final String containerId;

    private IoTEntity(ActorContext<Command> context, String containerId) {
        super(context);
        this.containerId = containerId;
    }

    public static Behavior<Command> create(String containerId) {
        return Behaviors.setup(ctx -> new IoTEntity(ctx, containerId));
    }

    @Override
    public Receive<Command> createReceive() {
        return newReceiveBuilder()
                .onMessage(SensorData.class, this::onSensorData)
                .build();
    }

    private Behavior<Command> onSensorData(SensorData msg) {
        getContext().getLog().info("Node [{}] processed: {} - {} - {}", 
            getContext().getSystem().address(), msg.containerId, msg.deviceType, msg.readings);
        return this;
    }
}
