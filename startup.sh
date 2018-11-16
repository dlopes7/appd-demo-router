#!/bin/bash

sh /usr/app/extractAgent.sh

# AppDynamics instrumentation
JAVA_OPTS="$JAVA_OPTS -Dappdynamics.agent.tierName=$TIER_NAME"
JAVA_OPTS="$JAVA_OPTS -Dappdynamics.agent.reuse.nodeName=true -Dappdynamics.agent.reuse.nodeName.prefix=$TIER_NAME"
JAVA_OPTS="$JAVA_OPTS -javaagent:/appdynamics/AppServerAgent/javaagent.jar"
JAVA_OPTS="$JAVA_OPTS -Dappdynamics.controller.hostName=$CONTROLLER_HOST -Dappdynamics.controller.port=$CONTROLLER_PORT -Dappdynamics.controller.ssl.enabled=$CONTROLLER_SSL_ENABLED"
JAVA_OPTS="$JAVA_OPTS -Dappdynamics.agent.accountName=$ACCOUNT_NAME -Dappdynamics.agent.accountAccessKey=$ACCOUNT_ACCESS_KEY -Dappdynamics.agent.applicationName=$APPLICATION_NAME"
JAVA_OPTS="$JAVA_OPTS -Dappdynamics.socket.collection.bci.enable=true"
JAVA_OPTS="$JAVA_OPTS -Xms64m -Xmx512m -XX:MaxPermSize=256m -Djava.net.preferIPv4Stack=true"
JAVA_OPTS="$JAVA_OPTS -Djava.security.egd=file:/dev/./urandom"

java $JAVA_OPTS -jar /usr/app/appd-demo-router-0.0.4.jar -Dserver.port=8080 -Dapp.login.url=$APP_LOGIN_URL -Dapp.account.url=$APP_ACCOUNT_URL
