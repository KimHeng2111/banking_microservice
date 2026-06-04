@echo off
start /b mvn clean package -f ./config-server/pom.xml > ./log/config.log
start /b mvn clean package -f ./loan/pom.xml > ./log/loan.log
start /b mvn clean package -f ./account/pom.xml > ./log/account.log
start /b mvn clean package -f ./eureka-server/pom.xml > ./log/eureka.log
pause