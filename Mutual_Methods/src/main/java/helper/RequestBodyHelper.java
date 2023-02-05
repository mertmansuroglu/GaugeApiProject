package helper;


import exceptions.RequestNotDefined;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.Utils;

import java.io.File;
import java.io.InputStream;
import java.util.Map;

public class RequestBodyHelper {
    // TODO: 11/6/2022 10. buradan baslanilacak anlatmaya
    private final Logger log = LogManager.getLogger(RequestBodyHelper.class);
    private static final String LOG_INFO = "Body added to request \n Body detail: {}";

    /**
     * Add payload to request.
     *
     * @param body is request body as object
     * @throws RequestNotDefined if request is null, the exception will throw
     *
     * diyelim post/put/path methoduna body ekleyecez o islem yapilir getRequestSpecification().body(body);
     * sonra ekledigim bodyide console a loglarim  prettyprint seklinde
     *
     *burdaki serializatino kismini rest assured yapiyor tek yaptigimiz biz dependency veriyoruz jackson/gson gibi kendisi yapiyor
     * ben object/file/Stringi atiyorum kendisi direk serialize ediyor  convert java object to byte stream/json object!!!
     * getRequestSpecification().body(body);
     */
    protected void addBody(Object body) throws RequestNotDefined {
        Utils utils = new Utils();
        ApiHelper.getInstance().getRequestSpecification().body(body);
        String stringBody = utils.prettyPrint(String.valueOf(body));
        log.info(LOG_INFO, stringBody);
    }

    /**
     * Add payload to request.
     *
     * @param body is request body as Map
     * @throws RequestNotDefined if request is null, the exception will throw
     */
    protected void addBody(Map<Object, Object> body) throws RequestNotDefined {

        ApiHelper.getInstance().getRequestSpecification().body(body);
        log.info(LOG_INFO, body);
    }

    /**
     * Add payload to request.
     *
     * @param body is request body as String
     * @throws RequestNotDefined if request is null, the exception will throw
     */
    protected void addBody(String body) throws RequestNotDefined {
        ApiHelper.getInstance().getRequestSpecification().body(body);
        log.info(LOG_INFO, body);
    }

    /**
     * Add payload to request.
     *
     * @param body is request body as file
     * @throws RequestNotDefined if request is null, the exception will throw
     */
    protected void addBody(File body) throws RequestNotDefined {
        ApiHelper.getInstance().getRequestSpecification().body(body);
        log.info(LOG_INFO, body.getAbsolutePath());
    }

    /**
     * Add payload to request.
     *
     * @param body is request body as InputStream
     * @throws RequestNotDefined if request is null, the exception will throw
     */
    protected void addBody(InputStream body) throws RequestNotDefined {
        ApiHelper.getInstance().getRequestSpecification().body(body);
        var utils = new Utils();
        var stringBody = utils.prettyPrint(body.toString());
        log.info(LOG_INFO, stringBody);
    }
}
