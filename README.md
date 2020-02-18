# helloMoney_api

You can use it at URL http://176.120.177.34:8081/

***POST "/add"***
Create new User.

body is required:

```json
{
	"username": "ЕЕЕ",
	"password": "123123123",
	"firstname": "ррррр",
	"lastname": "nnnnnnn"
}
```
***POST "/transfer"***
Transfers amount of money from one user to another. Body is required:
```json
{
  "fromUser": "user1",
  "toUser": "user2",
  "currency": "curr",
  "amount": 40
}
```
avaliable currencies: curr1, curr2, curr3

***POST "/user/{username}/buy"***
Buy some money

Body is required:
```json
{
  "currency": "curr1",
  "amount": 10.0
}
```
***GET "/all"***
View every user

***GET "/user/{username}"***
View personal info

***GET "/user/{username}/balance"***
View user's balance
