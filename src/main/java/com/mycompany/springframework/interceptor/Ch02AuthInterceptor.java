package com.mycompany.springframework.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class Ch02AuthInterceptor implements HandlerInterceptor {
	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler)
			throws Exception {
		log.info("preHandle() 실행");

		// 요청 매핑 메소드에 참조객체(@Auth 가 붙어있는지 조사하기)
		HandlerMethod handlerMethod = (HandlerMethod) handler;

		// 요청 매핑 메소드에 @Auth가 붙어있지 않다면, null 리턴
		// 요청 매핑 메소드에 @Auth가 붙어있으면, not null 리턴
		Auth auth = handlerMethod.getMethodAnnotation(Auth.class);
		if (auth == null) {
			// 로그인이 필요없는 경우
			// 요청 매핑 메소드를 실행
			return true;
		} else {
			// 로그인이 필요한 경우
			// 로그인 검사
			boolean loginStatus = true;
			if (loginStatus) {
				// 요청 매핑 메소드를 실행
				return true;
			} else {
				// 홈으로 강제이동 시키기
				// request.getContextPath() => /springframework
				response.sendRedirect(request.getContextPath()); // 이거 비회원 구매하기 누를때 로그인하라고 보내면 됨?
				// 요청 매핑 메소드를 실행을 하지 않음
				return false;
			}
		}
	}

}
