package cz.muni.fi.pa165.referenceManager;

import cz.muni.fi.pa165.referenceManager.rest.auth.WebSecurityConfig;
import org.springframework.web.context.request.RequestContextListener;
import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;

import javax.servlet.Filter;

public class Initializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[] {RootWebConfig.class, WebSecurityConfig.class};
    }

    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class[] {RootWebConfig.class, WebSecurityConfig.class};
    }

    @Override
    protected String[] getServletMappings() {
        return new String[]{"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter encodingFilter = new CharacterEncodingFilter();
        encodingFilter.setEncoding("utf-8");
        encodingFilter.setForceEncoding(true);

        return new Filter[]{encodingFilter};
    }

    @Override
    public void onStartup(javax.servlet.ServletContext servletContext) throws javax.servlet.ServletException {
        super.onStartup(servletContext);
        servletContext.addListener(RequestContextListener.class);
    }
}
