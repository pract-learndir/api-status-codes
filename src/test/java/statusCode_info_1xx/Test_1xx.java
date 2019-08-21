package statusCode_info_1xx;

import org.testng.annotations.Test;
import static io.restassured.RestAssured.given;

import com.util.TestBase;

import io.restassured.response.Response;




public class Test_1xx extends TestBase{
	
	/*100 series status codes SHOULD NOT be recorded in a 'response' XML element.*/

	@Test
	public void validate100_Continue() {

		//		requestSpecBuild.basePath("/100");
		given().spec(requestSpecBuild.basePath("100")).log().everything().when().get().then().assertThat().statusCode(100).log().everything();

		/*java.lang.AssertionError: 1 expectation failed.
		Expected status code <100> doesn't match actual status code <502>.
		 */		
		//		given().when().get("/100").then().assertThat().statusCode(100);


		System.out.println("---------------------->>>>>>>>>>>>><<<<<<<<<<<<<<<<<-----------------------------------");
	}


	@Test
	private void validate_101_Switching_Protocols(){
		//		requestSpecBuild.basePath("101").log().everything();
		//		given().spec(requestSpecBuild).when().get().then().assertThat().statusCode(101);

		requestSpecBuild.basePath("101").log().everything();
		Response response = given().spec(requestSpecBuild).when().get();
		response.then().statusCode(101).log().everything();
		
		System.out.println("---------------------->>>>>>>>>>>>><<<<<<<<<<<<<<<<<-----------------------------------");

	}

	@Test
	private void validate_102_Processing() {

		requestSpecBuild.basePath("102");
		requestSpecBuild.log().everything();

		given().spec(requestSpecBuild).when().get().then().assertThat().statusCode(102).log().everything();
		System.out.println("---------------------->>>>>>>>>>>>><<<<<<<<<<<<<<<<<-----------------------------------");

	}
	
	@Test
	private void validate_103_EarlyHint() {
	
		requestSpecBuild.basePath("103");
		requestSpecBuild.log().all();
		
		given().spec(requestSpecBuild).when().get().then().assertThat().statusCode(103).log().everything();

	}

}
