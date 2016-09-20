import datetime
import json
import urllib.error
import urllib.request

from bs4 import BeautifulSoup
from flask_sqlalchemy import SQLAlchemy

from flask import Flask, jsonify


app = Flask(__name__)
app.debug = True
app.config['SQLALCHEMY_DATABASE_URI'] = 'postgresql://dbadmin:teamauroradbadmin@teamaurora-db.cfofssvi9hbo.us-west-2.rds.amazonaws.com/sg_teamaurora_db'
app.config['SQLALCHEMY_ECHO'] = True
db = SQLAlchemy(app)

class Service_Requests_Log(db.Model):
    #RequestID, TimeStamp, Input, Output , Service
    __tablename__ = 'Service_Requests_Logger'
    id = db.Column(db.Integer, primary_key=True)
    request_id = db.Column(db.Integer)
    timestamp = db.Column(db.DateTime)
    input = db.Column(db.String(80))
    output = db.Column(db.String(80))
    service_name = db.Column(db.String(40))

    db.PrimaryKeyConstraint('id')

    def __init__(self,request_id, timestamp, Input, Output, Service):
        self.timestamp = timestamp
        self.request_id = request_id
        self.input = Input
        self.output = Output
        self.service_name = Service

    def __repr__(self):
        return self.request_id

@app.route('/') 
@app.route('/noaa-nexrad-level2.s3.amazonaws.com/<yy>/<mm>/<dd>/<stationId>/<filename>', methods=['GET'])
@app.route('/https://noaa-nexrad-level2.s3.amazonaws.com/<yy>/<mm>/<dd>/<stationId>/<filename>', methods=['GET'])
def stormDetector(yy=None, mm=None, dd=None, stationId=None, filename=None):


    baseUrl = 'https://noaa-nexrad-level2.s3.amazonaws.com/'
    kmlUrl =  baseUrl + yy + '/' + mm + '/' + dd + '/' + stationId + '/' + filename


    responseCode = 200
    response = urllib.request.urlopen(kmlUrl)
    data = response.read()
    text = BeautifulSoup(data).encode('utf-8')

    kmlStream = ""

    with open('KML_Samples.kml', 'r') as f:
        kmlStream += str(f.readlines())


    list = {'param': 'byteStream', 'val': kmlStream}

    json_str = json.dumps(list)
    json_data = json.loads(json_str)

    with open('data.json', 'w') as f:
        json.dump(json_data, f)

    requestLog = Service_Requests_Log(1,datetime.datetime.now(),kmlUrl,"data.json","StormDetction")
    db.session.add(requestLog)
    db.session.commit()

    return jsonify(results=list)

if __name__ == '__main__':
    app.run(
        host="127.0.0.1",
        port=int(5000),
        debug=True
    )