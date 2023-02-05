package imp;

import com.thoughtworks.gauge.Step;
import helper.RequestUrlHelper;
import utils.Utils;


public class UrlImp extends RequestUrlHelper {


    /**
     *Asagida requestimize base url veya base path yani endpointimizi ekleyen step yazdik
     */
    @Step({"Add base url <url>", "Url ekle <url>"})
    public void addBaseUrlToReq(String url) {
        addBaseUrl(url);
    }

    @Step({"Add endpoint <url>", "Endpoint ekle <url>", "Add base path <url>"})
    public void addBasePathToReq(String url) {

       String newURL= (String) Utils.getFromStoreData(url);
        addBasePath(newURL);
    }
}
