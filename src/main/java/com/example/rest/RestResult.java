package com.example.rest;

import java.util.Map;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
//getter,setterでアクセスが可能になる
@AllArgsConstructor
//全項目のコンストラクタを生成
public class RestResult {

	/** リターンコード */
	private int result;
	
	/** エラーマップ 
	 * key: フィールド名
	 * value: エラーメッセージ
	 */
	private Map<String, String> errors;
}
