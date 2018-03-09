package study.brewer.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
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
    extends WebMvcConfigurerAdapter {
}
