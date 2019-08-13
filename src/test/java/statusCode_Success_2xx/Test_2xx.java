package statusCode_Success_2xx;

import org.testng.annotations.Test;

import com.util.TestBase;
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
		 *A 204 response is cacheable by default;
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


}
