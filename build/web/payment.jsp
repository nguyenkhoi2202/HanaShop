<%-- 
    Document   : payment
    Created on : Jan 12, 2021, 10:47:20 AM
    Author     : Nguyen Khoi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Payment Page</title>
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet" id="bootstrap-css">
        <script src="//netdna.bootstrapcdn.com/bootstrap/3.0.0/js/bootstrap.min.js"></script>
        <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
        <style>
            .total{
                margin-left:  505px;
            }
        </style>
    </head>
    <body>
        <c:if test="${sessionScope.dto.role == 'user' || sessionScope.EMAIL !=null}">
            <form action="DispatcherServlet">
                <c:set var="cart" value="${sessionScope.CUSTCART.item}" />

                <div class="container">
                    <div class="row">
                        <div class="well col-xs-10 col-sm-10 col-md-6 col-xs-offset-1 col-sm-offset-1 col-md-offset-3">
                            <div class="row">
                                <div class="col-xs-6 col-sm-6 col-md-6">
                                    <address>
                                        <strong>HANA SHOP</strong>
                                        <br>
                                        31 Phan Huy Ích
                                        <br>
                                        Tân Bình TPHCM
                                        <br>
                                        <abbr title="Phone">Phone:</abbr> 0942691091
                                    </address>
                                </div>
                                <div class="col-xs-6 col-sm-6 col-md-6 text-right">
                                    <p>
                                        <em><h4> Customer Name : ${sessionScope.dto.fullname} ${sessionScope.USERNAME}</h4></em>
                                        <input type="hidden" name="txtCusName" value="${sessionScope.dto.username}${sessionScope.USERNAME}" />
                                    </p>
                                </div>
                            </div>
                            <div class="row">
                                <div class="text-center">
                                    <h1>Receipt</h1>
                                </div>
                                </span>
                                <table class="table table-hover">
                                    <thead>
                                        <tr>
                                            <th>Product</th>
                                            <th>Quantity</th>
                                            <th class="text-center">Price</th>
                                            <th class="text-center">Total</th>
                                        </tr>
                                    </thead>
                                    <c:forEach var="cart" items="${cart}" varStatus="counter">
                                        <tbody>
                                        <input type="hidden" name="txtid" value="${cart.value.id}" />
                                        <tr>
                                            <td class="col-md-6"><em>${cart.value.foodName}</em></h4>
                                                <input type="hidden" name="txtFoodname" value="${cart.value.foodName}" />
                                            </td>
                                            <td class="col-md-2" style="text-align: center"> ${cart.value.quantity} 
                                                <input type="hidden" name="txtQuantity" value="${cart.value.quantity}" />
                                            </td>
                                            <td class="col-md-2 text-center">${cart.value.price}
                                                <input type="hidden" name="txtPrice" value="${cart.value.price}" />
                                            </td>
                                            <td class="col-md-2 text-center">${cart.value.quantity * cart.value.price}
                                            </td>
                                        </tr>
                                        </tbody>
                                        <c:set var="total" value="${total + cart.value.quantity * cart.value.price}"></c:set>
                                    </c:forEach>
                                </table>
                                <div class="total">
                                    <tr>
                                        <td class="text-right"><h4><strong>Total: </strong></h4></td>
                                        <td class="text-center text-danger"><h4><strong>${total}</strong></h4>
                                            <input type="hidden" name="txtTotalPrice" value="${total}" />
                                        </td>
                                    </tr>
                                </div>
                                <input type="submit" value="Pay Now" class="btn btn-success btn-lg btn-block" name="btAction" />
                            </div>
                        </div>
                    </div>
                                        <h2>
                                            <font color="red">
                                            ${requestScope.OVER}
                                            </font>
                                        </h2>
            </form>
        </c:if>
        <c:if test="${sessionScope.dto.role != 'user' || sessionScope.EMAIL ==null}}">
            <h2>
                You Can Not have permission to use this site <br/>
                <a href="login.html">Login With User</a>
            </h2>
        </c:if>
    </body>
</html>
