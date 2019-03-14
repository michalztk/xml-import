package com.uploader;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateJpaAutoConfiguration;

/*class HibernateJpaAutoConfiguration was excluded because of the following error:
org.springframework.orm.jpa.EntityManagerHolder cannot be cast to org.springframework.orm.hibernate5.SessionHolder
*/
@SpringBootApplication(exclude = HibernateJpaAutoConfiguration.class)
public class XmlUploaderApplication {

	public static void main(String[] args) {
		SpringApplication.run(XmlUploaderApplication.class, args);
	}

}
