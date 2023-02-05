# User Delete
Tags:UserDelete
|UserKey         |Username          | Password    | Role  |Status code | Response key|Expected value |
|----------------|-----------------|-------------|--------|-------------|------------|---------------|
| userkeyma55699 |test_user_alp5989 |mert341!341S |admin  |200         |result       |succeed        |

 * Getting user token

## Add New User To Update
* Store variable "updatedUserKey" = <UserKey> during spec
* Create user with username : <Username> , password: <Password>,role: <Role> key: <UserKey>
* Check if status code is <Status code>
* Get <Response key> from response and then compare with <Expected value>, is it contains the value?

## Scenario-1 :Delete with registered Key
* Get All Users
* Get "content" from response and store it with "contentBeforeUserDelete" during scenario
* Get array length from "contentBeforeUserDelete" json list and store it during Scenario with "usersCountBefore"
* Getting the user with <UserKey> key
* Get "result" from response and then compare with "succeed", Are they equals?
* deleting the user with <UserKey>
* Get "result" from response and then compare with "succeed", Are they equals?
* Get All Users
* Get "content" from response and store it with "contentAfterUserDelete" during scenario
* Get array length from "contentAfterUserDelete" json list and store it during Scenario with "usersCountAfter"
* Is "usersCountAfter" Equal "usersCountBefore" - 1 ?
* Getting the user with <UserKey> key
* Get "error" from response and then compare with "user could not found", Are they equals?

## Scenario-2 :Delete with unregistered Key
* Get All Users
* Get "content" from response and store it with "contentBeforeUserDelete" during scenario
* Get array length from "contentBeforeUserDelete" json list and store it during Scenario with "usersCountBefore"
* Getting the user with "unregisteredkey" key
* Get "error" from response and then compare with "user could not found", Are they equals?
* deleting the user with "unregisteredkey"
* Get "result" from response and then compare with "failed", Are they equals?
* Get "error" from response and then compare with "user could not found", Are they equals?
* Get All Users
* Get "content" from response and store it with "contentAfterUserDelete" during scenario
* Get array length from "contentAfterUserDelete" json list and store it during Scenario with "usersCountAfter"
* Is "usersCountAfter" Equal "usersCountBefore"?

