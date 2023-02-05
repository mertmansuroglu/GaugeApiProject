package helper;

import exceptions.NullResponse;
import exceptions.NullValue;
import io.restassured.path.json.JsonPath;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.StoreApiInfo;

import java.util.List;

import static enums.DocumentType.JSON;
import static enums.DocumentType.XML;
import static enums.RequestInfo.RESPONSE;

public class  ResponseBodyHelper {
    // TODO: 11/6/2022 11. buradan baslanilacak anlatmaya
    private final Logger log = LogManager.getLogger(ResponseBodyHelper.class);

    /**
     * response Check if response is null.
     * eger response yoksa response yok hatasi firlatir!!
     * @throws NullResponse if response is null this exception will throw
     */
    protected void checkIfResponseNull() throws NullResponse {
        if (StoreApiInfo.get(RESPONSE.value) == null) {
            log.error("Response yok.");
            throw new NullResponse();
        }
    }

    /**
     * Gets response as String object
     *
     * @return is response as String
     * @throws NullResponse if response is null, it will be throw this exception.
     *
     * ben tum response u string olarak alabilirim istersem tabi once response null mu degilmi kontrolunu yaparim
     */
    protected String getResponseAsString() throws NullResponse {
        checkIfResponseNull();
        try {
            Response response = (Response) StoreApiInfo.get(RESPONSE.value);
            return response.then().log().all().extract().asString();
        } catch (Exception e) {
            log.warn("Response could not get as String \n Exception detail:\n {}", e.getMessage());
            throw e;
        }
    }

    /**
     * Gets response as JsonPath object
     *
     * @return is response as JsonPath
     * @throws NullResponse if response is null, it will be throw this exception.
     *
     * yada istersem jsonpath halinde response u alabilirim sonra icinden istedigime erisirim
     * int id = response.jsonPath().getInt("data[1].id");
     * example : System.out.println(id); //2
     */
    private JsonPath getResponseAsJsonPath() throws NullResponse {
        //istersek log koyup kontrolu burdan yapabiliriz
        checkIfResponseNull();
        try {
            Response response = (Response) StoreApiInfo.get(RESPONSE.value);
            return response.then().log().all().extract().jsonPath();
        } catch (Exception e) {
            log.warn("Response could not get as JsonPath \n Exception detail:\n {}", e.getMessage());
            throw e;
        }
    }

    /**
     * Gets response as XmlPath object
     *
     * @return is response as XmlPath
     * @throws NullResponse if response is null, it will be throw this exception.
     *
     * istersem response u xml path halinde alabilirim
     */
    private XmlPath getResponseAsXmlPath() throws NullResponse {
        checkIfResponseNull();
        try {
            Response response = (Response) StoreApiInfo.get(RESPONSE.value);
            return response.then().extract().xmlPath();
        } catch (Exception e) {
            log.warn("Response could not get as XmlPath \n Exception detail:\n {}", e.getMessage());
            throw e;
        }
    }

    /**
     * Return an json element from json response by selector
     *
     * @param selector Search json element with this selector
     * @return is json element as Object
     * @throws NullResponse if response is null, it will be throw this exception.
     *
     * yada istersem response icin hic path vermiyip direk istedigim selectoru yazip valuesini alabilirim getResponseAsJsonPath() metodu kullanip
     *
     * asagidaki method sayesinde biz pojo classlara gerek duymaduk getResponseAsJsonPath().get(selector)  yazip obje olarak biz responsu aliriz
     * ve ne oldugu nu biliyorsam bu objenin ben ona kendimk cevirebilirim
     * ne tipinde oldugunu bilemiyorsamda getsmiple name ini alip convert eden class imiz var o is goruyor
     *
     *
     */
    protected Object getJsonPathValue(String selector) throws NullResponse {
        try {
            return getResponseAsJsonPath().get(selector);
        } catch (Exception e) {
            log.warn("Json element could not find by {} \n Exception detail:\n {}", selector, e.getMessage());
            throw e;
        }
    }

    /**
     * Return an xml element from xml response by selector
     *
     * @param selector Search xml element with this selector
     * @return is xml element as String
     * @throws NullResponse if response is null, it will be throw this exception.
     */
    protected String getXmlPathValue(String selector) throws NullResponse {
        try {
            return getResponseAsXmlPath().get(selector);
        } catch (Exception e) {
            log.warn("Xml element could not find by {} \n Exception detail:\n {}", selector, e.getMessage());
            throw e;
        }

    }

    /**
     * asagida response umuzu string olarak body dedik aldik
     * sonra document helper yardimi ile json sa veya xml ise ona gore istedigimiz value sunu aliyoruz(getJsonPathValue(selector)) ile
     * eger value nullsa NullValue(selector) hatasi atariz
     * @param selector
     * @return
     * @throws NullResponse
     * @throws NullValue
     */
    public Object getResponseElement(String selector) throws NullResponse, NullValue {
        String body = getResponseAsString();
        DocumentHelper documentHelper = new DocumentHelper();
        if (documentHelper.isJsonOrXml(body) == JSON) {
            Object value = getJsonPathValue(selector);
            if (value == null)
                throw new NullValue(selector);
            return getJsonPathValue(selector);
        } else if (documentHelper.isJsonOrXml(body) == XML) {
            Object value = getJsonPathValue(selector);
            if (value == null)
                throw new NullValue(selector);
            return getXmlPathValue(selector);
        } else {
            return null;
        }
    }


    /**
     * asagida response u string olarak aldik sonra json veya cml e gore ayirdik ona gore istenen selector valuesi cekildi
     * null ise null donduruldu
     */
    protected Object getResponseElementEvenNull(String selector) throws NullResponse {
        String body = getResponseAsString();
        DocumentHelper documentHelper = new DocumentHelper();
        if (documentHelper.isJsonOrXml(body) == JSON)
            return getJsonPathValue(selector);
        else if (documentHelper.isJsonOrXml(body) == XML)
            return getXmlPathValue(selector);
        else
            return null;
    }



    protected <T> List<T> getListFromResponse(String jsonkey) {
        var response = (Response) StoreApiInfo.get(RESPONSE.value);
        return response.getBody().jsonPath().getList(jsonkey);
    }

/**
 * biz istersek response u alip bir java objesine cevirebiliriz yani deserialization yapabiliriz
 *
 * var responseMap= postResponse.getBody().as(new TypeRef<HashMap<String,Object>>()
 *
 * bu rest assured in kendi ozelligi biz response u aldik gittik hashmape cevirdik!
 * bir yerde patlayabilir bazen response array doner sen arrayi hashmape cevirmek istediginde hata gelir bu durumda asagidaki gibi yap
 *
 * var responseMap= postResponse.getBody().as(new TypeRef<Arraylist<HashMap<String,Object>>>()
 */
}
