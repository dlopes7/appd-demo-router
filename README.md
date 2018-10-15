# Sample Router App

## Description

This is a sample router app, simulating an Internet Banking backend.
It receives requests of form:

POST localhost:8080/appd-router

```javascript
{
	"method": "login",
	"data": {
		"user": "david",
		"password": "123"
	}
}
```

It will then, call an `HTTP Service` using Hystrix, based on which `method` was specified.

## Packaging and running

- `mvn springboot:run` to run the app
- `mvn package` to create the jar

## Docker

1. `docker build . -t appd-demo-router`
2. `docker run -d -p 8080:8080 --name=router -v /opt/java-agent:/usr/java-agent -e "JAVA_OPTS=-Dserver.port=8080 -javaagent:/usr/java-agent/javaagent.jar -Dappdynamics.agent.applicationName=Mobile -Dappdynamics.agent.tierName=router -Dappdynamics.agent.reuse.nodeName=true -Dappdynamics.agent.reuse.nodeName.prefix=router -Dappdynamics.controller.port=8090 -Dappdynamics.controller.hostName=controller_hostname -Dappdynamics.agent.accountAccessKey=controller_access_key" appd-demo-router`

## Implemented Services

### Login

```javascript
{
	"method": "login",
	"data": {
		"user": "david",
		"password": "123"
	}
}
```

### Balance

```javascript
{
	"method": "balance",
	"data": {
		"branchNumber": "0123",
		"accountNumber": "456789"
	}
}
```

### Transfer

```javascript
{
	"method": "transfer",
	"data": {
		"to": {
			"branchNumber": "0123",
			"accountNumber": "456789"
		},
		"from": {
			"branchNumber": "3210",
			"accountNumber": "987654"
		},
		"amount": 500

	}
}
```
