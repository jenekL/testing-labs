package uni.labs.testlabs;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@EnableJpaAuditing
@EnableJpaRepositories(basePackages = "uni.labs.testlabs.repository")
@SpringBootApplication
public class TestlabsApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestlabsApplication.class, args);
	}

}
