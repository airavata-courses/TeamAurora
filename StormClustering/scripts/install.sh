echo 'starting installation process' >> /var/log/sga-teamaurora-StormClustering-install.log
cd /home/ec2-user
mkdir -p python_libs
cd python_libs
mkdir -p stormclustering

cp /home/ec2-user/appspec.yml ./stormclustering/appspec.yml
cp -R /home/ec2-user/StormClustering ./stormclustering/StormClustering


cd ./stormclustering

echo 'Activating virtualenv for StormClustering Microservice' >> /var/log/sga-teamaurora-StormClustering-install.log
pip install virtualenv >> /var/log/sga-teamaurora-StormClustering-install.log
virtualenv env >> /var/log/sga-teamaurora-StormClustering-install.log
source env/bin/activate >> /var/log/sga-teamaurora-StormClustering-install.log
pip install Flask >> /var/log/sga-teamaurora-StormClustering-install.log
pip install nose >> /var/log/sga-teamaurora-StormClustering-install.log
pip install BeautifulSoup4 >> /var/log/sga-teamaurora-StormClustering-install.log
pip install Flask-SQLAlchemy >> /var/log/sga-teamaurora-StormClustering-install.log

export FLASK_APP=StormClustering.py
flask run --host=0.0.0.0 --port=5000 >> /var/log/sga-teamaurora-StormClustering-server.log 2>&1 &
python3 StormClustering/StormClustering.py

