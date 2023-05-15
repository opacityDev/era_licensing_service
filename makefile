MYSQL_CONTAINER=`docker ps -aq --filter="name=wp_auth_database"`
NAME=dev-0.0.1-SNAPSHOT.jar

all: start_server

.PHONY: start_server deploy_mysql_server build

build: src pom.xml
	mvn -e clean package

start_server: build ./target/dev-0.0.1-SNAPSHOT.jar 
	java -jar ./target/dev-0.0.1-SNAPSHOT.jar

deploy_database : delete_mysql_container deploy_mysql_docker_container create_database 

mysql_container: 
	rm out

delete_mysql_container:
	docker rm $(MYSQL_CONTAINER) --force

deploy_mysql_docker_container:
	docker pull mysql:latest
	docker run -d --name=wp_auth_database -e MYSQL_USER=usr -e MYSQL_ROOT_PASSWORD=pass mysql:latest

create_database:
	docker cp ./db.sql $(MYSQL_CONTAINER):/db.sql
	docker cp ./deploy.sh $(MYSQL_CONTAINER):/deploy.sh
	sleep 31
	docker exec -it $(MYSQL_CONTAINER) /deploy.sh

sh_databse:
	docker exec -it $(MYSQL_CONTAINER) sh

mysql_container_ip_address:
	docker container inspect -f '{{ .NetworkSettings.IPAddress }}' $(MYSQL_CONTAINER)