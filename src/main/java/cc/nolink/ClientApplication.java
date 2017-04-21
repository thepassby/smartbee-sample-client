/**
 * 
 */
package cc.nolink;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author reset
 *
 */
@SpringBootApplication
@EnableSwagger2
@ComponentScan(basePackages = {"io.swagger", "cc.nolink"})
public class ClientApplication implements CommandLineRunner {

	/* (non-Javadoc)
	 * @see org.springframework.boot.CommandLineRunner#run(java.lang.String[])
	 */
	@Override
	public void run(String... arg0) throws Exception {
		// TODO Auto-generated method stub

	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		SpringApplication.run(ClientApplication.class, args);
	}
}
