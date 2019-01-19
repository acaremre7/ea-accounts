<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet">
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/main.js"></script>
</head>
<body>
<a href="/"><img class="imgBack" src="${pageContext.request.contextPath}/images/back.png"/></a>
<h1>User Info</h1>

<table>
        <tr class="tableHeader">
            <td class="userInfo">Customer ID</td>
            <td class="userInfo">Name</td>
            <td class="userInfo">Surname</td>
            <td class="userInfo">Total Balance</td>
        </tr>
        <tr class="userInfoContainer">
                        <td class="userInfo">${customerID}</td>
                        <td class="userInfo">${name}</td>
                        <td class="userInfo">${surname}</td>
                        <td class="userInfo">${balance}</td>
        </tr>
</table>

<h1>Transaction History</h1>

<table>
        <tr class="tableHeader">
            <td class="userInfo">Account ID</td>
            <td class="userInfo">Amount</td>
        </tr>
        <c:forEach var="transaction" items="${transactions}">
            <tr class="userInfoContainer">
                <c:forEach var="transactionAmount" items="${transaction.value}">
                    <td class="userInfo">${transaction.key}</td>
                    <td class="userInfo">${transactionAmount.amount}</td>
                </c:forEach>
            </tr>
        </c:forEach>
</table>

<h1>Open New Account</h1>

<input type="number" id="initialCredit" onchange="validateInitialCredit()" onkeydown="validateInitialCredit()" placeholder="Initial Credit">
<br/>
<input type="Button" id="createAccount" value="Create Account" onclick="openAccount(${customerID})">



</body>
</html>