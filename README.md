# Pinga

Application of example about monitoring OS process using JS with JVM (Java). For instance we've process to execute a ping (icmp), traceroute and ping http/https.  by Alisson Pedrina ( pedrina.alisson@gmail.com )

### Requirements

<p>1 - Java version 8</p>
<p>2 - Maven 3.6.2</p> 

**This application was developed and tested using a macOS Mojave 10.14.5 and a Windows 10 S.

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
pinga.hosts=jasmin.com;oranum.com;uol.com
pinga.log.dir=/Users/alissonpedrina/Documents/log
pinga.icmp.delay=5000
pinga.icmp.command=ping -c 5
pinga.tcp.delay=500000
#http, https
pinga.tcp.type=https
ping.trace.command=traceroute -q 1 -f 1
pinga.trace.delay=5000
####ERROR####
pinga.error.pattern.unknown=Unknown host
pinga.error.pattern.unreachable=Unreachable
pinga.error.pattern.timeout=Request timed out

```

Windows configuration file example:
```bash
pinga.server.report.endpoint=/report
pinga.server.port=8080
#WARNING, SEVERE, INFO, ALL
pinga.log.level=WARNING
pinga.hosts=jasmin.com;oranum.com
pinga.log.dir=C:\\log
pinga.icmp.delay=5000
pinga.icmp.command=ping -n 5
pinga.tcp.delay=5000
#http, https
pinga.tcp.type=https
ping.trace.command=tracert
pinga.trace.delay=5000
####ERROR####
pinga.error.pattern.unknown=Unknown host
pinga.error.pattern.unreachable=Unreachable
pinga.error.pattern.timeout=Request timed out
```

### Compile

```bash
mvn clean install
```
### Run

```bash
java -Dconfig.file=<path_to>/pinga.properties -jar pinga-1.0.0-jar-with-dependencies.jar
```

### Report

By configuring the "pinga.server.report.endpoint" and "pinga.server.port" we've access to the report.
<br>
To query all hosts just make a post to:
```bash
curl -X POST http://localhost:<pinga.server.port><pinga.server.report.endpoint>
```
To query by one host just make a post with the payload: {"host":"jasmim.com"}:
```bash
curl -X POST http://localhost:<pinga.server.port><pinga.server.report.endpoint>
```
