package org.third.servlets;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

/**
 * <pre>
 *     <Connector port="8080" protocol="HTTP/1.1"
               connectionTimeout="20000"
               redirectPort="8443"
               URIEncoding="UTF-8"/>
               
                <Connector port="80" protocol="HTTP/1.1"
               connectionTimeout="20000"  redirectPort="8443"  compression="on" compressionMinSize="2048" noCompressionUserAgents="gozilla,traviata"
	compressableMimeType="text/html,text/xml,text/javascript,text/css,text/plain,application/json,application/javascript,application/x-javascript"
 * 
 * </pre>
 */
@WebFilter("/*")
public class CharsetEncodingFilter implements Filter {

	private String encoding = "UTF-8";
	private boolean forceRequestEncoding = true;
	private boolean forceResponseEncoding = true;

	public boolean isForceResponseEncoding() {
		return forceResponseEncoding;
	}

	public void setForceResponseEncoding(boolean forceResponseEncoding) {
		this.forceResponseEncoding = forceResponseEncoding;
	}

	public String getEncoding() {
		return encoding;
	}

	public void setEncoding(String encoding) {
		this.encoding = encoding;
	}

	public boolean isForceRequestEncoding() {
		return forceRequestEncoding;
	}

	public void setForceRequestEncoding(boolean forceRequestEncoding) {
		this.forceRequestEncoding = forceRequestEncoding;
	}

	/**
	 * Default constructor.
	 */
	public CharsetEncodingFilter() {
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {

		String encoding = getEncoding();
		if (encoding != null) {
			if (isForceRequestEncoding() || request.getCharacterEncoding() == null) {
				request.setCharacterEncoding(encoding);
			}
			if (isForceResponseEncoding()) {
				response.setCharacterEncoding(encoding);
			}
		}
		chain.doFilter(request, response);
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
