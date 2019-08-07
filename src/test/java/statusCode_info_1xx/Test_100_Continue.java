package statusCode_info_1xx;

import static org.junit.Assert.assertThat;
import static org.testng.Assert.assertEquals;

import java.time.LocalDate;
import java.time.Month;

import org.apache.http.client.methods.RequestBuilder;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;


import static io.restassured.RestAssured.given;



public class Test_100_Continue {
	

	

	@Test
	public void validate_100() {
		
		RestAssured.baseURI = "https://httpstat.us";
		

		RequestSpecBuilder reqSpecBuilder = new RequestSpecBuilder();
		ResponseSpecBuilder respoSpecBuilder = new ResponseSpecBuilder();
		
		reqSpecBuilder.setAccept(ContentType.ANY);
//		reqSpecBuilder.addHeader(headerName, headerValue);
		reqSpecBuilder.setBaseUri(RestAssured.baseURI);
		
		RequestSpecification reqSpec=reqSpecBuilder.build();
		

	}
	
	public void validate_sample(RequestSpecification reqSpec) {
	

		/*final Customer newCustomer = Customer.ofType(PERSON)
				.withBirthDate(LocalDate.of(1990, Month.AUGUST, 16))
				.build();*/
		
		String newCustomer = null;
		// Create a new customer
		final Response resp = 
			given()
				.spec(reqSpec)
				.body(newCustomer)
			.when()
				.post("/customers")
			.then()
				.assertThat()
				.statusCode(201)
				.extract().response();
		
		final String newCustomerUrl = resp.header("Location");
		
		
		
		
	}
	
}
