package edu.mefpd.academy.mscursosprofe;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan("edu.mefpd.academy.mscomunprofe.entity")
public class MscursosprofeApplication {

	public static void main(String[] args) {
		SpringApplication.run(MscursosprofeApplication.class, args);
	}

}
