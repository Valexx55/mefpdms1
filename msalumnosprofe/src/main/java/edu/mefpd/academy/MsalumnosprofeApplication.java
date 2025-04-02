package edu.mefpd.academy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;

@SpringBootApplication
@EnableDiscoveryClient //activamos Eureka
public class MsalumnosprofeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MsalumnosprofeApplication.class, args);
	}
	
	/**
	 * Intento fallido de configurar manualemnte la l11n i18n
	 * @return
	 */
	@Bean
	@Profile({"dev"})
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
        messageSource.setBasename("classpath:traduccion");
        messageSource.setDefaultEncoding("UTF-8");
        return messageSource;
    }
	
	

}
