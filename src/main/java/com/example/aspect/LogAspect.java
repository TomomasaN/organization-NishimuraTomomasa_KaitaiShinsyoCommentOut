package com.example.aspect;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Aspect
//AOPのクラスにつける
@Component
//AOPのクラスにつける
@Slf4j
//対応するメソッドを呼び出してログ出力する
public class LogAspect {

	/**
	 * サービスの実行前にログ出力する.
	 * 対象:[UserService]をクラス名に含んでいる.
	 */
	@Before("execution(* *..*.*UserService.*(..))")
	//メソッドが実行する前に処理を実行する
	public void startLog(JoinPoint jp) {
		log.info("メソッド開始:" + jp.getSignature());
	}

	/**
	* サービスの実行前にログ出力する.
	* 対象:[UserService]をクラス名に含んでいる.
	*/
	@After("execution(* *..*.*UserService.*(..))")
	//メソッドが実行された後に処理を実行する
	public void endLog(JoinPoint jp) {
		log.info("メソッド終了:" + jp.getSignature());
	}

	/** コントローラーの実行前後にログ出力する */
	//@Around("bean(Controller)")
	//対象のメソッド実行の前後に処理を実行する
	//@Around("@annotation(org.springframework.web.bind.annotation.GetMapping)")
	//対象のメソッド実行の前後に処理を実行する
	@Around("@within(org.springframework.stereotype.Controller)")
	//対象のメソッド実行の前後に処理を実行する
	public Object startLog(ProceedingJoinPoint jp) throws Throwable {
		//開始ログ出力
		log.info("メソッド開始:" + jp.getSignature());

		try {
			//エラーチェック
			//メソッド実行
			Object result = jp.proceed();

			//終了ログ出力
			log.info("メソッド終了:" + jp.getSignature());

			//実行結果を呼び出し元に返却
			return result;
		} catch (Exception e) {
			//エラーログ出力
			log.error("メソッド異常終了:" + jp.getSignature());

			//エラーの再スロー
			throw e;
		}
	}

}
