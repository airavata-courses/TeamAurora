echo 'Installing API Gateway'
echo 'Copying artifacts to backup directory.'
cd /home/ec2-user
sudo mkdir -p java_libs
cd java_libs
sudo mkdir -p dataingestorworker
cd dataingestorworker
sudo mkdir -p $(date +%m%d%Y)
cd $(date +%m%d%Y)

sudo cp /home/ec2-user/appspec.yml .
sudo cp -R /home/ec2-user/data_ingestor_worker/ .
sudo cp /home/ec2-user/data_ingestor_worker/dataingestorworker-1.0-jar-with-dependencies.jar ./data_ingestor_worker/docker/images/.

cd /home/ec2-user/java_libs/dataingestorworker/$(date +%m%d%Y)/data_ingestor_worker/docker/images/

echo '***Building docker image. This may take some time.***'
docker build -t img_dataingestorworker .
docker run --name dataingestorworker -d img_dataingestorworker
echo '***Docker container started.***'
