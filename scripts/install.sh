echo 'Installing ForecastTrigger'
echo 'Copying artifacts to backup directory.'
cd /home/ec2-user
sudo mkdir -p java_libs
cd java_libs
sudo mkdir -p forecasttriggerworker
cd forecasttriggerworker
sudo mkdir -p $(date +%m%d%Y)
cd $(date +%m%d%Y)

sudo cp /home/ec2-user/appspec.yml .
sudo cp -R /home/ec2-user/forecast_trigger_worker/ .
sudo cp /home/ec2-user/forecast_trigger_worker/forecasttriggerworker-1.0-jar-with-dependencies.jar ./forecast_trigger_worker/docker/images/.

cd /home/ec2-user/java_libs/forecasttriggerworker/$(date +%m%d%Y)/forecast_trigger_worker/docker/images/

echo '***Building docker image. This may take some time.***'
docker build -t img_forecasttriggerworker .
docker run --name forecasttriggerworker -d img_forecasttriggerworker
echo '***Docker container started.***'