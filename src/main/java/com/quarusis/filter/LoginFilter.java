package com.quarusis.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


/**
 * Servlet Filter implementation class LoginFilter
 */
@WebFilter("/LoginFilter")
public class LoginFilter implements Filter {

    /**
     * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
     */
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // 获得在下面代码中要用的request,response,session对象
        HttpServletRequest servletRequest = (HttpServletRequest) request;
        HttpServletResponse servletResponse = (HttpServletResponse) response;
        HttpSession session = servletRequest.getSession();
        // 获得用户请求的URI
        String path = servletRequest.getRequestURI();

        // 从session里取uin
        String uin = (String) session.getAttribute("uin");
        System.out.println("filter uin:" + uin);
        System.out.println("filter path:" + path);

        // 登录页面 无需过滤
        if(path.indexOf("/Quarusis/login") > -1) {
            chain.doFilter(servletRequest, servletResponse);
            return;
        }
        if(path.indexOf("/Quarusis/loginQRcode") > -1) {
            chain.doFilter(servletRequest, servletResponse);
            return;
        }
        if(path.indexOf("/Quarusis/checkName") > -1) {
            chain.doFilter(servletRequest, servletResponse);
            return;
        }
        if(path.indexOf("/Quarusis/setName.do") > -1) {
            chain.doFilter(servletRequest, servletResponse);
            return;
        }
        if(path.indexOf("/Quarusis/indexpage") > -1) {
            chain.doFilter(servletRequest, servletResponse);
            return;
        }

        // 判断如果没有取到员工信息,就跳转到登陆页面
        if (uin == null || "".equals(uin)) {
            // 跳转到页面
            servletResponse.sendRedirect("/Quarusis/login");
        } else {
            // 已经登陆,继续此次请求
            chain.doFilter(request, response);
        }
    }

    /**
     * Default constructor.
     */
    public LoginFilter() {}

    /**
     * @see Filter#destroy()
     */
    public void destroy() {}

    /**
     * @see Filter#init(FilterConfig)
     */
    public void init(FilterConfig fConfig) throws ServletException {}

}
