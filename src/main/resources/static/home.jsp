<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>test INDEX</title>
</head>
<body>
    <%
        String id = (String)session.getAttribute("id");
        String place_id = (String)session.getAttribute("place_id");
    %>
    <form  action="/review/simple/write"  method="post">
        <input type="radio" id="good" name="recmnd" value="Good">
        <label for="good">Email</label>
        <input type="radio" id="bad" name="recmnd" value="Bad">
        <label for="bad">Phone</label>
        <br><input type="text" name="content" value="내용">
        <br><input hidden type="text" name="place_id" value=<%=place_id%>>
        <br><input hidden type="text" name="user_id" value=<%=id%>>
        <br><button type="submit">작성</button>
    </form>
</body>
</html>