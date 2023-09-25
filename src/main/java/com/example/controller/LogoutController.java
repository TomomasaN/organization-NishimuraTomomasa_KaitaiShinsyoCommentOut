package com.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;

@Controller
//コントローラークラスにする
@Slf4j
//対応するメソッドを呼び出してログ出力する
public class LogoutController {
	//LogoutControllerクラスの始まり

	/**ログイン画面にリダイレクト*/
	@PostMapping("/logout")
	////POSTリクエストと[http://localhost:8080/logout]を紐付ける
	public String postLogout() {
		//String型のパブリックメソッドpostLogout
		log.info("ログアウト");
		//情報ログの出力
		return "redirect:/login";
	}
}
