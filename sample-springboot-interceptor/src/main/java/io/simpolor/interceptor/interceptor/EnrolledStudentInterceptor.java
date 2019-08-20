package io.simpolor.interceptor.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.*;

/***
 * preHandle : 컨트롤러 실행 직전에 동작
 * postHandle : 컨트롤러 진입 후 view 랜더링 되기전 수행
 * afterCompletion : 컨트롤러 집입 후 view가 정상적으로 랜더링 된 후 마지막으로 실행
 */
@Slf4j
@Component
public class EnrolledStudentInterceptor implements HandlerInterceptor {

    @Override // 매핑되기 전 처리
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        log.info("Interceptor > preHandle");

        // ... 학교에 등록된 학생인지 인터셉터하는 로직

        // HttpSession session = request.getSession();
        // Student student = (Student) session.getAttribute("Student");
        // session.setMaxInactiveInterval(30 * 60); // 세션 유지시간을 연장

        // if(ObjectUtils.isEmpty(student)){
        //    response.sendRedirect("/student/not");
        //    return false;
        // }

        return true;
    }

    @Override // 매핑되고난 후 처리
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable ModelAndView modelAndView) throws Exception {
        log.info("Interceptor > postHandle");
    }

    @Override // 모든 작업이 완료된 후 처리
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, @Nullable Exception ex) throws Exception {
        log.info("Interceptor > afterCompletion");
    }

}
