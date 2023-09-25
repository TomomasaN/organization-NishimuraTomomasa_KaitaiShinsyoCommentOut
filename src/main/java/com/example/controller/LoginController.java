package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
//Controller~Postmappingまでインポート

@Controller
//コントローラークラスにする
public class LoginController {
	//LoginControllerクラスの始まり
	/*: ログイン画面を表示 */
	@GetMapping("/login")
	//GETリクエストと[http://localhost:8080/login]を紐付ける
	public String getLogin() {
		//public変数、String型のgetLoginメソッドの始まり
		return "login/login";
	}

	/** ユーザー一覧画面にリダイレクト*/
	@PostMapping("/login")
	//POSTリクエストと[http://localhost:8080/login]を紐付ける
	public String postLogin() {
		//public変数、String型のpostLoginメソッドの始まり
		return "redirect:/user/list";
	}
}
