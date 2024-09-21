package ru.practicum;

import lombok.extern.slf4j.Slf4j;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.Wrapper;
import org.apache.catalina.startup.Tomcat;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

@Slf4j
public class LaterApplicationWithDispatcherServler {

    public static void main(String[] args) throws LifecycleException {
        Tomcat tomcat = new Tomcat();
        log.info("Создан объект  контейнер Tomcat");
        tomcat.getConnector().setPort(8080);

        Context tomcatContext = tomcat.addContext("", null);
        log.info("Создан объект Context из Tomcat");

        AnnotationConfigWebApplicationContext applicationContext =
                new AnnotationConfigWebApplicationContext();
        applicationContext.scan("ru.practicum");
        log.info("Объект AnnotationConfigWebApplicationContext просканировал папку ru.practicum в поисках бинов");
        applicationContext.setServletContext(tomcatContext.getServletContext());
        applicationContext.refresh();

        // добавляем диспетчер запросов
        DispatcherServlet dispatcherServlet = new DispatcherServlet(applicationContext);
        Wrapper dispatcherWrapper =
                Tomcat.addServlet(tomcatContext, "dispatcher", dispatcherServlet);
        dispatcherWrapper.addMapping("/");
        dispatcherWrapper.setLoadOnStartup(1);

        log.info("Запускаем контейнер Tomcat");
        tomcat.start();
    }
}

