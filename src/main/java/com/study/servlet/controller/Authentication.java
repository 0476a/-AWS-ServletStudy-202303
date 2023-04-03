package com.study.servlet.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.study.servlet.dto.RequestDto;
import com.study.servlet.dto.ResponseDto;
import com.study.servlet.entity.User;
import com.study.servlet.service.UserService;
import com.study.servlet.service.UserServiceImpl;

@WebServlet("/auth")
public class Authentication extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private UserService userService;
	private Gson gson;
	
	public Authentication() {
		userService = UserServiceImpl.getInstance();
		gson = new Gson();
	}
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
		
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 한줄만 적으면 원하는 타입으로 값을 JSON에서 받아올 수 있다.
		User user = RequestDto.<User>convertRequestBody(request, User.class);
		
		boolean duplicatedFlag = userService.duplicatedUsername(user.getUsername());
		
		response.setContentType("application/json;charset=utf-8");
		PrintWriter out = response.getWriter();
		
		if(duplicatedFlag) {
			// true == 중복, false == 가입가능
			ResponseDto<Boolean> responseDto = new ResponseDto<Boolean>(400, "duplicated username", duplicatedFlag);
			out.println(gson.toJson(responseDto));
			// return 안하면 밑에꺼 실행되버림 꼭 선언해주기
			return;
		}
		
		// 201은 생성되었다는 의미이다.
		ResponseDto<Integer> responseDto = new ResponseDto<Integer>(201, "signup", userService.addUser(user));
		out.println(gson.toJson(responseDto));
	}
	
	
// 정답 풀이
//	
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		String username = request.getParameter("username");
//		System.out.println(username);
//		
//		List<User> userList = new ArrayList<>();
//		userList.add(new User("aaa", "1234", "a", "aaa@gmail.com"));
//		userList.add(new User("bbb", "1234", "b", "bbb@gmail.com"));
//		userList.add(new User("ccc", "1234", "c", "ccc@gmail.com"));
//		userList.add(new User("ddd", "1234", "d", "ddd@gmail.com"));
//		
//		User findUser = null;
//		
//		for (User user : userList) {
//			if(user.getUsername().equals(username)) {
//				findUser = user;
//				break;
//			}
//		}
//		
//		Gson gson = new Gson();
//		
//		String userJson = gson.toJson(findUser);
//		
//		response.setContentType("application/json;charset=utf-8");
//		response.getWriter().println(userJson);
//		
//	}
//	
//	@Override
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		ServletInputStream inputStream = request.getInputStream();
//		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//		
//		String requestBody = bufferedReader.lines().collect(Collectors.joining());
//		Gson gson = new Gson();
//		User user = gson.fromJson(requestBody, User.class);
//		
//		System.out.println(user);
//		
//		response.setContentType("application/json;charset=utf-8");
//		PrintWriter out = response.getWriter();
//		
//		JsonObject responseBody = new JsonObject();
//		
//		if(user == null) {
//			responseBody.addProperty("success", false);
//		} else {
//			responseBody.addProperty("success", true);
//		}
//		out.println(responseBody.toString());
//	}
	
	
// 연습 (완성함!!)
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		User user = new User();
//		user.setUsername("kbs");
//		user.setPassword("1234");
//		user.setName("김삼겹");
//		user.setEmail("kbs@naver.com");
//		List<User> userList = new ArrayList<>();
//		userList.add(user);
//		
//		String username = request.getParameter("username");
//		String password = null;
//		String name = null;
//		String email = null;
//		for (User user2 : userList) {
//			if(user2.getUsername().contains(username)) {
//				password = user2.getPassword();
//				name = user2.getName();
//				email = user2.getEmail();
//			}
//		}
//		
//		
//		JsonObject responseData = new JsonObject();
//		responseData.addProperty("username", username);
//		responseData.addProperty("password", password);
//		responseData.addProperty("name", name);
//		responseData.addProperty("email", email);
//		
//		response.setContentType("application/json;charset=UTF-8");
//		response.getWriter().println(responseData.toString());
//		
//	}
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");
//		
//		ServletInputStream inputStream = request.getInputStream();
//		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//		
//		String requesJson = bufferedReader.lines().collect(Collectors.joining());
//		
//		Gson gson = new Gson();
//		User user = gson.fromJson(requesJson, User.class);
//		
//		System.out.println(user.toString());
//		
//		response.setCharacterEncoding("UTF-8");
//		PrintWriter out = response.getWriter();
//		
//		JsonObject successMessage = new JsonObject();
//		successMessage.addProperty("success", "true");
//		
//		out.println(successMessage);
//	
//	}

}
