# Pinga

Docler Holding "Homework" by Alisson Pedrina ( pedrina.alisson@gmail.com )

### Requirements

<p>1 - Java version 8</p>
<p>2 - Maven 3.6.2</p> 

**This application was developed and tested using a macOS Mojave 10.14.5 system.

### Concept

<p>
The aim of this homework is to check the user connections - status, time to the given host by ping and traceroute commands.
</p> 


### Config

All configurations are at pinga.properties file. This file either need be set up by parameter or need stay at user directory. 

MacOs configuration file example:
```bash
pinga.server.report.endpoint=/report
pinga.server.port=8080
#WARNING, SEVERE, INFO, ALL
pinga.log.level=WARNING
pinga.hosts=jasmin.com;oranum.com
pinga.log.dir=/Users/alissonpedrina/Documents/log
pinga.icmp.delay=5000
pinga.icmp.command=ping -c 5
pinga.tcp.delay=5000
pinga.tcp.command=ping -c 5
ping.trace.command=traceroute 
pinga.trace.delay=5000
```

Windows configuration file example:
```bash
pinga.server.report.endpoint=/report
pinga.server.port=8080
#WARNING, SEVERE, INFO, ALL
pinga.log.level=WARNING
pinga.hosts=jasmin.com;oranum.com
pinga.log.dir=/Users/alissonpedrina/Documents/log
pinga.icmp.delay=5000
pinga.icmp.command=ping -c 5
pinga.tcp.delay=5000
pinga.tcp.command=ping -c 5
ping.trace.command=traceroute 
pinga.trace.delay=5000
```

### Compile

```bash
mvn clean install
```
### Run

```bash
java -Dconfig.file=/pinga.properties -jar pinga-1.0.0-jar-with-dependencies.jar"
```

### Report

By configuring the "pinga.server.report.endpoint" and "pinga.server.port" we've access to the report.
<br>
To query all hosts just make a post to:
```bash
curl -X POST http://localhost:<pinga.server.report.endpoint>/report
```
To query by one host just make a post with the payload: {"host":"jasmim.com"}:
```bash
curl -X POST http://localhost:<pinga.server.report.endpoint>/report
```