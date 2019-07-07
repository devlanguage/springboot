<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page
	import="java.util.*,javax.servlet.jsp.*,javax.servlet.*,java.net.*"%>

<html>
<head>
</head>

<body>
	<%
		{
			URL url;
			try {
				url = new URL(request.getRequestURL().toString());
				String hostName = url.getHost();
				String domainName = hostName.substring(hostName.indexOf(".") + 1);
				//domainName = domainName.substring(domainName.indexOf(".")+1);
				out.println("domainName=" + domainName);

				session.setAttribute("test1", "xasdfasfd");
				Cookie c = new Cookie("HPSSO_COOKIE_CSRF", "XXXxxxxxxxxxx");
				c.setDomain(domainName);
				c.setPath("/");
				c.setSecure(true);
				c.setHttpOnly(true);
				response.addCookie(c);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		{
			URL url;
			try {
				url = new URL(request.getRequestURL().toString());
				String hostName = url.getHost();
				out.println("<pre>hostName=" + hostName);
				String domainName = hostName.substring(hostName.lastIndexOf(".") + 1);
				//domainName = domainName.substring(domainName.lasIndexOf(".")+1);
				out.println("domainName=" + domainName + "</pre>");
				Cookie c = new Cookie("TopDodmain", "XXXxxxxxxxxxx");
				c.setDomain(domainName);
				c.setPath("/");
				//c.setSecure(true);
				c.setHttpOnly(true);
				response.addCookie(c);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}

		{
			URL url;
			try {
				url = new URL(request.getRequestURL().toString());
				String hostName = url.getHost();
				String domainName = hostName.substring(hostName.indexOf(".") + 1);
				//domainName = domainName.substring(domainName.indexOf(".")+1);
				out.println("domainName=" + domainName);

				session.setAttribute("test1", "xasdfasfd");
				Cookie c = new Cookie("XCVZZ", "XXXxxxxxxxxxx");
				c.setDomain(domainName);
				c.setPath("/");
				//c.setSecure(true);
				c.setHttpOnly(true);
				response.addCookie(c);

			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	%>
	<hr>
	<a href="index.jsp">main page</a> [?2/?21
</body>



</html>