package org.springframework;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import java.io.File;

public class SpringMvcShowCaseApplication {

    private static final Logger LOGGER = LoggerFactory.getLogger(SpringMvcShowCaseApplication.class);
    private static final int PORT = 8085;

    public static void main(String[] args) throws LifecycleException, ServletException {
        Tomcat tomcat = new Tomcat();
        String baseDir = new File("src/main/webapp").getAbsolutePath();

        LOGGER.info("## Configuring tomcat port: {}", PORT);
        tomcat.setPort(PORT);

        LOGGER.info("## Adding webapp with directory: {}", baseDir);
        tomcat.addWebapp("/", baseDir);

        LOGGER.info("## Starting tomcat: {}", baseDir);
        tomcat.start();
        tomcat.getServer().await();
    }

}
