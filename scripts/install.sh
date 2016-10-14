echo 'Installing API Gateway' 

cd /home/ec2-user
mkdir -p java_libs
cd java_libs
mkdir -p apigateway
cd apigateway
mkdir -p $(date +%m%d%Y)
cd $(date +%m%d%Y)

cp /home/ec2-user/appspec.yml ./apigateway/appspec.yml
cp -R /home/ec2-user/api_gateway/apigateway.war ./apigateway/apigateway.war

cp /home/ec2-user/api_gateway/apigateway.war /usr/share/tomcat8/webapps/apigateway.war
echo 'Starting Tomcat service. This may take some time.'
sudo service tomcat8 start
sleep 15
