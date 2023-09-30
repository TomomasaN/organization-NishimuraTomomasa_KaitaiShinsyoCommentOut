package com.example.domain.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.example.domain.service.UserService;
import com.example.domain.user.model.MUser;
import com.example.repository.UserMapper;

@Service
//DIができるようになる
public class UserServiceImpl implements UserService {
	
	
	@Autowired
	//インスタンスの生成
	private UserMapper mapper;
	//UserMapper型の変数mapper
	
	@Autowired
	//インスタンスの生成
	private PasswordEncoder encoder;
	//PasswordEncoder型の変数encoder
	
	/** ユーザー登録 */
	@Override
	//オーバーライドする時に、引数の型などが間違ってないか確認できる
	public void signup(MUser user) {
		//signupメソッド、引数(MUser user)
		user.setDepartmentId(1); //部署
		user.setRole("ROLE_GENERAL"); //ロール
		
		//パスワード暗号化
		String rawPassword = user.getPassword();
		//user.getPassword()メソッドをString型の変数rawPasswordに代入
		user.setPassword(encoder.encode(rawPassword));
		mapper.insertOne(user);
	}
	
	/** ユーザー取得*/
	@Override
	//オーバーライドする時に、引数の型などが間違ってないか確認できる
	public List<MUser> getUsers(MUser user) {
		//List型のgetUsersメソッド、引数(MUser user)
		return mapper.findMany(user);
	}
	
	/** ユーザー取得（1件） */
	@Override
	//オーバーライドする時に、引数の型などが間違ってないか確認できる
	public MUser getUserOne(String userId) {
		//Muser型のgetUserOneメソッド、引数(String userId)
		return mapper.findOne(userId);
	}
	
	/** ユーザー更新（1件） */
	@Transactional
	//複数の処理をまとめる仕組み、@Modifyingアノテーションをつけたメソッドを実行するためにつける。@Modifyingの有無にかかわらず、登録・更新・削除のSQLであればつける
	@Override
	//オーバーライドする時に、引数の型などが間違ってないか確認できる

	public void updateUserOne(String userId, String password, String userName) {
		//updateUserOneメソッド、引数（String userId, String password, String userName）
		
		//パスワード暗号化
		String encryptpassword = encoder.encode(password);
		//encoder.encode(password)メソッドをString型の変数encryptpasswordに代入
		
		mapper.updateOne (userId, encryptpassword, userName);
		//updateOneメソッド、引数（userId, encryptpassword, userName）
		
		//例外を発生させる
		//int i = 1 / 0;
	}
	
	/** ユーザー削除（1件） */
	@Override
	//オーバーライドする時に、引数の型などが間違ってないか確認できる
	public void deleteUserOne(String userId) {
		//deleteUserOneメソッド、引数String userId
		int count = mapper.deleteOne(userId);
		//deleteOneメソッドを変数countに代入
	}
	
	/** ログインユーザー情報取得 */
	@Override
	//オーバーライドする時に、引数の型などが間違ってないか確認できる
	public MUser getLoginUser(String userId) {
		//MUser型のgetLoginUserメソッド、引数String userId
		return mapper.findLoginUser(userId);
	}
	

}
