package imp.methods;

import com.thoughtworks.gauge.Step;
import exceptions.RequestNotDefined;
import helper.methods.PostHelper;

public class PostRequestImp extends PostHelper {
    /**
     * gonderecegimiz post http requesti icin stepler
     * @throws RequestNotDefined
     */
    @Step({"Post request", "Post isteği gönder"})
    public void postRequests() throws RequestNotDefined {
        postRequest();

    }

    @Step({"Post request <url>", "Post isteği gönder <url>"})
    public void postRequests(String url) throws RequestNotDefined {
        postRequest(url);
    }
}
