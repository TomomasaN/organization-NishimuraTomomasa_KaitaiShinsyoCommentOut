package com.example.domain.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.example.domain.service.UserService;
import com.example.domain.user.model.MUser;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	//ユーザー認証のサービスを作る
	@Autowired
	//自動でインスタンスを生成
	private UserService service;

	@Override
	//オーバーライドする時に、引数の型などが間違ってないか確認できる
	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException {

		//ユーザー情報取得
		MUser loginUser = service.getLoginUser(username);

		//ユーザーが存在しない場合
		if (loginUser == null) {
			throw new UsernameNotFoundException("user not found");
		}

		//権限List作成
		GrantedAuthority authority = new SimpleGrantedAuthority(loginUser.getRole());
		List<GrantedAuthority> authorities = new ArrayList<>();
		authorities.add(authority);

		//UserDetails生成
		UserDetails userDetails = (UserDetails) new User(loginUser.getUserId(), loginUser.getPassword(), authorities);

		return userDetails;
	}

}
