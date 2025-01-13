package com.elearn.app;

import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.elearn.app.config.AppConstants;
import com.elearn.app.entities.Role;
import com.elearn.app.entities.User;
import com.elearn.app.repositories.RoleRepo;
import com.elearn.app.repositories.UserRepo;

@SpringBootApplication
public class StartLearnBackendApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(StartLearnBackendApplication.class, args);
	}

	@Autowired
	private UserRepo userRepo;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private RoleRepo roleRepo;

	@Override
	public void run(String... args) throws Exception {

		Role adminRole = new Role();
		adminRole.setRoleName(AppConstants.ROLE_ADMIN);
		adminRole.setRoleId(UUID.randomUUID().toString());

		Role guestRole = new Role();
		guestRole.setRoleName(AppConstants.ROLE_GUEST);
		guestRole.setRoleId(UUID.randomUUID().toString());

		//creating admin role
		roleRepo.findByRoleName(AppConstants.ROLE_ADMIN).ifPresentOrElse(
			role -> {
				System.out.println(role.getRoleName()+" already exists");
			},
			() -> {
				roleRepo.save(adminRole);
			}
			);
		//creating guest role
		roleRepo.findByRoleName(AppConstants.ROLE_GUEST).ifPresentOrElse(
			role -> {
				System.out.println(role.getRoleName()+" already exists");
			},
			() -> {
				roleRepo.save(guestRole);
			}
			);

		User user1 = new User();
		user1.setUserId(UUID.randomUUID().toString());
		user1.setName("test");
		user1.setEmail("test@gmail.com");
		user1.setPassword(passwordEncoder.encode("test123"));
		user1.setActive(true);
		user1.setEmailVerified(false);
		user1.setCreateAt(new Date());
		user1.assignRole(guestRole);

		userRepo.findByEmail("test@gmail.com").ifPresentOrElse(user -> {
			System.out.println(user.getEmail() + "already exists");
		}, () -> {
			userRepo.save(user1);
			System.out.println(user1 +" created");
		});

		User user2 = new User();
		user2.setUserId(UUID.randomUUID().toString());
		user2.setName("vini");
		user2.setEmail("vini@gmail.com");
		user2.setPassword(passwordEncoder.encode("vini"));
		user2.setActive(true);
		user2.setEmailVerified(false);
		user2.setCreateAt(new Date());
		user2.assignRole(guestRole);
		user2.assignRole(adminRole);

		userRepo.findByEmail("vini@gmail.com").ifPresentOrElse(user -> {
			System.out.println(user.getEmail() + " already exists");
		}, () -> {
			userRepo.save(user2);
			System.out.println(user2 +" created");
		});
	}

}
