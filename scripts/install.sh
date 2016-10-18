echo 'Installing API Gateway' 

mkdir -p java_libs
cd java_libs
mkdir -p apigateway

cp /home/ec2-user/appspec.yml ./apigateway/appspec.yml
cp -R /home/ec2-user/api_gateway/apigateway.war ./apigateway/apigateway.war

cp /home/ec2-user/api_gateway/apigateway.war /usr/share/tomcat8/webapps/apigateway.war
echo 'Starting Tomcat service. This may take some time.'
sudo service tomcat8 start
sleep 30