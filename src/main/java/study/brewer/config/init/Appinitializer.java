package study.brewer.config.init;

import org.springframework.web.filter.CharacterEncodingFilter;
import org.springframework.web.servlet.support.AbstractAnnotationConfigDispatcherServletInitializer;
import study.brewer.config.WebConfig;

import javax.servlet.Filter;

public class Appinitializer extends AbstractAnnotationConfigDispatcherServletInitializer {
    @Override
    protected Class<?>[] getRootConfigClasses() {
        return new Class[0];
    }

    // Retorna a classe de configuração que ajudará o Spring a encontrar o Controler
    @Override
    protected Class<?>[] getServletConfigClasses() {
        return new Class<?>[] { WebConfig.class };
    }

    // Define o padrão da URL que será delegada ao DispatcherServlet
    @Override
    protected String[] getServletMappings() {
        // receberá qualquer requisição de [nome_da_aplicação]/ para frente
        return new String[] {"/"};
    }

    @Override
    protected Filter[] getServletFilters() {
        CharacterEncodingFilter characterEncodingFilter = new CharacterEncodingFilter();
        characterEncodingFilter.setEncoding("UTF-8");
        characterEncodingFilter.setForceEncoding(true);

        return new Filter[] { characterEncodingFilter };
    }
}
