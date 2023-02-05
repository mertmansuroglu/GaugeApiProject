# check multiple user  fileds

|ID|Key          |Username        | Password    | Role  |Status code |Response key |Expected value               |Test case Definition       |Delete User After Test|
|--|-------------|----------------|-------------|-------|----------- |-------------|-----------------------------|---------------------------|----------------------|
|1 | userkeyma   |test_user_alp   |mert341!341S |admin  |200         |result       |succeed                      |creating user with success | No                   |
|2 | userkeyma   |test_user_alp   |mert341!341S |admin  |200         |error        |username is already exists   |alread added username test | No                   |
|3 | userkeyma   |test_user_john  |mert341!341S |admin  |200         |error        |userkey  is already exists   |alread added userkey  test | Yes                   |
|4 | userkeymb   |                |alp341!341S  |admin  |200         |error        |username must                |empty username test        | Yes                  |
|5 |             |test_user_mert  |cem341!341S  |admin  |200         |error        |key should not be empty      |empty key test             | Yes                  |
|6 | userkeymc   |test_user_cem   |ali341!341S  |       |200         |result       |failed                       |empty role test            | Yes                  |
|7 | userkeymd   |test_user_ali   |ot341!341S   |test   |200         |result       |failed                       |wrong role value test      | Yes                  |
|8 | userkeybd   |test_user_ahmet |22           |test   |200         |error        |min length: 8                |unacceptable password  test| Yes                  |
|9 | userkeybe   |usr             |ali341!341S  |test   |200         |error        |at least 8 characters long   |unacceptable username  test| Yes                  |
|10| userkeybd   |test_user_john  |             |admin  |200         |error        |password is not strong       |empty password test        | Yes                  |

// Uygulamada Role ile ilgili hiç bir doğrulamada kayıtta yok
// Bazı şeyleri genelleme yaparak şoyledir böyledir diye yorumlsyıp bir expectation yapıyoruz
// ancak doğru olan bize ayrıntılı standardların olduğu bir API dökumanı verilmesi gerekiyor.
// Şuanki uygulama bir demo
// Daha sonra tum test methodlarını negative pozitif test caseleri uygulayacağız boundary equal partion
// Şuan herbiri ayrı ayrı raporlanıyor. Daha Sonra tag verilerek Test Suit haline getirilecek ve tüm test toplu çalıştırılacak
// Sonuçlarda slack'a rapor olarak gidecek

 * Getting user token
 ## create user with data above
 * Store variable "deleteAfterTest" = <Delete User After Test> during scenario
 * Get All Users
 * Get "content" from response and store it with "contentBeforeUserCreate" during scenario
 * Get array length from "contentBeforeUserCreate" json list and store it during Scenario with "usersCountBefore"
 * Create user with username : <Username> , password: <Password>,role: <Role> key: <Key>
 * Check if status code is <Status code>
 * Get <Response key> from response and store it with "responseValue" during scenario
 * Get All Users
 * Get "content" from response and store it with "contentAfterUserCreate" during scenario
 * Get array length from "contentAfterUserCreate" json list and store it during Scenario with "usersCountAfter"
 * Check "usersCountBefore" and "usersCountAfter" depends on <Expected value>
 * Get "responseValue" from scenario store and check if it is contains <Expected value> as string

___
Tear down step
* deleting the user with <Key>
