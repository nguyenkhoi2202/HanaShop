<%@taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql"%>
<%-- 
    Document   : createitem
    Created on : Jan 7, 2021, 10:27:54 PM
    Author     : Nguyen Khoi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CreateItem Page</title>
    </head>
    <body>
        <c:if test="${sessionScope.dto.role == 'admin'}">
            <div>
                <h2>
                    Create Product
                </h2>
                <form action="DispatcherServlet">
                    <tr>
                        ID:<input type="text" name="id" value="" /><br/>
                    </tr>
                    <tr>
                        Name:<input type="text" name="name" value="${requestScope.NAME}" /><br/>
                    </tr>
                    <tr>
                        Image:<input type="file" name="image" value="${requestScope.IMAGE}" /> <br/>
                    </tr>
                    <tr>
                        DesCription:<input type="text" name="description" value="${requestScope.DESCRIPTION}" /><br/>
                    </tr>
                    <tr>
                        Price:<input type="text" name="price" value="${requestScope.PRICE}" /><br/>
                    </tr>
                    <tr>
                        Category:
                    <select name="category">
                        <c:forEach var="C" items="${CATEGORY}">
                            <option value="${C.category}">${C.categoryName}
                            </option>
                        </c:forEach>
                    </select><br/>
                    </tr>
                    <tr>
                        Quantity:<input type="text" name="quantity" value="${requestScope.QUANTITY}" /><br/>
                    </tr>
                    <input type="submit" value="Save" name="btAction"/>
                </form>
                <h3>
                    <font color ="red">${requestScope.err}</font>

                </h3>
            </div>             
        </c:if>
        <c:if test="${sessionScope.dto.role != 'admin'}">
            <h2>
                You Can Not have permission to use this site <br/>
                <a href="login.html">Login With Admin</a>
            </h2>
        </c:if>
    </body>
</html>
