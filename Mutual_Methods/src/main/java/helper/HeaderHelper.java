package helper;

import exceptions.RequestNotDefined;
import io.restassured.config.RestAssuredConfig;
import io.restassured.http.ContentType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.Map;

import static io.restassured.config.MultiPartConfig.multiPartConfig;

public class HeaderHelper {
    // TODO: 11/6/2022 2. buradan baslanilacak anlatmaya
    private final Logger log = LogManager.getLogger(HeaderHelper.class);

    /**
     * defineNewRequest() it add bulk header to request
     *
     * @param headers it's headers as map object bazen birden fazla headers olabilir bunlari bir table halinde eklemek guzel olur
     *                * Add Headers
     *     |key            |value              |
     *     |---------------|-------------------|
     *     |accept         |application/json   |
     *     |Content-Type   |application/json   |
     *     |Cache-Control  |max-age=0          |
     *
     *              normalde biz once
     *                RequestSpecification request= storeapiinfo.get.headers(headers)
     *                StoreApiInfo.put(RequestInfo.REQUEST.info, request);
     *                bunu derduk ama biz getRequestSpecification burda zaten direk requesti aliyoruz sonrada storepai infoya koymaya gerek yok
     *                cunku rest assured bunu static olarak tutuyor zaten
     */
    protected void addHeaders(Map<String, Object> headers) {
        ApiHelper.getInstance().getRequestSpecification().headers(headers);
        log.info("{} headers added", headers);
    }

    /**
     * defineNewRequest() it add to the request a header.
     *
     * @param key   it is the header key.
     * @param value it is the header value
     */
    public void addHeader(String key, String value) {
        ApiHelper.getInstance().getRequestSpecification().header(key, value);
        log.info("{}, {} header added", key, value);
    }

    protected void addContentTypeJson(){
        ApiHelper.getInstance().getRequestSpecification().contentType(ContentType.JSON);
    }
    /**
     * it is add to the request SOAPAction as header.
     *
     * @param soapAction it is the SOAPAction
     * @throws RequestNotDefined if request is null, the exception will throw
     */
    protected void addSOAPAction(String soapAction) throws RequestNotDefined {
        ApiHelper.getInstance().getRequestSpecification().header("SOAPAction", soapAction);
    }

    /**
     * it is add to the request bearer token as header
     *
     * @param accessToken it is the token.
     */
    protected void addBearerTokenToHeader(String accessToken) {
        addHeader("Authorization", "Bearer " + accessToken);
    }

    /**
     * asagida biz file vs gondermek istedigimizde content type multipart/form-data; type olarak headeri ekleriz
     * istege mesela pdf ekleyip yollayabilirsin veya jpeg
     * bunu ekledigimde content type biraz farkli olur ayrica boundary diye birsey hesaplanmasi lazim sen o yuzden boundary key veriyorsun
     */
    protected void addMultiPartContentType(String defaultBoundary) {
        ApiHelper.getInstance().getRequestSpecification().contentType("multipart/form-data")
                .config(RestAssuredConfig
                        .config()
                        .multiPartConfig(multiPartConfig()
                        .defaultFileName(null).defaultBoundary(defaultBoundary)))
                .contentType("multipart/form-data;");
    }
}
