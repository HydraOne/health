package cn.geny.health.exception;

import cn.geny.health.common.AjaxResult;
import com.alibaba.fastjson.JSONObject;
import org.apache.http.HttpStatus;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

@Component
public class AuthenticationEntryPointImpl implements AuthenticationEntryPoint, Serializable
{
    private static final long serialVersionUID = -8970718410437077606L;

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException e)
            throws IOException
    {
        int code = HttpStatus.SC_UNAUTHORIZED;
        String msg = String.format("请求访问：%s，认证失败，无法访问系统资源", request.getRequestURI());
        response.setCharacterEncoding("UTF-8");
        response.setContentType("application/json");
        response.setStatus(HttpStatus.SC_INTERNAL_SERVER_ERROR);
        response.getWriter().write(JSONObject.toJSONString(AjaxResult.error(code, msg)));
    }
}