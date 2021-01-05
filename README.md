
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
