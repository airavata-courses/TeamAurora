echo 'starting installation process' 
mkdir -p python_libs
cd python_libs
mkdir -p stormclustering

cp /home/ec2-user/appspec.yml ./stormclustering/appspec.yml
cp -R /home/ec2-user/StormClustering ./stormclustering
cd stormclustering/StormClustering/

docker ps -a | grep 'stormclustering' | awk '{print $1}' | xargs --no-run-if-empty docker stop
docker ps -a | grep 'stormclustering' | awk '{print $1}' | xargs --no-run-if-empty docker rm

docker build -t stormclustering .

echo "Starting Docker run"

docker run --name stormclustering1 -p 60622:60615 -d stormclustering &
#python3 StormClustering/StormClustering.py
#/usr/local/bin/docker-compose up -d >> /var/log/SGATeamAuroraMileStone2/sga-teamaurora-StormClustering-serverM2.log

