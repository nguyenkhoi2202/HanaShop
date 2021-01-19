<%-- 
    Document   : admin
    Created on : Jan 5, 2021, 8:19:17 PM
    Author     : Nguyen Khoi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css" integrity="sha384-MCw98/SFnGE8fJT3GXwEOngsV7Zt27NXFoaoApmYm81iuXoPkFOJwJ8ERdknLPMO" crossorigin="anonymous">
        <title>Admin Page</title>
        <style>
            table{
                margin-left: 140px;
            }
            p{
                color: green;
                font-size: 32px;
            }
            .shop{
                display: flex;  
            }
            h2 a{
                margin-left: 1500px;
            }
            .err{
                margin-left: 800px;
            }
            .title{
                padding-left: 300px;
                display: flex;
            }
        </style>
    </head>
    <body>
        <c:if test="${sessionScope.dto.role == 'admin'}">
            <div class="shop">
                <p>
                    Admin Manager
                </p>
                <c:if test="${sessionScope.dto != null}">
                    <h2>
                        <a href="LogoutServlet">Logout</a>
                    </h2>
                </c:if>
                <c:if test="${sessionScope.dto == null}">
                    <h2>
                        <a href="login.html">Login</a>
                    </h2>
                </c:if>
            </div>
            <h2>
                <font color="red">Wellcome to HANA SHOP,
                ${sessionScope.dto.fullname}
                </font>
            </h2>
            <div class="title">
                <form action="DispatcherServlet">  
                    <input type="submit" class="btn btn-primary" value="Create Item" name="btAction"/>
                    <input type="submit"  class="btn btn-secondary" value="Create Category" name="btAction" />  
                    <input type="submit" class="btn btn-success" value="Get All" name="btAction" /><br/>
                    <input type="search" name="txtkeya" value="${param.txtkeya}" class="form-control rounded" placeholder="Search" aria-label="Search"
                           aria-describedby="search-addon" />
                    <input type="submit" class="btn btn-outline-primary" value="Find" name="btAction" />
                </form>
            </div>
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
                                <li class="page-item"><a class="page-link" href="ListServlet?pageIndex=${i}">${i}</a></li>
                                </c:otherwise>
                            </c:choose>
                        </c:forEach>
                </ul>
            </nav>
            <c:choose>
                <c:when test="${requestScope.SEARCHADMIN != null}">
                    <table border="1">
                        <thead>
                            <tr>
                                <th>   No.</th>
                                <th>   Name   </th>
                                <th>   Image </th>
                                <th>   Description     </th>
                                <th>   Price   </th>
                                <th>   CreateDate   </th>
                                <th>   Category   </th>
                                <th>   Quantity   </th>
                                <th>  Update  </th>
                                <th>  Delete  </th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="search" items="${SEARCHADMIN}">
                            <form action="DispatcherServlet">
                                <tr>
                                <input type="hidden" name="txtusername" value="${sessionScope.dto.fullname}" />
                                <td>${search.id}
                                    <input type="hidden" name="txtId" value="${search.id}" />
                                </td>
                                <td>
                                    <input type="text" name="txtname" value="${search.name}" />
                                </td>
                                <td><img src="${search.image}" height="70px" weight="100px"></img>
                                    <input type="file" name="txtimage" value="${search.image}" /><br/>
                                    <input type="hidden" name="txtimage1" value="${search.image}" />  
                                </td>    
                                <td>
                                    <input type="text" required="" name="txtdescription" value="${search.description}" />
                                </td>
                                <td>
                                    <input type="number" min="1" max="1000000000" name="txtprice" value="${search.price}" />
                                </td>
                                <td>${search.createDate} </td>
                                <td>
                                    <select name="txtcategory">
                                        <c:forEach var="C" items="${requestScope.CATEGORY}">
                                            <c:choose>
                                                <c:when test="${C.category == search.category}">
                                                    <option value="${C.category}">${C.categoryName}</option>  
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${C.category}">${C.categoryName}</option>   
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <input type="number"  min="1" max="10000" name="txtquantity" value="${search.quantity}" />
                                </td>
                                <td>
                                    <input type="submit" value="Update Item" name="btAction" />
                                </td>
                                <td>
                                    <input type="submit" value="Delete Item" name="btAction" />
                                </td>
                                </tr>
                            </form>
                        </c:forEach>                                       
                    </tbody>
                </c:when>
                <c:otherwise>
                    <table border="1">
                        <thead>
                            <tr>
                                <th>   No.</th>
                                <th>   Name   </th>
                                <th>   Image </th>
                                <th>   Description     </th>
                                <th>   Price   </th>
                                <th>   CreateDate   </th>
                                <th>   Category   </th>
                                <th>   Quantity   </th>
                                <th>   Status   </th>
                                <th>  Update  </th>
                                <th>  Delete  </th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="dto" items="${LISTITEM}">
                            <form action="DispatcherServlet">
                                <tr>
                                <input type="hidden" name="txtusername" value="${sessionScope.dto.fullname}" />
                                <td>${dto.id}
                                    <input type="hidden" name="txtId" value="${dto.id}" />
                                </td>
                                <td>
                                    <input type="text" name="txtname" value="${dto.name}" />
                                </td>
                                <td><img src="${dto.image}" height="70px" weight="100px"></img>
                                    <input type="file" name="txtimage" value="${dto.image}" /><br/>
                                    <input type="hidden" name="txtimage1" value="${dto.image}" />  
                                </td>    
                                <td>
                                    <input type="text" required="" name="txtdescription" value="${dto.description}" />
                                </td>
                                <td>
                                    <input type="number" min="0" required="Input Wrong" name="txtprice" value="${dto.price}" />
                                </td>
                                <td>${dto.createDate} </td>
                                <td>
                                    <select name="txtcategory">
                                        <c:forEach var="C" items="${requestScope.CATEGORY}">
                                            <c:choose>
                                                <c:when test="${C.category == dto.category}">
                                                    <option value="${C.category}" selected="">${C.categoryName}</option>  
                                                </c:when>
                                                <c:otherwise>
                                                    <option value="${C.category}">${C.categoryName}</option>   
                                                </c:otherwise>
                                            </c:choose>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <input type="number" required="Input Wrong" min="0" max="10000" name="txtquantity" value="${dto.quantity}" />
                                </td>
                                <td>
                                    <select name="txtstatus">   
                                        <c:choose>
                                            <c:when test="${dto.status == 0}">
                                                <option value="0" >0</option>  
                                                <option  >1</option>  
                                            </c:when>
                                            <c:otherwise>
                                                <option value="1">1</option>   
                                                <option >0</option>   
                                            </c:otherwise>
                                        </c:choose>                                                          
                                    </select>   
                                </td>
                                <td>
                                    <input type="submit" value="Update Item" name="btAction" />
                                </td>
                                <td>
                                    <input type="submit" value="Delete Item" name="btAction" />
                                </td>
                                </tr>
                            </form>
                        </c:forEach>
                        </tbody>
                    </c:otherwise>
                </c:choose>
                <div class="err">
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
