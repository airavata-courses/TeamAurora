echo 'starting installation process' >> /var/log/SGATeamAuroraMileStone2/sga-teamaurora-StormClustering1-install.log
cd /home/ec2-user
mkdir -p python_libs
cd python_libs
mkdir -p stormclustering

cp /home/ec2-user/appspec.yml ./stormclustering/appspec.yml
cp -R /home/ec2-user/StormClustering ./stormclustering
cd stormclustering/StormClustering/

pip3 install -r requirements.txt

echo "Starting Docker Build" >> /var/log/SGATeamAuroraMileStone2/sga-teamaurora-StormClustering-installM2.log

#docker rm $(docker ps -a -q)
docker build -t stormclustering .  >> /var/log/SGATeamAuroraMileStone2/sga-teamaurora-StormClustering-serverM2.log
docker run  -p 60615:60615 stormclustering & >> /var/log/SGATeamAuroraMileStone2/sga-teamaurora-StormClustering-serverM2.log
#python3 StormClustering/StormClustering.py


