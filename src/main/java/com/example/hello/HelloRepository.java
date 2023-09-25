package com.example.hello;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
//Map,Autowired,JdbcTemplate,Repositoryをインポート

@Repository
//DB操作を表すリポジトリにつけるアノテーション
public class HelloRepository {
	//HelloRepositoryクラスの始まり
	
	@Autowired//インスタンスを自動生成
	private JdbcTemplate jdbcTemplate;
	//private変数、JdbcTemplate型のフィールド変数jdbcTemplate
	public Map<String, Object> findById(String id){
		//public変数、Map型のfindByIdメソッド、引数String id
		//SELECT文
		String query = "SELECT *"
				+ " FROM employee"
				+ " WHERE id=?";
		//検索実行
		Map<String, Object> employee = jdbcTemplate.queryForMap(query, id);
		//jdbcTemplate.queryForMap(query, id)をMap型の変数employeeに代入
		return employee;
	}

}
