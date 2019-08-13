package com.util;

import org.apache.http.client.methods.RequestBuilder;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import groovy.transform.BaseScript;
import io.restassured.RestAssured;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class TestBase {
	public  RequestSpecification requestSpecBuild ;
	public  ResponseSpecification responseSpecBuild;

	RequestSpecBuilder requestBuilder;
	ResponseSpecBuilder responseSpecBuilder;


	{
		System.out.println("static block---running--->");
		
		RestAssured.baseURI = "https://httpstat.us";
	}

	@BeforeClass
	public  void setupMainresourses() {
		System.out.println("Before class---running--->");
		responseSpecBuilder = new ResponseSpecBuilder();
		responseSpecBuild =  responseSpecBuilder.build();


	}

	@BeforeTest
	public  void setupSpec() {
		System.out.println("Before Test---running--->");

		try {
			requestBuilder =  new RequestSpecBuilder();
			requestSpecBuild = requestBuilder.build();

		} catch (Exception e) {

			System.out.println("exception hendled....");
		}


	}


	@AfterTest
	public  void closePrerequisites() {
		System.out.println("After Test---running--->");

		/*		requestBuilder.setBasePath("");
		requestBuilder.setAccept("");
		requestBuilder.setBody("");
		requestBuilder.setAccept(ContentType.ANY);
		requestBuilder.setContent("");*/


		/*Resets the baseURI, basePath, port, authentication and rootPath, filters(java.util.List), requestSpecification, responseSpecification, 
		 * urlEncodingEnabled, config, sessionId and proxy 
		 * to their default values of "http://localhost", "", -1, no authentication, <empty string>, null, null, <empty list>, null, null, none, 
		 * true, new RestAssuredConfig(), null and null.
		 * */
		RestAssured.reset();
		responseSpecBuilder.noRootPath();

	}

}
