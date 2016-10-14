
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

echo 'Copying artifacts to backup directory.'
cd /home/ec2-user
sudo mkdir -p java_libs
cd java_libs
sudo mkdir -p apigateway
cd apigateway
sudo mkdir -p $(date +%m%d%Y)
cd $(date +%m%d%Y)

cp /home/ec2-user/appspec.yml .
cp -R /home/ec2-user/api_gateway/ .
cp ../../apigateway.war .
