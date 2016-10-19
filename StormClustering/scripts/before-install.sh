#echo 'Kill All the Process' >> /var/log/flask-before.log
#ps -ef | grep python | grep -v grep | awk '{print $2}' | xargs kill >> /var/log/flask-before.log
#sleep 5
docker rm -f $(docker ps -a)
docker build -t stormclustering .  >> /var/log/SGATeamAuroraMileStone2/sga-teamaurora-StormClustering-serverM2.log
