#!/usr/bin/python

import psycopg2
import sys
import datetime

conn = None
conn = psycopg2.connect(database="sg_teamaurora_db", user="dbadmin", password="teamauroradbadmin", host="teamaurora-db.cfofssvi9hbo.us-west-2.rds.amazonaws.com", port="5432")

cur = conn.cursor()


queryStr = ''
tableName = 'service_requests_logger'
inputData = 'testInput'
outputData = 'DummyOutput'
service_name = 'StormDetector'
user_id = 1


cur.execute( "INSERT INTO service_requests_logger (request_id,timestamp,input,output, service_name,user_id) VALUES (4001, %s , %s, %s,%s, %s )", ( str(datetime.datetime.now()), inputData, outputData, service_name , user_id))

conn.commit()
conn.close()