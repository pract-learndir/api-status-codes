package errorTest;

import org.testng.annotations.Test;

import com.util.TestBase;

public class Error_5xx_Causable extends TestBase{
	
	
	@Test
	private void why_502_err() {

		/*Below are some common ways you might see it:

			502 Bad Gateway
			502 Service Temporarily Overloaded
			Error 502
			Temporary Error (502)
			502 Proxy Error
			502 Server Error: The server encountered a temporary error and could not complete your request
			HTTP 502
			502. That's an error
			Bad Gateway: The proxy server received an invalid response from an upstream server
			HTTP Error 502 - Bad Gateway
			Twitter's famous "fail whale" error that says Twitter is over capacity is actually a 502 Bad Gateway error (even though a 503 Error would make more sense).
			
			reference --> https://www.lifewire.com/502-bad-gateway-error-explained-2622939
			
			
*/		
		requestSpecBuild.basePath("");
		
		
		
		
		
		

	}

}
