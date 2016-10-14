echo '***Installing API Gateway***' 

cd /home/ec2-user
mkdir -p java_libs
cd java_libs
mkdir -p apigateway
cd apigateway
mkdir -p $(date +%m%d%Y)
cd $(date +%m%d%Y)

cp /home/ec2-user/appspec.yml .
cp -R /home/ec2-user/api_gateway/apigateway.war .

echo '***Building docker image. This may take some time.***'
