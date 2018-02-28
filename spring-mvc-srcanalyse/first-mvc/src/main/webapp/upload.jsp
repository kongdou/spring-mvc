<%@page contentType="text/html; charset=UTF-8" language="java"%>
<html>
	<head></head>
	<title>index.jsp</title>
	<body>
		<form action="upload/file-upload.action" method="post" enctype="multipart/form-data">
			<input type="file" name="file">
			<input type="submit" value="提交E">
		</form>
	</body>
</html>