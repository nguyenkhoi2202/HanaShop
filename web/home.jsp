<%-- 
    Document   : shopping
    Created on : Jan 5, 2021, 8:19:28 PM
    Author     : Nguyen Khoi
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <title>Home Page</title>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

        <style>
            h2{
                text-align: right; 
                padding-right: 200px;
            }
            .title{
                display: flex;  
                padding-top: 50px;
            }
            .buy{
                margin-left: 500px;
                display: flex;
            }
            .search{
                margin-left: 600px;
            }
            .info{
                padding-left: 50px;
            }
            .listpage{
                display: inline-block;

            }
        </style>
    </head>
    <body>      
        <c:if test="${sessionScope.dto.role == 'admin'}">
            <h2>
                You Can Not have permission to use this site <br/>
                <a href="login.html">Login With User</a>
            </h2>         
        </c:if>
        <c:if test="${sessionScope.dto.role != 'admin'}">

            <c:choose>
                <c:when test="${sessionScope.dto !=null || sessionScope.USERNAME != null}">
                    <div class="title">
                        <h2>
                            <font color="red">Wellcome to HANA SHOP,
                            ${sessionScope.dto.fullname} ${sessionScope.USERNAME}
                            </font>
                        </h2>      
                    </div>
                    <h2><a href="LogoutServlet">Logout</a></h2>
                    <h3><a href="DispatcherServlet?btAction=History&username=${sessionScope.dto.username} ${sessionScope.USERNAME}">History</a></h3>
                    <h3><a href="viewcart.jsp" >View Cart</a></h3>
                </c:when>
                <c:otherwise>
                    <h2>
                        <a href="login.html">Login Here</a>
                    </h2>
                </c:otherwise>
            </c:choose>
            <div class="full">
                <h2>
                    <font color="red">
                    ${requestScope.PRODUCT}
                    </font>
                </h2>
            </div>
            <form action="DispatcherServlet" class="search">
                Search Item <input type="text" name="txtkey" value="${param.txtkey}" /></br>
                <input type="submit" value="Search" name="btAction" />
                <input type="submit" value="All Item" name="btAction" />
            </form>
            <h2>
                <font color="red">${THANK}</font>
            </h2>
            <c:set var="searchValue" value="${param.txtkey}"/>
            <c:if test="${not empty searchValue}">
                <c:set var="result" value="${requestScope.SEARCHI}"/>
                <c:choose>
                    <c:when test="${not empty result}">
                        <div class="listpage">
                            <c:forEach var="dto" items="${result}" >
                                <br/>
                                <form action="DispatcherServlet">
                                    <div class="buy">
                                        <input type="hidden" name="txtid" value="${dto.id}" />
                                        <div>                     
                                            <img class="group list-group-image" src="${dto.image}" alt="${dto.name}" width="350" height="200">
                                        </div>
                                        <input type="hidden" name="updatename" value="${sessionScope.dto.fullname}" />
                                        <div class="info">
                                            <input type="hidden" name="txtcusName" value="${sessionScope.dto.fullname}${sessionScope.USERNAME}" />
                                            <h2>${dto.name}
                                                <input type="hidden" name="txtname" value="${dto.name}" />
                                            </h2>
                                            <h5>${dto.description}
                                                <input type="hidden" name="txtdescription" value="${dto.description}" />
                                            </h5>
                                            <h4>${dto.price}
                                                <input type="hidden" name="txtprice" value="${dto.price}" />
                                            </h4>
                                            <c:if test="${sessionScope.dto.role == 'user' || sessionScope.EMAIL != null}">
                                                <input type="submit" class="btn btn-success" value="Add Item" name="btAction" />
                                                <input type="hidden" name="lastSearchValue" value="${param.txtkey}" />
                                            </c:if>
                                        </div>
                                    </div>
                                </form>
                            </c:forEach>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <h1> No result </h1>
                    </c:otherwise> 
                </c:choose>
            </c:if>
            <c:if test="${empty searchValue}">
                <nav aria-label="..." class="d-flex justify-content-center">
                    <ul class="pagination pagination-lg">
                        <c:forEach begin="1" end="${totalPage}" var="i">
                            <c:choose>
                                <c:when test="${pageIndex == i}">
                                    <li class="page-item active" aria-current="page">
                                        <span class="page-link">
                                            ${i}
                                            <span class="sr-only">(current)</span>
                                        </span>
                                    </li>
                                </c:when>
                                <c:otherwise>
                                    <li class="page-item"><a class="page-link" href="HomeServlet?pageIndex=${i}">${i}</a></li>
                                    </c:otherwise>
                                </c:choose>
                            </c:forEach>
                    </ul>
                </nav>
                <c:forEach var="dto" items="${LISTITEM}" >
                    <br/>
                    <form action="DispatcherServlet">
                        <div class="buy">
                            <input type="hidden" name="txtid" value="${dto.id}" />
                            <div>                     
                                <img class="group list-group-image" src="${dto.image}" alt="${dto.name}" width="350" height="200">
                            </div>
                            <input type="hidden" name="updatename" value="${sessionScope.dto.fullname}" />
                            <div class="info">
                                <input type="hidden" name="txtcusName" value="${sessionScope.dto.fullname}${sessionScope.USERNAME}" />
                                <h2>${dto.name}
                                    <input type="hidden" name="txtname" value="${dto.name}" />
                                </h2>
                                <h5>${dto.description}
                                    <input type="hidden" name="txtdescription" value="${dto.description}" />
                                </h5>
                                <h4>${dto.price}
                                    <input type="hidden" name="txtprice" value="${dto.price}" />
                                </h4>
                                <c:if test="${sessionScope.dto.role == 'user' || sessionScope.EMAIL != null}">
                                    <input type="submit" class="btn btn-success" value="Add Item" name="btAction" />
                                    <input type="hidden" name="lastSearchValue" value="${param.txtkey}" />
                                </c:if>
                            </div>
                        </div>
                    </form>
                </c:forEach>
            </c:if>
        </c:if>             
    </body>
</html>