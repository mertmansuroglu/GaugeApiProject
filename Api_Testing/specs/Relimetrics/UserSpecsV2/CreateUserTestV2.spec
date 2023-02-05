# check multiple user  fields

|ID     |Test case Definition        |Method |Use Auth |Key          |Username        | Password    | Role  |Status code |Response key |Expected value              |Delete User After Test|
|-------|----------------------------|-------|---------|-------------|--------------- |-------------|-------|------------|-------------|----------------------------|----------------------|
|UC_TC1 |Creating user with success  |Post   |Yes      |userkeyma    |test_user_alp   |mert341!341S |admin  |200         |result       |succeed                     | No                   |
|UC_TC2 |Already added username test |Post   |Yes      |userkeyma    |test_user_alp   |mert341!341S |admin  |200         |error        |username is already exists  | No                   |
|UC_TC3 |Already added userkey  test |Post   |Yes      |userkeyma    |test_user_john  |mert341!341S |admin  |200         |error        |userkey  is already exists  | Yes                  |
|UC_TC4 |Empty username test         |Post   |Yes      |userkeymb    |                |alp341!341S  |admin  |200         |error        |username must               | Yes                  |
|UC_TC5 |Empty key test              |Post   |Yes      |             |test_user_mert  |cem341!341S  |admin  |200         |error        |key should not be empty     | Yes                  |
|UC_TC6 |Empty role test             |Post   |Yes      | userkeymc   |test_user_cem   |ali341!341S  |       |200         |result       |failed                      | Yes                  |
|UC_TC7 |Wrong role value test       |Post   |Yes      | userkeymd   |test_user_ali   |ot341!341S   |test   |200         |result       |failed                      | Yes                  |
|UC_TC8 |Unacceptable password  test |Post   |Yes      | userkeybd   |test_user_ahmet |22           |test   |200         |error        |min length: 8               | Yes                  |
|UC_TC9 |Unacceptable username  test |Post   |Yes      | userkeybe   |usr             |ali341!341S  |test   |200         |error        |at least 8 characters long  | Yes                  |
|UC_TC10|Empty password test         |Post   |Yes      | userkeybd   |test_user_john  |             |admin  |200         |error        |password is not strong      | Yes                  |
|UC_TC11|Testing with Get Method     |Get    |Yes      | userkeybd   |test_user_john  |mert341!341S |admin  |null        |null         |Method Not Allowed          | Yes                  |
|UC_TC12|Testing with Put Method     |Put    |Yes      | userkeybd   |test_user_john  |mert341!341S |admin  |null        |null         |Method Not Allowed          | Yes                  |
|UC_TC13|Testing with Delete Method  |Delete |Yes      | userkeybd   |test_user_john  |mert341!341S |admin  |null        |null         |Method Not Allowed          | Yes                  |
|UC_TC14|Testing with Delete username|Post   |Yes      | userkeybd   |remove_field    |mert341!341S |admin  |422         |detail[0].msg|field required              | Yes                  |
|UC_TC15|Testing with Delete key     |Post   |Yes      |remove_field |test_user_john  |mert341!341S |admin  |422         |detail[0].msg|field required              | Yes                  |
|UC_TC16|Testing with Delete password|Post   |Yes      |userkeybd    |test_user_john  |remove_field |admin  |422         |detail[0].msg|field required              | Yes                  |
|UC_TC17|Testing not adding auth head|Post   |No       |userkeybd    |test_user_john  |remove_field |admin  |200         |error        |no permission               | Yes                  |
|UC_TC18|Testing not adding auth head|Post   |No       |userkeybd    |test_user_john  |mert341!341S |admin  |200         |error        |no permission               | Yes                  |

 * Getting user token
 ## create user with data above
 * Store variable "deleteAfterTest" = <Delete User After Test> during scenario
 * Get All Users
 * Get "content" from response and store it with "contentBeforeUserCreate" during scenario
 * Get array length from "contentBeforeUserCreate" json list and store it during Scenario with "usersCountBefore"
 * Create user with username : <Username> , password: <Password>,role: <Role>, key: <Key>, Use Auth :<Use Auth>, Request Method:<Method>
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
