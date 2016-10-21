#echo 'Kill All the Process' >> /var/log/flask-before.log
#ps -ef | grep python | grep -v grep | awk '{print $2}' | xargs kill >> /var/log/flask-before.log
#sleep 5
#docker rm -f ec2user_stormclustering_1
pwd >> /var/log/SGATeamAuroraMileStone2/sga-teamaurora-StormClustering-beforeInstallM2.log
cd /home/ec2-user/python_libs/stormclustering/StormClustering/

#docker ps -a | grep 'stormclustering' | awk '{print $1}' | xargs --no-run-if-empty docker stop
#docker ps -a | grep 'stormclustering' | awk '{print $1}' | xargs --no-run-if-empty docker rm

#docker build -t stormclustering .  >> /var/log/SGATeamAuroraMileStone2/sga-teamaurora-StormClustering-serverM2.log
