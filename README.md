# GitHub Repo for 4413 project by Team 2021

## Team members
* Kenan Li (KenanL1)
* Nathan Duff-Mccracken (nathan-duff)
* Kyle Hallman (khallman97)
* Risheed Malatombee (rasho95)

## Running the application

### Cloud Platform
 http://bookstore2021-env-1.eba-h9nj3uua.us-east-1.elasticbeanstalk.com/main 
 This is hosted on AWS (We have encountered some problems where after a time period their would be a problem loading resources, should this encounter either 1. Wait a bit or 2. Contact us and we will refresh our instance on AWS)

### Local Host
Import the [war ](https://github.com/khallman97/4413-2021/blob/main/War%20Files/Bookstore.war) file into eclipse enterprise 
From there, run our application inside eclipse which will then be available on your local host

## Testing

### Load Testing
For load testing we used jmeter. 
Please ensure you have Jmeter installed in order to follow our load testing. Download the [TestPlan.jmx](https://github.com/khallman97/4413-2021/tree/main/Load%20Testing%20Setup%20and%20Results/JMeter%20Test%20File) and open it inside of jmeter. After this hit the run button to and check the summery results as well as full results to see load testing. To view results we got please refer to [the excel file](https://github.com/khallman97/4413-2021/tree/main/Load%20Testing%20Setup%20and%20Results/Excel%20file%20for%20all%20preformance%20reports) for our full performance results.

### XSS Testing
For XSS testing, select a book form the main page.
In the review section, enter the following: <script>alert("Attack")</script>
Click submit
If the text is added to the review section, but no alert is triggered on the page, the XSS failed, and the security worked.
If an alert is triggered after adding the review or on reloading the page, the XSS attack worked.

### SQL Injection Testing
For SQL Injection testing we created a rest client to attempt to replicate an attacker trying to access the SQL tables using methods such as "1=1" is always true and batched sql statements. You can run the test case by running the java application on eclipse located at https://github.com/khallman97/4413-2021/tree/main/Code/TestCode/src/testClient. This will attempt to login into a account using "1=1" as the password and delete all the books in the sql table with batched sql statements using rest api.


