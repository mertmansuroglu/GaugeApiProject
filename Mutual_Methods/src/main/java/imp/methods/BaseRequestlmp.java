package imp.methods;

import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.datastore.ScenarioDataStore;
import exceptions.RequestNotDefined;
import utils.Utils;

public class BaseRequestlmp {
    @Step({"Build Request depends on  <Method>"})
    public void BuildRequestsDependOnMethodName( String methodName) throws RequestNotDefined {
        if (methodName.toLowerCase().equals("post")){
            new PostRequestImp().postRequests();
        } else {
            try {
                if (methodName.toLowerCase().equals("get")) {
                    new GetRequestImp().getRequests();
                } else if (methodName.toLowerCase().equals("put")) {
                    new PutRequestsImp().putRequests();
                } else if (methodName.toLowerCase().equals("delete")) {
                    new DeleteRequestImp().deleteRequests();
                }
            }
            catch (Exception e){
                System.out.println("hata::"+e.getMessage());
                ScenarioDataStore.put("responseValue", e.getMessage());
            }

        }
    }
}
