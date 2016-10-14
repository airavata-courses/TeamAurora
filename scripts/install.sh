
cd /home/ec2-user/java_libs/apigateway/$(date +%m%d%Y)/api_gateway/docker/images/

echo '***Building docker image. This may take some time.***'
docker build -t apigateway .
docker run apigateway &
