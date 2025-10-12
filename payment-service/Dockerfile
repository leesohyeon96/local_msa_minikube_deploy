FROM eclipse-temurin:21-jdk-alpine
# designate /tmp as a container volumn (/tmp 디렉토리를 컨테이너 볼륨으로 지정)
VOLUME /tmp
ARG JAR_FILE=build/libs/*.jar
COPY ${JAR_FILE} app.jar
# command to run when the container starts (컨테이너 시작시 실행할 명령)
ENTRYPOINT ["java","-jar","/app.jar"]
