package com.example.form;

import java.util.Date;

import javax.validation.constraints.Email;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import org.hibernate.validator.constraints.Length;
import org.springframework.format.annotation.DateTimeFormat;

import lombok.Data;

@Data
public class SignupForm {

	@NotBlank(groups = ValidGroup1.class)
	//文字列がnull、空文字、空白スペースのみでないことをチェック
	@Email(groups = ValidGroup2.class)
	//文字列がメールアドレス形式かどうかチェック
	private String userId;
	//プライベート変数、String型のフィールド変数userId
	@NotBlank(groups = ValidGroup1.class)
	//文字列がnull、空文字、空白スペースのみでないことをチェック
	@Length(min = 4, max = 100, groups = ValidGroup2.class)
	//文字列の長さが指定した範囲内であるかをチェック
	@Pattern(regexp = "^[a-zA-Z0-9]+$", groups = ValidGroup2.class)
	//指定した正規表現に一致するかどうかをチェック
	private String password;
	//プライベート変数、String型のフィールド変数password
	@NotBlank(groups = ValidGroup1.class)
	//文字列がnull、空文字、空白スペースのみでないことをチェック
	private String userName;
	//プライベート変数、String型のフィールド変数userName
	@DateTimeFormat(pattern = "yyyy/MM/dd")
	//日付の形になっているかチェック
	@NotNull(groups = ValidGroup1.class)
	//nullではないことをチェック
	private Date birthday;
	//プライベート変数、Date型のフィールド変数birthday
	@Min(value = 20, groups = ValidGroup2.class)
	//指定した値以上であるかチェック
	@Max(value = 100, groups = ValidGroup2.class)
	//指定した値以下であるかチェック
	private Integer age;
	//プライベート変数、Integer型のフィールド変数age
	@NotNull(groups = ValidGroup1.class)
	//nullでないことをチェック
	private Integer gender;
	//プライベート変数、Integer型のフィールド変数gender
}
