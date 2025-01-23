package com.hotdog.saas.domain.utils;

import com.hotdog.saas.domain.constant.Constants;

public class HttpUtils {

    public static Boolean validHttpMethod(String method) {
        return Constants.HTTP_METHOD.contains(method);
    }

    public static Boolean validHttpPost(String method){
        return Constants.HTTP_POST.equals(method);
    }

}
