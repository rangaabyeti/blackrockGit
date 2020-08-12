package Test;

import org.testng.Assert;
import org.testng.Reporter;
import org.testng.annotations.Test;
import java.io.IOException;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.ContentType;
import org.apache.http.impl.client.HttpClientBuilder;
import org.testng.annotations.Test;

public class RestApiWithJava {
	
	@Test
	public void givenUserDoesNotExists_whenUserInfoIsRetrieved_then404IsReceived()
	  throws ClientProtocolException, IOException {
	 
	    // Given
	    String name = RandomStringUtils.randomAlphabetic( 8 );
	    HttpUriRequest request = new HttpGet( "https://api.github.com/users/" + name );
	    
	    // When
	    HttpResponse httpResponse = HttpClientBuilder.create().build().execute( request );
	 
	    // Then
	    int statusCode1 = httpResponse.getStatusLine().getStatusCode();
	    int statusCode2 = HttpStatus.SC_NOT_FOUND;
	    
	    Reporter.log("HttpRespone" + statusCode1);
	    Assert.assertEquals(statusCode1, statusCode2);
	    
	}
	
	
	@Test
	public void givenRequestWithNoAcceptHeader_whenRequestIsExecuted_thenDefaultResponseContentTypeIsJson()
	  throws ClientProtocolException, IOException {
	 
	   // Given
	   String jsonMimeType = "application/json";
	   HttpUriRequest request = new HttpGet( "https://api.github.com/users/eugenp" );
	 
	   // When
	   HttpResponse response = HttpClientBuilder.create().build().execute( request );
	 
	   // Then
	   String mimeType = ContentType.getOrDefault(response.getEntity()).getMimeType();
	   Assert.assertEquals( jsonMimeType, mimeType );
	}

}
