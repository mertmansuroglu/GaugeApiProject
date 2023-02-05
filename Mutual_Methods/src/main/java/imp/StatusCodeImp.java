package imp;

import com.thoughtworks.gauge.Step;
import exceptions.NullResponse;
import helper.StatusCodeHelper;

public class StatusCodeImp extends StatusCodeHelper {
    /**
     * burda responsetan gelen statucode unu dogrulamak icin methodlar mevcut
     */
    @Step({"Check if status code is <code>",
            "Statü kodunu kontrol et <kod> mü?",
            "Verify that the status code is <code>"})
    public void checkStatusCodeFromRes(String key) throws NullResponse {
        if (!key.equals("null"))
                checkStatusCode(Integer.parseInt(key));
    }
}
