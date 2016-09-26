echo 'Installing API Gateway' 
cp /home/ec2-user/api_gateway/apigateway.war /usr/share/tomcat8/webapps/apigateway.war
echo 'Starting Tomcat service. This may take some time.'
sudo service tomcat8 start
sleep 30
