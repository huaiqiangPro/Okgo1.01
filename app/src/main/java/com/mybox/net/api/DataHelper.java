package com.mybox.net.api;



/**
 * Created by yuerq on 16/9/21.
 */

public class DataHelper {

    private static CntvRepository cntvRepository;

    public static CntvRepository getCntvRepository() {
        if (cntvRepository == null) {
            cntvRepository = new CntvDataRepository();
        }
        return cntvRepository;
    }

}
