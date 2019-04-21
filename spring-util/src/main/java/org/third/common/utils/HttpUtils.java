package org.third.common.utils;

import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URLDecoder;
import java.net.URLEncoder;
import java.util.regex.Pattern;

import static org.third.common.http.HttpHeader.AUTHORIZATION;
import static org.third.common.utils.CommonUtils.*;

import org.apache.commons.lang3.StringEscapeUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class HttpUtils {
	private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);
	private static final String IPV4_BASIC_PATTERN_STRING = "(([1-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){1}(([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])\\.){2}([0-9]|[1-9][0-9]|1[0-9]{2}|2[0-4][0-9]|25[0-5])";
	private static final Pattern IPV4_PATTERN = Pattern.compile("^" + IPV4_BASIC_PATTERN_STRING + "$");
	private static final Pattern IPV4_MAPPED_IPV6_PATTERN = Pattern
			.compile("^::[fF]{4}:" + IPV4_BASIC_PATTERN_STRING + "$");
	private static final Pattern IPV6_STD_PATTERN = Pattern.compile("^[0-9a-fA-F]{1,4}(:[0-9a-fA-F]{1,4}){7}$");
	private static final Pattern IPV6_HEX_COMPRESSED_PATTERN = Pattern
			.compile("^(([0-9A-Fa-f]{1,4}(:[0-9A-Fa-f]{1,4}){0,5})?)::(([0-9A-Fa-f]{1,4}(:[0-9A-Fa-f]{1,4}){0,5})?)$");

	/**
	 * Checks whether the parameter is a valid IPv4 address
	 *
	 * @param input
	 *            the address string to check for validity
	 * @return true if the input parameter is a valid IPv4 address
	 */
	public static boolean isIPv4Address(final String input) {
		return IPV4_PATTERN.matcher(input).matches();
	}

	public static boolean isIPv4MappedIPv64Address(final String input) {
		return IPV4_MAPPED_IPV6_PATTERN.matcher(input).matches();
	}

	/**
	 * Checks whether the parameter is a valid standard (non-compressed) IPv6
	 * address
	 *
	 * @param input
	 *            the address string to check for validity
	 * @return true if the input parameter is a valid standard (non-compressed) IPv6
	 *         address
	 */
	public static boolean isIPv6StdAddress(final String input) {
		return IPV6_STD_PATTERN.matcher(input).matches();
	}

	/**
	 * Checks whether the parameter is a valid compressed IPv6 address
	 *
	 * @param input
	 *            the address string to check for validity
	 * @return true if the input parameter is a valid compressed IPv6 address
	 */
	public static boolean isIPv6HexCompressedAddress(final String input) {
		int colonCount = 0;
		for (int i = 0; i < input.length(); i++) {
			if (input.charAt(i) == ':') {
				colonCount++;
			}
		}
		return colonCount <= 7 && IPV6_HEX_COMPRESSED_PATTERN.matcher(input).matches();
	}

	/**
	 * Checks whether the parameter is a valid IPv6 address (including compressed).
	 *
	 * @param input
	 *            the address string to check for validity
	 * @return true if the input parameter is a valid standard or compressed IPv6
	 *         address
	 */
	public static boolean isIPv6Address(final String input) {
		return isIPv6StdAddress(input) || isIPv6HexCompressedAddress(input);
	}

	/**
	 * Add the authentication information for HTTP connection. By default
	 * authentication type is Basic
	 * 
	 * @param username
	 * @param password
	 * @param connection
	 */
	public static String getBasicAuth(String username, String password) {
		if (username != null && !username.trim().isEmpty() && password != null && !password.trim().isEmpty()) {
			String authHeader = username + ":" + password;
			return "Basic " + CodecUtils.encodeBase64String(authHeader.getBytes(UTF8_CHARSET), true);
		} else {
			return "";
		}

	}

	/**
	 * Add the authentication information for HTTP connection. By default
	 * authentication type is Basic
	 * 
	 * @param username
	 * @param password
	 * @param connection
	 */
	public static void addPasssworddBasicAuth(String username, String password, HttpURLConnection connection) {
		if (username != null && !username.trim().isEmpty() && password != null && !password.trim().isEmpty()) {
			String authHeader = username + ":" + password;
			String encoded = CodecUtils.encodeBase64String(authHeader.getBytes(UTF8_CHARSET), true);
			connection.setRequestProperty(AUTHORIZATION.getName(), "Basic " + encoded);
		}
	}

	/**
	 * Translates a string into {@code application/x-www-form-urlencoded} format
	 * using a specific encoding scheme. This method uses the supplied encoding
	 * scheme to obtain the bytes for unsafe characters.
	 * 
	 * @param url
	 *            plain URL
	 * @return encode URL
	 */
	public static final String encodeUrl(String url) {
		try {
			return URLEncoder.encode(url, UTF8);
		} catch (UnsupportedEncodingException e) {
			logger.warn("Cannot encode the request URL: " + url);
			return (url);
		}
	}

	/**
	 * Decodes a {@code application/x-www-form-urlencoded} string using a specific
	 * encoding scheme. The supplied encoding is used to determine what characters
	 * are represented by any consecutive sequences of the form
	 * "<i>{@code %xy}</i>".
	 * <p>
	 * <em><strong>Note:</strong> The
	 * <a href= "http://www.w3.org/TR/html40/appendix/notes.html#non-ascii-chars">
	 * World Wide Web Consortium Recommendation</a> states that UTF-8 should be
	 * used. Not doing so may introduce incompatibilities.</em>
	 *
	 * @param s
	 *            the {@code String} to decode
	 * @param url
	 *            plain URL
	 * @return encode URL
	 */
	public static final String decodeUrl(String url) {
		try {
			return URLDecoder.decode(url, UTF8);
		} catch (UnsupportedEncodingException e) {
			logger.warn("Cannot encode the request URL: " + url);
			return (url);
		}
	}

//	/**
//	 * Construct the normalized request uri without useless "/". for example, if
//	 * request path is http:///test.com///abc///ax.jsp. it will return
//	 * http://test.com/abc/ax.jsp
//	 * 
//	 * @param request
//	 *            instance of {@link HttpServletRequest}
//	 * @return normalized http request path
//	 */
//	public static final String getRequestUri(HttpServletRequest request) {
//		return decodeUrl(request.getRequestURI());
//	}
//
//	/**
//	 * Get the current web application uri.
//	 *
//	 * @param request
//	 *            {@link javax.servlet.http.HttpServletRequest}
//	 * @return current web application uri
//	 */
//	public static final String getWebAppUri(HttpServletRequest request) {
//		StringBuilder sb = new StringBuilder();
//		sb.append(request.getScheme()).append("://").append(request.getServerName()).append(":")
//				.append(request.getServerPort()).append(request.getContextPath());
//		return sb.toString();
//	}

	/**
	 * Assert if path is matched with url pattern. For example. <br>
	 * <br>
	 * matchPath("/abc", "/asdf")): false <br>
	 * matchPath("/abc", "/abc")): true <br>
	 * matchPath("*.do", "/asdf.do")): true <br>
	 * matchPath("/*.do", "/asdf.do")): true <br>
	 * matchPath("/asdf/*.do", "/asdf/xxx/asdf.do")): true <br>
	 * matchPath("/asdf/*.do", "/asddf/xxx/asdfdo")): false <br>
	 * 
	 * @param pattern
	 *            url pattern
	 * @param path
	 *            http request url
	 * @return
	 */
	public static boolean matchPath(String pattern, String path) {
		if (pattern.indexOf("*") != -1) {
			int pos = pattern.indexOf("*.");
			if (pattern.endsWith("/*")) {
				return path.startsWith(pattern.substring(0, pattern.length() - 1));
			} else if (pos != -1) {
				if (pos == 0 || (pos == 1 && pattern.startsWith("/"))) {
					return path.endsWith(pattern.substring(2));
				} else {
					return path.endsWith(pattern.substring(pos + 2)) && path.startsWith(pattern.substring(0, pos));
				}

			} else {
				return false;
			}
		} else {
			return pattern.equalsIgnoreCase(path);
		}
	}

	public static final String encodeHtml(String htmlSrc) {
		return StringEscapeUtils.escapeHtml4(htmlSrc);
	}

	public static final String decodeHtml(String htmlSrc) {
		return StringEscapeUtils.unescapeHtml4(htmlSrc);
	}
}
