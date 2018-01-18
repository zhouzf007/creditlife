package com.entrobus.credit.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.zuul.EnableZuulProxy;
import org.springframework.context.annotation.Bean;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@SpringBootApplication
@EnableDiscoveryClient
@EnableZuulProxy
public class GatewayApplication {

	/*@Bean
	public CorsFilter corsFilter() {
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		CorsConfiguration corsConfiguration = new CorsConfiguration();
		corsConfiguration.addAllowedOrigin("*");
		corsConfiguration.addAllowedHeader("*");
		corsConfiguration.addAllowedMethod("*");
		corsConfiguration.addExposedHeader("loginStatus");//添加自定义输出请求头
		source.registerCorsConfiguration("/**", corsConfiguration);
		return new CorsFilter(source);
	}*/

	/**
	 * 全局处理跨域请求
	 * @return
	 */
//	@Bean
//	public CorsFilter corsFilter() {
//		final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
//		final CorsConfiguration config = new CorsConfiguration();
//		config.setAllowCredentials(true); // 允许cookies跨域
//		config.addAllowedOrigin("*");// #允许向该服务器提交请求的URI，*表示全部允许，在SpringMVC中，如果设成*，会自动转成当前请求头中的Origin
//		config.addAllowedHeader("*");// #允许访问的头信息,*表示全部
//		config.setMaxAge(18000L);// 预检请求的缓存时间（秒），即在这个时间段里，对于相同的跨域请求不会再预检了
//		config.addAllowedMethod("OPTIONS");// 允许提交请求的方法，*表示全部允许
//		config.addAllowedMethod("HEAD");
//		config.addAllowedMethod("GET");// 允许Get的请求方法
//		config.addAllowedMethod("PUT");
//		config.addAllowedMethod("POST");
//		config.addAllowedMethod("DELETE");
//		config.addAllowedMethod("PATCH");
//		source.registerCorsConfiguration("/**", config);
//		return new CorsFilter(source);
//	}
	public static void main(String[] args) {
		SpringApplication.run(GatewayApplication.class, args);
	}
}
