	package dao;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;
//import com.example.demo.entity.Role;


@Repository
public class UserDao {





	  private final static List<UserDetails> APPLICATION_USERS = Arrays.asList(
		        new User("sarah", "sarah", Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))),
			  new User("admin", "admin", Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))),

			  new User("user", "user", Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")))
		    );


	  @Bean
	public void saveUser(String name,String password) {

		// new User(name,password,Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));

		APPLICATION_USERS.add(



				new User(name,password,Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")))

		);
	}



		public UserDetails findUserByEmail(String email) {
		  
		  return APPLICATION_USERS
					.stream()
					.filter(u -> u.getUsername().equals(email))
				 ///  .filter(u->u.getPassword().equals(password))

				   .findFirst()
					.orElseThrow(()->new UsernameNotFoundException("user not found"));
		  
	  
	  }

}
