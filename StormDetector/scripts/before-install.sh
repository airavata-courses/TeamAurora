echo 'Kill All the Process' >> /var/log/flask-before.log
ps -ef | grep python | grep -v grep | awk '{print $2}' | xargs kill >> /var/log/flask-before.log
sleep 5


sudo su
sudo yum -y update
sudo yum -y install yum-utils
sudo yum -y groupinstall development

yum install postgresql-devel
sudo yum install gcc
sudo yum install postgresql-server postgresql-contrib
yum install python-devel
service postgresql initdb
service postgresql start
yum install python-psycopg2

sudo yum install bzip2-devel
sudo yum -y install https://centos6.iuscommunity.org/ius-release.rpm
yum -y install python35u-3.5.2
yum -y install python35u-pip
pip3.5 install --upgrade pip
pip3.5 install pika
pip3.5 install psycopg2
pip3.5 install bs4

yum -y install python35u-devel

#docker ps -a | grep 'stormdetector' | awk '{print $1}' | xargs --no-run-if-empty docker stop
#docker ps -a | grep 'stormdetector' | awk '{print $1}' | xargs --no-run-if-empty docker rm

#chmod 777 /home/ec2-user/StormDetector/scripts/install.sh
#cd /home/ec2-user/python_libs/stormdetector/StormDetector/
#docker build -t stormdetector .
