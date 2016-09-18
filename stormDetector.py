from flask import Flask, jsonify
from flask import request
from flask import make_response
import random
import urllib.request as read

app = Flask(__name__)


@app.route('/') 
@app.route('/noaa-nexrad-level2.s3.amazonaws.com/<yy>/<mm>/<dd>/<stationId>/<filename>', methods=['GET'])
@app.route('/https://noaa-nexrad-level2.s3.amazonaws.com/<yy>/<mm>/<dd>/<stationId>/<filename>', methods=['GET'])
def stormDetector(yy=None, mm=None, dd=None, stationId=None, filename=None):

   
    f = open('test.txt','w')
    f.write(yy)
    f.write(mm)
    f.write(dd)
    f.write(stationId)
    f.write(filename)
    f.close()

    list = [
        {'param': 'foo', 'val': stationId},
        {'param': 'bar', 'val': filename}
    ]
        #flag = random.getrandbits(1)
    return jsonify(results=list)

if __name__ == '__main__':
    app.run(
        host="0.0.0.0",
        #port=int(5000),
        #debug=True
    )