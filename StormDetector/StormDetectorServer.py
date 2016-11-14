import pika
import json
import urllib.error
import urllib.request
import datetime
from bs4 import BeautifulSoup
from flask_sqlalchemy import SQLAlchemy


connection = pika.BlockingConnection(pika.ConnectionParameters(host='localhost'))

channel = connection.channel()
channel.queue_declare(queue='APIGATEWAY_STORMDETECTOR_QUEUE',durable=True)


def StormDetector(body):

    nexradUrl = body
    json_body = json.loads(body)

    print(body)

    #response = urllib.request.urlopen(nexradUrl)
    #data = response.read()
    #text = BeautifulSoup(data).encode('utf-8')

    kmlStream = ""

    for i in range(1):
        #print("test " + str(i))
        with open('KML_Samples.kml', 'r') as f:
            kmlStream += str(f.readlines())

    #json_return_data = "klm string";
    list = {'val': 'test', 'userId' : json_body['userId'], 'requestId': json_body['requestId'] ,  'fromService':'STORMDETECTOR','toService':'APIGATEWAY'}

    json_str = json.dumps(list)
    #json_data = json.loads()

    

	
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
