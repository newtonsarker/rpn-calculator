# Reverse Polish Notation Calculator
* The calculator uses command pattern to extend or control the features. ServiceSelector will cater the required operations, which can be extended by CalculatorService interface.
* Jetty embedded server is used for the web functionalities. CalculatorServlet is mapped to path `/calculate/*`, defined in CalculatorServletContext. RPN expressions can be passes as a path parameter. For example -
  * `http://localhost:8080/calculate/1 3 + 5 6 MIN 8 *`
  * Of course the expression has to be URL encoded

## Assumptions
* Values available in an expression are integers
* Tokens in an expression are seperated by spaces

## Operations
* Test: `./mvnw clean test`
  * Test server uses port `8083` to perform the tests
* Run Application: `./mvnw compile exec:java -Dexec.mainClass="com.newton.calculator.rpn.Application"`
  * The application will run on port `8080`


## Dockerfile
```shell
# add the current user
sudo usermod -aG docker $USER
newgrp docker

# build the image
docker build --tag rpn-calculator .

# list docker images
docker images

# remove image
docker rmi <image_id>

# run the image
docker run rpn-calculator
docker run --name rpn-calculator -p 8080:8080 -d rpn-calculator 

# list all docker containers
docker ps -a

# stop the running container
docker stop <container_id or image_name>

# remove container
docker rm <container_id or image_name>

# tag the docker image with dockerhub
docker tag rpn-calculator newtonsarker/rpn-calculator

# push image
docker login -u "username" -p "password" docker.io
docker push newtonsarker/rpn-calculator
```

## Docker Compose
```shell
# run containers from uploaded image
docker-compose up -d

# stop container
docker-compose stop <image_name>
```