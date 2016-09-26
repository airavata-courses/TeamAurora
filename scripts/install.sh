echo 'Installing Forecast Trigger' 
cp /home/ec2-user/forecast_trigger/forecasttrigger.war /usr/share/tomcat8/webapps/forecasttrigger.war
echo 'Starting Tomcat service. This may take some time.'
sudo service tomcat8 start
sleep 30
