package imp;

import com.thoughtworks.gauge.Step;
import helper.ApiHelper;
import io.cucumber.java.en.Given;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import utils.Utils;


public class RequestImp {

    private final Logger log = LogManager.getLogger(RequestImp.class);
/**
 * here, we are creating our steo for defining new request by initiating init method
 */
    @Step({"Define new request", "Yeni bir api isteği tanımla"})
    @Given("Define new request")
    public void defineNewApiRequest() {
        ApiHelper.getInstance().defineNewRequest();
        log.info("New request defined");
    }


}
