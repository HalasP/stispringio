package ite.librarymaster.web.filter;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@WebFilter(filterName = "SecurityFilter", urlPatterns = ("/*") , asyncSupported = true)
public class SecurityFilter implements Filter {
	final Logger logger = LoggerFactory.getLogger(DefaultUtf8Filter.class);
	final Map<String,String> map = new HashMap<>();
	
    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain) throws IOException, ServletException {
    	logger.info("Applaying Security Filter Servlet filter...");
    	HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
    	final String authorization = httpRequest.getHeader("Authorization");
        if (authorization != null && authorization.startsWith("Basic")) {
            // Authorization: Basic base64credentials
            String base64Credentials = authorization.substring("Basic".length()).trim();
            String credentials = new String(Base64.getDecoder().decode(base64Credentials),
                    Charset.forName("UTF-8"));
            // credentials = username:password
            final String[] values = credentials.split(":",2);
            if (values.length == 2) {
	            if (map.containsKey(values[0]) && values[1].equals(map.get(values[0]))) {
	            	chain.doFilter(request, response);
	            } else {
	            	unauthorized(httpResponse, "Invalid authentication token");
	            }
	        } else {
	        	unauthorized(httpResponse, "Invalid authentication token");
	        }
        } else {
        	unauthorized(httpResponse, "Invalid authentication token");
        }
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    	logger.info("Initializing Servlet filter...");
    	map.put("HalasP","123456");
    }

    @Override
    public void destroy() {
    	logger.info("Destroying Servlet filter...");
    }
    
    private void unauthorized(HttpServletResponse response, String message) throws IOException {
        response.setHeader("WWW-Authenticate", "Basic realm=\"Protected\"");
        response.sendError(401, message);
      }
}
