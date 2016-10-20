echo 'Installing Forecast Trigger' 
echo 'Copying artifacts to backup directory.'
cd /home/ec2-user
sudo mkdir -p java_libs
cd java_libs
sudo mkdir -p forecasttrigger
cd forecasttrigger
sudo mkdir -p $(date +%m%d%Y)
cd $(date +%m%d%Y)

sudo cp /home/ec2-user/appspec.yml .
sudo cp -R /home/ec2-user/forecast_trigger/ .
sudo cp /home/ec2-user/forecast_trigger/forecasttrigger.war ./forecast_trigger/docker/images/.

cd /home/ec2-user/java_libs/forecasttrigger/$(date +%m%d%Y)/forecast_trigger/docker/images/

echo '***Building docker image. This may take some time.***'
docker build -t img_forecasttrigger .
docker run -p 8083:8080 --name forecasttrigger -d img_forecasttrigger
echo '***Docker container started.***'
