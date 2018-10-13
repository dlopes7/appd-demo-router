package com.appd.demo;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class RouterApplication {


	public static void main(String[] args) {
		new SpringApplicationBuilder(RouterApplication.class).web(WebApplicationType.SERVLET).run();
	}

	@Bean
    public ServletRegistrationBean<HystrixMetricsStreamServlet> hystrixMetrics(){
        return new ServletRegistrationBean<HystrixMetricsStreamServlet>(new HystrixMetricsStreamServlet(),"/hystrix.stream");
    }

}
