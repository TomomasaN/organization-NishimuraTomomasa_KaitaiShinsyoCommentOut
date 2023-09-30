package com.example.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.example.domain.user.model.MUser;

public interface UserRepository extends JpaRepository<MUser, String> {
	//JPAでリポジトリを生成する、 JpaRepositoryジェネリックスではJpaRepository<MUser, String>の型を指定する

	/** ログインユーザー検索 */
	@Query("select user" + " from MUser user" + " where userId = :userId")
	//任意のSQLを実行できるメソッドを用意できる。アノテーション内にSQLを書く
	public MUser findLoginUser(@Param("userId") String userId);
	//@Paramを使えばメソッド引数の名称を変更することができる

	/** ユーザー更新 */
	@Modifying
	//@Queryアノテーションを使ってinsert,update,delete文とDDLを実行する場合つける
	@Query("update MUser" + " set" + " password = :password" + " ,user_name = :userName" + " where"
			+ " userId = :userId")
	//任意のSQLを実行できるメソッドを用意できる。アノテーション内にSQLを書く
	public Integer updateUser(@Param("userId") String userId, @Param("password") String password,
			@Param("userName") String userName);
	//@Paramを使えばメソッド引数の名称を変更することができる
}
