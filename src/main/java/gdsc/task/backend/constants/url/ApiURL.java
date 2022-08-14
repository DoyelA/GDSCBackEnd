package gdsc.task.backend.constants.url;

public class ApiURL {
    private ApiURL() {

    }

    private static final String BASE_URL = "api/v1";
    public static final String PRODUCT_URL = BASE_URL + "/products";
    public static final String PRODUCT_ADD_URL = PRODUCT_URL + "/add";
    public static final String PRODUCT_UPDATE_URL = PRODUCT_URL + "/update";
    public static final String PRODUCT_DELETE_URL = PRODUCT_URL + "/delete";

}
