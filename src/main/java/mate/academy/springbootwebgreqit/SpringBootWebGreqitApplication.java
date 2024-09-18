package mate.academy.springbootwebgreqit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "mate.academy.springbootwebgreqit.repository")
public class SpringBootWebGreqitApplication {
    public static void main(String[] args) {
        SpringApplication.run(SpringBootWebGreqitApplication.class, args);
    }
}
