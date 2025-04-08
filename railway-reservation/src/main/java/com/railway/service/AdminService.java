package com.railway.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.railway.model.Admin;
import com.railway.repository.AdminRepository;

@Service
public class AdminService {

	@Autowired
	private AdminRepository adminRepository;

	public void saveAdmin(Admin admin) {
		adminRepository.save(admin);
	}

	public Optional<Admin> login(String username, String password) {
		return adminRepository.findByUsernameAndPassword(username, password);
	}
}
