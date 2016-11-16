import pika
import json
import psycopg2
import sys
import datetime
import urllib.error
import urllib.request
import datetime
from bs4 import BeautifulSoup
#from flask_sqlalchemy import SQLAlchemy
from amqplib import client_0_8 as amqp



#conn = amqp.Connection(host="172.31.4.128", userid="test", password="test", virtual_host="/")

#ch = conn.channel()


print("conn done succesfully")

credentials = pika.PlainCredentials('test', 'test')
param = pika.ConnectionParameters('35.161.35.175',5672,'/',credentials,None ,None,None,None,None,3,None,100 ,None,None)
#parameters  = pika.ConnectionParameters(host = '35.161.35.175', port = 5672, virtual_host=  '/',credentials =  credentials,socket_timeout=1,ssl=None)
#connection = pika.BlockingConnection(parameters=parameters)
conn  = pika.BlockingConnection(param)

channel = conn.channel()
#channel = connection.channel()
channel.queue_declare(queue='APIGATEWAY_STORMDETECTOR_QUEUE',durable=True)


def StormDetector(body):
    nexradUrl = '{"val":"https://noaa-nexrad-level2.s3.amazonaws.com/2016/11/11/KABR/KABR20161111_000038_V06" , "userId" : "1", "requestId":"4001"}'
    json_body = json.loads(nexradUrl)


    response = urllib.request.urlopen(json_body['val'])
    data = response.read()
    text = BeautifulSoup(data).encode('utf-8')

    kmlStream = ""

    for i in range(1):
        with open('Test.txt', 'w') as f:
            f.writelines(str(text))

    for i in range(1):
        with open('KML_Samples.kml', 'r') as f:
            kmlStream += str(f.readlines())

    list = {'val': 'test', 'userId' : json_body['userId'], 'requestId': json_body['requestId'] ,  'fromService':'STORMDETECTOR','toService':'APIGATEWAY'}

    json_str = json.dumps(list)
    #json_data = json.loads()

    #------------DB logging--------------------


    conn = psycopg2.connect(database="sg_teamaurora_db", user="dbadmin", password="teamauroradbadmin", host="teamaurora-db.cfofssvi9hbo.us-west-2.rds.amazonaws.com", port="5432")
    cur = conn.cursor()

    inputData = json_body['val']
    outputData = 'Dummy Kml File'
    service_name = 'StormDetector'
    user_id = json_body['userId']


    cur.execute( "INSERT INTO service_requests_logger (request_id,timestamp,input,output, service_name,user_id) VALUES (4001, %s , %s, %s,%s, %s )", ( str(datetime.datetime.now()), inputData, outputData, service_name , user_id))

    conn.commit()
    conn.close()
    
    #--------End of DB logging---------------
	
    return json_str

def on_request(ch, method, props, body):


    print(" [.] url(%s)" % body)
    response = StormDetector(body)

    channel.basic_publish(exchange='',
                          routing_key='APIGATEWAY_ALLSERVICES_QUEUE',
                          body=response
                          )
    ch.basic_ack(delivery_tag = method.delivery_tag)


channel.basic_qos(prefetch_count=1)
channel.basic_consume(on_request, queue='APIGATEWAY_STORMDETECTOR_QUEUE')


print(" [x] Awaiting RPC requests")
channel.start_consuming()
