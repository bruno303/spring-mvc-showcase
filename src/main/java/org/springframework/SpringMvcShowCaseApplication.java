package org.springframework;

import org.springframework.service.TomcatService;

import java.io.File;

public class SpringMvcShowCaseApplication {

    private static final int PORT = 8080;

    public static void main(String[] args) {
        final TomcatService tomcatService = new TomcatService();

        File baseDir = new File("src/main/webapp");
        tomcatService.createAndStartTomcatServer(baseDir, PORT);
    }

}
