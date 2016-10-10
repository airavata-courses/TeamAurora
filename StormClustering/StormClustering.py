import datetime
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

    def __init__(self,request_id, user_id, timestamp, Input, Output, Service):
        self.timestamp = timestamp
        self.request_id = request_id
        self.user_id = user_id
        self.input = Input
        self.output = Output
        self.service_name = Service

    def __repr__(self):
        return self.request_id

@app.route('/StormClustering', methods=['GET', 'POST'])
def stormClustering():

    data = request.json

    kmlStream = str(data['results']['val'])
    userId = str(data['userId'])
    requestId = str(data['requestId'])

    with open('test.txt' , 'w') as f:
        f.write(str(kmlStream))


    responseCode = 200

    list =   {'param': 'byteStream',   'cluster': "dummy clusters"}

    #requestLog = Service_Requests_Log(requestId,userId, datetime.datetime.now(),"data.json","clustering","StormClustering")
    #db.session.add(requestLog)
    #db.session.commit()

    return jsonify(results=list)

if __name__ == '__main__':
    app.run(
        #host="0.0.0.0",
        port=int(60615),
        #debug=True
    )
