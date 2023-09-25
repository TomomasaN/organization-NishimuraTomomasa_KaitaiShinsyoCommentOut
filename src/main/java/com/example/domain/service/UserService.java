package com.example.domain.service;

import java.util.List;

import com.example.domain.user.model.MUser;

public interface UserService {
	//インターフェースのUserServiceクラスの始まり
	/** ユーザー登録 */
	public void signup(MUser user);
	//パブリックメソッドsignup、引数MUser user
	
	
	/** ユーザー取得 */
	public List<MUser> getUsers(MUser user);
	//List型のパブリックメソッドgetUsers、引数MUser user
	
	/** ユーザー取得（1件）*/
	public MUser getUserOne(String userId);
	//Muser型のパブリックメソッドgetUserOne、引数String userId
	
	/** ユーザー更新（１件） */
	public void updateUserOne(String userId, String password, String userName);
	//パブリックメソッドupdateUserOne、引数String userId, String password, String userName
	
	/** ユーザー削除（1件） */
	public void deleteUserOne(String userId);
	//パブリックメソッドdeleteUserOne、引数String userId
	
	/** ログインユーザー情報取得 */
	public MUser getLoginUser(String userId);
	//パブリックメソッドgetLoginUser、引数String userId


}
