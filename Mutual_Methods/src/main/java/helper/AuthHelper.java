package helper;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public class AuthHelper {
    // TODO: 11/6/2022 3. buradan baslanilacak anlatmaya
    private final Logger log = LogManager.getLogger(AuthHelper.class);

    /**
     *
     * @param user
     * @param password
     *
     * biz user ve pwd yi verip burda basicAuth yapiyoruz
     */
    protected void basicAuth(String user, String password)  {
        ApiHelper.getInstance().getRequestSpecification().auth().basic(user, password);
        log.info("Basic auth to request as user: {}, password{}", user, password);
    }

    /**
     * bazi servisler ozellikle spring ile yazilanlar sen istegi attiginda ben authentication bilgilerinide istiyorum
     * sen istegi yollamadin diyip ikinci sansi vermiyor
     * yani basic authda dogru bilgiler yolladigin halde sana unauthorized falan donuyor
     * sen preemptive() metodunu cagirdiginda karsi tarafa authentication bilgisi ie atar sunucu tarafina
     * */
    protected void preBasicAuth(String user, String password)  {
        ApiHelper.getInstance().getRequestSpecification().auth().preemptive().basic(user, password);
    }

    /**
     * biz istenen bearer tokenu gonderip bearer authorization yapiyoruz
     * @param token
     */
    protected void addBearerToken(String token){
        HeaderHelper headerHelper = new HeaderHelper();
        headerHelper.addBearerTokenToHeader(token);
    }
    protected void addAuthToken(String token){
        HeaderHelper headerHelper = new HeaderHelper();
        headerHelper.addHeader("Authorization",token);
    }
    protected void oauth2(String var1){
        ApiHelper.getInstance().getRequestSpecification().auth().oauth2(var1);
    }

}
