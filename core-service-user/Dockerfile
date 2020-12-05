FROM openjdk:15-slim
#to create the jar file: mvn package spring-boot:repackage
COPY target/core-service-user-0.0.1-SNAPSHOT.jar /opt/spring-cloud/lib/server.jar
#COPY files/spring-cloud-config-server-entrypoint.sh /opt/spring-cloud/bin/
#ENV SPRING_APPLICATION_JSON= \ 
#  '{"spring": {"cloud": {"config": {"server": \
#  {"git": {"uri": "/var/lib/spring-cloud/config-repo", \
#  "clone-on-start": true}}}}}}'
ENTRYPOINT ["/usr/local/openjdk-15/bin/java"]
CMD ["-jar", "/opt/spring-cloud/lib/server.jar"] #parameter to entrypoint
#While a container is immutable, when not committed to an image after application exits, data stored in a volume will be persistent over several containers.
#VOLUME /var/lib/spring-cloud/config-repo 
EXPOSE 8771
