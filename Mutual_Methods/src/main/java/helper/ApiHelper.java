package helper;

import enums.RequestInfo;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.StoreApiInfo;

public class ApiHelper {
    // TODO: 11/6/2022 1. buradan baslanilacak anlatmaya

    /**
     * biz asagida api helper class ini singleton haline getirdik cunku
     * biz sadece bir tane api helper instanci yaratip bu instance ile RequestSpecification i tum proje boyunca kullanabiliyoruz
     * bu sayede surekli inherit etmek zorunda kalmiyoruz baska class tan olayimiz singleton
     *
     * eger requestim yeni bir request degilse getinstance cagiririm yeni request ise definenewrequest kullanilir
     */
    private final Logger log = LogManager.getLogger(ApiHelper.class);
    private static ApiHelper instance;

    private ApiHelper() {
        init();
    }

    public static ApiHelper getInstance() {
        if (instance == null) {
            instance = new ApiHelper();
        }
        return instance;
    }

    /**
     * asagida reqeust speci storeapi infodan aliyoruz sonra kullanabiliyoruz
     * RequestInfo.REQUEST.info  biz direk enumdan requestin string halini aliyoruz
     */
    public RequestSpecification getRequestSpecification() {
        return (RequestSpecification) StoreApiInfo.get(RequestInfo.REQUEST.value);
    }

    /**
     * burda ise storeapi infoya ekliyoruz reqeustspec i ve sadece o anda olusan thread icin sakliyor
     * request.info storeApi infodaki key,value oluyor
     * buranin amaci requesti olusturup storeApiInfoda saklamak
     *
     * definenewrequestifull bu methoda gerek yok cunku doluysa onu kullanaracak zaten yoksada en basta olusturacak
     * asagidaki rest assured given metodu static oldugundan surekli requesti put etmeye gerek kalmayacak guncel request elimizde olacak
     * log islemini burda yapabiliriz
     */
    public void init() {
        StoreApiInfo.put(RequestInfo.REQUEST.value, RestAssured.given());
    }

    /**
     * here, we are calling init method which is defining a new request specification
     *
     *
     *     public static RequestSpecification given() {
     *         return createTestSpecification().getRequestSpecification();
     *     }
     *
     *     yukardaki method rest assure in methodu ben buraya gelip reqeust specification aliyorum burdan cunku keyword driven oldugundan sonra kullanicinin
     *     eklemesini istiyorum header/methods vs belli degil
     *
     *     bir senaryoda iki farkli istek atmak istedigimizde birden fazla asagidaki metodu kullanabiliriz
     *     ve yeni istekte onceki request tamamen sifirlanir Rest assured.given yeni clear bir request olusturur
     */
    public void defineNewRequest() {
        init();
        log.info("New requests defined");
    }


}
