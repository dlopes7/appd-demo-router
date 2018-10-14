package com.appd.demo.services;

import com.appd.demo.models.TransferVO;
import com.appd.demo.responses.LoginResponse;
import com.appd.demo.models.LoginVO;
import com.appd.demo.models.RouteRequest;
import com.google.gson.Gson;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import okhttp3.*;

import java.io.IOException;

/**
 * Created by dlopes on 5/20/18.
 */

public class TransferService extends HystrixCommand<String> {

    private final String urlPath;
    private RouteRequest routeRequest;

    public TransferService(String urlPath, RouteRequest routeRequest){
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("LoginGroup"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().
                        withMetricsRollingStatisticalWindowInMilliseconds(60000)));

        this.urlPath = urlPath;
        this.routeRequest = routeRequest;

    }

    @Override
    protected String run() throws IOException {

        Gson gson = new Gson();
        TransferVO transferVO = gson.fromJson(routeRequest.getData(), TransferVO.class);

        // We are forwarding every header
        Headers headers = Headers.of(routeRequest.getHeaders());

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, gson.toJson(transferVO));
        Request request = new Request.Builder()
                .url(urlPath)
                .post(body)
                .headers(headers)
                .build();

        return post(request);
    }

    private String post(Request request) throws IOException {

        OkHttpClient client = new OkHttpClient();
        Response response = client.newCall(request).execute();


        return response.body().string();
    }

    @Override
    protected String getFallback() {

        Gson gson = new Gson();
        LoginResponse loginResponse = new LoginResponse();

        loginResponse.setMessage("Falha na transferencia!");
        loginResponse.setSuccess(false);

        return gson.toJson(loginResponse);
    }

}

