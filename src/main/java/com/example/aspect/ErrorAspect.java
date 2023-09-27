package com.example.aspect;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
//AOPのクラスにつける
@Component
//AOPのクラスにつける
@Slf4j
//対応するメソッドを呼び出してログ出力する
public class ErrorAspect {

	@AfterThrowing(value = "execution(* *..*..*(..))&&" + "(bean(*Controller) || bean(*Service) || bean(*Repository))", throwing = "ex")
	//例外発生時のAOPを実装できる
	public void throwingNull(DataAccessException ex) {
		//例外処理の内容（ログ出力）
		log.error("DataAccessExeptionが発生しました");
	}
}
