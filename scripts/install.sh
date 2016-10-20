echo 'Installing API Gateway'
echo 'Copying artifacts to backup directory.'
cd /home/ec2-user
sudo mkdir -p java_libs
cd java_libs
sudo mkdir -p dataingestor
cd dataingestor
sudo mkdir -p $(date +%m%d%Y)
cd $(date +%m%d%Y)

sudo cp /home/ec2-user/appspec.yml .
sudo cp -R /home/ec2-user/data_ingestor/ .
sudo cp /home/ec2-user/data_ingestor/dataingestor.war ./data_ingestor/docker/images/.

cd /home/ec2-user/java_libs/dataingestor/$(date +%m%d%Y)/data_ingestor/docker/images/

echo '***Building docker image. This may take some time.***'
docker build -t img_dataingestor .
docker run -p 8081:8080 --name dataingestor -d img_dataingestor
echo '***Docker container started.***'