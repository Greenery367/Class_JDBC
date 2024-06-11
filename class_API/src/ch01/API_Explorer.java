package ch01;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLEncoder;

import javax.net.ssl.HttpsURLConnection;

public class API_Explorer {
	
	public static void main(String[] args) throws IOException {
		
		// 순수 자바 코드로 (클라이언트 측 코딩)
		// 준비물이 필요하다.
		// 1. 서버측 주소가 필요하다.(=경로)
		// http://localhost:8080/test?name=홍길동&age=20 <-쿼리스트링 방식
		// http://localhost:8080/test?name=%ED%99%8D%EA%B8%B8%EB%8F%99&age=20
		StringBuilder urlBuilder=new StringBuilder("http://apis.data.go.kr/B552584/UlfptcaAlarmInqireSvc/getUlfptcaAlarmInfo");
		urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "=j7A04sj%2B4J4hKHJePWishFk9iQF7PsQjtnPn5vGrQ8GrHhFwjthLyuO0YPnhdetjiZLp%2B0G4%2FxbFQenhifiuLw%3D%3D"); /*Service Key*/
		urlBuilder.append("&" + URLEncoder.encode("returnType","UTF-8") + "=" + URLEncoder.encode("json", "UTF-8")); /*xml 또는 json*/
		urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("100", "UTF-8")); /*한 페이지 결과 수*/
	    urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*페이지번호*/
	    urlBuilder.append("&" + URLEncoder.encode("year","UTF-8") + "=" + URLEncoder.encode("2024", "UTF-8")); /*측정 연도*/
	    urlBuilder.append("&" + URLEncoder.encode("itemCode","UTF-8") + "=" + URLEncoder.encode("PM10", "UTF-8")); /*미세먼지 항목 구분(PM10, PM25), PM10/PM25 모두 조회할 경우 파라미터 생략*/
	    
	    // URL 개체에서 문자열 경로 넣어서 객체 생성
	    // url.openConnection() 데이터 요청 보내기 - 설정하고
	    
	    URL url = new URL(urlBuilder.toString());
	    HttpsURLConnection conn=(HttpsURLConnection)url.openConnection();
	    conn.setRequestMethod("GET"); // 서버에게 자원 요청
	    conn.setRequestProperty("Content-type", "application/json");
	    // 성공 시 200, 실패 시 404, 405가 발생한다.
        System.out.println("Response code: " + conn.getResponseCode());
        // 100~500 의미 (약속)
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
		// Builder와 Buffer의 차이점
		// 빌더는 단일 스레드에서, 버퍼는 멀티 스레드에서 안정적으로 사용할 수 있다.
		
	} // end of main

} // end of class
