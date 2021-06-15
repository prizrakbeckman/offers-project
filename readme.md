
# What i added for the test offer

Here you can find the following points of what i have done in this project:

- I implement two endpoints :One to add an account to a user, and another one to fetch users registered
- I implemented validation using spring boot
- I implemented persistence layer with MongoDb
- I added unit tests for adding an account to a user, and for the services (getting the users, and adding the account)
- I implemented asynchronous calls with **completablefutures**, and enhanced the calls with an **executor** bean.
- I add a rest call with  a path variable as requested.
- I wanted to add aop for logging parameters and return values, but it's still on my to do list.
- The response errors are handled by an advice controller, and few Customized exception.
- I added the incremenation of ids using a listener on the user model.
- For the data model : The relation is a **User** has an **Account**. ***A one to one relation***
- I put few mandatory fields with some constraints

# Conditions && assumptions

- I assumed that to create an account, it's already existing in the database.
- I chose to work with MongoTemplate because it allows me to manipulate the queries as i want.
- I added the method to add new users to database, (<**/user/adduser** uri)

# database information
- Mongo compass is required or some some mongo db supported interface or server.
- A creation of the database in mongo with the name : **_offerscurrentdb_**, and a **collection** named **_users_**
- An account collection isn't required since we have an account per user.

# Example of requests used for testing

`{
"id":16,
"userId":"user1",
"fullName":"Jean Paul",
"age":20,
"localisation":"Paris",
"account":{
    "accountId":"account1",
    "username":"user1",
    "email":"user1@gmail.com",
    "description":"utilisateur"
    }
}`
`
For testing creation of account based on age, change the field age to less than 18, to see validation error exception thrown