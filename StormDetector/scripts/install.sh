echo 'starting installation process'
cd /home/ec2-user
mkdir -p python_libs
cd python_libs
mkdir -p stormdetector

cp /home/ec2-user/appspec.yml ./stormdetector/appspec.yml
cp -R /home/ec2-user/StormDetector ./stormdetector

cd ./stormdetector/StormDetector/

sudo su
sudo yum -y update
sudo yum -y install yum-utils
sudo yum -y groupinstall development

sudo yum install bzip2-devel
sudo yum -y install https://centos6.iuscommunity.org/ius-release.rpm
yum -y install python35u-3.5.2
yum -y install python35u-pip
pip3.5 install --upgrade pip
pip3.5 install pika
yum -y install python35u-devel



docker ps -a | grep 'stormdetector' | awk '{print $1}' | xargs --no-run-if-empty docker stop
docker ps -a | grep 'stormdetector' | awk '{print $1}' | xargs --no-run-if-empty docker rm


docker build -t stormdetector .


echo 'starting Storm Detection'

docker run --name stormdetector1 -p 60623:60608 -d stormdetector &

#cd ../
#pwd
#ls
#/usr/local/bin/docker-compose up -d >> /var/log/SGATeamAuroraMileStone2/sga-teamaurora-StormDetector-serverM2.log
#pwd >> /var/log/sga-teamaurora-StormDetection-server1.log
#ls -la >> /var/log/sga-teamaurora-StormDetection-server1.log 
#python3 ./StormDetector/stormDetector.py >> /var/log/sga-teamaurora-StormDetection-server1.log 2>&1 &
#python3 /home/ec2-user/python_libs/stormclustering/StormClustering/StormClustering.py >> /var/log/sga-teamaurora-StormClustering-server1.log 2>&1 &
