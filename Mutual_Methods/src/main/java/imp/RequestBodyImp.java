package imp;

import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.TableRow;
import com.thoughtworks.gauge.datastore.ScenarioDataStore;
import com.thoughtworks.gauge.datastore.SpecDataStore;
import com.thoughtworks.gauge.datastore.SuiteDataStore;
import exceptions.RequestNotDefined;
import helper.DocumentHelper;
import helper.FileHelper;
import helper.RequestBodyHelper;
import io.cucumber.java.en.Given;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.Utils;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class RequestBodyImp extends RequestBodyHelper {
    // TODO: 11/10/2022 helperlar bittikten sonra 7.sira
    private final Logger log = LogManager.getLogger(RequestBodyImp.class);

    /**
     * asagida request bodyu string olarak yollayan bir step yazmisiz
     * @param fileName
     * @throws RequestNotDefined
     *
     * asagida once resource istenen filename pathini buldu sonrada icini okudu!
     */
    /**
     * * asagida request bodyu hic stringle ugrasmiyip file olarak yolluyoruz kendisi serailize yapiyor byte ocde a cevirip
     * @param fileName
     * @throws RequestNotDefined
     */
    @Step({"Add payload as file from resource <file name>",
            "Add body as file from resource <file name>",
            "Dosya tipinde istek body'si ekle <dosya adı>"})
    @Given("payload as file {string} from resource")
    public void addBodyAsFile(String fileName) throws RequestNotDefined {
        String filePath = Objects.requireNonNull(getClass().getClassLoader().getResource(fileName)).getPath();

        File file = new File(filePath);
        addBody(file);
    }
/**
 *  * * asagida request body i direk table halinde giriyoruz key value table olarak yani map olarak
 */

    @Step({"Add payload as map <table>",
            "Add body as map <table>",
            "Tablodan istek body'si ekle <table>"})
    public void addBodyAsFile(Table table) throws RequestNotDefined {
        List<TableRow> rows = table.getTableRows();
        HashMap<Object, Object> body = new HashMap<>();
        for (TableRow row : rows) {
            if(!row.getCellValues().get(1).equals("remove_field")) {
                body.put(row.getCellValues().get(0), row.getCellValues().get(1));
            }
        }
        addBody(body);
    }

    public void addBodyAsFile(Map<String,Object> body) throws RequestNotDefined {
        addBody(body);
    }
    /**
     * asagida request body i direk ScenarioDataStore dan cagiriyoruz onceden attiysak
     */
    @Step({"Add payload from scenario store with <key>",
            "Add body from scenario store with <key>",
            "Senaryo kayıtlı verisinden istek body'si ekle, kayıt anahtarı <key>"})
    public void addBodyFromScenarioStore(String key) throws RequestNotDefined {
        addBody(ScenarioDataStore.get(key));
    }

    @Step({"Add payload from suite store with <key>",
            "Add body from suite store with <key>",
            "Suit kayıtlı verisinden istek body'si ekle, kayıt anahtarı <key>"})
    public void addBodyFromScenarioSuite(String key) throws RequestNotDefined {
        addBody(SuiteDataStore.get(key));
    }

    @Step({"Add payload from spec store with <key>",
            "Add body from spec store with <key>",
            "Spec kayıtlı verisinden istek body'si ekle, kayıt anahtarı <key>"})
    public void addBodyFromScenarioSpec(String key) throws RequestNotDefined {
        addBody(SpecDataStore.get(key));
    }

    /**
     * asagidaki method saklanan bodyi alip okuyor mesela jsondaki name field ine git kopek ise kedi yap diyoruz
     * sonrada bunu ayni key ve yeni body ile data store a ekliyor
     * @param key
     * @param selector
     * @param key1
     *
     * update document en son dokumani istenen selector ve valuesina gore degistirir
     */
    @Step({"Get body with <key> from store and update <selector> as <key1> from scenario data",
            "<key> anahtarı ile saklanan body'den, <selector> değerini al, kayıtlı <key1>'in değeri ile güncelle"})
    public void updateBodyFromScenarioData(String key, String selector, String key1) {
        DocumentHelper documentHelper = new DocumentHelper();
        String body = String.valueOf(Utils.getFromStoreData(key));
        String newValue = String.valueOf(ScenarioDataStore.get(key1));
        newValue = newValue.equalsIgnoreCase("null") ? null : newValue;
        Object newBody = documentHelper.updateDocument(body, selector, newValue);
        ScenarioDataStore.put(key, newBody);
        log.info("\"{}\" is update as \"{}\" from \"{}\" in scenario store", selector, newValue, key);
    }

    @Step({"Get body <payloadName> and update <selector>=<value> in json then store it during the scenario with <key>"})
    public void updateBodyFromScenarioData(String key, String selector, String newValue, String key1) {
        DocumentHelper documentHelper = new DocumentHelper();
        String body = String.valueOf(Utils.getFromStoreData(key));
        if (!body.startsWith("<") || !body.startsWith("{")) {
            var is = getClass().getClassLoader().getResourceAsStream("payloads/" + key);
            body = new FileHelper().readFileAsString(is);
        }
        newValue = String.valueOf(Utils.getFromStoreData(newValue));
        newValue = newValue.equalsIgnoreCase("null") ? null : newValue;
        Object newBody = documentHelper.updateDocument(body, selector, newValue);
        ScenarioDataStore.put(key1, newBody);
        log.info("\"{}\" is update as \"{}\" from \"{}\" in scenario store", selector, newValue, key);
    }

    /**
 * asagidaki method ise elindeki jsonu alip degistiriyor istenen selector ve value da
 */
    @Step({"Update <selector>=<value> json from stored scenario with key <key>"})
    public void updateBody(String selector, String newValue, String key) {
        DocumentHelper documentHelper = new DocumentHelper();
        String body = String.valueOf(ScenarioDataStore.get(key));
        newValue = String.valueOf(Utils.getFromStoreData(newValue));
        newValue = newValue.equalsIgnoreCase("null") ? null : newValue;
        Object newBody = documentHelper.updateDocument(body, selector, newValue);
        ScenarioDataStore.put(key, newBody);
        log.info("\"{}\" is update as \"{}\" from \"{}\" in scenario store", selector, newValue, key);
    }

    @Step({"Get json file <file> and update as <selector>=<new> then add the request",
            "Resource'dan json dosyasını <filename> oku ve <selector>=<value> olarak güncele sonra requeste ekle "})
    public void updateAndAdd(String fileName, String selector, String newValue) throws RequestNotDefined {
        DocumentHelper documentHelper = new DocumentHelper();
        FileHelper fileHelper = new FileHelper();
        var filePath =Objects.requireNonNull(getClass().getClassLoader().getResource(fileName)).getPath();

        var body = fileHelper.readFileAsString(filePath);
        newValue = newValue.equalsIgnoreCase("null") ? null : newValue;
        Object newBody = documentHelper.updateDocument(body, selector, newValue);
        addBody(newBody);
    }

    @Step({"Add payload from store with key <key>",
            "Senaryo kayıtlı verisinden istek body'si ekle, kayıt anahtarı <key>"})
    public void addBodyFromStore(String key) throws RequestNotDefined {
        addBody(Utils.getFromStoreData(key));
    }
}
