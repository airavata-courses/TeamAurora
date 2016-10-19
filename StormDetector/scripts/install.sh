echo 'starting installation process'
cd /home/ec2-user
mkdir -p python_libs
cd python_libs
mkdir -p stormdetector

cp /home/ec2-user/appspec.yml ./stormdetector/appspec.yml
cp -R /home/ec2-user/StormDetector ./stormdetector

cd ./stormdetector


#sudo yum -y install python34
#sudo yum -y install python34-pip
#sudo alternatives --set python /usr/bin/python3.4

echo 'Activating virtualenv for StormDetection Microservice' 
#pip-3.4 install -r /home/ec2-user/python_libs/stormdetector/StormDetector/requirements.txt 
usr/local/bin/pip-3.4 install virtualenv
#cd StormDetector
virtualenv env
source env/bin/activate
usr/local/bin/pip-3.4 install Flask
usr/local/bin/pip-3.4 install nose
usr/local/bin/pip-3.4 install BeautifulSoup4
usr/local/bin/pip-3.4 install bs4
usr/local/bin/pip-3.4 install Flask-SQLAlchemy
echo 'Running Flask Server' >> /var/log/sga-teamaurora-flask-install1.log
#export FLASK_APP=StormDetection.py
#flask run --host=0.0.0.0 --port=5000 >> /var/log/sga-teamaurora-StormDetection-server.log 2>&1 &

echo 'starting Storm Detection'

cd ../../
/usr/local/bin/docker-compose up -d >> /var/log/SGATeamAuroraMileStone2/sga-teamaurora-StormDetector-serverM2.log
#pwd >> /var/log/sga-teamaurora-StormDetection-server1.log
#ls -la >> /var/log/sga-teamaurora-StormDetection-server1.log 
#python3 ./StormDetector/stormDetector.py >> /var/log/sga-teamaurora-StormDetection-server1.log 2>&1 &
#python3 /home/ec2-user/python_libs/stormclustering/StormClustering/StormClustering.py >> /var/log/sga-teamaurora-StormClustering-server1.log 2>&1 &
