package statusCode_Redirection_3xx;

import org.testng.annotations.Test;

import com.util.TestBase;

import io.restassured.response.Response;


import static io.restassured.RestAssured.given;;

public class Test_3xx extends TestBase{


	@Test
	private void validate_300_multipleChoices() {
		/*
		 * The target resource has " more than one representation", 
		 * each with its own more specific identifier, and information about the alternatives is being provided 
		 * so that the user (or user agent) can select a preferred representation 
		 * by redirecting its request to one or more of those identifiers.
		 * 
		 * If the server has a preferred choice, 
		 * the server SHOULD generate a Location header field containing a preferred choice's URI reference. 
		 * The user agent MAY use the Location field value for automatic redirection.
		 * 
		 * */
		requestSpecBuild.basePath("300").log().all();
		Response respVal = given().spec(requestSpecBuild).when().get();
		System.out.println("content type >>"+respVal.getContentType());
		System.out.println("content ststus code >>"+respVal.getStatusCode());
		System.out.println("content sttus text >>"+respVal.getStatusLine());
		System.out.println("content type >> \n "+respVal.getBody().prettyPrint());
		System.out.println("remote add >>"+respVal.getHeader("Remote Address"));
		System.out.println("ALL header >> \n"+respVal.headers());

	}

	@Test
	private void validate_301_movedPermantly() {

		/*
		 * The target resource has been assigned a "new permanent URI" and any future references to "this resource ought to use one of the enclosed URIs".
		 * 
		 * The server SHOULD generate a "Location header field" in the response 'containing a preferred URI reference for the new permanent URI'.
		 * The user agent MAY use the Location field value for automatic redirection. 
		 * The server's response payload usually contains a short hypertext note with a hyperlink to the new URI(s).
		 * 
		 * End user agent/user can only got newly permamnt url and corresponding responsed meta-data and body.
		 * 
		 * The Location response header indicates the URL to redirect a page to. It only provides a meaning when served with a 3xx (redirection) or 201 (created) status response.
		 * 
		 * Note: For historical reasons, a user agent MAY change the request method from POST to GET for the subsequent request. 
		 * 			If this behavior is undesired, the 307 Temporary Redirect status code can be used instead.
		 * 
		 * NOTE--> we can validate such request over api for chache and cocckies setted on first call anf maintains all over next calls.
		 * NOTE --> on api level , we cann't cover api's response for 301
		 * */

		requestSpecBuild.basePath("301");

		Response resp301 = given().spec(requestSpecBuild).when().get();
		System.out.println("status code -->"+resp301.statusCode());
		System.out.println("status line -->"+resp301.statusLine());
		System.out.println("redierctive uri <header location val -->"+resp301.getHeader("Location"));
		//		System.out.println("reponse body --> \n"+resp301.body().prettyPrint());
		System.out.println("logs -> \n"+resp301.then().log().all());



	}


	@Test
	private void validate_302_Found() {

		/*
		 * The target resource resides temporarily under a different URI. 
		 * Since the redirection might be altered on occasion, 
		 * the client ought to continue to use the effective request URI for future requests.
		 * 
		 * The server SHOULD generate a Location header field in the response containing a URI reference for the different URI. 
		 * The user agent MAY use the Location field value for automatic redirection. 
		 * The server's response payload usually contains a short hypertext note with a hyperlink to the different URI(s).
		 * 
		 * NOTE--> we can validate such request over api for chache and cocckies setted on first call anf maintains all over next calls. 
		 * NOTE = End user agent/user can only got newly permamnt url and corresponding responsed meta-data and body.
		 * NOTE --> on api level , we cann't cover api's response for 302
		 * 
		 * */

		requestSpecBuild.basePath("302").log().all();
		

		Response resp302 = given().spec(requestSpecBuild).when().get();
		System.out.println(".........................");
		System.out.println("status code -->"+resp302.statusCode());
		System.out.println("status line -->"+resp302.statusLine());
		System.out.println("redierctive uri <header location val -->"+resp302.getHeader("Location"));
		System.out.println("cockies -->"+resp302.header("Set-Cookie"));
		//		System.out.println("reponse body --> \n"+resp301.body().prettyPrint());
		resp302.then().log().all();

	}
	
	@Test
	private void validate_303_seeOther() {
	
		/*
		 * The server is redirecting the user agent to a different resource, 
		 * as indicated by a URI in the Location header field, 
		 * which is intended to provide an indirect response to the original request.
		 * 
		 * A 303 response to a GET request indicates that the origin server does not have a representation of the target resource that can be transferred by the server over HTTP.
		 * 
		 * NOTE--> we can validate such request over api for chache and cocckies setted on first call anf maintains all over next calls. 
		 * NOTE = End user agent/user can only got newly permamnt url and corresponding responsed meta-data and body.
		 * NOTE --> on api level , we cann't cover api's response for 302
		 * */

		requestSpecBuild.basePath("303").log().all();
		
		Response resp303 = given().when().spec(requestSpecBuild).get();
		System.out.println("...............");
		System.out.println("respo. code -->"+resp303.statusCode());
		System.out.println("respo line-->"+resp303.statusLine());
		System.out.println("location in header -->"+resp303.getHeader("Location"));
		System.out.println("cockies -->"+resp303.header("Set-Cookie"));
		resp303.then().log().all();
		
		
	}

}
