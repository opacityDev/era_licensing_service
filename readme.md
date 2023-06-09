# ERA PLUGINS licensing server and connector wordpress plugin

This README provides an overview of the ERA Plugins Licensing Server and Connector WordPress Plugin. It explains how the server works and how to communicate with it using REST API endpoints for generating, binding, and authenticating license keys. The deployment process involves creating a Docker container for the database management system, preparing the database, and building and running the server.

## How it works

The licensing server depends on the **ERA Connector Plugin**, which is a WordPress plugin that adds a WooCommerce hook after a purchase is made. This hook notifies the licensing server to generate a license key for the purchased plugin and associate it with the user who is currently logged in to the session. The user has the option to bind the license key to a specific domain name. To authenticate/activate the plugin, the user can send a request to the licensing server with only the license key. The server will then determine whether the request is coming from the domain name that is bound to the given license key and respond accordingly.

***REST :***

Action        | Mapping                              | Description
------------- |-------------                         | -------------
Generate      | `/notify/gen/$user_id/$product_id` |Generates a new license key  
Bind          | `/notify/bind/$license_key/$domain_name`| Binds **license_key** to the given **domain_name** 
Authenticate  | `/$license_key`| Authenticates by comparing the domain name in the request header and the one associated with the given **license_key**


- `notify` can only be accessed by the machine hosting the wordpress website.

- `authentication` s' access is not restricted.

## Deployment

### -- TODO

### Prerequisites

Make sure that you have the following programs installed on the hosting machine
- Maven
- Docker **| ONLY for local testing**
- Make
- Java
  
**Skip the first 3 steps if you don't need to deploy a separate DMBS for the project**

### 1) Create a docker container for the DBMS

If you did deploy a container before, make sure to execute this command to delete the existing container.

` make delete_mysql_container  `

The command below will pull the latest mysql docker image and create a container with a predefined password for the root user 

` make deploy_mysql_docker_container `
<br>

*The only user available will be `root` with a default password `pass` unless changed in the makefile*


### 2) Prepare the DBMS

Now we will create the database and a user with remote access to be used by the spring server

` make create_database `

### 3) Update application.properties

Check for the ip address used by the newly created docker container of you DBMS 

` make mysql_container_ip_address `

and then update `src/main/resources/application.properties` accordingly


### 4) BUILD & RUN
` make `
