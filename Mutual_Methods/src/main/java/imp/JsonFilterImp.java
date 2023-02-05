package imp;

import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.datastore.ScenarioDataStore;
import com.thoughtworks.gauge.datastore.SpecDataStore;
import com.thoughtworks.gauge.datastore.SuiteDataStore;
import helper.JsonListFilterHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.Utils;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonFilterImp extends JsonListFilterHelper {
    Logger log = LogManager.getLogger(JsonListFilterHelper.class);

    @Step("Get <json select> from <json array> json list which one equals <filter>=<filterValue>, and store it during Scenario with <key>")
    public void jsonArrayFilter(String requested, String json, String filter, String filterValue, String key) {
        Object jsonArray = Utils.getFromStoreData(json);
        var result = getJsonValueByFilter(jsonArray, filter, filterValue, requested);
        ScenarioDataStore.put(key, result);
        log.info("{} stored in scenario store", result);
    }
    @Step("Get <json select> from <json array> json list which one equals <filter>=<filterValue>, and store it during Spec with <key>")
    public void jsonArrayFilterSpec(String requested, String json, String filter, String filterValue, String key) {
        Object jsonArray = Utils.getFromStoreData(json);
        var result = getJsonValueByFilter(jsonArray, filter, filterValue, requested);
        SpecDataStore.put(key, result);
        log.info("{} stored in Spec store", result);
    }
    @Step("Get <json select> from <json array> json list which one equals <filter>=<filterValue>, and store it during suit with <key>")
    public void jsonArrayFilterSuit(String requested, String json, String filter, String filterValue, String key) {
        Object jsonArray = Utils.getFromStoreData(json);
        var result = getJsonValueByFilter(jsonArray, filter, filterValue, requested);
        SuiteDataStore.put(key, result);
        log.info("{} stored in suit store", result);
    }

    @Step("Get array length from <json array> json list and store it during Scenario with <key>")
    public void jsonArrayFilterr(String json,String key) {
        Object jsonArray = Utils.getFromStoreData(json);
        var result = getJsonObjectCountInArray(jsonArray);
        ScenarioDataStore.put(key, result);
        log.info("{} stored in scenario store", result);
    }
    @Step("Get array length from <json array> json list and store it during Spec with <key>")
    public void jsonArrayFilterSpecc(String json,String key) {
        Object jsonArray = Utils.getFromStoreData(json);
        var result = getJsonObjectCountInArray(jsonArray);
        SpecDataStore.put(key, result);
        log.info("{} stored in scenario store", result);
    }
    @Step("Get array length before from <json array> json list and store it during spec with <key>")
    public void jsonArrayFilterSpec(String json,String key) {
        Object jsonArray = Utils.getFromStoreData(json);
        var result = getJsonObjectCountInArray(jsonArray);
        SpecDataStore.put("idCountBefore", result);
        log.info("{} stored in scenario store", result);
    }
    @Step("Get array length before from <json array> json list and store it during scenario with <key>")
    public void jsonArrayFilterScenario(String json,String key) {
        Object jsonArray = Utils.getFromStoreData(json);
        var result = getJsonObjectCountInArray(jsonArray);
        ScenarioDataStore.put("idCountBefore", result);
        log.info("{} stored in scenario store", result);
    }

    @Step("Check user count for before and current user count depends on <Expected value>")
    public void jsonArrayFilterScenarioUserCount(String expectedValue) {
        Object idCountBefore = Utils.getFromStoreData("idCountBefore");
        MutualCompareImp mutualCompareImp= new MutualCompareImp();
        Object idCount = Utils.getFromStoreData("idCount");
        int intval1=Integer.parseInt(idCountBefore.toString());
        int intval2=Integer.parseInt(idCount.toString());
        int before=intval1+1;

        System.out.println("countss before"+intval1);
        System.out.println("countsss last"+intval2);
        if(expectedValue.equalsIgnoreCase("succeed")){

            assertEquals(before, intval2);
        }else{
            assertEquals(intval1, intval2);
        }
    }

    @Step("Check <userCountBefore> and <userCountAfter> depends on <Expected value>")
    public void jsonArrayFilterScenarioUserCount(String userCountBefore, String userCountAfter,String expectedValue) {
        Object userCountBeforeO = Utils.getFromStoreData(userCountBefore);
        Object userCountAfterO = Utils.getFromStoreData(userCountAfter);

        int userCountBeforeI=Integer.parseInt(userCountBeforeO.toString());
        int userCountAfterI=Integer.parseInt(userCountAfterO.toString());


        System.out.println("countss before"+userCountBeforeI);
        System.out.println("countsss after"+userCountAfterI);
        if(expectedValue.equalsIgnoreCase("succeed")){

            assertEquals(userCountBeforeI+1, userCountAfterI);
        }else{
            assertEquals(userCountBeforeI, userCountAfterI);
        }
    }
    @Step("Is <usersCountAfter> Equal <usersCountBefore> - 1 ?")
    public void jsonArrayFilterScenarioUserCounts(String userCountAfter, String userCountBefore) {
        Object userCountBeforeO = Utils.getFromStoreData(userCountBefore);
        Object userCountAfterO = Utils.getFromStoreData(userCountAfter);

        int userCountBeforeI=Integer.parseInt(userCountBeforeO.toString());
        int userCountAfterI=Integer.parseInt(userCountAfterO.toString());

        assertEquals(userCountBeforeI-1, userCountAfterI);

    }
    @Step("Is <usersCountAfter> Equal <usersCountBefore>?")
    public void jsonArrayFilterScenarioUserCounts1(String userCountAfter, String userCountBefore) {
        Object userCountBeforeO = Utils.getFromStoreData(userCountBefore);
        Object userCountAfterO = Utils.getFromStoreData(userCountAfter);

        int userCountBeforeI=Integer.parseInt(userCountBeforeO.toString());
        int userCountAfterI=Integer.parseInt(userCountAfterO.toString());

        assertEquals(userCountBeforeI, userCountAfterI);

    }

}
