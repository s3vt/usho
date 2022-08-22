MVN_REPO=${HOME}/.m2
CODE_BASE=${PWD}
IMAGE_TAG=usho:0.1

clean:
	mvn clean

pack:
	mvn package

dpack: clean
	docker run --rm \
 		--volume ${MVN_REPO}:/root/.m2 \
 		--volume ${CODE_BASE}:/app \
 		--workdir /app s3vt/maven:latest mvn install

image: pack
	docker build --compress -t ${IMAGE_TAG} -f docker/usho/Dockerfile .

up: image
	docker-compose --project-directory . -f docker/docker-compose.yaml up

down:
	docker-compose -f docker/docker-compose.yaml down
