echo 'starting installation process' >> /var/log/SGATeamAuroraMileStone2/sga-teamaurora-StormClustering1-install.log
cd /home/ec2-user
mkdir -p python_libs
cd python_libs
mkdir -p stormclustering

cp /home/ec2-user/appspec.yml ./stormclustering/appspec.yml
cp -R /home/ec2-user/StormClustering ./stormclustering
cd stormclustering/StormClustering/

usr/local/bin/pip3 install -r requirements.txt

echo "Starting Docker Build" >> /var/log/SGATeamAuroraMileStone2/sga-teamaurora-StormClustering-installM2.log

docker run -name stormclustering1 -p 60622:60615 -d stormclustering  >> /var/log/SGATeamAuroraMileStone2/sga-teamaurora-StormClustering-serverM2.log
#python3 StormClustering/StormClustering.py
#/usr/local/bin/docker-compose up -d >> /var/log/SGATeamAuroraMileStone2/sga-teamaurora-StormClustering-serverM2.log

