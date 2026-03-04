package es.gob.gesau.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("file:${sgtic.configpath}/aplicacion.properties")
@ComponentScan("es.gob")
public class GesauConfig {
}
