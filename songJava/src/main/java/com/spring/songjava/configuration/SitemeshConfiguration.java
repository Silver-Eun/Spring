package com.spring.songjava.configuration;

import jakarta.servlet.*;
import org.sitemesh.builder.SiteMeshFilterBuilder;
import org.sitemesh.config.ConfigurableSiteMeshFilter;

import java.io.IOException;

public class SitemeshConfiguration extends ConfigurableSiteMeshFilter implements Filter {
    @Override
    protected void applyCustomConfiguration(SiteMeshFilterBuilder builder) {
        builder.addDecoratorPath("/community**", "/WEB-INF/views/decorator/default-layout.jsp");
        builder.addDecoratorPath("/notice**", "/WEB-INF/views/decorator/default-layout.jsp");
        builder.addDecoratorPath("/faq**", "/WEB-INF/views/decorator/default-layout.jsp");
        builder.addDecoratorPath("/inquiry**", "/WEB-INF/views/decorator/default-layout.jsp");
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        // SiteMesh 필터 처리
        super.applyCustomConfiguration(new SiteMeshFilterBuilder());

        // 요청을 다음 필터로 전달
        chain.doFilter(request, response);
    }
}
