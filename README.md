# Reverse Polish Notation Calculator
* The calculator uses command pattern to extend or control the features. ServiceSelector will cater the required operations, which can be extended by CalculatorService interface.
* Jetty embedded server is used for the web functionalities. CalculatorServlet is mapped to path `/calculate/*`, defined in CalculatorServletContext. RPN expressions can be passes as a path parameter. For example -
  * `http://localhost:8083/calculate/1 3 + 5 6 MIN 8 *`
  * Of course the expression has to be URL encoded

## Assumptions
* Values available in an expression are integers
* Tokens in an expression are seperated by spaces

## Operations
* Test: `./mvnw clean test`
  * Test server uses port `8083` to perform the tests
* Run Application: `./mvnw compile exec:java -Dexec.mainClass="com.newton.calculator.rpn.Application"`
  * The application will run on port `8080`


## Docker
```shell
# add the current user
sudo usermod -aG docker $USER
newgrp docker

# build the image
docker build --tag rpn-calculator .

# run the image
docker run rpn-calculator
docker run --name rpn-calculator -p 8080:8080 -d rpn-calculator 

# list all docker containers
docker ps -a

# stop the running container
docker stop <container_id or image_name>
```
