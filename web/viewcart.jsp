<%-- 
    Document   : viewcart
    Created on : Jan 10, 2021, 3:46:04 PM
    Author     : Nguyen Khoi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>ViewCart Page</title>
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.3/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.3/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    </head>
    <body>
        <c:if test="${sessionScope.dto.role == 'user' || sessionScope.EMAIL !=null}">
            <c:set var="cart" value="${sessionScope.CUSTCART}" />
            <c:choose>
                <c:when test="${not empty cart}">
                    <c:set var="item" value="${cart.item}"/>
                    <c:choose>
                        <c:when test="${not empty item}">
                            <div class="container">
                                <div class="row">
                                    <div class="col-xs-8">
                                        <div class="panel panel-info">
                                            <div class="panel-heading">
                                                <div class="panel-title">
                                                    <div class="row">
                                                        <div class="col-xs-3">
                                                            <h5><span class="glyphicon glyphicon-shopping-cart"></span> Shopping Cart</h5>
                                                        </div>
                                                        <div class="col-xs-6">
                                                            <h4> Customer Name : ${sessionScope.dto.fullname}${sessionScope.USERNAME}</h4>
                                                        </div>
                                                        <div class="col-xs-3">
                                                            <form action="DispatcherServlet">
                                                                <input type="submit" class="btn btn-primary btn-lg" value="Add more item" name="btAction" />
                                                            </form>
                                                        </div>
                                                    </div>
                                                </div>
                                            </div>
                                            <form action="DispatcherServlet">
                                                <c:forEach var="dto" items="${item}" varStatus="counter">
                                                    <div class="panel-body">
                                                        <div class="row">
                                                            <div class="col-xs-2"><img class="img-responsive" src="">
                                                            </div>
                                                            <div class="col-xs-4">
                                                                <h4 class="product-name"><strong>${dto.value.foodName}</strong></h4>
                                                                <h4><small>${dto.value.description}</small></h4>
                                                                <input type="hidden" name="txtfoodname" value="${dto.value.foodName}" />
                                                            </div>
                                                            <div class="col-xs-6">
                                                                <div class="col-xs-6 text-right">
                                                                    <h6><strong><span class="text-muted"> ${dto.value.price}$</span></strong></h6>
                                                                    <input type="hidden" name="txtprice" value="${dto.value.price}" />
                                                                </div>
                                                                <div class="col-xs-4">
                                                                    <input type="number" name="quantity" min="1"  class="form-control input-sm" value="${dto.value.quantity}">
                                                                    <input type="hidden" name="txtquantity" value="${dto.value.price}" />
                                                                </div>
                                                                <div class="col-xs-2">
                                                                    <input type="checkbox" name="chkitem" value="${dto.key}" />
                                                                </div>
                                                            </div>
                                                        </div>
                                                        <c:set var="total" value="${total + dto.value.price * dto.value.quantity}"></c:set>

                                                    </c:forEach>

                                                    <hr>
                                                    <div class="row">
                                                        <div class="text-center">
                                                            <div class="col-xs-9">
                                                                <h6 class="text-right">Added items?</h6>
                                                            </div>
                                                            <div class="col-xs-3">
                                                                <input type="submit" class="btn btn-default btn-sm btn-block" value="Remove Selected" name="btAction" />
                                                                <input type="submit" value="Update Quantity" class="btn btn-success" name="btAction" />
                                                            </div>
                                                        </div>
                                                    </div>
                                                </div>


                                                <div class="panel-footer">
                                                    <div class="row text-center">
                                                        <div class="col-xs-9">
                                                            <h4 class="text-right">${total}$ <strong>${cart.item.value.price * cart.item.value.quantity}</strong></h4>
                                                        </div>
                                                        <div class="col-xs-3">
                                                            <input type="submit" class="btn btn-success btn-block" value="Checkout" name="btAction" />

                                                        </div>
                                                    </div>
                                                </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <h2>
                                <font color="red">
                                ${requestScope.OVER} 
                                </font>
                            </h2> 
                        </form>
                    </c:when>
                    <c:otherwise>
                        <h1> Cart have no item</h1> 
                        <a href="home.jsp"> Add Item to cart</a>
                    </c:otherwise>
                </c:choose>
            </c:when>
            <c:otherwise>
                <h1> Cart have no item</h1> 
                <a href="home.jsp"> Add Item to cart</a>
            </c:otherwise>
        </c:choose>
    </c:if>
    <c:if test="${sessionScope.dto.role == 'admin' || sessionScope.EMAIL ==null} ">
        <h2>
            You Can Not have permission to use this site <br/>
            <a href="login.html">Login With User</a>
        </h2>
    </c:if>
</body>
</html>
