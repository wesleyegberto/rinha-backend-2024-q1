FROM container-registry.oracle.com/graalvm/native-image:21-ol8 AS builder
WORKDIR /app

COPY . .

RUN ./mvnw --no-transfer-progress native:compile -Pnative -DskipTests

FROM container-registry.oracle.com/os/oraclelinux:9-slim
EXPOSE 8080

COPY --from=builder /app/target/rinha-backend-2024-q1 rinha-backend-2024-q1
ENTRYPOINT ["/rinha-backend-2024-q1"]
