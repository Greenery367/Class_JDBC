package ch01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;

public class D {

	public static void main(String[] args) throws IOException {

		// 순수 자바코드로 (클라이언트 측 코딩)
		// 준비물
		// 1. 서버측 주소 - 경로
		// http://localhost:8080/test?name=홍길동&age=20
		// http://localhost:8080/test?name=%ED%99%8D%EA%B8%B8%EB%8F%99&age=20
		StringBuilder urlBuilder = new StringBuilder(
				"http://api.data.go.kr/openapi/tn_pubr_public_small_pblfclt_risk_appn_api");
		urlBuilder.append("?" + URLEncoder.encode("serviceKey", "UTF-8")
				+ "=j7A04sj%2B4J4hKHJePWishFk9iQF7PsQjtnPn5vGrQ8GrHhFwjthLyuO0YPnhdetjiZLp%2B0G4%2FxbFQenhifiuLw%3D%3D"); 
		urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("10", "UTF-8")); /*한 페이지 결과 수*/
        urlBuilder.append("&" + URLEncoder.encode("dataType","UTF-8") + "=" + URLEncoder.encode("XML", "UTF-8")); /*요청자료형식(XML/JSON)Default: XML*/
        urlBuilder.append("&" + URLEncoder.encode("stnId","UTF-8") + "=" + URLEncoder.encode("108", "UTF-8")); /*108 전국, 109 서울, 인천, 경기도 등 (활용가이드 하단 참고자료 참조)*/
        urlBuilder.append("&" + URLEncoder.encode("tmFc","UTF-8") + "=" + URLEncoder.encode("20240604040600", "UTF-8")); /*-일 2회(06:00,18:00)회 생성 되며 발표시각을 입력 YYYYMMDD0600 (1800)-최근 24시간 자료만 제공*/
		
        URL url = new URL(urlBuilder.toString());
		
		HttpURLConnection conn = (HttpURLConnection)url.openConnection();
		
		conn.setRequestMethod("GET"); // 서버에게 자원 요청
		conn.setRequestProperty("Content-type", "application/json");
		// 200, 실패 404, 405 
        System.out.println("Response code: " + conn.getResponseCode());
        
        // 100 ~ 500 의미 (약속)
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        System.out.println(sb.toString());
		
	} // end of main

} // end of class
