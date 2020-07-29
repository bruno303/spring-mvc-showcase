package org.springframework.service;

import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.exceptions.TomcatStartException;

import java.io.File;

public class TomcatService {

    private static final Logger LOGGER = LoggerFactory.getLogger(TomcatService.class);

    public void createAndStartTomcatServer(File baseDir, int port) {
        Tomcat tomcat = createTomcatServer(baseDir, port);
        startTomcat(tomcat);
    }

    public void createAndStartTomcatServer(String baseDir, int port) {
        Tomcat tomcat = createTomcatServer(baseDir, port);
        startTomcat(tomcat);
    }

    private void startTomcat(Tomcat tomcat) {
        try {
            tomcat.start();
        } catch (LifecycleException e) {
            throw new TomcatStartException(e);
        }
        tomcat.getServer().await();
    }

    public Tomcat createTomcatServer(File baseDir, int port) {
        return createTomcatServer(baseDir.getAbsolutePath(), port);
    }

    public Tomcat createTomcatServer(String baseDir, int port) {
        Tomcat tomcat = new Tomcat();

        File baseDirFile = new File(baseDir);
        if (!baseDirFile.exists()) {
            throw new IllegalArgumentException(String.format("Directory '%s' doesn't exist", baseDir));
        }

        LOGGER.info("## Configuring tomcat port: {}", port);
        tomcat.setPort(port);
        tomcat.getConnector();

        LOGGER.info("## Adding webapp with directory: {}", baseDir);
        tomcat.addWebapp("/", baseDir);
        return tomcat;
    }

}
