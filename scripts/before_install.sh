echo 'Stopping Tomcat Service. This will take some time.'
sudo service tomcat8 stop
sleep 30
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

echo 'Copying artifacts to backup directory.'
cd /home/ec2-user
mkdir -p java_libs
cd java_libs
mkdir -p apigateway
cd apigateway
mkdir -p $(date +%m%d%Y)
cd $(date +%m%d%Y)

cp /home/ec2-user/appspec.yml .
cp -R /home/ec2-user/api_gateway/ .
