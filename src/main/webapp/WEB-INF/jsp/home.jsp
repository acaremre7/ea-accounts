<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/main.js"></script>
</head>
<body>
	<h1>Account Manager</h1>
    <input type="Button" name="initialize" id="initialize" value="Initialize Data" onclick="initializeData();" <c:if test="${not empty userMap}">disabled = disabled</c:if>>

<c:if test="${not empty userMap}">
    <div class="userList">
        <h2>Customers</h2>
        <table>
        <tr class="tableHeader">
            <td class="userInfo">Customer ID</td>
            <td class="userInfo">Name</td>
            <td class="userInfo">Surname</td>
        </tr>
        <c:forEach var="entry" items="${userMap}">
        <tr id ="userInfo-${entry.key}" class="userInfoContainer" onclick="getUserDetails(${entry.key});">
                <td class="userInfo">${entry.key}</td>
                <td class="userInfo">${entry.value.name}</td>
                <td class="userInfo">${entry.value.surname}</td>
        </tr>
        </c:forEach>
        </table>
    </div>
</c:if>
</body>
</html>