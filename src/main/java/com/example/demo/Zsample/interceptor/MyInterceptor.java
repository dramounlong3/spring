package com.example.demo.Zsample.interceptor;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


// 攔截器實作HandlerInterceptor方法
@Component
public class MyInterceptor implements HandlerInterceptor {

    // 進到controller方法前會先執行, 通常用於身分和權限驗證
    // return true表示通過, false表示不通過
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("執行myInterceptor.preHandler");
        response.setStatus(401); //強制定義回傳代碼, true時 進到controller後, 如又有設定其他代碼就會被蓋掉
        //response.setStatus(200); //先覆蓋成200
        return true;

        // 若true則印出順序為
        // 1.執行myInterceptor.preHandler
        // 2.執行test8 controller method
        // 3.執行test8 controller method
        // 4.執行myInterceptor.行postHandle
        // 5.執行myInterceptor.afterCompletion

        // 若false則印出順序為 (因為false會擋住http request 不再讓其往後送到controller)
        // 1.執行myInterceptor.preHandler

    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {
        System.out.println("執行myInterceptor.postHandle");
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        System.out.println("執行myInterceptor.afterCompletion");
    }
}
