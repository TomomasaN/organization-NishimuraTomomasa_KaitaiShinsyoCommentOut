package com.example.controller;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.domain.service.UserService;
import com.example.domain.user.model.MUser;
import com.example.form.UserDetailForm;

import lombok.extern.slf4j.Slf4j;

@Controller
//コントローラークラスにする
@RequestMapping("/user")
//[http://localhost:8080/user]とUserDetailControllerクラスを紐づける
@Slf4j
//対応するメソッドを呼び出してログ出力する
public class UserDetailController {
	//UserDetailController
	
	@Autowired
	//自動でインスタンスを生成
	private UserService userService;
	//UserService型の変数userService
	
	@Autowired
	//自動でインスタンスを生成
	private ModelMapper modelMapper;
	//ModelMapper型の変数modelMapper
	
	/** ユーザー詳細画面を表示 */
	@GetMapping("/detail/{userId:.+}")
	//GETメソッドのHTTPリクエストを受け付ける、[http://localhost:8080/detail/{userId}]へのリクエストを受け付け、動的URL{userId}を使って、URLの一部を受け取る
	public String gerUser(UserDetailForm form, Model model, @PathVariable("userId") String userId) {
		//URL内の変数の値を受け取るために@PathVariableを使う
		//ユーザーを１件取得
		MUser user = userService.getUserOne(userId);
		//getUserOne(userId)メソッド
		user.setPassword(null);
		//setPassword(null)メソッド
		
		//Userをformに変換
		form = modelMapper.map(user, UserDetailForm.class);
		form.setSalaryList(user.getSalaryList());
		
		//Modelに登録
		model.addAttribute("userDetailForm", form);
		
		//ユーザー詳細画面を表示
		return"user/detail";
	}
	/** ユーザー更新処理　*/
	@PostMapping(value = "/detail", params = "update")
	//POSTリクエストをURl：detailのvalueと紐づける、buttonタグのname属性と同じ値を指定。コントローラーで受け取るメソッドを変更することができる
	public String updateUser(UserDetailForm form, Model model) {
		//String型のupdateUserメソッド、引数(UserDetailForm form, Model model)
		
		try {
		
		//ユーザーを更新
		userService.updateUserOne(form.getUserId(), form.getPassword(), form.getUserName());
		//updateUserOneメソッド、引数(form.getUserId(), form.getPassword(), form.getUserName())
		} catch (Exception e) {
			log.error("ユーザー更新でエラー", e);
		}
		//エラーチェック
		//ユーザー一覧画面にリダイレクト
		return "redirect:/user/list";
	}
	/** ユーザー削除処理 */
	@PostMapping(value = "/detail", params = "delete")
	//POSTリクエストをURl：detailのvalueと紐づける、buttonタグのname属性と同じ値を指定。コントローラーで受け取るメソッドを変更することができる
	public String deleteUser(UserDetailForm form, Model model) {
		//String型のdeleteUserメソッド、引数(UserDetailForm form, Model model)
		//ユーザーを削除
		userService.deleteUserOne(form.getUserId());
		//deleteUserOneメソッド、引数(form.getUserId())
		
		//ユーザー一覧画面にリダイレクト
		return "redirect:/user/list";
	}

}
