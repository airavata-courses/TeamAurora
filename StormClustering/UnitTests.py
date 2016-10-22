import json
import StormClustering
import nose
from nose.tools import assert_equal

app = StormClustering.app.test_client()

def test_root():
    response = app.get('/')

    assert_equal(response.status_code, 200)
    assert_equal(response.data, "REST API for getting the files from AWS Nexrad")

def test_getlink_success():
    response = app.get('/StormClustering')
    assert_equal(response.status_code, 200)


def test_getlink_error():
    expected = "Nothing exists matching the given parameters"
    response = app.get('/StormClustering')
    assert_equal(response.status_code, 200)
    link = json.loads(response.data)['error']
    assert_equal(link,expected)
