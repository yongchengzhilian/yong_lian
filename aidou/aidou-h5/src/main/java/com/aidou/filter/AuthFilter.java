package com.aidou.filter;


import com.aidou.dao.entity.TbMatchmaker;
import com.aidou.dao.mapper.TbMatchmakerMapper;
import com.aidou.enums.RmStatusEnum;
import com.aidou.util.entity.AidouResult;
import com.aidou.util.entity.CurrentUser;
import com.aidou.util.entity.StatusCode;
import com.aidou.util.exception.ExpiredTokenException;
import com.aidou.util.exception.InvalidTokenException;
import com.aidou.util.tool.GsonUtil;
import com.aidou.util.tool.JwtConfig;
import com.aidou.util.tool.JwtUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class AuthFilter implements Filter {

    public final static String TOKEN = "token";
    private final static Logger LOG = LoggerFactory.getLogger(AuthFilter.class);

    private JwtConfig jwtConfig;

    private TbMatchmakerMapper tbMatchmakerMapper;


    public void init(FilterConfig fConfig) throws ServletException {
        ServletContext sc = fConfig.getServletContext();
        ApplicationContext cxt = (ApplicationContext) WebApplicationContextUtils.getWebApplicationContext(sc);
        if (cxt != null && cxt.getBean("tbMatchmakerMapper") != null && tbMatchmakerMapper == null){
            tbMatchmakerMapper = (TbMatchmakerMapper) cxt.getBean("tbMatchmakerMapper");
        }

        if (cxt != null && cxt.getBean("jwtConfig") != null && jwtConfig == null){
            jwtConfig = (JwtConfig) cxt.getBean("jwtConfig");
    }

}


    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        final String uri = request.getRequestURI();
        System.out.println("进来的请求"+uri);
        if (!RnFilterUrlUtil.ifFilter(uri)) {
            LOG.info("No need to auth for public resource  :" + uri);
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        LOG.info("auth filter begin for security method....");
        String token = request.getHeader(TOKEN);
        if (token != null && token.length() > 10) {
            Jws<Claims> jwsClaims = null;
            try {
                jwsClaims = JwtUtils.parseClaims(jwtConfig, token);
            }
            catch (InvalidTokenException e) {
                LOG.error("invalidToken");
                errorResponse(servletResponse, AidouResult.success(StatusCode.LOGIN,"请重新登陆"));
                return;
            } catch (ExpiredTokenException expiredException) {
                LOG.info("expiredToken");
                errorResponse(servletResponse,AidouResult.success(StatusCode.LOGIN,"请重新登录"));
                return;
            }
            final String strUserId = jwsClaims.getBody().getSubject();
            System.out.println("获取到的userId:"+strUserId);
            if (strUserId == null) {
                errorResponse(servletResponse,AidouResult.error("用户ID不存在"));
                return;
            }

            final Long userId = Long.parseLong(strUserId);
            TbMatchmaker tbMatchmaker = tbMatchmakerMapper.selectByPrimaryKey(userId);
            if (RmStatusEnum.STOP.getIndex()==tbMatchmaker.getStatus()) {
                errorResponse(servletResponse,AidouResult.error("用户被锁定"));
                return;
            }
            CurrentUser currentUser = new CurrentUser();
            currentUser.setId(userId);
            currentUser.setStatus(tbMatchmaker.getStatus());
            currentUser.setWechat(tbMatchmaker.getWechat());
            currentUser.setMobile(tbMatchmaker.getMobile());
            CurrentUser.set(currentUser);
            LOG.info("auth success...");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
    }


    private void errorResponse(ServletResponse servletResponse,AidouResult result) throws IOException {
        HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
        httpResponse.setCharacterEncoding("UTF-8");
        httpResponse.setContentType("application/json; charset=utf-8");
        httpResponse.setStatus(HttpServletResponse.SC_OK);
        httpResponse.getWriter().write(GsonUtil.gsonString(result));
    }


    @Override
    public void destroy() {

    }
}
