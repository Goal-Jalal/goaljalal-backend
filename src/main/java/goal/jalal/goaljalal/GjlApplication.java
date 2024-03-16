package goal.jalal.goaljalal;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@EnableJpaAuditing
@SpringBootApplication
public class GjlApplication {

    public static void main(String[] args) {
        SpringApplication.run(GjlApplication.class, args);
    }

}
