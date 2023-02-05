package imp;

import com.thoughtworks.gauge.*;

import com.thoughtworks.gauge.datastore.ScenarioDataStore;
import com.thoughtworks.gauge.datastore.SpecDataStore;
import com.thoughtworks.gauge.datastore.SuiteDataStore;
import gauge.messages.Messages;
import gauge.messages.Spec;
import helper.AuthHelper;
import utils.Utils;

import java.util.HashMap;
import java.util.List;

public class AuthImp extends AuthHelper {
    // TODO: 11/10/2022 helperlar bittikten sonra 1.sira

    /**
     * Buranin mantigi specten/feature dan verilen keywordleri
     * yani username/pwd leri basic aut helperi yada bearer etc methodlari call edip
     * authentication bilgisini requestspece ekliyor yani burda  requesti gondermiyoruz daha
     * onu biz methods kisminda yapiyoruz get/post/put gibi
     *
     * @param user
     * @param password
     */



    @Step({"Basic auth with <username> and <password>",
            "Kullanıcı adı: <username>, Şifre <password> ile temel yetkilendirme yap"})
    public void basic(String user, String password){
        basicAuth(user, password);
    }

    @Step({"Basic auth with <username> and <password> as preemptive",
            "Kullanıcı adı: <username>, Şifre <password> ile preemptive temel yetkilendirme yap"})
    public void basicAuthWithPreemptive(String user, String password)  {
        preBasicAuth(user, password);
    }

    @Step({"Add Bearer token <token>",
            "Bearer token ekle <token>"})
    public void bearerAuth(String token) {
        addBearerToken(token);
    }

    @Step({"Add auth token from scenario store <key>",
            "senaryo store'dan auth token ekle <token>"})
    public void AuthTokenFromScenarioStore(String key) {

        String token = String.valueOf(Utils.getFromStoreData(key));
        addAuthToken(token);
    }
    @Step({"Add auth token from scenario store <key> depend on <UseAuth>",
            "senaryo store'dan auth token ekle <token>"})
    public void AuthTokenFromScenarioStoreDependsOnUseAuth(String key,String UseAuth) {
        if (!UseAuth.toLowerCase().equals("no")){
            String token = String.valueOf(Utils.getFromStoreData(key));
            addAuthToken(token);
        }

    }
    /**
     * eger istersek o tokeni daha once key ile ScenarioDataStore a attiysak ordan cekebiliriz
     * @param key
     */
    @Step({"Add Bearer token from scenario store <key>",
            "Senaryo store'dan Bearer token ekle <token>"})
    public void bearerAuthFromScenarioStore(String key)  {
        String token = String.valueOf(ScenarioDataStore.get(key));
        addBearerToken(token);
    }
    /**
     * eger istersek o tokeni daha once key ile SuiteDataStore a attiysak ordan cekebiliriz
     *
     */
    @Step({"Add Bearer token from suit store <key>",
            "Suit store'dan Bearer token ekle <token>"})
    public void bearerAuthFromSuitStore(String key) {
        String token = String.valueOf(SuiteDataStore.get(key));
        addBearerToken(token);
    }


    /**
     * biz SpecDataStore.get(key));  ister spec ister suit isterse scenario boyunca istedigimiz degeri tutup geri cagirabiliriz icinden
     * @param key
     */
    @Step({"Add Bearer token from spec store <key>",
            "Spec store'dan Bearer token ekle <token>"})
    public void bearerAuthFromSpecStore(String key)  {
        String token = String.valueOf(SpecDataStore.get(key));
        addBearerToken(token);
    }

    @Step({"Ekrana Yaz <sampleTable>"})
    public void ekranaYaz(String key)  {
        System.out.println("Deneme::"+key);
    }
    @Step({"Update User with Below table data <Table>"})
    public void updateUserWithData(Table table)  {
        TableRow tableRow=table.getTableRows().get(0);
        String userKey=tableRow.getCell("UserKey");
        String newUserName=tableRow.getCell("NewUserName");
        String newPassword=tableRow.getCell("NewPassword");
        String newRole=tableRow.getCell("NewRole");
        String statusCode=tableRow.getCell("Status code");
        String ResponseKey =tableRow.getCell("Response key");
        String expectedValue =tableRow.getCell("Expected value");

        HashMap<Object, Object> body = new HashMap<>();
      //  body.put(row.getCellValues().get(0), row.getCellValues().get(1));
    //    addBody(body);
    }
    @Step({"Getir  <dene>"})
    public void deneme(String table)  {


     System.out.println("DenemeSooo::"+table);
    }
}
