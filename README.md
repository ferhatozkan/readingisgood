# readingisgood
ReadingIsGood

• As seen in the picture above I have seperated each business. (Customer, book, order etc.)
• Run docker file with 
docker run -d --name getir -p 5000:5000 getir
• Java SDK 11 was used.
• Order consists of books, customerId, totalPrice, createdOn and id.
• Each book has price, name, stock, id and createdOn.
• Each Customer has name, email, password, age, createdOn and id.
• OrderBook is a one to many table where I store which books of the order, the count of them 
and the price of book on order creation. I had to store price on this table since price of 
products may change in future but Order price should not change.
• Statistics controller returns statistics. It is much better in read performance to store 
statistical data in a non relational database (MongoDb etc.). But since the time was limited I 
could not integrate MongoDb in the project.
• All success and error messages are handled with a exception handler. 
• /customer/authorize is where we get a Bearer Token which can be added to Header as a 
Authorization parameter. Otherwise the application will return 403 Forbidden. Create a 
customer with /customer/register and receive a Bearer token from the authorization 
endpoint.
• Postman file is provided.
• Each service operation is annotated with Transactional. In case of any error the code will 
rollback db operations ensuring stock updates are done safe.
• Example unit test is included but I couldn’t write unit tests for every line of code since time 
was limited. But I guess two unit tests are enough as an example. I have tried to cover as 
much cases as I can to make the application error proof.
