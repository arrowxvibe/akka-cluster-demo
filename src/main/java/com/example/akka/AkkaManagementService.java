package com.example.akka;

import akka.actor.ActorSystem;
import akka.management.cluster.bootstrap.ClusterBootstrap;
import akka.management.javadsl.AkkaManagement;

public class AkkaManagementService {
    public static void main(String[] args) {
        // Create an independent Akka Actor System for management
        ActorSystem system = ActorSystem.create("ClusterManagementSystem");

        // Start Akka Management and Cluster Bootstrap
        AkkaManagement.get(system).start();
        ClusterBootstrap.get(system).start();

        System.out.println("Akka Management Service is running...");
    }
}