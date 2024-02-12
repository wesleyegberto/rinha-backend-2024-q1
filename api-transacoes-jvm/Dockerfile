FROM maven:3.8-openjdk-21-slim AS builder
WORKDIR /app

COPY pom.xml .
RUN mvn verify --fail-never

COPY src src/
RUN mvn package -DskipTests

RUN mkdir -p target/dependency && (cd target/dependency; jar -xf ../*.jar)

FROM ibm-semeru-runtimes:open-21-jdk
WORKDIR /app
EXPOSE 8080

# 75% de memória do container (500 MB)
ENV JAVA_OPTIONS="-Xmx375m -Xms375m -XX:+UseSerialGC -XX:+TieredCompilation -XX:TieredStopAtLevel=1 -Xshareclasses -Xquickstart -Djava.security.egd=file:/dev/./urandom"
ENV SPRING_OPTIONS="--spring.config.location=classpath:/ --spring.jmx.enabled=false -Dspring.backgroundpreinitializer.ignore=true"

ARG DEPENDENCY=/app/target/dependency
COPY --from=builder ${DEPENDENCY}/BOOT-INF/lib lib/
COPY --from=builder ${DEPENDENCY}/META-INF META-INF/
COPY --from=builder ${DEPENDENCY}/BOOT-INF/classes .

ENTRYPOINT ["java", "-cp", ".:./lib/*", "com.github.wesleyegberto.ApiTransacoesApplication"]
