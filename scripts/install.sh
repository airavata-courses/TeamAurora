echo 'Installing API Gateway'
cp /home/ec2-user/data_ingestor/dataingestor.war /usr/share/tomcat8/webapps/dataingestor.war
echo 'Starting Tomcat service. This may take some time.'
sudo service tomcat8 start
sleep 30
