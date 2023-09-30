package com.example.domain.service.impl;

import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Primary;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.data.domain.ExampleMatcher.StringMatcher;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.domain.service.UserService;
import com.example.domain.user.model.MUser;
import com.example.repository.UserRepository;

@Service
@Primary
//このクラスが優先してDIされる
public class UserServiceImpl2 implements UserService {
	
	@Autowired
	//インスタンスの生成
	private UserRepository repository;
	
	@Autowired
	//インスタンスの生成
	private PasswordEncoder encoder;
	
	/** ユーザー登録 */
	@Transactional
	//複数の処理をまとめる仕組み
	@Override
	//オーバーライドする時に、引数の型などが間違ってないか確認できる
	public void signup(MUser user) {
		//存在チェック
		boolean exists = repository.existsById(user.getUserId());
		if(exists) {
			throw new DataAccessException("ユーザーが既に存在"){};	
		}
		
		user.setDepartmentId(1);
		user.setRole("ROLE_GENERAL");
		
		//パスワード暗号化
		String rawPassword = user.getPassword();
		user.setPassword(encoder.encode(rawPassword));
		
		//insert
		repository.save(user);
		//登録・更新
	}
	
	/**　ユーザー取得 */
	@Override
	//オーバーライドする時に、引数の型などが間違ってないか確認できる
	public List<MUser> getUsers(MUser user) {
		
		//検索条件
		ExampleMatcher matcher = ExampleMatcher
				//and条件、or条件、LIKE検索などの指定ができる
				.matching()//and条件
				.withStringMatcher(StringMatcher.CONTAINING)//Like句
				.withIgnoreCase();//大文字・小文字の両方
		
		return repository.findAll(Example.of(user, matcher));
		//findAll→全件検索　Example→動的SQLを生成して検索条件を変更する
	}
	
	/** ユーザー取得（１件） */
	@Override
	//オーバーライドする時に、引数の型などが間違ってないか確認できる
	public MUser getUserOne(String userId) {
		Optional<MUser> option = repository.findById(userId);
		//主キー検索
		MUser user = option.orElse(null);
		return user;
	}
	
	/** ユーザー更新（1件） */
	@Transactional
	@Override
	public void updateUserOne(String userId, String password, String userName) {
		
		//パスワード暗号化
		String encryptPassword = encoder.encode(password);
		
		//ユーザー更新
		repository.updateUser(userId, encryptPassword, userName);
		
	}
	/** ユーザー削除（1件） */
	@Transactional
	@Override
	public void deleteUserOne(String userId) {
		repository.deleteById(userId);
		//削除（１件）
	}
	
	/** ログインユーザー取得 */
	@Override
	public MUser getLoginUser(String userId) {
		Optional<MUser> option = repository.findById(userId);
		MUser user = option.orElse(null);
		return repository.findLoginUser(userId);
	}
	

}
