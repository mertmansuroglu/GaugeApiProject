package imp;

import com.thoughtworks.gauge.Step;
import exceptions.NullResponse;
import exceptions.NullValue;
import helper.ResponseBodyHelper;
import helper.StringHelper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.Utils;

import java.security.NoSuchAlgorithmException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CompareImp  {
    // TODO: 11/10/2022 helperlar bittikten sonra 2.sira

/**
 * bir seyi birden fazla kez yapiyorsak en yukarda global tanimla ve o sekilde cagir
 */
    private static final String ARE_N0T_EQUALS = "They aren't equals";
    private static final String EQUALS = "They are equals";
    private static final String CONTAINS = "%s isn't contains %s as String";

    private final Logger log = LogManager.getLogger(CompareImp.class);
    /**
     * asagidaki metodlar tamamne response aldiktan sonra compare etmek icin kullanilir
     */

    /**
     *
     * @param selector
     * @param value
     * @throws NullResponse
     * @throws NullValue
     * burdaki olayimiz en son responsu check etmektir
     */
    @Step({"Get <selector> from response and then compare with <value>, Are they not equals?",
            "Get value with <selector> from response and verify it isn't equal with <value>",
            "Yanıttan <selector> ile değer alın ve <value> ile eşit olmadığını doğrulayın"})
    public void dataCompareNotEqualsFromResponse(String selector, Object value) throws NullResponse, NullValue {
        ResponseBodyHelper responseBodyHelper = new ResponseBodyHelper();
        Object value2 = responseBodyHelper.getResponseElement(selector);
        Utils utils = new Utils();
        value = utils.parsSameType(value2, value);
        assertNotEquals(value2, value, ARE_N0T_EQUALS);
        log.info("{} and {} not equals",value,value2);

    }

    @Step({"Get <selector> from response and then compare with <value>, Are they equals?",
            "Get value with <selector> from response and verify it is equal with <value>",
            "Yanıttan <selector> ile değer alın ve <value> ile eşit olduğunu doğrulayın"})
    public void dataCompareEqualsFromResponse(String selector, Object value) throws NullResponse, NullValue {
        ResponseBodyHelper responseBodyHelper = new ResponseBodyHelper();
        Object value2 = responseBodyHelper.getResponseElement(selector);
        Utils utils = new Utils();
        value = utils.parsSameType(value2, value);
        assertEquals(value, value2, EQUALS);
    }
    @Step({"Get <selector> from response and then compare with MD5 encrypted <value>, Are they equals?",
            "Get value with <selector> from response and verify it is equal with MD5 encrypted <value>",
            "Yanıttan <selector> ile değer alın ve MD5 şifrelenmiş <value> ile eşit olduğunu doğrulayın"})
    public void dataCompareEqualsFromResponseWithMD5(String selector, Object value) throws NullResponse, NullValue, NoSuchAlgorithmException {
        ResponseBodyHelper responseBodyHelper = new ResponseBodyHelper();
        Object value2 = responseBodyHelper.getResponseElement(selector);

        Utils utils = new Utils();
        value=StringHelper.stringToMd5( value.toString());
        value = utils.parsSameType(value2, value);

        assertEquals(value, value2, EQUALS);
    }

    @Step({"Get <selector> from response and then compare with MD5 encrypted <value>, Are they not equals?",
            "Get value with <selector> from response and verify it is not equal with MD5 encrypted <value>",
            "Yanıttan <selector> ile değer alın ve MD5 şifrelenmiş <value> ile eşit olmadığını doğrulayın"})
    public void dataCompareNotEqualsFromResponseWithMD5(String selector, Object value) throws NullResponse, NullValue, NoSuchAlgorithmException {
        ResponseBodyHelper responseBodyHelper = new ResponseBodyHelper();
        Object value2 = responseBodyHelper.getResponseElement(selector);

        Utils utils = new Utils();
        value=StringHelper.stringToMd5( value.toString());
        value = utils.parsSameType(value2, value);

        assertNotEquals(value, value2, ARE_N0T_EQUALS);

    }

    /**
     * asagidaki methodda istenen value yi direk tum responsta iceriliyormu diye arar
     * @param selector
     * @param value
     * @throws NullResponse
     * @throws NullValue
     */
    @Step("Get <selector> from response and then compare with <value>, is it contains the value?")
    public void dataCompareContainsFromResponse(String selector, String value) throws NullResponse, NullValue {
        ResponseBodyHelper responseBodyHelper = new ResponseBodyHelper();
        String value2 = String.valueOf(responseBodyHelper.getResponseElement(selector));
        assertTrue(value2.contains(value), CONTAINS);
    }

    @Step("Get <store key> list from response and then, compare list count with <count>, Are they equals?")
    public void compareListCount(String storeKey, int count){
        var list = (List<?>) Utils.getFromStoreData(storeKey);
        assertEquals(count,list.size());
        //th[contains("Who Initiated Transfer")]/following-sibling::td/li/span
    }

}
