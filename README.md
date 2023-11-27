# distributed-tracing-logging
Spring boot application for distributed tracing and logging using Grafana, Tempo and Loki

**distributed-tracing-logging** is a multi-module spring boot application to integrate distributed tracing and distributed logging features to Spring Boot microservices.
This maven based project has two modules: **user-service** and **address-service** each representing an microservice.

# user-service
A simple service which retrieves user details, calls **address-service** for user's address details. This is a dummy service returns same response on each call. <br/><br/>
&emsp;**GET:** http://localhost:7000/user/123  <br/>
&emsp;**Response:** {"name":"123","address":{"field":"123"}}  <br/>
 
# address-service
A simple service which retrieves an user's address, called by **user-service** for user's address details. This is a dummy service returns same response on each call. <br/>

# Build steps
Build user-service and address-service as war artifacts.<br/><br/>
&emsp;mvn clean install<br/>

# Logging configuration
Loki and Promtail are required for distributed logging.<br/>
&emsp;-> Start Loki followed by Promtail<br/>
&emsp;-> Start Promtail: promtail-windows-amd64.exe -**-config.file=promtail-local-config.yml**<br/>
&emsp;-> Promtail config file: **config_files\promtail-local-config.yml**<br/>
&emsp;-> Start Loki: loki-windows-amds64.exe **--config.file=loki-local-config.yml**<br/>
&emsp;-> Loki config file: **config_files\loki-local-config.yml**<br/>
# Tracing configuration
Download Tempo for windows:<br/>
&emsp;-> Go to: https://github.com/grafana/tempo/releases<br/>
&emsp;-> Expand "Assests" section to find binary for windows<br/>
&emsp;-> Link to binary: https://github.com/grafana/tempo/releases/download/v2.3.0/tempo_2.3.0_windows_amd64.tar.gz<br/>
&emsp;-> Start Tempo: tempo.exe **-config.file=tempo.yml**<br/>
&emsp;-> Tempo config file: **config_files\tempo.yml**<br/>
# Grafana configure Loki and Tempo as datasource
Start Grafana: grafana-server.exe<br/>
&emsp;-> Configure Loki datasource: Give URL as : http://localhost:3100<br/>
<img src="images/grafana-loki-ds.PNG" width="128"/>
&emsp;-> Configure Tempo datasource: Give URL as : http://localhost:9000<br/>
<img src="images/grafana-tempo-ds.PNG" width="128"/>
# Create spring boot services with dependencies and configuration
Create two service: user-service and address-service<br/>
&emsp;-> user-service calls address-service for address related information<br/>
<br/>Endpoint exposed by user-service<br/>
&emsp;->GET: http://localhost:7000/user/123<br/>
&emsp;->Response: {"name":"123","address":{"field":"123"}}<br/>
<br/>Maven dependencies added:<br/>
&emsp;->spring-cloud-starter-sleuth	: <br/>
&emsp;->spring-cloud-sleuth-otel-autoconfigure<br/>
&emsp;->opentelemetry-exporter-otlp<br/>
<br/>application.properties<br/>
&emsp;->spring.application.name=user-service<br/>
&emsp;->spring.sleuth.otel.config.trace-id-ratio-based=1.0<br/>
&emsp;->spring.sleuth.otel.exporter.otlp.endpoint=http://localhost:4317<br/>	
<br/>logback.xml<br/>
&emsp;<encoder><br/>
&emsp;&emsp;<pattern>%d{yyyy-MM-dd HH:mm:ss.SSS, GMT} %p [ traceid=%X{traceId} spanid=%X{spanId} ] %c{1.} [%t] %m%n</pattern><br/>
&emsp;</encoder><br/>
