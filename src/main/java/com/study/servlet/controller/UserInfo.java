package com.study.servlet.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.ServletInputStream;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

/**
 * Servlet implementation class UserInfo
 */
@WebServlet("/user")
public class UserInfo extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 현재 encoding 이 무엇인지 출력해본다.
//		System.out.println(response.getCharacterEncoding());
		
		// UTF-8을 encoding 해준다. (writer가 되기 전에 인코딩 해줘야하므로 젤 위에 올려준다.)
//		response.setCharacterEncoding("UTF-8");
		
		Gson gson = new Gson();
		
		System.out.println("GET 요청");
		String name = request.getParameter("name");
		String phone = request.getParameter("phone");
		
		Map<String, String> userMap = new HashMap<>();
		userMap.put("name", name);
		userMap.put("phone", phone);
		
		String userJson = gson.toJson(userMap);
		System.out.println(userJson);
		
		/**
		 * 1. 주소창, src, href, replace 등으로 요청할 수 있음.
		 * 2. 데이터를 전달하는 방법(Query String) 
		 * 		http(s)://서버주소:포트/요청메세지?key=value&key=value
		 */
		
		// content-type으로 그냥 text로 볼건지 html로 볼건지를 정해줄수 있다.
//		response.addHeader("Content-Type", "text/html;charset=UTF-8");
//		response.addHeader("test", "test data");
		
		// set도 있어서 줄여서 쓰는 것도 가능함!
//		response.setContentType("text/html;charset=UTF-8");          HTML 응답
		
		// setCharacterEncoding을 해줄 필요가 없다.
		response.setContentType("application/json;charset=UTF-8");
		
		PrintWriter out = response.getWriter();
//		out.println("이름: " + name);
//		out.println("연락처: " + phone);
		out.println(userJson);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("POST 요청");
		request.setCharacterEncoding("UTF-8");
//		System.out.println(request.getParameter("address"));
		
		Gson gson = new Gson();
		
		ServletInputStream inputStream = request.getInputStream();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		// 쉬운 방벙
		String json = "";
		String line = null;
		while((line = bufferedReader.readLine()) != null) {
			json += line;
		}
		json = json.replaceAll(" ", "");
		System.out.println(json);
		
		// 조금 어려운 방법
//		// Atomic의 경우에는 객체형태로 되어있으며 꼭 사용해줘야함!
//		AtomicReference<String> jsonAtomic = new AtomicReference<>("");
//		// lines는 스트림을 리턴받는다.
//		bufferedReader.lines().forEach(line -> {
//			// t라는 공백값에서 line 값을 받아와 값을 더해줘서 넣어준다.
//			jsonAtomic.getAndUpdate(t -> t + line);
//		});
//		// replaceAll로 공백을 없애줘 버렸다.
//		String json = jsonAtomic.get().replaceAll(" ", "");
//		
//		System.out.println(json);
		
		Map<String, String> jsonMap = gson.fromJson(json, Map.class);
		
		System.out.println(jsonMap);
		
		/**
		 * 1. 
		 * 요청메시지는 url 과 같다.
		 * <form action = "http://localhost:8080/Servlet_Study_20230331/user" method="post">
		 * 		<input name="key" value="value">
		 * 		<button type="submit">요청</button>
		 * </form>
		 * 
		 */
	}

}
