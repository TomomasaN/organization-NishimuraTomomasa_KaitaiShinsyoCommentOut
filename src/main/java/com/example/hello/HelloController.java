package com.example.hello;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
//Autowired,Controller,Model,GetMapping,PostMapping,RequestParamをインポート

@Controller
//HelloControllerクラスをコントローラーにする
public class HelloController {
	//HelloControllerクラスの始まり
	@Autowired
	//フィールドにインスタンスを自動注入
	private HelloService service;
	//private変数、HelloServiceクラスの変数service	
	@GetMapping("/hello")
	//GETメソッドのHTTPリクエストを受け付ける、[http://localhost:8080/hello]へのリクエストを受け付け
	public String getHello() {
		//public変数のString型getHelloメソッド
		//hello.htmlに画面遷移
		return "hello";
	}

	@PostMapping("/hello")
	//POSTメソッドのHTTPリクエストを受け付ける、[http://localhost:8080/hello]へのリクエストを受け付け
	public String postRequest(@RequestParam("text1") String str, Model model) {
		//Public変数,String型のpostRequestメソッド @RequestParamのアノテーション引数とhtmlのname属性が一致するように引数を指定する。
		//画面から受け取った文字列をModelに登録
		model.addAttribute("sample", str);

		//response.htmlに画面遷移
		return "hello/response";
	}

	@PostMapping("/hello/db")
	//POSTメソッドのHTTPリクエストを受け付ける、[http://localhost:8080/hello]へのリクエストを受け付け
	public String postDbRequest(@RequestParam("text2") String id, Model model) {
		//Public変数,String型のpostRequestメソッド @RequestParamのアノテーション引数とhtmlのname属性が一致するように引数を指定する。
		//1件検索
		Employee employee = service.getEmployee(id);
		//service.getEmployee(id)をEmployee employeeに代入
		//検索結果をModelに登録
		model.addAttribute("employee", employee);
		//modelクラスのAttributeメソッドにキー名と値を指定する
		//db.htmlに画面遷移
		return "hello/db";
	}
}
