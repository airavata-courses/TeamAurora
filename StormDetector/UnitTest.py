import json
import stormDetector
import nose
from nose.tools import assert_equal

app = stormDetector.app.test_client()

def test_root():
    response = app.get('/')

    assert_equal(response.status_code, 200)
    #assert_equal(response.data, "REST API to download the files from AWS Nexrad")

def test_getlink_success():
    
    response = app.get('/StormDetector', data=json.dumps({  "url": "https://noaa-nexrad-level2.s3.amazonaws.com/2016/09/17/KIND/KIND20160917_000007_V06", "usedId" : "1", "requestId" : "4001"}), content_type='application/json')
    assert_equal(response.status_code, 200)


def test_getlink_error():
    expected = "Nothing exists matching the given parameters"
    response = app.get('/StormDetector', data=json.dumps({  "url": "https://noaa-nexrad-level2.s3.amazonaws.com/2016/09/17/KIND/KIND20160917_000007_V06", "usedId" : "1", "requestId" : "4001"}), content_type='application/json')
    assert_equal(response.status_code, 200)
    


    
