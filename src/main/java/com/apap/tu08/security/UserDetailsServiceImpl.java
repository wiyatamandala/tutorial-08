package com.apap.tu08.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.apap.tu08.model.UserRoleModel;
import com.apap.tu08.repository.UserRoleDB;

@Service	
public class UserDetailsServiceImpl implements UserDetailsService{
	@Autowired
	private UserRoleDB userRoleDb;
	
	@Override
	public UserDetails loadUserByUsername(String username)throws UsernameNotFoundException{
		UserRoleModel user = userRoleDb.findByUsername(username);
		Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
		grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole()));
		return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
	}
}