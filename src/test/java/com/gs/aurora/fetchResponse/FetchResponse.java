package com.gs.aurora.fetchResponse;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.Response;

public class FetchResponse {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		Client client=ClientBuilder.newClient();
		
		Response response=client.target("http://localhost:9090/dataIngestor/webapi/urlgen").request().get();
		String str=response.readEntity(String.class);
		System.out.println(str);
		
		
		

	}

}
