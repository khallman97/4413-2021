package testClient;

import javax.ws.rs.*;
import javax.ws.rs.client.*;
import javax.ws.rs.core.Form;
import javax.ws.rs.core.MediaType;


public class sqlInjectionClient {

	public static void main(String[] argv){
		
		String rootUrl = "http://localhost:8080/Bookstore/";
//		String rootUrl = "http://bookstore2021.us-east-1.elasticbeanstalk.com/";
		
		//create a client
		Client client = ClientBuilder.newClient();
		
//		 SQL Injection Test Based on ""="" is Always True
//		 Testing if hacker can login by putting username and password as 1=1
		String loggedIn=client.target(rootUrl + "rest/user/login")
				  .queryParam("username", "admin").queryParam("password","1=1" )	
		          .request(MediaType.TEXT_PLAIN)
		          .get(String.class);
		// 1 means the hacker gets logged in, 0 he doesn't a 
//		System.out.println("User is logged in" + loggedIn);
		if (loggedIn.equals("1")) {
			System.out.println("SQL not safe");
		} else {
			System.out.println("SQL is safe from hacker");
		}
		
		// SQL Injection Test Based on Batched SQL Statements    
		// Testing if hacker can delete the Book SQL table
		String batch=client.target(rootUrl + "rest/review/read")
				  .queryParam("bid", "9780006479888; DROP TABLE BookStore2021.Book")
		          .request(MediaType.TEXT_PLAIN)
		          .get(String.class);

		// Checking if book table still exist (returns books)
		String check=client.target(rootUrl + "rest/book/read")
				  .queryParam("field", "category").queryParam("value","" )	
		          .request(MediaType.TEXT_PLAIN)
		          .get(String.class);
		
		System.out.println(check);
		if (check.equals("[]")) {
			System.out.println("Table is not safe from attack");
		} else {
			System.out.println("SQL Table is safe from attack");
		}
		client.close();	
	}
}
