package com.cafe.kaffeine.filter;

import com.cafe.kaffeine.config.Configurations;
import jakarta.servlet.Filter;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import lombok.NoArgsConstructor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@NoArgsConstructor
public class CORSFilter implements Filter {

    public static final String RESPONSE_HEADER_ACCESS_CONTROL_ALLOW_ORIGIN =
            "Access-Control-Allow-Origin";

    public static final String RESPONSE_HEADER_ACCESS_CONTROL_MAX_AGE = "Access-Control-Max-Age";

    public static final String RESPONSE_HEADER_ACCESS_CONTROL_ALLOW_METHODS =
            "Access-Control-Allow-Methods";

    public static final String RESPONSE_HEADER_ACCESS_CONTROL_ALLOW_HEADERS =
            "Access-Control-Allow-Headers";

    public static final String REQUEST_HEADER_ORIGIN = "Origin";

    public static final String DEFAULT_ALLOWED_ORIGINS = "*";

    public static final String DEFAULT_ALLOWED_HTTP_METHODS =
            "GET,POST,HEAD,OPTIONS,DELETE,PUT,PATCH";

    public static final int DEFAULT_PREFLIGHT_MAX_AGE = 1728000;

    public static final String DEFAULT_ALLOWED_HTTP_HEADERS =
            "Origin,Accept,X-Requested-With,Content-Type,"
                    + "Access-Control-Request-Method,Access-Control-Request-Headers,Authorization,X-HPTM-Transaction-ID,X-HPTM-Session-ID";

    private static final Logger log = LoggerFactory.getLogger(CORSFilter.class);

    private static Set<String> allowedOrigins = null;

    @Override
    public void init(FilterConfig fConfig) {
        log.info("CORSFilter initialized");
    }

    @Override
    public void destroy() {
        log.info("CORSFilter destroyed");
    }

    @Override
    public void doFilter(
            ServletRequest servletRequest, ServletResponse servletResponse, FilterChain chain)
            throws IOException, ServletException {
        allowedOrigins = Configurations.getCORSAllowOrigin();
        HttpServletRequest request = (HttpServletRequest) servletRequest;
        HttpServletResponse response = (HttpServletResponse) servletResponse;

        String originHeader = request.getHeader(REQUEST_HEADER_ORIGIN);
        String hostHeader = request.getHeader("Host");
        String requestUrl = request.getRequestURI();

        if (StringUtils.isNotEmpty(requestUrl)) {
            log.debug(
                    "Origin Header {}, Host Header {} , Request URL {}",
                    originHeader,
                    hostHeader,
                    requestUrl);
        }

        if (StringUtils.isNotEmpty(originHeader)) {

            // Add CORS headers only if Origin header is present and is equal to the one set
            // in salt config.
            // Chrome/Safari sends Origin for every POST/PUT/DELETE requests.
            // So its for the same domain, and we don't need to block those and allow the
            // CORS request.
            if (allowedOrigins.contains(originHeader)) {

                // If method is Options add all the CORS Headers.
                if (request.getMethod().equals("OPTIONS")) {
                    // If method is not Options and Origin is present add only the Allowed Origin
                    // Header.
                    response.addHeader(RESPONSE_HEADER_ACCESS_CONTROL_ALLOW_ORIGIN, originHeader);
                    response.addHeader(
                            RESPONSE_HEADER_ACCESS_CONTROL_ALLOW_METHODS, DEFAULT_ALLOWED_HTTP_METHODS);

                    // Authorize (allow) all domains to consume the content
                    response.addHeader(
                            RESPONSE_HEADER_ACCESS_CONTROL_ALLOW_HEADERS, DEFAULT_ALLOWED_HTTP_HEADERS);
                    response.addIntHeader(RESPONSE_HEADER_ACCESS_CONTROL_MAX_AGE, DEFAULT_PREFLIGHT_MAX_AGE);
                    response.setStatus(HttpServletResponse.SC_NO_CONTENT);
                    return;
                }

                // If method is not Options and Origin is present add only the Allowed Origin
                // Header.
                response.addHeader(RESPONSE_HEADER_ACCESS_CONTROL_ALLOW_ORIGIN, originHeader);
                chain.doFilter(request, servletResponse);

            } else {
                log.warn(
                        "CORS request failed. Origin Allowed was {}, origin received {}",
                        allowedOrigins,
                        originHeader);
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                return;
            }
        } else {
            // pass the request along the filter chain
            chain.doFilter(request, servletResponse);
        }
        Map<String, Object> securedHeaders = getSecuredHeaders();
        for (Map.Entry<String, Object> entry : securedHeaders.entrySet()) {
            response.addHeader(entry.getKey(), (String) entry.getValue());
        }
    }

    public static Map<String, Object> getSecuredHeaders() {
        Map<String, Object> headersMap = new HashMap<>();
        headersMap.put("Cache-Control", "no-cache, no-store, max-age=0, must-revalidate");
        headersMap.put("Expires", "Fri, 01 Jan 1990 00:00:00 GMT");
        headersMap.put("Pragma", "no-cache");
        headersMap.put("Strict-Transport-Security", "max-age=631138519; includeSubDomains");
        headersMap.put("X-Content-Type-Options", "nosniff");
        headersMap.put("X-XSS-Protection", "1");
        return headersMap;
    }
}

