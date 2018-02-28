# challenge-dev

Running application
- mvn spring-boot:run


API 

Getting an user's post 

- get http://localhost:8080//v001/{userId}/posts
- userId type string min 1 max 50 characters
- return type PostDto collection  

Posting new message 

- post http://localhost:8080//v001/{userId}/post
- userId type string min 1 max 50
- return type void

In body message should be laced JSON object see below sample:

JSON sample message:
{ "body" : "sample body" }

The body field is limited from min = 1 to max = 140 characters.
Additional if a user is not registered the new user is created


Getting post observed users 

- get http://localhost:8080//v001/{userId}/followed
- userId string min 1 max 50
- return type PostDto collection 


Add new observed user

- post http://localhost:8080//v001/time-line/{ownerId}/{observedUserId}
- ownerId type string min 1 max 50 characters
- observedUserId type string min 1 max 50 characters
- return type void