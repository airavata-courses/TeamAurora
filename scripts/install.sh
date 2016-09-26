echo 'Installing API Gateway' 
cd '/home/ec2-user/api_gateway'
cp api_gateway/apigateway.war /usr/share/tomcat8/webapps/apigateway.war
sudo service tomcat8 start
