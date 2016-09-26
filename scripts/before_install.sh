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
