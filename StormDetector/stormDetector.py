import urllib.error
import urllib.request
import datetime
from bs4 import BeautifulSoup
from flask_sqlalchemy import SQLAlchemy
from flask import Flask, jsonify, request,json


app = Flask(__name__)
app.debug = True
app.config['SQLALCHEMY_DATABASE_URI'] = 'postgresql://dbadmin:teamauroradbadmin@teamaurora-db.cfofssvi9hbo.us-west-2.rds.amazonaws.com/sg_teamaurora_db'
app.config['SQLALCHEMY_ECHO'] = True
db = SQLAlchemy(app)

class Service_Requests_Log(db.Model):
    #RequestID, TimeStamp, Input, Output , Service
    __tablename__ = 'service_requests_logger'
    id = db.Column(db.Integer, primary_key=True)
    request_id = db.Column(db.Integer)
    user_id = db.Column(db.Integer)
    timestamp = db.Column(db.DateTime)
    input = db.Column(db.String(80))
    output = db.Column(db.String(80))
    service_name = db.Column(db.String(40))

    db.PrimaryKeyConstraint('id')

    def __init__(self,request_id, userId, timestamp, Input, Output, Service):
        self.timestamp = timestamp
        self.request_id = request_id
        self.user_id = userId
        self.input = Input
        self.output = Output
        self.service_name = Service

    def __repr__(self):
        return self.request_id

@app.route('/')
@app.route('/StormDetector' , methods=['GET', 'POST'])
@app.route('/noaa-nexrad-level2.s3.amazonaws.com/<yy>/<mm>/<dd>/<stationId>/<filename>', methods=['GET'])
@app.route('/https://noaa-nexrad-level2.s3.amazonaws.com/<yy>/<mm>/<dd>/<stationId>/<filename>', methods=['GET'])
def stormDetector(yy=None, mm=None, dd=None, stationId=None, filename=None):

    apiData = request.json

    nexradUrl = apiData['url']
    requestID = apiData['requestId']
    userid = apiData['userId']

    baseUrl = 'https://noaa-nexrad-level2.s3.amazonaws.com/'
    #kmlUrl =  baseUrl + yy + '/' + mm + '/' + dd + '/' + stationId + '/' + filename


    responseCode = 200
    response = urllib.request.urlopen(nexradUrl)
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



    #requestLog = Service_Requests_Log(requestID,userid,datetime.datetime.now(),nexradUrl,"data.json","StormDetction")
    #db.session.add(requestLog)
    #db.session.commit()



    return jsonify(results=list)

if __name__ == '__main__':
    app.run(
        host="0.0.0.0",
        port=int(60608),
        #debug=True
    )
