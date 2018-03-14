package study.brewer.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.spring4.SpringTemplateEngine;
import org.thymeleaf.spring4.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring4.view.ThymeleafViewResolver;
import org.thymeleaf.templatemode.TemplateMode;
import org.thymeleaf.templateresolver.ITemplateResolver;
import study.brewer.controller.CervejasController;

@Configuration
// Poderia especificar conforme abaixo, com o nome do pacote onde está o Controller.
//@ComponentScan("study.brewer.controller")
// Mas como utilizamos a anotação @Controller, utilizamos o padrão basePackageClasses, que basicamente informa
// ao Spring que deve procurar os Controllers que estão no mesmo pacote das classes definidas no array de Strings
// Dessa forma, mesmo que mudássemos as estruturas de pacotes, continuaria funcionando
@ComponentScan(basePackageClasses = {CervejasController.class})
@EnableWebMvc
public class WebConfig

    // Spring permite que utilizemos adaptadores que facilitam as nossas configurações
    extends WebMvcConfigurerAdapter

    // para poder receber o application context quando a aplicação subir
        implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    // Com essa anotação, esse objeto fica disponível no contexto spring
    @Bean
    public ViewResolver viewResolver() {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        resolver.setCharacterEncoding("UTF-8");
        resolver.setContentType("text/html; charset=UTF-8");
        return resolver;
    }

    // Com essa anotação, esse objeto fica disponível no contexto spring
    @Bean
    public TemplateEngine templateEngine() {
        SpringTemplateEngine engine = new SpringTemplateEngine();
        engine.setEnableSpringELCompiler(true);
        engine.setTemplateResolver(templateResolver());
        return engine;
    }

    private ITemplateResolver templateResolver() {
        SpringResourceTemplateResolver resolver = new SpringResourceTemplateResolver();
        // Thymeleaf precisa do applicationContext
        resolver.setApplicationContext(applicationContext);
        // os templates estarão nessa pasta, em resources
        resolver.setPrefix("classpath:/templates/");
        resolver.setSuffix(".html");
        resolver.setTemplateMode(TemplateMode.HTML);
        resolver.setCharacterEncoding("UTF-8");
        return resolver;
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/**").addResourceLocations("classpath:/static/");
    }
}
