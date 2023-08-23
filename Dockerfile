FROM java:8
MAINTAINER lty
EXPOSE 9000
ADD target/*.jar app.jar
RUN bash -c "mkdir /config"
COPY target/classes/*.yaml /config/
COPY target/classes/**/*.xml /config/
RUN ln -sf /usr/share/zoneinfo/Asia/Shanghai /etc/localtime && echo 'Asia/Shanghai' >/etc/timezone
ENV JAVA_OPTS='-Xms1024M -Xmx1024M'
ENV SPRING_CONFIG_LOCATION='/config/'
ENV SPRING_PROFILES_ACTIVE='pro'
ENTRYPOINT java $JAVA_OPTS -jar app.jar -Dspring.config.location=$SPRING_CONFIG_LOCATION --spring.profiles.active=$SPRING_PROFILES_ACTIVE