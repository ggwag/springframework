package com.mycompany.springframework.controller;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.mycompany.springframework.dto.FileInfo;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/ch02")
public class Ch02Controller {
		@GetMapping("/getMethod")
		public String getMethod(String chNum, String bkind, String bno, Model model ) {
			//요청 처리 코드
			log.info("getMethod() 실행");
			log.info("chNum: " + chNum);
			log.info("bkind: " + bkind);
			log.info("bno: " + bno);
			
			model.addAttribute("chNum", chNum);
			return "ch02/getMethod";
		}
		
		@RequestMapping(value="/getMethodAjax", method=RequestMethod.GET)
		public String getMethodAjax(String chNum, String bkind, String bno, Model model ) {
			//요청 처리 코드
			log.info("getMethod() 실행");
			log.info("bkind: " + bkind);
			log.info("bno: " + bno);
			
			return "ch02/getMethodAjax";
		}
		
		@RequestMapping("/postMethod")
		public String postMethod(String chNum,String mid, String mpassword, Model model ) {
			//요청 처리 코드
			log.info("postMethod() 실행");
			log.info("chNum: " + chNum);
			log.info("mid: " + mid);
			log.info("mpassword: " + mpassword);
			
			model.addAttribute("chNum", chNum);
			return "ch02/postMethod";
		}
		
		@PostMapping("/postMethodAjax")
		public void postMethodAjax(String mid, String mpassword, HttpServletResponse response ) throws IOException {
			//요청 처리 코드
			log.info("postMethodAjax() 실행");
			log.info("mid: " + mid);
			log.info("mpassword: " + mpassword);
			
			Map<String, String> map = new HashMap<>();
			map.put("spring","12345");
			map.put("summer","67890");
			
			JSONObject result = new JSONObject();
			if(map.containsKey(mid)) {
				if(map.get(mid).equals(mpassword)) {
					//로그인 성공 시
					result.put("result", "success");
				} else {
					//비밀번호가 틀린 경우
					result.put("result", "fail");
					result.put("reason","wrongMpassword");
				}
			} else {
				//아이디가 존재하지 않는 경우
				result.put("result", "fail");
				result.put("reason","wrongMid");
			}
			
			//직접 응답을 생성해서 브라우저로 보냄 jsp의 도움을 받지않고 해보기
			//문자 입력 스트림
			PrintWriter pw = response.getWriter();
			response.setContentType("application/json; charset=UTF-8"); 
			pw.println(result.toString());
			pw.flush();
			pw.close();

		}
		
		@GetMapping("/modelAndViewReturn")
		public ModelAndView modelAndViewReturn(String chNum) {
			log.info("modelAndViewReturn() 실행");
			ModelAndView modelAndView = new ModelAndView();
			modelAndView.addObject("chNum", chNum);
			modelAndView.addObject("login", true);
			modelAndView.addObject("userName", "감자바");
			modelAndView.setViewName("ch02/modelAndViewReturn");
			return modelAndView;
		}
		
		@GetMapping("/voidReturn")
		public void imageDownload(HttpServletRequest request, HttpServletResponse response) throws IOException {
			//다운로드 할 이미지 파일 선택
			String fileName = "photo1.jpg";
			//실제 파일의 경로 얻기
			String urlPath = "/resources/image/photos/photo1.jpg";
			String fileSystemPath = request.getServletContext().getRealPath(urlPath); //실제 파일 시스템의 절대경로를 얻는다 (웹 애플리케이션 경로)
			log.info("realPath" + fileSystemPath);
			//파일의 MIME 타입 얻기
			String mimeType = request.getServletContext().getMimeType(fileName);
			log.info("mimeType: "+ mimeType); //content 타입에 쓰임
			
			//파일 데이터를 읽는 입력 스트림을 얻기
			InputStream is = new FileInputStream(fileSystemPath);
			
			//응답 본문에 데이터를 넣는 출력 스트림을 얻기
			OutputStream os = response.getOutputStream();

			//응답 헤더에 Content-Type 값을 지정하기
			response.setContentType(mimeType); //이미지라 charset 필요X
			
			
			//응답 본문에 데이터 싣기
			//입력 스트림에서 데이터를 읽고 -> 출력 스트림으로 데이터를 쓰기
			
			//방법1
		/*	byte[] bytes = new byte[1024];
			while(true) {
				int readNum = is.read(bytes); 
				if(readNum == -1) break;
				os.write(bytes, 0 , readNum);
			}*/ //직접읽고 출력한걸 작성
			
			//방법2
			FileCopyUtils.copy(is , os); //copy 메소드로 입,출력 스트림 넣어주면 읽고 바로 출력
			
			os.flush();
			is.close();
			os.close();
		}
		
		@GetMapping("/objectReturn")
		public String objectReturn(String chNum, Model model) {
			model.addAttribute("chNum" , chNum);
			return "ch02/objectReturn";
		}
		
		@GetMapping(value="/objectReturnJson1" , 
				    produces="application/json; charset=UTF-8") //produces 리턴값이 생성된 것의 형태를 제공한다?
		@ResponseBody //응답본문 어노테이션    리턴된 객체를 JSON으로 해석하고 응답 본문에 넣겠다.
		public String objectReturnJson1() {
			log.info("objectReturnJson1() 실행");
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("fileName" , "photo1.jpg");
			// return {"fileName":"photo1.jpg"} 뷰 이름 아니고 파일의 내용
			return jsonObject.toString(); //json 응답 내용을 return값으로 하고싶다 
		}
		
		@GetMapping(value="/objectReturnJson2",
					produces="application/json; charset=UTF-8")
		@ResponseBody // 리턴된 객체를 JSON으로 변환하고 응답 본문에 넣겠다.
		public FileInfo objectReturnJson2() {
			log.info("objectReturnJson2() 실행");
			FileInfo fileInfo = new FileInfo();
			fileInfo.setFileName("photo2.jpg");
			fileInfo.setInfo("아름다운 풍경 사진");
			return fileInfo;
		}
		
}
