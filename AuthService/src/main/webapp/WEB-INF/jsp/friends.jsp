<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8"/>
    <title>Друзья</title>
</head>
<body>
    <c:forEach var="friend" items="${friends}">
        <c:out value="${friend.name}" /><br/>
    </c:forEach>
</body>
</html>