package org.third.spring.boot.hello;

import org.apache.catalina.Context;
import org.apache.catalina.connector.Connector;
import org.apache.tomcat.util.descriptor.web.SecurityCollection;
import org.apache.tomcat.util.descriptor.web.SecurityConstraint;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.flyway.FlywayAutoConfiguration;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;
import org.springframework.boot.context.embedded.EmbeddedServletContainerFactory;
import org.springframework.boot.context.embedded.tomcat.TomcatEmbeddedServletContainerFactory;
import org.springframework.context.annotation.Bean;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableSwagger2
//@Configuration // 指出该类是 Bean 配置的信息源，相当于XML中的<beans></beans>，一般加在主类上。
//@ComponentScan // 表示将该类自动发现（扫描）并注册为Bean，可以自动收集所有的Spring组件（@Component , @Service , @Repository , @Controller 等），包括@Configuration类。
//@ComponentScan(basePackages = { "org.third.spring.boot.hello" })
@SpringBootApplication(exclude = { DataSourceAutoConfiguration.class, HibernateJpaAutoConfiguration.class,
		FlywayAutoConfiguration.class }) // @EnableAutoConfiguration、@ComponentScan和@Configuration的合集
public class HelloSpringApplication {
	public static void main(String[] args) {
		try {
			SpringApplication.run(HelloSpringApplication.class, args);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Bean
	public EmbeddedServletContainerFactory servletContainer() {
		TomcatEmbeddedServletContainerFactory tomcat = new TomcatEmbeddedServletContainerFactory() {
			@Override
			protected void postProcessContext(Context context) {
//				SecurityConstraint constraint = new SecurityConstraint();
//				constraint.setUserConstraint("CONFIDENTIAL");
//				SecurityCollection collection = new SecurityCollection();
//				collection.addPattern("/*");
//				constraint.addCollection(collection);
//				context.addConstraint(constraint);
			}
		};
		tomcat.addAdditionalTomcatConnectors(httpConnector());
		return tomcat;
	}

	@Bean
	public Connector httpConnector() {
		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		connector.setScheme("http");
		// Connector监听的http的端口号
		connector.setPort(18080);
		connector.setSecure(false);
		// 监听到http的端口号后转向到的https的端口号
		connector.setRedirectPort(18443);
		return connector;
	}
}
