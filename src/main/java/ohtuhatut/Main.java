
package ohtuhatut;

import ohtuhatut.profile.ProductionProfile;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

/**
 * Starts the application
 * 
 * @author tuomokar
 */
@Import({ProductionProfile.class})
@SpringBootApplication
public class Main {
    
    public static void main(String[] args) throws Exception {
        SpringApplication.run(Main.class, args);
    }
}
