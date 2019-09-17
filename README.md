
# Coding Exercise For Update  #

## Task ##

NewsService

To serve data to the app we need Restful API for the following use cases 

• Allow an editor to create/update/delete an article 

• display an article 

• list all articles for a given author 

• list all articles for a given period 

• find all articles for a specific keywords 

An Article usually consists for the following information 
 . Header
 . short Description
 . Text 
 . Author(s)
 . keywords 
 
 


## Solution Description ##

I have used Hexagonal/ Port and Adapter Design Architecture for this Demo

I have used ElasticSearch for Database Storage .

## Resources on Hexagonal / Ports and Adapters ##
 - [Hexagonal Architecture](http://alistair.cockburn.us/Hexagonal+architecture)
 - [Ports-And-Adapters / Hexagonal Architecture](http://www.dossier-andreas.net/software_architecture/ports_and_adapters.html)

This Project has 3 Main Modules

 •  domain  (All Domain Models and Services)

 this module contain all business logic and domain models .


 • Controller 

 This Module Expose Rest Endpoints.

if you just start the application and navigate to `http://localhost:8091/swagger-ui.html`. There you'll find a nice API documentation thanks to Swagger.


 .config

 this module configure all beans and dependencies
 
## To keep Assignment Short I have opt out below features 

1. Authentication
2. Validation and Exception Handling 

## Getting Started ##

Steps
## Install ElastichSearch ##

1. Download ElastichSearch 
https://download.elastic.co/elasticsearch/release/org/elasticsearch/distribution/zip/elasticsearch/2.4.0/elasticsearch-2.4.0.zip
2. Extract Zip 

3.  cd elasticsearch-2.4.0/bin/



4. execute ./elasticsearch

with below configurations 

elasticsearch
  clustername: my-application
  host: localhost
  port: 9300
 
 or modify in  application.yaml 

## Prepare and Start Service  ##

1. Download or Clone Project 

2. cd demo/

3. Build Application   mvn clean package

4. Start application with  below command(make sure you have 8091 port opened)

java -jar demo/target/demo-0.0.1-SNAPSHOT.jar


