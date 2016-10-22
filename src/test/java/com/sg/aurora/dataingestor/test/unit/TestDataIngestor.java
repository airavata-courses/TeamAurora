package com.sg.aurora.dataingestor.test.unit;

import java.sql.SQLException;
import java.text.ParseException;

import org.junit.Assert;
import org.junit.Test;

import com.sg.aurora.dataingestor.rest.model.URLData;
import com.sg.aurora.dataingestor.rest.model.URLFormData;
import com.sg.aurora.dataingestor.rest.resources.URLCreation;



/**
 * Created by Pratik Sanghvi on 10/21/16.
 */
public class TestDataIngestor {

    @Test
    public void testOutput()
    {
    	//Preparing input for the service.
    	String station = "KIND";
    	String date = "2016/09/05";
    	String time = "21:30:03";
    	int userId = 1;
    	int requestId = 1;
    	URLFormData urlFormData = new URLFormData(station, date, time, userId, requestId);
    	
    	URLCreation dataIngestor = new URLCreation();
    	URLData urldata = new URLData();
    	try {
    		urldata = dataIngestor.getURL(urlFormData);
		} catch (ParseException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	
    	Assert.assertNotNull(urldata.getUrl());

    }


}