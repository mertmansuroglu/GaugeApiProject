package helper;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Objects;

import static java.lang.Object.*;

public class JsonListFilterHelper {

    /**
     * "tags": [
     *     {
     *       "id": 2,
     *       "name": "kuçukuçu"
     *     },
     *     {
     *       "id": 3,
     *       "name": "kuş"
     *     },
     *
     *     diyelim ben yukarda name i kus olanin id sini istiyorum asagidaki method bunu bulmaya yarar
     *     buraya json arraylist i yolluyoruz sonra hangi filterSelector ve filterValue ise onu istenen selectorude veriyoruz
     *     var jsonPath = (List<LinkedHashMap<String, Object>>) jsonPathObj;  burda biz json arraylisti yolluyoruz kendisi cast islemini yapiyor
     *     sonrada dongu kurup istenn filter selector filter value ya esitse requestedSelector getirir
     * @param jsonPathObj
     * @param filterSelector
     * @param filterValue
     * @param requestedSelector
     * @return
     */
    protected Object getJsonValueByFilter(Object jsonPathObj, String filterSelector, String filterValue, String requestedSelector) {
        @SuppressWarnings("unchecked")
        var jsonPath = (List<LinkedHashMap<String, Object>>) jsonPathObj;
        jsonPath.size();
        for (LinkedHashMap<String, Object> json : jsonPath) {
            String json_result = String.valueOf(json.get(filterSelector));
            if (json_result.equalsIgnoreCase(filterValue)) {
                return json.get(requestedSelector);
            }
        }
        return null;
    }
    protected Object getJsonObjectCountInArray(Object jsonPathObj) {
        @SuppressWarnings("unchecked")
        var jsonPath = (List<LinkedHashMap<String, Object>>) jsonPathObj;
        return jsonPath.toArray().length;

    }

}
