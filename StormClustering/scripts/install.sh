echo 'starting installation process' >> /var/log/sga-teamaurora-StormClustering-install.log
mkdir -p python_libs
cd python_libs
mkdir -p stormclustering
cd '/home/ec2-user/StormClustering'
mv ./home/ec2-user/StormClustering ./python_libs/stormclustering/StormClustering

echo 'Activating virtualenv for StormClustering Microservice' >> /var/log/sga-teamaurora-StormClustering-install.log
pip install virtualenv >> /var/log/sga-teamaurora-StormClustering-install.log
virtualenv env >> /var/log/sga-teamaurora-StormClustering-install.log
source env/bin/activate >> /var/log/sga-teamaurora-StormClustering-install.log
pip install Flask >> /var/log/sga-teamaurora-StormClustering-install.log
pip install nose >> /var/log/sga-teamaurora-StormClustering-install.log
pip install BeautifulSoup4 >> /var/log/sga-teamaurora-StormClustering-install.log
pip install Flask-SQLAlchemy >> /var/log/sga-teamaurora-StormClustering-install.log

export FLASK_APP=StormClustering.py
flask run --host=0.0.0.0 --port=65000 >> /var/log/sga-teamaurora-StormClustering-server.log 2>&1 &
