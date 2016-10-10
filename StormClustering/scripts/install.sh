echo 'starting installation process' >> /var/log/sga-teamaurora-StormClustering1-install.log
cd /home/ec2-user
mkdir -p python_libs
cd python_libs
mkdir -p stormclustering

cp /home/ec2-user/appspec.yml ./stormclustering/appspec.yml
cp -R /home/ec2-user/StormClustering ./stormclustering

cd ./stormclustering

echo 'Activating virtualenv for StormClustering Microservice' >> /var/log/sga-teamaurora-StormClustering1-install.log
pip-3.4 install virtualenv >> /var/log/sga-teamaurora-StormClustering-install.log
virtualenv env >> /var/log/sga-teamaurora-StormClustering-install.log
source env/bin/activate >> /var/log/sga-teamaurora-StormClustering-install.log
pip-3.4 install Flask >> /var/log/sga-teamaurora-StormClustering-install.log
pip-3.4 install nose >> /var/log/sga-teamaurora-StormClustering-install.log
pip-3.4 install BeautifulSoup4 >> /var/log/sga-teamaurora-StormClustering-install.log
pip-3.4 install Flask-SQLAlchemy >> /var/log/sga-teamaurora-StormClustering-install.log
pip-3.4 install bs4
#export FLASK_APP=StormClustering.py
#flask run --host=0.0.0.0 --port=5000 >> /var/log/sga-teamaurora-StormClustering-server.log 2>&1 &
echo 'running StormClustering'
pwd >> /var/log/sga-teamaurora-StormClustering-server1.log
ls -la >> /var/log/sga-teamaurora-StormClustering-server1.log 
python3 StormClustering/StormClustering.py

