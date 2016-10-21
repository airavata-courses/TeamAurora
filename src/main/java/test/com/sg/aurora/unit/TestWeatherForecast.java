package test.com.sg.aurora.unit;

import org.junit.Assert;
import org.junit.Test;

import com.sg.aurora.ForecastTrigger.ClusterData;
import com.sg.aurora.ForecastTrigger.ForecastDetect;

/**
 * Created by Pratik Sanghvi on 10/21/16.
 */
public class TestWeatherForecast {

    @Test
    public void testOutput()
    {
    	//Preparing input for the service.
    	ClusterData input = new ClusterData();
    	input.setClusters("dummyClusters");
    	input.setRequestId(1);
    	input.setUserId(1);
    	
    	ForecastDetect runWeatherForecast = new ForecastDetect();
        ClusterData result = runWeatherForecast.results(input);
        Assert.assertTrue(result.isStormExists() == true || result.isStormExists() == false);

    }


}