package com.example.akka;

import akka.actor.typed.ActorSystem;
import akka.actor.typed.javadsl.Behaviors;
import akka.management.javadsl.AkkaManagement;
import akka.management.cluster.bootstrap.ClusterBootstrap;
import com.typesafe.config.Config;
import com.typesafe.config.ConfigFactory;

public class AkkaManagementService {

    public static void main(String[] args) {
        // Load Akka configuration from the application.conf file
        Config config = ConfigFactory.load();

        // Create ActorSystem with the configuration
        ActorSystem<Void> system = ActorSystem.create(Behaviors.empty(), "ClusterSystem", config);

        // Start Akka Management (handles cluster management)
        AkkaManagement.get(system).start();

        // Start Cluster Bootstrap (automatically joins the cluster)
        ClusterBootstrap.get(system).start();

        // Log the status of the Akka Management service
        System.out.println("✅ Akka Management Service is running...");
    }
}