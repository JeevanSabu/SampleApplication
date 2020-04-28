package com.SPro.Ticket.tools;

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

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

@WebFilter(filterName = "AuthFilter", urlPatterns = { "*.xhtml" ,"*.jsf"})
public class AuthorizationFilter implements Filter {
	private static final Logger LOGGER = LogManager.getLogger(AuthorizationFilter.class);

	public AuthorizationFilter() {
	}

	public void init(FilterConfig filterConfig) throws ServletException {

	}

	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		LOGGER.trace("inside Authorization Filter doFilter");
		
		HttpServletRequest reqt = (HttpServletRequest) request;
		HttpServletResponse resp = (HttpServletResponse) response;
		HttpSession ses = reqt.getSession();

		String reqURI = reqt.getRequestURI();

		try {

			
			if(!reqURI.contains("javax.faces.resource")) {
				resp.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
			    resp.setHeader("Pragma", "no-cache");
			    resp.setDateHeader("Expires", 0);
			} 
			
//			if(reqURI.indexOf("/login.xhtml") < 0 && (null == ses || null == ses.getAttribute("username"))){
//				LOGGER.trace("redirection");
//				resp.sendRedirect(reqt.getContextPath() + "/faces/login.xhtml");
//			}
			if((reqURI.indexOf("/login.xhtml")>=0||reqURI.indexOf("/authentication.xhtml")>=0)
					&&(null!=ses && null!=ses.getAttribute("verifiedid"))){
				LOGGER.trace("redirection to home");
				resp.sendRedirect(reqt.getContextPath() + "/faces/home.xhtml");
			}
			else if (reqURI.indexOf("/login.xhtml") >= 0
					|| (null != ses && null != ses.getAttribute("username"))
					|| reqURI.indexOf("/public/") >= 0
					|| reqURI.contains("javax.faces.resource")) {
				chain.doFilter(request, response);
			}
			else {
				LOGGER.trace("redirection");
				resp.sendRedirect(reqt.getContextPath() + "/faces/login.xhtml");
//				chain.doFilter(request, response);
			}

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			if(null!=ses && null!=ses.getAttribute("username")) {
				ses.setAttribute("username", null);
				ses.invalidate();
			}
			resp.sendRedirect(reqt.getContextPath() + "/faces/login.xhtml");
//			}
		}

		LOGGER.trace("Leaving Athorization Filter doFilter");
	}

	public void destroy() {

	}
}
