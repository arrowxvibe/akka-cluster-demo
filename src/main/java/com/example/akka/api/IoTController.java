package com.example.akka.api;

import akka.cluster.sharding.typed.javadsl.ClusterSharding;
import akka.cluster.sharding.typed.javadsl.EntityRef;
import akka.cluster.sharding.typed.javadsl.EntityTypeKey;
import com.example.akka.cluster.IoTEntity;
import com.example.akka.cluster.IoTShardingSystem;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/sensor-data")
public class IoTController {

    private final ClusterSharding sharding;
    private final EntityTypeKey<IoTEntity.Command> entityKey;

    public IoTController(ClusterSharding sharding) {
        this.sharding = sharding;
        this.entityKey = IoTShardingSystem.ENTITY_KEY;
    }

    @PostMapping
    public String sendData(@RequestParam String containerId,
                           @RequestParam String deviceType,
                           @RequestParam String readings) {

        EntityRef<IoTEntity.Command> entityRef = sharding.entityRefFor(entityKey, containerId);
        entityRef.tell(new IoTEntity.SensorData(containerId, deviceType, readings));

        return "Data sent for container " + containerId;
    }
}
