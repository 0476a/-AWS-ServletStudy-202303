package com.study.servlet.dto;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Collectors;

import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;

import com.google.gson.Gson;

public class RequestDto<T> {
	
	// static으로 제네릭 타입을 따로 줄 수 있다. (어짜피 공용으로 쓸거기 때문에 static으로 해준다. 굳이 생성 필요 X)
	public static <T> T convertRequestBody(HttpServletRequest request, Class<?> c) throws IOException {
		ServletInputStream inputStream = request.getInputStream();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		String requestBody = bufferedReader.lines().collect(Collectors.joining());
		
		Gson gson = new Gson();
		T object = (T) gson.fromJson(requestBody, c);
		
		return object;
	}
}
