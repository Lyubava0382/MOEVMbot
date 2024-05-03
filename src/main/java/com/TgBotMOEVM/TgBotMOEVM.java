package com.TgBotMOEVM;

import com.TgBotMOEVM.config.Storage;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.modelmapper.ModelMapper;

@SpringBootApplication
public class TgBotMOEVM {

	public static void main(String[] args) {
		SpringApplication.run(TgBotMOEVM.class, args);
	}
	@Bean
	public ModelMapper modelMapper(){
		return new ModelMapper();
	}
}
