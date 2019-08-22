package statusCode_Redirection_3xx;

import org.testng.annotations.Test;

import com.util.TestBase;

import io.restassured.RestAssured;
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


	@Test
	private void validate_304_notModified() {

		/*
		 * In other words, 
		 * there is no need for the server to transfer a representation of the target resource because the request indicates that the client, 
		 * which made the request conditional, 
		 * already has a valid representation; 
		 * the server is therefore redirecting the client to make use of that stored representation as if it were the payload of a 200 OK response.
		 * 
		 * The server generating a 304 response MUST generate any of the following 
		 * 		header fields that would have been sent in a 200 OK response to the same request:
		 * 			 Cache-Control, Content-Location, Date, ETag, Expires, and Vary.
		 * 
		 * 
		 * NOTE --> Since the goal of a 304 response is to minimize information transfer when the "recipient already has one or more cached representations", 
		 * 			a sender SHOULD NOT generate representation metadata other than the above listed fields 
		 * 			unless said metadata exists for the purpose of guiding cache updates (e.g., Last-Modified might be useful if the response does not have an ETag field).
		 * 
		 * NOTE--> we can validate such request over api for chache and cocckies setted on first call anf maintains all over next calls. 
		 * NOTE --> on api level , we can cover api's response for 302
		 * NOTE-->status code 304 has sent representation once in user agent and it is be cached till cookies expiration ,  whenever user agent request a same resourecess
		 * */
		requestSpecBuild.basePath("304").log().all();

		Response respo304 = given().spec(requestSpecBuild).when().get();

		System.out.println("status code -->"+respo304.statusCode());
		System.out.println("status line -->"+respo304.statusLine());
		System.out.println("cache control val --"+respo304.header("Cache-Control"));
		System.out.println("Content-Location val --"+respo304.header("Content-Location"));
		System.out.println("Date val --"+respo304.header("Date"));
		System.out.println("ETag val --"+respo304.header("ETag"));
		System.out.println("Expires val --"+respo304.header("Expires"));
		System.out.println(".............................");
		System.out.println(respo304.headers().toString());
		System.out.println(".............................");
		System.out.println(respo304.getBody().prettyPrint());
		System.out.println(".............................");
		respo304.then().log().all();


	}

	@Test
	private void validate_305_useProxy() {

		/*
		 * The requested resource MUST be accessed through the proxy given by the Location field. 
		 * The "Location" field gives the "URI of the proxy". 
		 * The recipient is expected to repeat this single request via the proxy. 
		 * 
		 * NOTE:-305 responses MUST only be generated by origin servers
		 * */

		requestSpecBuild.basePath("305").log().all();

		Response respo305 = given().spec(requestSpecBuild).when().get();

		System.out.println("ststus code -->"+respo305.statusCode());
		System.out.println("ststus line -->"+respo305.statusLine());
		System.out.println("status all headers --> \n"+respo305.headers());
		System.out.println("body lines val --> \n"+respo305.getBody().prettyPrint());
		System.out.println("...............");
		System.out.println("key store/trust store -->"+"");
		System.out.println("...............");
		System.out.println("location header val -->"+respo305.headers().getValue("Location"));
		System.out.println("---------------");

		respo305.then().log().all();

		System.out.println("----------------------->>>>>>>>>>To aviad an 305 error<<<<<<<<<<<<--------------------------");
		requestSpecBuild.proxy(respo305.headers().getValue("Location")).log().all();

		Response revisedRespo305 = given().spec(requestSpecBuild).relaxedHTTPSValidation().when().get();
//				Response revisedRespo305 = given().trustStore("D:\\Project_Pract\\esseneData4IO\\httpstatWeb.cer", "httpstatSSLpass").spec(requestSpecBuild).when().get();

//		RestAssured.useRelaxedHTTPSValidation();
//		RestAssured.config().getSSLConfig().with().keyStore("classpath:httpstatWeb.cer", "httpstatSSLpass");

//		Response revisedRespo305 = given().spec(requestSpecBuild).when().get();

		System.out.println("ststus code -->"+revisedRespo305.statusCode());
		System.out.println("ststus line -->"+revisedRespo305.statusLine());
		System.out.println("status all headers --> \n"+revisedRespo305.headers());
		System.out.println("body lines val --> \n"+respo305.getBody().prettyPrint());
		System.out.println("...............");
		System.out.println("location header val -->"+revisedRespo305.headers().getValue("Location"));
		System.out.println("---------------");
		revisedRespo305.then().log().all();

	}

	@Test
	private void validate_306_unUsed() {
	
		/*
		 * The 306 status code was used in a previous version of the specification, is no longer used, and the code is reserved.
		 * */
		
		
		requestSpecBuild.basePath("306").log().all();
		
		Response respo306 = given().spec(requestSpecBuild).when().get();
		
		System.out.println("...........");
		
		System.out.println("status code -->"+respo306.statusCode());
		System.out.println("status line -->"+respo306.statusLine());
		System.out.println("body -->"+respo306.body().prettyPrint());
		

	}
	
	@Test
	private void validate_307_tempRedirect() {
	
		/*
		 * The target resource resides temporarily under a different URI and the user agent MUST NOT change the request method 
		 * if it performs an automatic redirection to that URI.
		 * The server SHOULD generate a Location header field in the response containing a URI reference for the different URI. 
		 * The user agent MAY use the Location field value for automatic redirection.
		 * 
		 * Since the redirection can change over time, the client ought to continue using the original effective request URI for future requests.
		 * 
		 * Note: 
		 * This status code is "similar to '302 Found', except that it does not allow changing the request method from POST to GET. 
		 * 
		 * This specification defines no equivalent counterpart for '301 Moved Permanently' (RFC7238, 
		 * however, proposes defining the status code '308 Permanent Redirect' for this purpose)."
		 * 
		 * */
		
		requestSpecBuild.basePath("307").log().all();
		
		Response respo307 = given().spec(requestSpecBuild).when().get();
		
		System.out.println("");
		System.out.println("status code -->"+respo307.statusCode());
		System.out.println("status line -->"+respo307.statusLine());
		System.out.println("redirective location -->"+respo307.header("Location"));
		System.out.println("body -->"+respo307.body().prettyPrint());

	}
	
	@Test
	private void validate_308_permanantRedirect() {
	
		/*
		 * The target resource has been assigned a new permanent URI and any future references to this resource ought to use one of the enclosed URIs.
		 * 
		 * The server SHOULD generate a Location header field2 in the response containing a preferred URI reference for the new permanent URI. 
		 * The user agent MAY use the Location field value for automatic redirection.
		 * 
		 * NOTE- This respo and result set can cached in user agent once and user forever for all next request calls. 
		 * 		 BUt still api response is belongs to actual api request and not a redierctive result set.  
		 * */
		

		requestSpecBuild.basePath("308").log().all();
		
		Response respo308 = given().spec(requestSpecBuild).when().get();
		
		System.out.println("status code -->"+respo308.statusCode());
		System.out.println("status line -->"+respo308.statusLine());
		System.out.println("redirective location -->"+respo308.header("Location"));
		System.out.println("body -->"+respo308.body().prettyPrint());
		respo308.then().log().all();
		
		
	}
}
