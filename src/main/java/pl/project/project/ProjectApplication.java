package pl.project.project;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

@SpringBootApplication
public class ProjectApplication {

	/**
	 * do stworzenia własnych wiadomości;  usuwa komunikaty z pakietów zasobów dla różnych ustawień np NoBlank (domyślnych??)
	 *
	 * wykorzystanie nie standardoych komunikatów
	 * @return
	 */
	@Bean
	public MessageSource messageSource() {
		ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
		messageSource.setBasename("classpath:error-messages");
		messageSource.setDefaultEncoding("UTF-8");
		return messageSource;
	}

	/**
	 * Aby użyć niestandardowych komunikatów nazw w pliku właściwości, takich jak musimy zdefiniować LocalValidatorFactoryBean i zarejestrować messageSource
	 *
	 * @return
	 */
	@Bean
	public LocalValidatorFactoryBean validator() {
		LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
		bean.setValidationMessageSource(messageSource());
		return bean;
	}
	public static void main(String[] args) {
		SpringApplication.run(ProjectApplication.class, args);
		System.out.println("działam!!!");
	}

}

