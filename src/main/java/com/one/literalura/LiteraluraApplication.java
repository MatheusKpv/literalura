package com.one.literalura;

import com.one.literalura.config.DotenvInitializer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiteraluraApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(LiteraluraApplication.class);
        app.addInitializers(new DotenvInitializer());
        app.run(args);
    }

}
