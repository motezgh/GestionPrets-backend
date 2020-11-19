package com.pfe.gestionPret;

import java.util.stream.Stream;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.rest.core.config.RepositoryRestConfiguration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.pfe.gestionPret.entities.AppRole;
import com.pfe.gestionPret.entities.AppUser;
import com.pfe.gestionPret.entities.Erole;
import com.pfe.gestionPret.entities.Pret;
import com.pfe.gestionPret.service.AccountService;

@SpringBootApplication
public class Application implements CommandLineRunner  {

	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}
	
	@Autowired
	private RepositoryRestConfiguration repositoryRestConfiguration; 
	
	@Override
	public void run(String... args) throws Exception {
		
		repositoryRestConfiguration.exposeIdsFor(AppUser.class,Pret.class,AppRole.class);
	}

/*
	@Bean
    CommandLineRunner start(AccountService accountService){
        return args->{
            accountService.save(new AppRole(null,Erole.EMPLOYE));
            accountService.save(new AppRole(null,Erole.RH));
            Stream.of("user1","user2","user3","admin").forEach(un->{
                accountService.saveUser(un,null,"1234", 1000, null, null, null);
            });
            accountService.addRoleToUser("admin",Erole.RH);
        };
	}
*/

	@Bean
    BCryptPasswordEncoder getBCPE(){
        return new BCryptPasswordEncoder();
    }
	
}
