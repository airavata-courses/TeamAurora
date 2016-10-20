echo 'Copying artifacts to backup directory.'
cd /home/ec2-user
sudo mkdir -p java_libs
cd java_libs
sudo mkdir -p apigateway
cd apigateway
sudo mkdir -p $(date +%m%d%Y)
cd $(date +%m%d%Y)

cp /home/ec2-user/appspec.yml .
cp -R /home/ec2-user/api_gateway/ .
sudo cp /home/ec2-user/api_gateway/apigateway.war ./api_gateway/docker/images/.

cd /home/ec2-user/java_libs/apigateway/$(date +%m%d%Y)/api_gateway/docker/images/

echo '***Building docker image. This may take some time.***'
docker build -t img_apigateway .
docker run -p 8081:8080 --name apigateway -d img_apigateway
sleep 10