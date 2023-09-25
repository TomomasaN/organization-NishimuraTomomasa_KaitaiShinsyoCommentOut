package com.example.hello;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//Map,Autowired,Serviceをインポート

@Service
//Autowiredアノテーションを使用して、依存性注入（DI）が可能になる
public class HelloService {
	//HelloServiceクラスの始まり

	@Autowired
	//依存性の注入ができる
	private HelloRepository repository;
	//private変数、HelloRepository型のフィールド変数repository

	/**従業員を1人取得する*/
	public Employee getEmployee(String id) {
		//public変数、EmployeeクラスのgetEmployeeメソッド、引数String id
		//検索
		Map<String, Object> map = repository.findById(id);
		//repository.findById(id)をMap型変数mapに代入
		//Mapから値を取得
		String employeeId = (String) map.get("id");
		//(String) map.get("id")メソッドをString employeeIdに代入
		String name = (String) map.get("name");
		//(String) map.get("name")メソッドをString nameに代入
		int age = (Integer) map.get("age");
		//(Integer) map.get("age")メソッドをint ageに代入
		//Employeeクラスに値をセット
		Employee employee = new Employee();
		//new Employee()メソッドをEmployee employeeに代入
		
		employee.setEmployeeId(employeeId);
		//employee.setEmployeeIdメソッド、引数employeeId
		employee.setEmployeeName(name);
		//employee.setEmployeeNameメソッド、引数name
		employee.setEmployeeAge(age);
		//employee.setEmployeeAgeメソッド、引数age

		return employee;
	}
}
