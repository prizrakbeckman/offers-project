
# What i added for the test offer

Here you can find the following points of what i have done in this project:

 - I implement two endpoints :One to add an account to a user.
- An another one to fetch users registered
- I implemented validation using spring boot
- I implemented persistence layer with MongoDb
- I added unit tests for adding an account to a user, and for the services (getting the users, and adding the account)
- I implemented asynchronous calls with completablefutures, and enhanced the calls with an executor bean.
- I add a rest call with  a path variable as requested.
- I wanted to add aop for logging parameters and return values, but it's still on my to do list.
- The response errors are handled by an advice controller, and few Customized exception.
- I added the incremenation of ids using a listener on the user model.

# Conditions && assumptions

- I assumed that to create an account, it's already existing in the database.
- I chose to work with MongoTemplate because it allows me to manipulate the queries as i want.
