package com.example.application.service;

import java.util.LinkedHashMap;
import java.util.Locale;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
//LinkedHashmap~Serviceまでインポート

@Service
//DIができるようになる
public class UserApplicationService {
	//UserApplicationServiceクラスの始まり
	@Autowired
	//インスタンスの生成
	private MessageSource messageSource;
	//private変数、MessageSource型の変数messageSource

	/**性別のMapを作成する*/
	public Map<String, Integer> getGenderMap(Locale locale) {
		//public変数、Map型のgetGenderMapメソッド、引数Locale locale
		Map<String, Integer> genderMap = new LinkedHashMap<>();
		//new LinkedHashMap<>()メソッドをMap<String, Integer> genderMapに代入
		String male = messageSource.getMessage("male", null, locale);
		//messageSource.getMessage("male", null, locale)メソッドをString maleに代入　getMessageメソッドを使用すれば、messages.propertiesの値を取得できる　getMessage{キー名、埋め込みパラメーター、ロケール}P70参照
		String female = messageSource.getMessage("female", null, locale);
		//messageSource.getMessage("female", null, locale)をString femaleに代入
		genderMap.put(male, 1);
		//Mapにデータを追加
		genderMap.put(female, 2);
		//Mapにデータを追加
		return genderMap;

	}

}
