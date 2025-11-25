FROM maven:3.9.9-eclipse-temurin-21 AS builder

WORKDIR /app

# --- FIX: Copy the specific dependency JAR from the host into the Docker image ---
# You need to ensure the path below matches your local system's path exactly.
# Example assumes standard Linux/Mac path structure for local .m2 repo:
#COPY /Users/vishal/.m2/repository/com/pm/billing-service/1.0-SNAPSHOT/billing-service-1.0-SNAPSHOT.jar /root/.m2/repository/com/pm/billing-service/1.0-SNAPSHOT/billing-service-1.0-SNAPSHOT.jar
#COPY /Users/vishal/.m2/repository/com/pm/billing-service/1.0-SNAPSHOT/billing-service-1.0-SNAPSHOT.pom /root/.m2/repository/com/pm/billing-service/1.0-SNAPSHOT/billing-service-1.0-SNAPSHOT.pom
#COPY ../billing-service/target/billing-service-1.0-SNAPSHOT.jar /root/.m2/repository/com/pm/billing-service/1.0-SNAPSHOT/billing-service-1.0-SNAPSHOT.jar
#COPY ../billing-service/target/billing-service-1.0-SNAPSHOT.jar \
#    /root/.m2/repository/com/pm/billing-service/1.0-SNAPSHOT/billing-service-1.0-SNAPSHOT.jar
# VIDEO SOLUTION
COPY pom.xml .

RUN mvn dependency:go-offline -B

COPY src ./src

RUN mvn clean package
## Build the JAR (skip tests for faster build)
##RUN mvn -q -DskipTests package

#CHATGPT SOLUTION
# Copy full multi-module project
#WORKDIR /build
#
## COPY ENTIRE MULTI-MODULE PROJECT
#COPY .. /build
#
## Build only patient-service + its dependencies
#RUN mvn -q -pl patient-service -am -DskipTests package

FROM eclipse-temurin:21-jdk AS runner

WORKDIR /app

COPY --from=builder ./app/target/patient-service-1.0-SNAPSHOT.jar ./app.jar

EXPOSE 4000

ENTRYPOINT ["java", "-jar", "app.jar"]