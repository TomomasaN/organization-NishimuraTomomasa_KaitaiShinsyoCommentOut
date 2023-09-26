package com.example.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
//Beanの設定を行うクラスであることを示す
public class JavaConfig {
//JavaConfigクラスの始まり
	@Bean
	//Beanを作成するものと認識される
	public ModelMapper modelMapper() {
		//ModelMapperメソッドの始まり
		return new ModelMapper();
	}
}
