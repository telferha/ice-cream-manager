package io.github.pbremer.icecreammanager;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.annotation.Import;

@SpringBootApplication
@Import(ApplicationConfiguration.class)
public class Application {

    public static void main(final String[] args) {
	new SpringApplicationBuilder(Application.class)
	        .listeners(new ApplicationLoadEventLister()).run(args);
	/*
	 * SpringApplication.run(Application.class, args)
	 * .addApplicationListener(new ApplicationLoadEventLister());
	 */
    }

}
