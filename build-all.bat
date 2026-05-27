@echo off
start /b mvn clean package -f ./config-server/pom.xml > ./log/config-server.log
start /b mvn clean package -f ./loan/pom.xml > ./log/loan.log
start /b mvn clean package -f ./account/pom.xml > ./log/account.log
pause