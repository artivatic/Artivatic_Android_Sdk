package com.artivatic.artivaticsdk.rest;


import java.io.IOException;

import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

/**
 * Created by Soham Dutta on 21-01-2016.
 */
public class SessionRequestInterceptor implements Interceptor {

//    private static String signatureKey = ApplicationProperties.getSignatureHash();





    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        request = request.newBuilder()
                .addHeader("headerKey0", "HeaderVal0")
                .addHeader("headerKey0", "HeaderVal0--NotReplaced/NorUpdated") //new header added
                .build();

        //alternative
        Headers moreHeaders = request.headers().newBuilder()
                .add("X-API-Key", "stage")
                .build();

        request = request.newBuilder().headers(moreHeaders).build();

        Response response = chain.proceed(request);
        return response;
    }
   /* public static void addUAHeaders(RequestInterceptor.RequestFacade request) {
        SessionManager sessionManager = new SessionManager(CogxioApp.getAppContext());
        request.addHeader("User-Agent", sessionManager.getUserAgent());
        request.addHeader("Browser-Agent", sessionManager.getBrowserUA());
        request.addHeader("Device-Id", DeviceInfoProvider.getDeviceId());
    }*/
}
