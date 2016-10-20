echo 'Kill All the Process' >> /var/log/flask-before.log
ps -ef | grep python | grep -v grep | awk '{print $2}' | xargs kill >> /var/log/flask-before.log
sleep 5

docker ps -a | grep 'stormdetector' | awk '{print $1}' | xargs --no-run-if-empty docker rm

#chmod 777 /home/ec2-user/StormDetector/scripts/install.sh
cd /home/ec2-user/python_libs/stormdetector/StormDetector/
docker build -t stormdetector .
