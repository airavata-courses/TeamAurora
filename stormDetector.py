from flask import Flask, jsonify
import urllib.request
import urllib.error
from bs4 import BeautifulSoup
import json


app = Flask(__name__)


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


    list = {'param': 'byteStream',   'val': kmlStream}

    json_str = json.dumps(list)
    json_data = json.loads(json_str)

    with open('data.json', 'w') as f:
        json.dump(json_data, f)

    return jsonify(results=list)

if __name__ == '__main__':
    app.run(
        host="127.0.0.1",
        port=int(5000),
        debug=True
    )