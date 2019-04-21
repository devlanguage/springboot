package org.third.spring.boot.hello;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

import springfox.documentation.swagger2.annotations.EnableSwagger2;
@EnableSwagger2
//@Configuration // 指出该类是 Bean 配置的信息源，相当于XML中的<beans></beans>，一般加在主类上。
//@ComponentScan // 表示将该类自动发现（扫描）并注册为Bean，可以自动收集所有的Spring组件（@Component , @Service , @Repository , @Controller 等），包括@Configuration类。
//@ComponentScan(basePackages = { "org.third.spring.boot.hello" })
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class,
		FlywayAutoConfiguration.class }) //@EnableAutoConfiguration、@ComponentScan和@Configuration的合集
public class HelloSpringApplication {
	public static void main(String[] args) {
		SpringApplication.run(HelloSpringApplication.class, args);
//		 Provider jsse = Security.getProvider("BCJSSE");
//		 Provider BCFIPSe = Security.getProvider("BCFIPS");
//
//		 System.out.println(jsse != null && jsse.getInfo().contains("FIPS"));
//		 
	}
}
