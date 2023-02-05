package imp;

import com.thoughtworks.gauge.Step;
import helper.HttpsHelper;
import utils.Utils;

public class HttpsImp extends HttpsHelper {
    // TODO: 11/10/2022 helperlar bittikten sonra 7.sira
    @Step({"Add relaxed HTTPS validation",
            "Varsayılan https sertifkası ekleyin"})
    public void addRelaxedHTTPSValidationToRequest() {

        addRelaxedHTTPSValidation();
    }
}
