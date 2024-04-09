package comx;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EntityScan(basePackages = "comx.entity")
@EnableJpaRepositories(basePackages ="comx.repository")
@SpringBootApplication(scanBasePackages = "comx")
public class AdvancedJavaExamApplication {

	public static void main(String[] args) {
		SpringApplication.run(AdvancedJavaExamApplication.class, args);
	}

}
