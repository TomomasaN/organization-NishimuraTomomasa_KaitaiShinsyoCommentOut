package com.example.controller;

import java.util.Locale;
import java.util.Map;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.example.application.service.UserApplicationService;
import com.example.domain.service.UserService;
import com.example.domain.user.model.MUser;
import com.example.form.GroupOrder;
import com.example.form.SignupForm;

import lombok.extern.slf4j.Slf4j;
//Locale~Slf4jまでインポート

@Controller
//コントローラークラスにする
@RequestMapping("/user")
//[http://localhost:8080/user]とSignupControllerクラスを紐づける
@Slf4j
public class SignupController {
	//パブリック変数、SignupControllerクラスの始まり
	@Autowired
	//インスタンスの生成
	private UserApplicationService userApplicationService;
	//プライベート変数、UserApplicationServiceクラスのフィールド変数userApplicationService
	@Autowired
	//インスタンスの生成
	private UserService userService;
	//プライベート変数、UserServiceクラスのフィールド変数userService
	@Autowired
	//インスタンスの生成
	private ModelMapper modelMapper;

	//プライベート変数、ModelMapperクラスのフィールド変数modelMapper
	/** ユーザー登録画面を表示*/
	@GetMapping("/signup")
	//GETメソッドのHTTPリクエストを受け付ける、[http://localhost:8080/signup]へのリクエストを受け付け

	public String getSignup(Model model, Locale locale, @ModelAttribute SignupForm form) {
		//パブリック変数,String型のgetSignupメソッド、引数Model model, Locale locale, @ModelAttributeの後ろの要素はModelクラスに自動格納される
		//性別を取得
		Map<String, Integer> genderMap = userApplicationService.getGenderMap(locale);
		//userApplicationService.javaのgetGenderMap(locale)をMap型の変数genderMapに代入
		model.addAttribute("genderMap", genderMap);
		//genderMapをgenderMapという名前でhtmlに渡す
		//ユーザー登録画面に遷移
		return "user/signup";
	}

	/**ユーザー登録処理*/
	@PostMapping("/signup")
	//POSTメソッドのHTTPリクエストを受け付ける、[http://localhost:8080/signup]へのリクエストを受け付け
	public String postSignup(Model model, Locale locale, @ModelAttribute @Validated(GroupOrder.class) SignupForm form,
			BindingResult bindingResult) {
		//パブリック変数,String型のgetSignupメソッド、引数Model model, Locale locale, @ModelAttributeの後ろの要素はModelクラスに自動格納される、バインドエラーやバリデーションエラーが発生しているかどうかはBindingResultメソッドで確認
		// @Validated内にGroupOrder.classを指定するとバリデーションの順番設定を反映できる
		//入力チェック結果
		if (bindingResult.hasErrors()) {
			//if文：エラーがあった場合
			//NG:ユーザー登録画面に戻ります
			return getSignup(model, locale, form);
		}
		log.info(form.toString());

		//formをMUserクラスに変換
		MUser user = modelMapper.map(form, MUser.class);

		//ユーザー登録
		userService.signup(user);
		//ログイン画面にリダイレクト
		return "redirect:/login";
	}

	/** データベース関連の例外処理 */
	@ExceptionHandler(DataAccessException.class)
	//例外処理を実装できる
	public String dataAccessExceptionHandler(DataAccessException e, Model model) {

		//空文字をセット
		model.addAttribute("error", "");

		//メッセージをModelに登録
		model.addAttribute("message", "SignupControllerで例外が発生しました");

		//HTTPのエラーコード（500）をModelに登録
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);

		return "error";
	}

	/** その他の例外処理 */
	@ExceptionHandler(Exception.class)
	public String exceptionHandler(Exception e, Model model) {

		//空文字をセット
		model.addAttribute("error", "");

		//メッセージをModelに登録
		model.addAttribute("message", "SignupControllerで例外が発生しました");

		//HTTPのエラーコード（500）をModelに登録
		model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);

		return "error";
	}

}
