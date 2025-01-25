package com.hotdog.saas.matrix.domain.utils;

import com.hotdog.saas.matrix.domain.constant.Constants;

public class HttpUtils {

    public static Boolean validHttpMethod(String method) {
        return Constants.HTTP_METHOD.contains(method);
    }

    public static Boolean validHttpPost(String method){
        return Constants.HTTP_POST.equals(method);
    }

}
