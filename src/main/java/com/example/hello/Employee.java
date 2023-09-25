package com.example.hello;

import lombok.Data;
//Dataをインポート

@Data
//getter,setter,toString,hashCode,wqualsメソッドを自動で生成する
public class Employee {
	//Employeeクラスの始まり
	private String employeeId;
	//private変数、String型のフィールド変数employeeId
	private String employeeName;
	//private変数、String型のフィールド変数employeeName
	private int employeeAge;
	//private変数、String型のフィールド変数employeeAge
}
