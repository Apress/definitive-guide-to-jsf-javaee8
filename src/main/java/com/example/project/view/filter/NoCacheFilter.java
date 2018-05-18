package com.example.project.view.filter;

import java.io.IOException;

import javax.faces.application.ResourceHandler;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(servletNames = "facesServlet")
public class NoCacheFilter implements Filter {

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// NOOP. Note: this method should be unnecessary in Servlet 4.0.
	}

	@Override
	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		String resourcePath = request.getContextPath() + ResourceHandler.RESOURCE_IDENTIFIER;

		if (!request.getRequestURI().startsWith(resourcePath)) {
			response.setHeader("Cache-Control", "no-store, must-revalidate");
		}

		chain.doFilter(request, response);
	}

	@Override
	public void destroy() {
		// NOOP. Note: this method should be unnecessary in Servlet 4.0.
	}
}
