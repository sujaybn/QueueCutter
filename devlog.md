# Dev Journal

---

## 2026-03-17 · Kafka from Scratch

### Session 1 — Kafka Cluster Setup
**Goal:** Get a local Kafka cluster running with a UI  
**Done:** Configured Docker Compose with KRaft mode (no Zookeeper) + Kafka-UI  
**Fix:** `UnknownHostException` on producer connect → misconfigured `KAFKA_ADVERTISED_LISTENERS`, fixed to point to the correct container hostname

---

### Session 2 — Producer Implementation
**Goal:** Publish messages from Spring Boot  
**Done:** Integrated `spring-kafka`, wired up a basic producer  
**Fix:** `JsonSerializer` is deprecated in Spring Kafka 4.x → bypassed by implementing manual Jackson serialization

---

### Session 3 — Consumer & Integration
**Goal:** Close the loop — consume messages and verify decoupling  
**Done:** Built the listener service with `@KafkaListener`  
**Verified:** Simulated service downtime mid-stream → observed lag build up in Kafka-UI → confirmed clean recovery on restart

---