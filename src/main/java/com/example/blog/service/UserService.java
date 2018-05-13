package com.example.blog.service;

import com.example.blog.entity.Role;
import com.example.blog.entity.User;
import com.example.blog.repository.RoleRepository;
import com.example.blog.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashSet;

@Service("userService")
public class UserService implements IUserService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;

	@Override
	public User findUserByEmail(String email) {
		return userRepository.findByEmail(email);
	}

	@Override
	public User findUserByNick(String nick) {
		return userRepository.findByNick(nick);
	}

	@Override
	public void saveUser(User user) {
		user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
		user.setActive(1);
		Role userRole = roleRepository.findByRole("ROLE_USER");
		user.setRoles(new HashSet<>(Arrays.asList(userRole)));
		userRepository.save(user);
	}

	@Override
	public void updateUser(String email, User user) {
		User userFromDatabase = findUserByEmail(email);
		userFromDatabase.setFirstName(user.getFirstName());
		userFromDatabase.setLastName(user.getLastName());
		userRepository.save(userFromDatabase);
	}

	@Override
	public void changeUserPassword(String email, String password) {
		User userFromDatabase = findUserByEmail(email);
		userFromDatabase.setPassword(bCryptPasswordEncoder.encode(password));
		userRepository.save(userFromDatabase);
	}

	@Override
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public void toggleUserStatus(String nick) {
		User user = findUserByNick(nick);
		if (user.getActive() == 0){
			user.setActive(1);
		}
		else {
			user.setActive(0);
		}

		userRepository.save(user);
	}
}