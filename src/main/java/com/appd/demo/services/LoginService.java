package com.appd.demo.services;

import com.appd.demo.models.LoginResponse;
import com.appd.demo.models.LoginVO;
import com.appd.demo.models.RouteRequest;
import com.google.gson.Gson;
import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandProperties;
import okhttp3.*;

import javax.validation.constraints.NotNull;
import java.io.IOException;
import java.util.Map;

/**
 * Created by dlopes on 5/20/18.
 */

public class LoginService extends HystrixCommand<String> {

    private final String urlPath;
    private RouteRequest routeRequest;

    public LoginService(String urlPath, RouteRequest routeRequest){
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("LoginGroup"))
                .andCommandPropertiesDefaults(HystrixCommandProperties.Setter().
                        withMetricsRollingStatisticalWindowInMilliseconds(60000)));

        this.urlPath = urlPath;
        this.routeRequest = routeRequest;

    }

    @Override
    protected String run() throws IOException {

        Gson gson = new Gson();
        LoginVO loginVO = gson.fromJson(routeRequest.getData(), LoginVO.class);

        // We are forwarding every header
        Headers headers = Headers.of(routeRequest.getHeaders());

        MediaType JSON = MediaType.parse("application/json; charset=utf-8");
        RequestBody body = RequestBody.create(JSON, gson.toJson(loginVO));
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

        loginResponse.setMessage("Falha no Login!");
        loginResponse.setSuccess(false);

        return gson.toJson(loginResponse);
    }

}

