<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.*,javax.naming.*,java.sql.*,javax.sql.*"%>
<%@ page import="javax.servlet.jsp.*,javax.servlet.*"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>实战</title>

<base href="<%=request.getContextPath()%>/" target="_blank">

</head>
<body>
	<table id="table_01">
	</table>
	<script src="scripts/inline-js.js">
		
	</script>

	<script>
		alert(234)
	</script>
	<script src="resources/jquery/1.12.1/jquery.js"></script>
	<script src="resources/jquery/1.12.1/jquery-ui.js"></script>
	<!--为我们定制指令所用-->
	<script src="resources/angular/1.7.5/angular.js"></script>

	<script src="resources/bootstrap/4.2.1/bootstrap.js"></script>
	<!--
   <script src="https://cdn.staticfile.org/angular.js/1.4.6/angular.js"></script>
-->

	<img src="images/img1.jpg" width="24" height="39" alt="Stickman">
	<script type="text/javascript" src="resources/bookstore.js"></script>
	<div ng-app="bookstore" ng-controller="BookAdd">
		<input name="bookId1" value="20001" ng-model="bookId" type="text">{{bookId}}
		{{BookAdd.bookId1.$valid}} <br> <input value="Test With Selenium"
			ng-model="bookName" type="text">{{bookName}} <br>
		fullName: {{fullName()}}
		<ul>
			<li ng-repeat="x in countries | filter:test | orderBy:'country'">
				{{ (x.name | uppercase) + ', ' + x.country }}</li>
		</ul>
		
		<bookstoreDirective/>

		<form name="myForm">
			Email: <input type="email" name="myAddress" ng-model="email">
			<span ng-show="myForm.myAddress.$error.email">不是一个合法的邮箱地m址</span>
		</form>


		<form name="myForm2" ng-init="myText = 'test2@runoob.com'">
			Email: <input type="email" name="myAddress" ng-model="myText"
				required>
			</p>
			<span ng-show="myForm2.myAddress.$error.email">不是一个合法的邮箱地址</span>

			Status: {{myForm2.myAddress.$valid}} {{myForm2.myAddress.$dirty}}
			{{myForm2.myAddress.$touched}} {{myForm2.myAddress.$empty}}

		</form>

		{{email}}
	</div>

	<pre> 
    As defined above, special URL schemes that refer to specific pieces of unique content,
    such as "data:", "blob:" and "filesystem:" are excluded from matching a policy of  and must be explicitly listed. 
    Policy authors should note that the content of such URLs is often derived from a response body or execution in a Document context, which may be unsafe. 
    Especially for the default-src and script-src directives, policy authors should be aware that 
       allowing "data:" URLs is equivalent to unsafe-inline and 
       allowing "blob:" or "filesystem:" URLs is equivalent to unsafe-eval.

       In either case, authors SHOULD NOT include either 'unsafe-inline' or data: 
         as valid sources in their policies. Both enable XSS attacks by allowing code to be included directly in the document itself; they are best avoided completely.

     Content-Security-Policy: default-src https: 'unsafe-inline' 'unsafe-eval'
    Content-Security-Policy:
        default-src 'self'; img-src *;
        object-src media1.example.com media2.example.com *.cdn.example.com;
        script-src trustedscripts.example.com
</pre>
	<%
		/*
		   try{
		
		Context ctx = new InitialContext(); 
		out.println(ctx.getEnvironment());
		
		out.println("<br>GlobalDS: " + ctx.lookup("java:comp/env/db/postgres/idmDS"));
		
		ctx = (Context)ctx.lookup("java:comp/env");
			
		out.println("<br>minExemptions:"+ctx.lookup("minExemptions"));
		out.println("<br>web.xml:"+ctx.lookup("db/idmgongyo2"));
		out.println("<br>globainExemptions:"+ctx.lookup("db/postgres/idmDS"));
		out.println("<br>cotnext.mlx:"+ctx.lookup("db/postgres/idmDS2"));
		
		
		   out.println(ctx);
		for(NamingEnumeration<NameClassPair> ctx2= ctx.list("env");ctx2.hasMore();) {
		NameClassPair p1 = ctx2.next();
		out.println(p1.getNameInNamespace());
		}        
		//Connection conn = ds.getConnection();  
		//System.out.println(conn.isClosed());  
		}
		catch(Exception e){
		out.println("Err:"+e.getMessage());  
		e.printStackTrace();
		}
		*/

		out.println("<pre>");
		out.println("<h2>Header list</h2>");
		{
			java.util.Enumeration<String> headerEnum = request.getHeaderNames();
			while (headerEnum.hasMoreElements()) {
				String headerName = headerEnum.nextElement();
				out.println("name=" + headerName + ",value=" + request.getHeader(headerName));
			}
		}

		out.println("<h2>cookie list</h2>");
		Cookie[] cookies = request.getCookies();
		if (cookies != null) {
			for (Cookie c : cookies) {
				out.println("name=" + c.getName() + ",value=" + c.getValue() + ",domain=" + c.getDomain());
			}

		}
		out.println("</pre>");
	%>

	<a href="test2.jsp">update cookie</a> sadf


</body>
</html>