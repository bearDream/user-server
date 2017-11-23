package cn.beardream.userserver.filter;

import cn.beardream.core_common.constant.SysConstant;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 2017/11/6
 * 没有token，或token过期则需要进行过滤
 * @author chi.zhang
 * @email laxzhang@outlook.com
 */
@Slf4j
public class TokenFilter implements Filter{


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {

    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest)request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String token = httpRequest.getHeader("zuul-token");
        log.info("URI: ==========>   {}", httpRequest.getRequestURI());
        if (httpRequest.getRequestURI().equals(SysConstant.LOGINURI.substring(12))){
            filterChain.doFilter(request, response);
        }
        if (token == null){
            httpResponse.setCharacterEncoding("UTF-8");
            httpResponse.setContentType("application/json; charset=utf-8");
            httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return;
        }
        filterChain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }

    private boolean checkToken(){

        return false;
    }
}
