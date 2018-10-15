package com.appd.demo;

import com.appd.demo.models.RouteRequest;
import com.appd.demo.services.BalanceService;
import com.appd.demo.services.LoginService;
import com.appd.demo.services.TransferService;
import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Enumeration;

/**
 * Created by dlopes on 5/20/18.
 */

@RestController
public class RouterController {

    @Autowired
    private HttpServletRequest context;

    @Autowired
    private Environment env;

    @RequestMapping(value = "/appd-router", produces = "application/json")
    public String router(@RequestBody String body) throws Exception{
        //System.out.println(body);

        Gson gson = new Gson();
        RouteRequest routeRequest = gson.fromJson(body, RouteRequest.class);

        // We have to forward every header we got in
        Enumeration<String> headerNames = context.getHeaderNames();
        while (headerNames.hasMoreElements()){
            String headerName = headerNames.nextElement();
            String headerValue = context.getHeader(headerName);

            // Don't forward singularityheader, don't want to break correlation
            if(!headerName.equals("singularityheader")) {
                routeRequest.getHeaders().put(headerName, headerValue);
            }
        }

        switch (routeRequest.getMethod()) {
            case "login" :
                System.out.println("ROUTER - Doing login");
                return new LoginService(env.getProperty("app.login.url") + "/api/login", routeRequest).execute();

            case "transfer" :
                System.out.println("ROUTER - Doing transfer");
                return new TransferService(env.getProperty("app.account.url") + "/api/transfer", routeRequest).execute();

            case "balance" :
                System.out.println("ROUTER - Doing balance");
                return new BalanceService(env.getProperty("app.account.url") + "/api/balance", routeRequest).execute();

            default :
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("").toString();
        }

    }
}
