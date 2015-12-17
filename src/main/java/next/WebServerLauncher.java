package next;

import java.io.File;

import org.apache.catalina.startup.Tomcat;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class WebServerLauncher {
	private static final Logger logger = LoggerFactory.getLogger(WebServerLauncher.class);
	
	public static void main(String[] args) throws Exception {
        String webappDirLocation = "webapp/";
        Tomcat tomcat = new Tomcat(); //톰캣 인스턴스 생성 
        tomcat.setPort(8080); //포트번호 설정

        tomcat.addWebapp("/", new File(webappDirLocation).getAbsolutePath()); //웹앱의 가장 상위의 위치를 webapp/으로 설정한다.
        logger.info("configuring app with basedir: {}", new File("./" + webappDirLocation).getAbsolutePath());

        tomcat.start(); //톰캣을 시작한다.
        tomcat.getServer().await();
    }
}
