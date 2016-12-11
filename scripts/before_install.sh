echo 'Setting environment'
export JAVA_HOME=/usr/lib/jvm/java
echo 'Checking if MAVEN is installed'
mvn --version
if [ "$?" -ne 0 ]; then
    echo 'Installing Maven...'
	sudo wget http://repos.fedorapeople.org/repos/dchen/apache-maven/epel-apache-maven.repo -O /etc/yum.repos.d/epel-apache-maven.repo
	sudo sed -i s/\$releasever/6/g /etc/yum.repos.d/epel-apache-maven.repo
	sudo yum install -y apache-maven
	mvn --version
fi

echo 'Checking if Docker is installed'
docker --version
if [ "$?" -ne 0 ]; then
	echo "Installing docker."
	sudo yum update -y
	sudo yum install -y docker
	sudo service docker start
	sudo usermod -a -G docker ec2-user
	docker info
	#Installing docker-compose
	echo "Installing docker."
	curl -L https://github.com/docker/compose/releases/download/1.8.0/docker-compose-`uname -s`-`uname -m` > /usr/local/bin/docker-compose
	chmod +x /usr/local/bin/docker-compose
fi

docker ps -a | grep 'apigateway' | awk '{print $1}' | xargs --no-run-if-empty docker stop
docker ps -a | grep 'apigateway' | awk '{print $1}' | xargs --no-run-if-empty docker rm
