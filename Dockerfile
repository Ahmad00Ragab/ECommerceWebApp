# Step 1: Use the official OpenJDK 22 slim image as a base
FROM openjdk:22-jdk-slim

# Step 2: Set the Tomcat version and installation directory
ENV TOMCAT_VERSION 10.1.28
ENV CATALINA_HOME /usr/local/tomcat

# Step 3: Install wget and other necessary dependencies, clean up after installation
RUN apt-get update && apt-get install -y wget \
    && rm -rf /var/lib/apt/lists/*

# Step 4: Download and install Tomcat
RUN wget https://archive.apache.org/dist/tomcat/tomcat-10/v${TOMCAT_VERSION}/bin/apache-tomcat-${TOMCAT_VERSION}.tar.gz -O /tmp/tomcat.tar.gz \
    && tar -xvzf /tmp/tomcat.tar.gz -C /usr/local/ \
    && mv /usr/local/apache-tomcat-${TOMCAT_VERSION} $CATALINA_HOME \
    && rm /tmp/tomcat.tar.gz

# Step 5: Set environment variables and path
ENV PATH $CATALINA_HOME/bin:$PATH
ENV CATALINA_OPTS -Xms512m -Xmx1024m

# Step 6: Copy your WAR file to the Tomcat webapps directory
COPY target/ECommerceWebApp-1.0-SNAPSHOT.war $CATALINA_HOME/webapps/

# Step 7: Expose the port Tomcat will run on
EXPOSE 8080

# Step 8: Add a health check to verify if Tomcat is running correctly
HEALTHCHECK --interval=30s --timeout=10s --start-period=30s --retries=3 \
  CMD curl --fail http://localhost:8080 || exit 1

# Step 9: Start Tomcat
CMD ["catalina.sh", "run"]