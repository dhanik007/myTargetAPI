### Prerequisites:-
* [1] SpringBoot Tool suite or any IDE framework for ease of use.
* [2] Maven
* [3] Cassandra
* [4] PostMan is used as a client to access Restful webservices



### How to start webservices server ?
1) Before starting webservices server make sure that Cassandra is running on port number mentioned in the properties file
2) Run the below command to start the webservices server under the target folder

java -jar myretail-0.1.0.jar

### How to test the webservices using PostMan client ?

1) Test GET Webservice

In the postman client select the GET option and in the url section copy the below url
http://localhost:8080/products/13860428
and the response should be
{"productID":13860428,"productName":"The Big Lebowski (Blu-ray)","priceInfo":{"currency":"USD","price":13.49}}

2) Test PUT Webservice

In the postman client select the PUT option and in the url section copy the below url
http://localhost:8080/products/13860428 and in the body section copy the below JSON 
{"priceInfo":{"price":99.9,"currency":"USD"}}
and the response should be
Given product with id 13860428 is successfully updated

