package com.study.servlet.controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.HashMap;
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

/**
 * Servlet implementation class CarInfo
 */
@WebServlet("/car")
public class CarInfo extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Map<String, String> carMap = new HashMap<>();
		carMap.put("1", "쏘나타");
		carMap.put("2", "K5");
		carMap.put("3",  "SM6");
		
		// parameter는 문자열만 받아요
		String id = request.getParameter("id");
		String findModel = carMap.get(id);
		
		JsonObject responseData = new JsonObject();
		
		if(findModel == null) {
			responseData.addProperty("statuesCode", 400);
			responseData.addProperty("errorMessage", "Not Found!!");
		} else {
			responseData.addProperty("id", id);
			responseData.addProperty("model", findModel);
		}
		
		
		response.setContentType("application/json;charset=UTF-8");
		response.getWriter().println(responseData.toString());
	}
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("UTF-8");
		
		ServletInputStream inputStream = request.getInputStream();
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
		
		// collect는 스트림에서 사용할 수 있는 메소드이다.
		// Collectors 안에 있는 메소드들을 꺼내서 사용이가능하다.
		String requestJson = bufferedReader.lines().collect(Collectors.joining());
		
		Gson gson = new Gson();
		List<Map<String, String>> requestData = gson.fromJson(requestJson, List.class);
		
		response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
		
		requestData.forEach(obj -> {
			System.out.println("id(" + obj.get("id") + "): " + obj.get("model"));
			out.println("id(" + obj.get("id") + "): " + obj.get("model"));
		});
		
	}
	
	
	// 연습
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		Gson gson = new Gson();
//		
//		String id = request.getParameter("id");
//		
//		Map<String, String> car1 = new HashMap<>();
//		car1.put("id", "1");
//		car1.put("model", "쏘나타");
//		Map<String, String> car2 = new HashMap<>();
//		car2.put("id", "2");
//		car2.put("model", "K5");
//		Map<String, String> car3 = new HashMap<>();
//		car3.put("id", "3");
//		car3.put("model", "SM6");
//		
//		String userJson = "";
//		
//		if(car1.get("id").contains(id)) {
//			userJson = gson.toJson(car1);
//		} else if (car2.get("id").contains(id)) {
//			userJson = gson.toJson(car2);
//		} else if (car3.get("id").contains(id)) {
//			userJson = gson.toJson(car3);
//		}
//		
//		System.out.println(userJson);
//		
//		response.setContentType("application/json;charset=UTF-8");
//		
//		PrintWriter out = response.getWriter();
//		out.println(userJson);
//	}
//	
//	
//
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("UTF-8");
//		
//		Gson gson = new Gson();
//		
//		ServletInputStream inputStream = request.getInputStream();
//		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
//		String json = "";
//		String line = null;
//		while((line = bufferedReader.readLine()) != null) {
//			json += line;
//		}
//		
//		json = json.replaceAll(" ", "");
//		
//		List<Map<String, String>> jsonList = gson.fromJson(json, List.class);
//		
//		System.out.println(jsonList);
//	}

}
