package listener;

import facade.FacadeParisStaticImpl;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

public class InitApplication implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent
                                           servletContextEvent) {
        servletContextEvent.getServletContext()
                .setAttribute("facade",new FacadeParisStaticImpl());
    }
    @Override
    public void contextDestroyed(ServletContextEvent
                                         servletContextEvent) {
    }
}
