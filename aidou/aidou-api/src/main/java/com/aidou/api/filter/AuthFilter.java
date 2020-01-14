package com.aidou.api.filter;


import com.aidou.api.adapter.AppDateInitConfig;
import com.aidou.dao.entity.TbUser;
import com.aidou.dao.mapper.TbUserMapper;
import com.aidou.util.entity.CurrentUser;
import com.aidou.util.entity.StatusCode;
import com.aidou.util.enums.UserStatusEnum;
import com.aidou.util.exception.ExpiredTokenException;
import com.aidou.util.exception.InvalidTokenException;
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
import java.io.IOException;

public class AuthFilter implements Filter {

    public final static String TOKEN = "token";
    private final static Logger LOG = LoggerFactory.getLogger(AuthFilter.class);

    private JwtConfig jwtConfig;

    private TbUserMapper tbUserMapper;


    @Override
    public void init(FilterConfig fConfig) throws ServletException {
        ServletContext sc = fConfig.getServletContext();
        ApplicationContext cxt = WebApplicationContextUtils.getWebApplicationContext(sc);
        if (cxt != null && cxt.getBean("tbUserMapper") != null && tbUserMapper == null) {
            tbUserMapper = (TbUserMapper) cxt.getBean("tbUserMapper");
        }
        if (cxt != null && cxt.getBean("jwtConfig") != null && jwtConfig == null) {
            jwtConfig = (JwtConfig) cxt.getBean("jwtConfig");
        }
    }

    @Override
    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        final String uri = request.getRequestURI();
        String token = request.getHeader(TOKEN);
        LOG.info("No need to auth for public resource  :" + uri);
        System.out.println("token"+token);
        if (!FilterUrlUtil.ifFilter(uri)) {
            LOG.info("未拦截");
            filterChain.doFilter(servletRequest, servletResponse);
            return;
        }
        Jws<Claims> jwsClaims = null;
        try {
            jwsClaims = JwtUtils.parseClaims(jwtConfig, token);
        } catch (InvalidTokenException e) {
            RequestDispatcher requestDispatcher = servletRequest.getRequestDispatcher("/error/msg/" + StatusCode.LOGIN);
            requestDispatcher.forward(servletRequest, servletResponse);
            return;
        } catch (ExpiredTokenException e) {
            servletRequest.getRequestDispatcher("/error/msg/" + StatusCode.LOGIN).forward(servletRequest, servletResponse);
            return;
        }
        final String strUserId = jwsClaims.getBody().getSubject();
        if (strUserId == null) {
            servletRequest.getRequestDispatcher("/error/msg/" + StatusCode.LOGIN).forward(servletRequest, servletResponse);
            return;
        }
        final Long userId = Long.parseLong(strUserId);
        System.out.println("userId"+userId);
        TbUser tbUser = tbUserMapper.selectByPrimaryKey(userId);
        if (tbUser == null) {
            servletRequest.getRequestDispatcher("/error/msg/" + StatusCode.LOGIN).forward(servletRequest, servletResponse);
            return;
        }
        if (UserStatusEnum.STOP.getIndex() == tbUser.getStatus()) {
            servletRequest.getRequestDispatcher("/error/msg/" + StatusCode.USER_STOP_LOGIN).forward(servletRequest, servletResponse);
            return;
        }
        CurrentUser currentUser = new CurrentUser();
        currentUser.setId(userId);
        currentUser.setStatus(tbUser.getStatus());
        currentUser.setWechat(tbUser.getWechat());
        currentUser.setMobile(tbUser.getMobile());
        currentUser.setNikeName(tbUser.getNikename());
        currentUser.setSex(tbUser.getSex());
        currentUser.setRealName(tbUser.getRealName());
        CurrentUser.set(currentUser);
        //保存最后登录时间
        AppDateInitConfig.putDate(userId);
        LOG.info("auth success...");
        filterChain.doFilter(servletRequest, servletResponse);

    }

    @Override
    public void destroy() {

    }

}
