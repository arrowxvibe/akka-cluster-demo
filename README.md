Here’s your README.md with instructions on running the Akka Cluster with Spring Boot on two different ports:

README.md

# Akka Cluster with Spring Boot

This project integrates **Spring Boot** with **Akka Cluster**, allowing you to run multiple nodes for distributed computing.

## 🚀 Getting Started

### **Prerequisites**
Ensure you have the following installed:
- **Java 17+**
- **Gradle 8+**
- **Docker** (optional, for running Akka Management)

---

## 🛠 Build & Run

### **1️⃣ Build the Project**
Run the following command to build the project:
```sh
./gradlew clean build

2️⃣ Running Two Instances (Two Ports)

Start First Node (Port 8081)

SPRING_APPLICATION_JSON='{"server.port":8081}' java -jar build/libs/akka-cluster-1.0.0.jar

Start Second Node (Port 8082)

SPRING_APPLICATION_JSON='{"server.port":8082}' java -jar build/libs/akka-cluster-1.0.0.jar

⚙️ Akka Cluster Configuration

Ensure your application.conf (or application.yaml) has cluster settings, such as:

akka {
  actor {
    provider = "cluster"
  }
  remote {
    artery {
      enabled = on
      transport = tcp
      canonical.hostname = "127.0.0.1"
      canonical.port = 2551
    }
  }
  cluster {
    seed-nodes = [
      "akka://ClusterSystem@127.0.0.1:2551",
      "akka://ClusterSystem@127.0.0.1:2552"
    ]
  }
}

🛠 Testing the Cluster

After starting both instances, you can test the cluster by hitting:

curl http://localhost:8081/actuator/health
curl http://localhost:8082/actuator/health

You should see a response indicating both instances are running successfully.

📜 License

This project is licensed under the MIT License.

---

### **What’s included?**
✅ **Commands to build and run** on multiple ports  
✅ **Akka Cluster configuration example**  
✅ **Health check endpoints for validation**  

This **README** provides everything you need to **set up, run, and test** your Akka Cluster in Spring Boot. 🚀 Let me know if you need modifications!