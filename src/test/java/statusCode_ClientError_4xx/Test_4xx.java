package statusCode_ClientError_4xx;

import static io.restassured.RestAssured.given;

import org.testng.annotations.Test;

import com.util.TestBase;

import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestLogSpecification;

public class Test_4xx extends TestBase{


	@Test
	private void validate_400_badRequest() {

		/*
		 * The server cannot or will not process the request due to something that is perceived to be a client error 
		 * (e.g., malformed request syntax, invalid request message framing, or deceptive request routing).
		 * */

		requestSpecBuild.basePath("400").log().all();

		Response respo400 = given().spec(requestSpecBuild).when().get();

		System.out.println("status code -->"+respo400.statusCode());
		System.out.println("status line -->"+respo400.statusLine());
		System.out.println("body  --> \n"+respo400.body().prettyPrint());

		respo400.then().log().all();


	}


	@Test
	private void validate_401_unauthorized() {

		/*
		 * The request has not been applied because it lacks valid authentication credentials for the target resource.
		 * 
		 * The server generating a 401 response MUST send a " WWW-Authenticate header " field1 containing at least one challenge applicable to the target resource.
		 * 
		 * If the request included authentication credentials, 
		 * then the 401 response indicates that authorization has been refused for those credentials. 
		 * The user agent MAY repeat the request with a new or replaced Authorization header field2. 
		 * 
		 * If the 401 response contains the same challenge as the prior response, 
		 * and the user agent has already attempted authentication at least once, 
		 * then the user agent SHOULD present the enclosed representation to the user, since it usually contains relevant diagnostic information.
		 * */

		requestSpecBuild.basePath("401").log().all();

		Response respo401 = given().spec(requestSpecBuild).when().get();

		System.out.println("status code -->"+respo401.statusCode());
		System.out.println("status line -->"+respo401.statusLine());
		System.out.println("www authetication header -->"+respo401.header("WWW-Authenticate"));
		System.out.println("body  --> \n"+respo401.body().prettyPrint());


	}


	@Test
	private void validate_402_paymentRequired() {

		/*Reserved for future use.*/

		requestSpecBuild.basePath("402").log().all();

		Response respo402 = given().spec(requestSpecBuild).when().get();

		System.out.println("status code -->"+respo402.statusCode());
		System.out.println("status line -->"+respo402.statusLine());

		System.out.println("body  --> \n"+respo402.body().prettyPrint());

	}

	@Test
	private void validate_403_forbidden() {

		/*
		 * The server understood the request but refuses to authorize it.
		 * 
		 * A server that wishes to make public why the request has been forbidden can describe that reason in the response payload (if any).
		 * An origin server that wishes to "hide" the current existence of a forbidden target resource MAY instead respond with a status code of 404 Not Found.
		 * 
		 * NOTE- response body need to verify for type of refusion from server.
		 * */

		requestSpecBuild.basePath("403").log().all();

		Response respo403 = given().spec(requestSpecBuild).when().get();

		System.out.println("status code -->"+respo403.statusCode());
		System.out.println("status line -->"+respo403.statusLine());

		System.out.println("body detail maassge for refuse state --> \n"+respo403.body().prettyPrint());
	}

	@Test
	private void validate_404_notFound() {


		/*
		 * The origin server did not find a current representation for the target resource or is not willing to disclose that one exists.
		 * A 404 response is cacheable by default; i.e., unless otherwise indicated by the method definition or explicit cache controls1.
		 * 
		 * A 404 status code does not indicate whether this lack of representation is temporary or permanent; 
		 * the 410 Gone status code is preferred over 404 if the origin server knows, 
		 * presumably through some configurable means, that the condition is likely to be permanent
		 * 
		 *NOTE -->Need to validate a cache also. 
		 * 
		 * */

		requestSpecBuild.basePath("404").log().all();

		Response respo404 = given().spec(requestSpecBuild).when().get();

		System.out.println("status code -->"+respo404.statusCode());
		System.out.println("status line -->"+respo404.statusLine());
		System.out.println("headers :"+ respo404.headers());
		System.out.println("header chacheed/coockies chached  :" + respo404.headers().getValue("Set-Cookie"));

		System.out.println("body detail maassge for not found state --> \n"+respo404.body().prettyPrint());

	}

	@Test
	private void validate_405_methodNotAllowed() {

		/*
		 * The method received in the request-line is known by the origin server but not supported by the target resource.
		 * The origin server MUST generate an Allow header field in a 405 response containing a list of the target resource's currently supported methods.
		 * A 405 response is cacheable by default.
		 * 
		 * */

		requestSpecBuild.basePath("405").log().all();
		Response respo405 = given().spec(requestSpecBuild).when().get();

		System.out.println("status code -->"+respo405.statusCode());
		System.out.println("status line -->"+respo405.statusLine());
		System.out.println("headers :\n"+ respo405.headers());
		System.out.println("header chacheed/coockies chached  :" + respo405.headers().getValue("Set-Cookie"));

		System.out.println(" Allow header field for public acces --> " + respo405.headers().getValue("Access-Control-Allow-Origin"));
		System.out.println("body:\n"+respo405.body().prettyPrint());


	}

	@Test
	private void validate_406_notAcceptable() {

		/*
		 * The target resource does not have a current representation that would be acceptable to the user agent, 
		 * according to the proactive negotiation header fields received in the request1, 
		 * and the server is unwilling to supply a default representation.
		 * 
		 * The server SHOULD generate a payload containing a list of available representation characteristics 
		 * and corresponding resource identifiers from which the user or user agent can choose the one most appropriate. 
		 * A user agent MAY automatically select the most appropriate choice from that list
		 * 
		 * 
		 * NOTE :
		 * need to validate request content type and response content type or options provide in payload : It should not same
		 * 
		 * */

		requestSpecBuild.basePath("406").log().all();


		Response respo406 = given().spec(requestSpecBuild).when().get();

		System.out.println("status code -->"+respo406.statusCode());
		System.out.println("status line -->"+respo406.statusLine());
		System.out.println("headers :\n"+ respo406.headers());
		System.out.println("header chacheed/coockies chached  :" + respo406.headers().getValue("Accept"));

		System.out.println("header Content-Type --s  :" + respo406.headers().getValue("Content-Type"));

		//want  avalidation of req.accept/content-Type with erspo.content-type

	}

	@Test
	private void validate_407_proxyAuthenticationRequired() {

		/*
		 * Similar to 401 Unauthorized, but it indicates that the client needs to authenticate itself in order to use a proxy.
		 * 
		 * The proxy MUST send a "Proxy-Authenticate header" field1 containing a challenge applicable to that proxy for the target resource. 
		 * The client MAY repeat the request with a new or replaced Proxy-Authorization header field2.
		 * 
		 * NOTE : 
		 * 	common error thoughs :ERR_UNEXPECTED_PROXY_AUTH
		 * */

		requestSpecBuild.basePath("407").log().all();

		Response respo407 = given().spec(requestSpecBuild).when().get();

		System.out.println("status code -->"+respo407.statusCode());
		System.out.println("status line -->"+respo407.statusLine());
		System.out.println("headers :\n"+ respo407.headers());
		System.out.println("header  Proxy-Authenticate :"+respo407.header("Proxy-Authenticate"));
		System.out.println("---------------");
		System.out.println("body:\n"+respo407.body().prettyPrint());

		System.out.println("--------||||||||||-------");
//		Header name = new Header("Authorization", "Basic realm=\"Fake Realm\"");
		Header name = new Header("Proxy-Authenticate", "Basic realm=\"Fake Realm\"");
		given().spec(requestSpecBuild).header(name).when().get().then().log().all();
	}

	@Test
	private void validate_408_requestTime() {
	
		/*
		 * The server did not receive a complete request message within the time that it was prepared to wait.
		 * 
		 * A server SHOULD send the "close" connection option1 in the response, 
		 * since 408 implies that the server has decided to close the connection rather than continue waiting. 
		 * If the client has an outstanding request in transit, the client MAY repeat that request on a new connection.
		 * 
		 * */

		requestSpecBuild.basePath("408").log().all();

		Response respo408 = given().spec(requestSpecBuild).when().get();
		
		System.out.println("status code -->"+respo408.statusCode());
		System.out.println("status line -->"+respo408.statusLine());
		System.out.println("---------------");
		System.out.println("headers :\n"+ respo408.headers());
		System.out.println("---------------");
		System.out.println("header  Connection value: :"+respo408.header("Connection"));
		System.out.println("---------------");
		System.out.println("body:\n"+respo408.body().prettyPrint());
		
	}
	
	@Test
	private void validate_409_conflict() {
		
		/*
		 * The request could not be completed due to a conflict with the current state of the target resource. 
		 * This code is used in situations where the user might be able to resolve the conflict and resubmit the request.
		 * 
		 * The server SHOULD generate a payload that includes enough information for a user to recognize the source of the conflict.
		 * 
		 * Conflicts are most likely to occur in response to a PUT request. 
		 * For example, 
		 * if versioning were being used and the representation being PUT included changes to
		 *  a resource that conflict with those made by an earlier (third-party) request, 
		 *  the origin server might use a 409 response to indicate that it can't complete the request. 
		 *  In this case, the response representation would likely contain information useful for merging the differences based on the revision history.
		 * 
		 * NOTE : this type status code mostly assign over hTTP method "PUT" calls
		 * */
		requestSpecBuild.basePath("409").log().all();

		Response respo409 = given().spec(requestSpecBuild).when().get();
		
		System.out.println("status code -->"+respo409.statusCode());
		System.out.println("status line -->"+respo409.statusLine());
		System.out.println("---------------");
		
		System.out.println("---------------");
		
		System.out.println("---------------");
		System.out.println("body:\n"+respo409.body().prettyPrint());
	

	}
	
	
	@Test
	private void validate_410_gone() {
	
		/*
		 * The target resource is no longer available at the origin server and that this condition is likely to be permanent.
		 * 
		 * he 410 response is primarily intended to assist the task of web maintenance by notifying the recipient 
		 * that the resource is intentionally unavailable and that the server owners desire that remote links to that resource be removed. 
		 * Such an event is common for limited-time, promotional services and for resources belonging to individuals no longer associated with the origin server's site
		 * 
		 * NOTE: A 410 response is cacheable by default;
		 * 
		 * If the origin server does not know, or has no facility to determine, 
		 * whether or not the condition is permanent, the status code 404 Not Found ought to be used instead.
		 * */
		
		requestSpecBuild.basePath("410").log().all();

		Response respo410 = given().spec(requestSpecBuild).when().get();
		
		System.out.println("status code -->"+respo410.statusCode());
		System.out.println("status line -->"+respo410.statusLine());
		System.out.println("---------------");
		System.out.println("respo headers: \n"+respo410.headers());
		System.out.println("---------------");
		System.out.println("cachable coockies --> "+respo410.header("Set-Coockies"));
		System.out.println("---------------");
		System.out.println("body:\n"+respo410.body().prettyPrint());

	}

}
