echo 'starting installation process' >> /var/log/sga-teamaurora-StormDetection-install.log
cd /home/ec2-user
mkdir -p python_libs
cd python_libs
mkdir -p stormdetector

cp /home/ec2-user/appspec.yml ./stormdetector/appspec.yml
cp -R /home/ec2-user/StormDetector ./stormdetector/StormDetector

cd ./stormdetector

echo 'Activating virtualenv for StormDetection Microservice' >> /var/log/sga-teamaurora-StormDetection-install.log
pip install -r requirements.txt >>  /var/log/sga-teamaurora-StormDetection-install.log
pip install virtualenv >> /var/log/sga-teamaurora-StormDetection-install.log
virtualenv env >> /var/log/sga-teamaurora-StormDetection-install.log
source env/bin/activate >> /var/log/sga-teamaurora-StormDetection-install.log
pip install Flask >> /var/log/sga-teamaurora-StormDetection-install.log
pip install nose >> /var/log/sga-teamaurora-StormDetection-install.log
pip install BeautifulSoup4 >> /var/log/sga-teamaurora-StormDetection-install.log
pip install bs4 >> /var/log/sga-teamaurora-StormDetection-install.log
pip install Flask-SQLAlchemy >> /var/log/sga-teamaurora-StormDetection-install.log
#echo 'Running Flask Server' >> /var/log/sga-teamaurora-flask-install.log
export FLASK_APP=StormDetection.py
flask run --host=0.0.0.0 --port=5000 >> /var/log/sga-teamaurora-StormDetection-server.log 2>&1 &
python StormDetection.py >> /var/log/sga-teamaurora-StormDetection-server.log 2>&1 &
