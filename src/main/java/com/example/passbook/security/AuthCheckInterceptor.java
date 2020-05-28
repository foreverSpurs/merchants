package com.example.passbook.security;

import com.example.passbook.constant.Constants;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *  权限拦截器：拦截所有的HTTP请求
 *  方便起见，这里规定所有的商户都是用同一个token，所以才有了之前Constants类中的常量：TOKEN & TOKEN_STRING
 *  用来验证商户传递的 token 信息与服务器存储的 token 是否一致
 *  在实际的场景中，应该是一个商户对应一个token，映射信息存放在数据库中，请求到来时，从数据库中获取数据进行验证；
 */
@Component
public class AuthCheckInterceptor implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        //在http请求头中：根据 token string 获取 token info
        String token = request.getHeader(Constants.TOKEN_STRING);
        //校验token info
        if(StringUtils.isEmpty(token)){
            throw new Exception("Header 中缺少 " + Constants.TOKEN_STRING + "!");
        }
        if(!token.equals(Constants.TOKEN)){
            throw new Exception("Header 中 " + Constants.TOKEN_STRING + "错误！");
        }

        //校验合法后，保存token信息
        AccessContext.setToken(token);

        return true;
    }

    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        //请求处理完毕后，清空token信息
        AccessContext.clearAccessKey();
    }
}
