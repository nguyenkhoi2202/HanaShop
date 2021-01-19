<%-- 
    Document   : createcategory
    Created on : Jan 12, 2021, 2:18:21 PM
    Author     : Nguyen Khoi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>CreateCategory Page</title>
        <style>
            .category{
                margin: 0 auto;
                margin-left: 500px;
            }
        </style>
    </head>
    <body>
        <c:if test="${sessionScope.dto.role == 'admin'}">
            <div class="category">
                <h2>Create Category</h2>
                <form action="DispatcherServlet">
                    <div class="form-row">
                        <div class="col">
                            Category ID: <input type="text" name="categoryid" value="${requestScope.ID}">
                        </div>
                        <div class="col">
                            Category Name: <input type="text" name="categoryname" value="${requestScope.NAME}">
                        </div>         
                    </div>
                    <input type="submit" value="Create" name="btAction" />
                </form>
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
