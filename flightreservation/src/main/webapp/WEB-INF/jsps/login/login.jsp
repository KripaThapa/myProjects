<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body align="center">
	<h2>Login Page</h2>
	<form action="login" method="post">
		<pre>
			Username: <input type="text" name="email" />
			Password: <input type="password" name="password" />
			<input type="submit" name="login" />
		</pre>
	
	</form>
	
	${msg}
</body>
</html>