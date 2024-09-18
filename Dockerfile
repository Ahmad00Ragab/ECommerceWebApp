# Step 1: Use the official OpenJDK 22 image as a base
FROM openjdk:22-jdk
# Step 2: Set the Tomcat version and installation directory
ENV TOMCAT_VERSION 10.1.28
ENV CATALINA_HOME /usr/local/tomcat

# Step 3: Install wget and other necessary dependencies
RUN apt-get update && apt-get install -y wget \
    && apt-get clean \
    && rm -rf /var/lib/apt/lists/*

# Step 4: Download and install Tomcat
RUN wget https://dlcdn.apache.org/tomcat/tomcat-10/v${TOMCAT_VERSION}/bin/apache-tomcat-${TOMCAT_VERSION}.tar.gz \
    && tar -xvzf apache-tomcat-${TOMCAT_VERSION}.tar.gz -C /usr/local/ \
    && mv /usr/local/apache-tomcat-${TOMCAT_VERSION} /usr/local/tomcat \
    && rm apache-tomcat-${TOMCAT_VERSION}.tar.gz

# Step 5: Set environment variables and path
ENV PATH $CATALINA_HOME/bin:$PATH
ENV CATALINA_OPTS -Xms512m -Xmx1024m

# Step 6: Copy your WAR file to the Tomcat webapps directory
COPY target/ECommerceWebApp-1.0-SNAPSHOT.war /usr/local/tomcat/webapps/

# Step 7: Expose the port Tomcat will run on
EXPOSE 8080

# Step 8: Start Tomcat
CMD ["catalina.sh", "run"]
