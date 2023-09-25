package com.example.repository;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.example.domain.user.model.MUser;

@Mapper
//MyBatisでリポジトリを作成する
public interface UserMapper {
	//UserMapperクラスの始まり

	/** ユーザー登録 */
	public int insertOne(MUser user);
	//int型のパブリックメソッドinsertOne、引数MUser user

	/** ユーザー取得 */
	public List<MUser> findMany(MUser user);
	//List型のパブリックメソッドfindMany、引数MUser user

	/** ユーザー取得（1件）*/
	public MUser findOne(String userId);
	//Muser型のパブリックメソッドfindOne、引数String userId

	/** ユーザー更新（1件） */
	public void updateOne(@Param("userId") String userId, @Param("password") String password,
			@Param("userName") String userName);
	//@param:引数と引数の説明を記述

	/** ユーザー削除(1件) */
	public int deleteOne(@Param("userId") String userId);
	//@param:引数と引数の説明を記述

	/** ログインユーザー取得 */
	public MUser findLoginUser(String userId);
	//Muser型のパブリックメソッドfindLoginUser、引数String userId
}
