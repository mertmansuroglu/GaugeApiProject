package imp;

import com.thoughtworks.gauge.AfterSpec;
import enums.RequestInfo;
import helper.ApiHelper;
import helper.AuthHelper;
import helper.HeaderHelper;
import helper.RequestUrlHelper;
import io.restassured.response.Response;
import utils.StoreApiInfo;
import utils.Utils;

public class Hooks  {
    @AfterSpec(tags = {"UserUpdate"})
    public  void preSpec() {
        String userKey= String.valueOf(Utils.getFromStoreData("updatedUserKey"));
        System.out.println("Deneme:  Acaba::"+     String.valueOf(Utils.getFromStoreData("updatedUserKey")));
        ApiHelper.getInstance().defineNewRequest();
        ApiHelper.getInstance().getRequestSpecification().baseUri("https://4.184.73.178:5001");
        String endPoint= "/identity/delete_user_by_key/"+userKey;
        ApiHelper.getInstance().getRequestSpecification().basePath(endPoint);
        String token = String.valueOf(Utils.getFromStoreData("TOKEN"));
        HeaderHelper headerHelper = new HeaderHelper();
        headerHelper.addHeader("Authorization",token);
        ApiHelper.getInstance().getRequestSpecification().relaxedHTTPSValidation();
        Response response = ApiHelper.getInstance().getRequestSpecification().delete()
                .then()
                .extract()
                .response();
        StoreApiInfo.put(RequestInfo.RESPONSE.value, response);
    }
}
