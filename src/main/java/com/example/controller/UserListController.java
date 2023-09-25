package com.example.controller;

import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.service.UserService;
import com.example.domain.user.model.MUser;
import com.example.form.UserListForm;

@Controller
//コントローラークラスにする
@RequestMapping("/user")
//[http://localhost:8080/user]とUserListControllerクラスを紐づける
public class UserListController {
	//UserListControlleクラスの始まり
	@Autowired
	//自動でインスタンスを生成
	private UserService userService;
	//プライベート変数、UserService型のフィールド変数userService

	@Autowired
	//自動でインスタンスを生成
	private ModelMapper modelMapper;

	//プライベート変数、ModelMapper型のフィールド変数modelMapper
	/**　ユーザー一覧画面を表示*/
	@GetMapping("/list")
	//GETメソッドのHTTPリクエストを受け付ける、[http://localhost:8080/list]へのリクエストを受け付け
	public String getUserList(@ModelAttribute UserListForm form, Model model) {
		//String型のパブリックメソッドgetUserList、引数@ModelAttribute UserListForm form, Model model
		//@ModelAttributeの後ろの要素はModelクラスに自動格納される

		//formをMUserクラスに変換
		MUser user = modelMapper.map(form, MUser.class);
		//modelMapper.map(form, MUser.class)メソッドをMUser userに代入

		//ユーザー検索
		List<MUser> userList = userService.getUsers(user);
		//userService.getUsers(user)をList<MUser> userListに代入

		//Modelに登録
		model.addAttribute("userList", userList);
		//userListをuserListという名前でhtmlに渡す
		//ユーザー一覧画面を表示
		return "user/list";
	}

	/** ユーザー検索処理 */
	@PostMapping("/list")
	//POSTリクエストを[http://localhost:8080/list]と紐づける
	public String postUserList(@ModelAttribute UserListForm form, Model model) {
		//String型のパブリックメソッド、 postUserList、引数@ModelAttribute UserListForm form, Model model
		//@ModelAttributeの後ろの要素はModelクラスに自動格納される

		//formをMUserクラスに変換
		MUser user = modelMapper.map(form, MUser.class);
		// modelMapper.map(form, MUser.class)メソッドをMUser userに代入

		//ユーザー検索
		List<MUser> userList = userService.getUsers(user);
		//userService.getUsers(user)をList<MUser> userListに代入

		//Modelに登録
		model.addAttribute("userList", userList);
		//userListをuserListという名前でhtmlに渡す

		//ユーザー一覧画面を表示
		return "user/list";
	}

}
