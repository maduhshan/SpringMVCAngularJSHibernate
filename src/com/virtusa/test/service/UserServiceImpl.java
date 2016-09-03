package com.virtusa.test.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.virtusa.test.model.User;

@Service("userService")
public class UserServiceImpl implements UserService {

	private static final AtomicLong counter = new AtomicLong();

	private static List<User> users;

	static {
		users = populateDummyUsers();
	}

	@Override
	public User findById(long id) {
		for (User user : users) {
			if (user.getId() == id) {
				return user;
			}
		}
		return null;
	}

	@Override
	public User findByName(String name) {
		for (User user : users) {
			if (user.getUsername().equalsIgnoreCase(name)) {
				return user;
			}
		}
		return null;
	}

	@Override
	public void saveUser(User user) {
		user.setId(counter.incrementAndGet());
		users.add(user);

	}

	@Override
	public void updateUser(User user) {
		int index = users.indexOf(user);
        users.set(index, user);
	}

	@Override
	public void deleteUserById(long id) {
		for (User user : users) {
			if (user.getId() == id) {
				users.remove(user);
			}
		}

	}

	@Override
	public List<User> findAllUsers() {
		return users;
	}

	@Override
	public void deleteAllUsers() {
		users.clear();
	}

	@Override
	public boolean isUserExist(User user) {
		return findByName(user.getUsername()) != null;
	}

	private static List<User> populateDummyUsers() {
		List<User> users = new ArrayList<User>();
		users.add(new User(counter.incrementAndGet(), "Madushan", "SL", "madushan@abc.com"));
		users.add(new User(counter.incrementAndGet(), "Shenali", "AUS", "shenali@abc.com"));
		users.add(new User(counter.incrementAndGet(), "Chathuranga", "UK", "chathuranga@abc.com"));
		return users;
	}

}
