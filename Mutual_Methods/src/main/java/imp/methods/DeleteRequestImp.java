package imp.methods;

import com.thoughtworks.gauge.Step;
import exceptions.RequestNotDefined;
import helper.methods.DeleteHelper;
import utils.Utils;

public class DeleteRequestImp extends DeleteHelper {


    @Step({"Delete request", "Delete isteği gönder"})
    public void deleteRequests() throws RequestNotDefined {
        String isDelete = String.valueOf(Utils.getFromStoreData("deleteAfterTest"));
        if (!isDelete.equals("No")) {

            deleteRequest();
        }
        System.out.println("Deletion Result:"+isDelete);
    }

    @Step({"Delete request <url>", "Delete isteği gönder <url>"})
    public void deleteRequests(String url) throws RequestNotDefined {
        deleteRequest(url);
    }
}
