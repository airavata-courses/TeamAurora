from flask import Flask, render_template, request, jsonify
import json

app = Flask(__name__)

@app.route('/StormClustering', methods=['GET', 'POST'])
def stormClustering(req=None):


    with open('data.json', 'r') as f:
        data = json.load(f)

    f.close()
    kmlStream = data['val']

    responseCode = 200

    list = [
        {'param': 'responseCode', 'val': responseCode},
        {'param': 'byteStream',   'val': kmlStream}
    ]

    return jsonify(results=list)

if __name__ == '__main__':
    app.run(
        host="127.0.0.1",
        port=int(5000),
        #debug=True
    )