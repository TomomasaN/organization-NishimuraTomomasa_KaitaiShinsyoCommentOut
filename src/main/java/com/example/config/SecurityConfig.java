package com.example.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@EnableWebSecurity
//セキュリティ設定クラスを用意する
@Configuration
//セキュリティ設定クラスを用意する
public class SecurityConfig extends WebSecurityConfigurerAdapter {
	
	@Autowired
	//インスタンスの生成
	private UserDetailsService userDetailsService;
	//UserDetailsServiceクラスの変数userDetailsService
	
	@Bean
	//Beanを作成するものと認識される
	public PasswordEncoder passwordEncoder() {
		//インメモリ認証で用意したユーザーのパスワードを暗号化
		return new BCryptPasswordEncoder();
	}

	/** セキュリティの対象外を設定 */
	@Override
	//オーバーライドする時に、引数の型などが間違ってないか確認できる
	public void configure(WebSecurity web) throws Exception {
		//セキュリティを適用しない
		web
		.ignoring()
		.antMatchers("/webjars/**")
		.antMatchers("/css/**")
		.antMatchers("/js/**")
		.antMatchers("/h2-console/**");
		//セキュリティ対象から除外する、antMatchersメソッドはメソッドチェーンを利用できる
	}
	
	/** セキュリティの各種設定 */
	@Override
	//オーバーライドする時に、引数の型などが間違ってないか確認できる
	protected void configure(HttpSecurity http) throws Exception {
		//ログイン不要ページの設定
		http
		.authorizeRequests()
		//直リンクを禁止する、パス毎の設定を追加していく
		.antMatchers("/login").permitAll()//直リンクOK
		.antMatchers("/user/signup").permitAll()//直リンクOK
		.antMatchers("/user/signup/rest").permitAll()//直リンクOK
		.antMatchers("/admin").hasAuthority("ROLE_ADMIN") //権限制御
		.anyRequest().authenticated(); //それ以外は直リンクNG
		
		//ログイン処理
		 http
		 .formLogin()
		 //ログイン処理を追加する
		 .loginProcessingUrl("/login")//ログイン処理のパス
		 .loginPage("/login")//ログインページの指定
		 .failureUrl("/login?error")//ログイン失敗時の遷移先
		 .usernameParameter("userId")//ログインページのユーザーID
		 .passwordParameter("password")//ログインページのパスワード
		 .defaultSuccessUrl("/user/list", true);//成功後の遷移先
		 
		 //ログアウト処理
		http
		 .logout()
		 .logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
		 //GETメソッドでログアウトのリクエストを送る
		 .logoutUrl("/logout")
		 //ログアウトのリクエスト先パスを設定する
		 .logoutSuccessUrl("/login?logout");
		//ログアウト成功時の遷移先を指定する
		
		//CSRF対策を無効に設定（一時的）
		// http.csrf().disable();
	}
	/** 認証の設定 */
	@Override
	//オーバーライドする時に、引数の型などが間違ってないか確認できる
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		PasswordEncoder encoder = passwordEncoder();
 //インメモリ認証
/*		
 auth
 .inMemoryAuthentication()
 .withUser("user") //userを追加
 .password(encoder.encode("user"))
 .roles("GENERAL")
 .and()
 .withUser("admin")//adminを追加
 .password(encoder.encode("admin"))
 .roles("ADMIN");
 */
		
		//ユーザーデータで認証
		auth
		.userDetailsService(userDetailsService)
		.passwordEncoder(encoder);
	}
	
	
}
