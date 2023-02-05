# User Update
Tags:UserUpdate
|UserKey         |Username          | Password    | Role  |Status code | Response key|Expected value |
|----------------|-----------------|-------------|--------|------------|-------------|---------------|
| userkeyma55699 |test_user_alp5989 |mert341!341S |admin  |200         |result       |succeed        |

 * Getting user token

## Add New User To Update
* Store variable "updatedUserKey" = <UserKey> during spec
* Create user with username : <Username> , password: <Password>,role: <Role> key: <UserKey>
* Check if status code is <Status code>
* Get <Response key> from response and then compare with <Expected value>, is it contains the value?

## Scenario-1 :Update only username
//Bu senarioda yapılan denemelerde silinmiş bir username ile güncelleme yapıldığında "username already exist" hatası veriyor
* Update user with key: <UserKey>, username : "test_usr78", password: <Password>, role: <Role>
* Get "result" from response and then compare with "succeed", Are they equals?
* Get the user with <UserKey>
* Get "content.username" from response and then compare with "test_usr78", Are they equals?

## Scenario-2:Update only username with unacceptable char length
* Update user with key: <UserKey>, username : "userT", password: <Password>, role: <Role>
* Get "result" from response and then compare with "failed", Are they equals?
* Get "error" from response and then compare with "username must be at least 8 characters long", is it contains the value?
* Get the user with <UserKey>
* Get "content.username" from response and then compare with "userT", Are they not equals?

## Scenario-3:Update only username with empty user key value
// Key değeri Empty iken güncelleme yapıldığında "failed" sonucu dönmesi gerekirken "success" mesajı geliyor.
* Update user with key: "", username : "test_user_cbc45", password: <Password>, role: <Role>
* Get "result" from response and then compare with "failed", Are they equals?

## Scenario-4:Update only Password with unacceptable char length or type

* Update user with key: <UserKey>, username : <Username>, password: "test", role: <Role>
* Get "result" from response and then compare with "failed", Are they equals?
* Get the user with <UserKey>
* Get "content.password" from response and then compare with MD5 encrypted "test", Are they not equals?

## Scenario-5:Update only Password
// Sadece Password Güncellemek istediğimizde username'i aynı gönderiyoruz. Ancak "username already exist" hatası veriyor.
* Update user with key: <UserKey>, username : <Username>, password: "Aydin123**", role: <Role>
* Get "result" from response and then compare with "succeed", Are they equals?
* Get the user with <UserKey>
* Get "content.password" from response and then compare with MD5 encrypted "Aydin123**", Are they equals?

## Scenario-6 :Update Username and Password
//Bu senarioda yapılan denemelerde silinmiş bir username ile güncelleme yapıldığında "username already exist" hatası veriyor
* Store variable "deleteAfterSenario" = "yes" during spec
* Update user with key: <UserKey>, username : "test_user_cc599", password: "Aydin123**", role: <Role>
* Get "result" from response and then compare with "succeed", Are they equals?
* Get the user with <UserKey>
* Get "content.username" from response and then compare with "test_user_cc599", Are they equals?
* Get "content.password" from response and then compare with MD5 encrypted "Aydin123**", Are they equals?


//Spec boyunca kullanılan User "After Spec Hook" methodunda  sildirilmiştir.

