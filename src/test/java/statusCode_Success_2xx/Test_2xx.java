package statusCode_Success_2xx;

import org.testng.annotations.Test;

import com.util.TestBase;

import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static io.restassured.RestAssured.given;

public class Test_2xx extends TestBase{

	@Test
	private void validate_200_Ok() {

		/*
		 * ---------------------------------------------------------------------------------
		 * The client srequest has succeeded. 
		 * The information returned with the response is dependent on the method used in the request,
		 * -----------------------------------------------------------------------------------
		 * */

		requestSpecBuild.basePath("200");
		requestSpecBuild.log().all();

		given().spec(requestSpecBuild).when().get().then().assertThat().statusCode(200);
	}


	@Test
	private void validate_201_Created() {

		/*
		 * ---------------------------------------------------------------------------------
		 * The request has been fulfilled and has resulted in one or more new resources being created.
		 *The primary resource created by the request is identified by either a Location header field in the response or, 
		 *if no Location field is received, by the effective request URI.
		 * -----------------------------------------------------------------------------------
		 * */

		requestSpecBuild.basePath("201");

		given().spec(requestSpecBuild).when().get().then().assertThat().statusCode(201).log().all(true);


	}

	@Test
	private void validate_202_Accepetd() {

		/*
		 * ---------------------------------------------------------------------------------
		 *The request has been accepted for processing, but,
		 *The 202 response is intentionally noncommittal. 
		 *Its purpose is to allow a server to accept a request for some other process 
		 *(perhaps a batch-oriented process that is only run once per day) without requiring that 
		 *the user agent's connection to the server persist until the process is completed.
		 * -----------------------------------------------------------------------------------
		 * */
		requestSpecBuild.basePath("202").log().all();

		given().spec(requestSpecBuild).when().get().then().assertThat().statusCode(202).log().all();
	}

	@Test
	private void validate_203_NonAuthinfo() {

		/*
		 * ---------------------------------------------------------------------------------
		 *The request was successful but the enclosed payload has been modified from that of the origin server's 200 OK response by a transforming proxy
		 *This status code allows the proxy to notify recipients when a transformation has been applied, 
		 *since that knowledge might impact later decisions regarding the content
		 *A 203 response is cacheable by default
		 *The 203 response is similar to the Warning code of 214 Transformation Applied2, 
		 *which has the advantage of being applicable to responses with any status code
		 *
		 *
		 *The returned metainformation in the entity-header is not the definitive set as available from the origin server, 
		 *but is gathered from a local or a third-party copy. The set presented MAY be a subset or superset of the original version.
		 *
		 *
		 *WHAT : Transformations
		 *
		 *Some intermediaries include features for transforming messages and
//		   their payloads.  A proxy might, for example, convert between image
//		   formats in order to save cache space or to reduce the amount of
//		   traffic on a slow link.  However, operational problems might occur
//		   when these transformations are applied to payloads intended for
//		   critical applications, such as medical imaging or scientific data
//		   analysis, particularly when integrity checks or digital signatures
//		   are used to ensure that the payload received is identical to the
//		   original.
//		
//		   An HTTP-to-HTTP proxy is called a "transforming proxy" if it is
//		   designed or configured to modify messages in a semantically
//		   meaningful way (i.e., modifications, beyond those required by normal
//		   HTTP processing, that change the message in a way that would be
//		   significant to the original sender or potentially significant to
//		   downstream recipients).
		 * -----------------------------------------------------------------------------------
		 * */
		requestSpecBuild.basePath("203").log().all();
		given().spec(requestSpecBuild).when().get().then().assertThat().statusCode(203).log().all();
		

	}

	

	@Test
	private void validate_204_NoContent() {

		/*
		 * ---------------------------------------------------------------------------------
		 *The server has successfully fulfilled the request and that there is no additional content to send in the response payload body.
		 *
		 *Metadata in the response header fields refer to the target resource and its selected representation after the requested action was applied.
		 *For example, 
		 *if a 204 status code is received in response to a PUT request and 
		 *the response contains an ETag header field, then the PUT was successful and 
		 *the ETag field-value contains the entity-tag for the new representation of that target resource.
		 *
		 *A 204 response is catchable by default;
		 *
		 *The 204 response allows a server to indicate that the action has been successfully applied to the target resource, 
		 *while implying that the user agent does not need to traverse away from its current "document view" (if any). 
		 *The server assumes that the user agent will provide some indication of the success to its user, 
		 *in accord with its own interface, and apply any new or updated metadata in the response to its active representation.
		 * -----------------------------------------------------------------------------------
		 * */
		requestSpecBuild.basePath("204").log().all();
		given().spec(requestSpecBuild).when().get().then().assertThat().statusCode(204).log().all();
		
		
	}
	
	@Test
	private void validate_205_ResetContent() {

		/*
		 * The server has fulfilled the request and desires that the user agent reset the "document view", 
		 * which caused the request to be sent, to its original state as received from the origin server.
		 * 
		 * This response is intended to support a common data entry use case 
		 * where the user receives content that supports data entry (a form, notepad, canvas, etc.), enters 
		 * or manipulates data in that space, causes the entered data to be submitted in a request, 
		 * and then the data entry mechanism is reset for the next entry so that the user can easily initiate another input action.
		 * 
		 * Understanding : 
		 * Some time user put un-relevent/non prefreered rentries over request-body/form-parm/meta-info , and 
		 * server does autocorrection while validating on same request as response for same requste uri / endpoint state , adding a corrective preferable data respect to entries points and 
		 * user/user agent should re-request/foreward request agian for 100 % as conmplaince request-response
		 *
		 * NO caching for 205		 
		 * * */
		requestSpecBuild.basePath("205").log().all();
		
		 Response respoVal = given().spec(requestSpecBuild).when().get().thenReturn();
		System.out.println("coockies details--> coookies are setted heere....");
		System.out.println(respoVal.cookies().toString());
		System.out.println("entri set-->");
		System.out.println(respoVal.cookies().entrySet().isEmpty());
		System.out.println(respoVal.cookies().entrySet().toString());
		System.out.println("entri set valuies-->");
		System.out.println(respoVal.cookies().values().toString());
		
	}
	
	@Test
	private void validate_206_PartialContent() {
		
		/*
		 * ---------------------------------------------------------------------------------
		 *The server is successfully fulfilling a range request for the target resource by 
		 *transferring one or more parts of the selected representation that correspond to 
		 *the satisfiable ranges found in the request's Range header field
		 *
		 *If a single part is being transferred, 
		 *the server generating the 206 response MUST generate a Content-Range header field, 
		 *describing what range of the selected representation is enclosed, and a payload consisting of the range. For example:
		 *
		**	HTTP/1.1 206 Partial Content
		**	Date: Wed, 15 Nov 1995 06:25:24 GMT
		**	Last-Modified: Wed, 15 Nov 1995 04:58:08 GMT
		**	Content-Range: bytes 21010-47021/47022
		**	Content-Length: 26012
		**	Content-Type: image/gif
		**	... 26012 bytes of partial image data ...
		 *
		 *If multiple parts are being transferred, 
		 *the server generating the 206 response MUST generate a "multipart/byteranges" payload2, 
		 *and a Content-Type header field containing the multipart/byteranges media type and its required boundary parameter. 
		 *
		 *To avoid confusion with single-part responses, 
		 *a server MUST NOT generate a Content-Range header field in the HTTP header section of a multiple part response (this field will be sent in each part instead).
		 *
		 *
		 *	HTTP/1.1 206 Partial Content
		**	Date: Wed, 15 Nov 1995 06:25:24 GMT
		**	Last-Modified: Wed, 15 Nov 1995 04:58:08 GMT
		**	Content-Length: 1741
		**	Content-Type: multipart/byteranges; boundary=THIS_STRING_SEPARATES
		**
		**	--THIS_STRING_SEPARATES
		**	Content-Type: application/pdf
		**	Content-Range: bytes 500-999/8000
		**
		**	...the first range...
		**	--THIS_STRING_SEPARATES
		**	Content-Type: application/pdf
		**	Content-Range: bytes 7000-7999/8000
		**
		**	...the second range
		**	--THIS_STRING_SEPARATES--
		 *
		 * -----------------------------------------------------------------------------------
		 * */
		requestSpecBuild.basePath("206").log().all();
		Response respVal = given().spec(requestSpecBuild).when().get().thenReturn();
		
		System.out.println("response status No->"+respVal.statusCode());
		System.out.println("response status text->"+respVal.statusLine());
		System.out.println("response content type ->"+respVal.getContentType());
		System.out.println("response Content-Length->"+respVal.getHeader("Content-Length"));
		System.out.println("response Content-Range->"+respVal.getHeader("Content-Range"));
		

	}

	@Test
	private void validate_207_multiStatus() {

		/*
		 * ---------------------------------------------------------------------------------
		 *A Multi-Status response conveys information about multiple resources in situations where multiple status codes might be appropriate.
		 *
		 *The default Multi-Status response body is a text/xml or application/xml HTTP entity with a 'multistatus' root element. 
		 *Further elements contain 200, 300, 400, and 500 series status codes generated during the method invocation. 
		 *100 series status codes SHOULD NOT be recorded in a 'response' XML element.
		 * -----------------------------------------------------------------------------------
		 * */
		
		
	}
	
	@Test
	private void validate_208_alreadyReported() {
	
		/*Used inside a DAV: propstat response element to avoid enumerating the internal members of multiple bindings to the same collection repeatedly.
		 * 
		 * For each binding to a collection inside the request's scope, only one will be reported with a 200 status, 
		 * while subsequent DAV:response elements for all other bindings will use the 208 status, and no DAV:response elements for their descendants are included.
		 *
		 * Note that the 208 status will only occur for "Depth: infinity" requests, 
		 * and that it is of particular importance when the multiple collection bindings cause a bind loop1.
		 * 
		 * For backward compatibility with clients not aware of the 208 status code appearing in multistatus response bodies, 
		 * it SHOULD NOT be used unless the client has signaled support for this specification using the "DAV" request header2. 
		 * Instead, a ""508" Loop Detected status should be returned" when a binding loop is discovered.
		 * 
		 * */

	}

}
