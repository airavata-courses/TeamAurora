echo 'starting installation process' >> /var/log/sga-teamaurora-StormClustering1-install.log
cd /home/ec2-user
mkdir -p python_libs
cd python_libs
mkdir -p stormclustering

cp /home/ec2-user/appspec.yml ./stormclustering/appspec.yml
cp -R /home/ec2-user/StormClustering ./stormclustering
cd stormclustering/StormClustering/

pip3 install -r requirements.txt

echo "Starting Docker Build" >> /var/log/sga-teamaurora-StormClustering-installM2.log

docker build -t stormclustering .  >> /var/log/sga-teamaurora-StormClustering-serverM2.log
docker run stormclustering & >> /var/log/sga-teamaurora-StormClustering-serverM2.log
#python3 StormClustering/StormClustering.py


