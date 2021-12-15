FROM openjdk:11
WORKDIR /app
ENV PORT 8080
COPY ./target/rpn-calculator.jar /app/rpn-calculator.jar
COPY ./target/libs /app/libs
CMD ["java", "-jar", "rpn-calculator.jar"]