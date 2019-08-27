package statusCode_ServerError_5xx;

import org.testng.annotations.Test;

import com.util.TestBase;

import io.restassured.response.Response;

import static io.restassured.RestAssured.given;

public class Test_5xx extends TestBase{
	
	@Test
	private void validate_500_internalServerErr() {
	
		/*
		 * The server encountered an unexpected condition that prevented it from fulfilling the request.
		 * 
		 * 
		 * */
		
		requestSpecBuild.basePath("500").log().all();

		Response respo500 = given().spec(requestSpecBuild).when().get();
		
		System.out.println("status code -->"+respo500.statusCode());
		System.out.println("status line -->"+respo500.statusLine());
		System.out.println("---------------");
		System.out.println("respo headers: \n"+respo500.headers());
		System.out.println("---------------");
		/*System.out.println("cachable coockies --> "+respo500.header("Set-Coockies"));*/
		System.out.println("---------------");
		System.out.println("body:\n"+respo500.body().prettyPrint());

	}
	
	@Test
	private void validate_502_badGateway() {
		/*
		 * The server, while acting as a gateway or proxy, 
		 * received an invalid response from an inbound server it accessed while attempting to fulfill the request.
		 * 
		 * 502 Bad Gateway server error response code indicates that the server, 
		 * while acting as a gateway or proxy, received an invalid response from the upstream server.
		 * 
		 * */
		requestSpecBuild.basePath("502").log().all();

		Response respo502 = given().spec(requestSpecBuild).when().get();
		
		System.out.println("status code -->"+respo502.statusCode());
		System.out.println("status line -->"+respo502.statusLine());
		System.out.println("---------------");
		System.out.println("respo headers: \n"+respo502.headers());
		System.out.println("---------------");
		/*System.out.println("cachable coockies --> "+respo500.header("Set-Coockies"));*/
		System.out.println("---------------");
		System.out.println("body:\n"+respo502.body().prettyPrint());
		
		
	}
	
	@Test
	private void validate_503_serviceUnavailable() {
	
		
		/*The server is currently unable to handle the request due to a temporary overload or scheduled maintenance
		 * 
		 * NOTE --> The server MAY send a Retry-After header field1 to suggest an appropriate amount of time for the client to wait before retrying the request.
		 * */
		
		requestSpecBuild.basePath("503").log().all();
		
		Response respo503 = given().when().spec(requestSpecBuild).get();
		
		System.out.println("response code :"+respo503.statusCode());
		System.out.println("response line -->"+respo503.statusLine());
		
		System.out.println("All headers :"+respo503.headers());
		
		System.out.println("-----------------");
		System.out.println("Retry-After header val :"+ respo503.headers().getValue("Retry-After"));
		System.out.println("-----------------");
		System.out.println("Retry-After header val :"+ respo503.headers().get("Retry-After"));
		System.out.println("-----------------");
		
		respo503.then().log().all();
		
	}
	
	@Test
	private void validate_504_gatewayTimeout() {
	
		
		/*The server, while acting as a gateway or proxy, 
		 * did not receive a timely response from an upstream server it needed to access in order to complete the request.
		 * 
		 * */
		
		
		requestSpecBuild.basePath("504").log().all();
		
		Response respo504 = given().when().spec(requestSpecBuild).get();
		
		System.out.println("reponse code :"+respo504.statusCode());
		System.out.println("response line :"+respo504.statusLine());
		
		System.out.println("------------------");
		System.out.println("all headers : \n"+respo504.headers());
		System.out.println("------------------");
		System.out.println("------------------");
		respo504.then().log().all();

	}
	
	@Test
	private void validate_505_httpVersionUnsupported() {
	
		/*The server does not support, or refuses to support, the major version of HTTP that was used in the request message.*/

		/*The server is indicating that it is unable or unwilling to complete the request using the same major version as the client, 
		 * as described in Section 2.6 of RFC7230, other than with this error message. 
		 * The server SHOULD generate a representation for the 505 response that describes why 
		 * that version is not supported and what other protocols are supported by that server.
		 * 
		 * 
		 * 
		 * */
		
		requestSpecBuild.basePath("505").log().everything();
		
		Response respo505 = given().when().spec(requestSpecBuild).get();
		
		System.out.println("response code :"+respo505.statusCode());
		
		System.out.println("response line :"+respo505.statusLine());
		
		System.out.println("response body :"+respo505.body().prettyPrint());
		
	}

	@Test
	private void validate_511_networkAuthenticationRequired() {
	
/*		The response representation SHOULD contain a link to a resource that allows the user to submit credentials (e.g., with an HTML form).
 * 
 * Note that the 511 response SHOULD NOT contain a challenge or the login interface itself,
 * 
 * The 511 status SHOULD NOT be generated by origin servers; 
 * it is intended for use by intercepting proxies that are interposed as a means of controlling access to the network.
 * 
 * Responses with the 511 status code MUST NOT be stored by a cache.
 * 
 * The 511 status code is designed to mitigate problems caused by "captive portals" to software 
 * (especially non-browser agents) that is expecting a response from the server 
 * that a request was made to, not the intervening network infrastructure.
 * 
 * */

		requestSpecBuild.basePath("511").log().all();
		
		Response respo511 = given().when().spec(requestSpecBuild).get();
		
		System.out.println("response code :"+respo511.getStatusCode());
		
		System.out.println("response line :"+respo511.statusLine());
		
		System.out.println("set cache coockies headers :"+respo511.headers().getValue("Set-Cookie"));
		
		System.out.println("body : \n"+respo511.body().prettyPrint());
		
		System.out.println("-----------------");
		respo511.then().log().all();
		
	}
	
	@Test
	private void validate_522_ConnectionTimedOut() {
	
		
		requestSpecBuild.basePath("522").log().all();
		
		Response respo522 = given().when().spec(requestSpecBuild).get();
		
		System.out.println("response code :"+respo522.statusCode());
		System.out.println("response line"+respo522.statusLine());
		
		System.out.println("headers :\n"+respo522.headers());
		
		System.out.println("body: \n"+respo522.body().prettyPrint());
		
		
		
		

	}
}
