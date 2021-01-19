<%-- 
    Document   : history.jsp
    Created on : Jan 18, 2021, 10:12:54 PM
    Author     : Nguyen Khoi
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>History Page</title>
        <style>
            .title_oder_header{
                display: flex;
            }
            .title_order_body_row_head{
                display: flex;

            }
            .title_oder_header_row{
                margin-right: 100px;

            }
            #a{
                margin-top: 15px;
            }

            .title_order_body_row{
                margin-right: 110px;
            }
            .title_order{
                display: flex;
            }


            .title_view_header{
                display: flex;
            }
            .title_view_body_row_head{
                display: flex;

            }
            .title_view_header_row{
                margin-right: 160px;

            }

            .title_view_body_row{
                margin-right: 40px;
                width: 200px;
                height: 50px;
            }
        </style>
    </head>
    <body>
        <div class="title_order">
            <div class="title_order_1">
                <div class="title_oder_header">
                    <div class="title_oder_header_row">
                        <Strong>
                            id
                        </Strong>
                    </div>
                    <div class="title_oder_header_row">
                        <Strong>
                            UserName
                        </Strong>
                    </div>
                    <div class="title_oder_header_row">
                        <Strong>
                            Date Oder
                        </Strong>
                    </div>
                    <div class="title_oder_header_row">
                        <strong>
                            Total Price
                        </strong>
                    </div>
                </div>
                <div class="title_order_body">
                    <form action="DispatcherServlet">
                        <c:forEach var="dto" items="${requestScope.HISTORY}" varStatus="counter">
                            <div class="title_order_body_row_head">
                                <div class="title_order_body_row">
                                    <p>
                                        ${dto.orderid}
                                    </p>
                                </div>
                                <div class="title_order_body_row">
                                    <p>
                                        ${dto.username}
                                    </p>
                                </div>
                                <div class="title_order_body_row">
                                    <p>
                                        ${dto.createdate}
                                    </p>
                                </div>
                                <div class="title_order_body_row">
                                    <p>
                                        ${dto.totalprice}
                                    </p>
                                </div>

                                <div class="title_order_body_row" id="a">
                                    <a href="DispatcherServlet?btAction=ShowOrder&orderId=${dto.orderid}">View</a>
                                </div>
                            </div>
                        </c:forEach>
                    </form>
                </div>
            </div>
            <c:set var="resultDetail" value="${requestScope.HISTORYDETAIL}"/>
            <c:if test="${not empty resultDetail}">
                <div class="title_order_2">
                    <div class="title_view_header">
                        <div class="title_view_header_row">
                            <Strong>
                                Food Name
                            </Strong>
                        </div>
                        <div class="title_view_header_row">
                            <Strong>
                                Price
                            </Strong>
                        </div>
                        <div class="title_view_header_row">
                            <Strong>
                                Quantity
                            </Strong>
                        </div>
                        <div class="title_view_header_row">
                            <strong>
                                Total Price
                            </strong>
                        </div>
                    </div>
                    <div class="title_view_body">
                        <c:forEach var="dtoDetail" items="${resultDetail}" varStatus="counter">
                            <div class="title_view_body_row_head">
                                <div class="title_view_body_row">
                                    <p>
                                        ${dtoDetail.name}
                                    </p>
                                </div>
                                <div class="title_view_body_row">
                                    <p>
                                        ${dtoDetail.price}
                                    </p>
                                </div>
                                <div class="title_view_body_row">
                                    <p>
                                        ${dtoDetail.quantity}
                                    </p>
                                </div>
                                <div class="title_view_body_row">
                                    <p>
                                        ${dtoDetail.price * dtoDetail.quantity}
                                    </p>
                                </div>
                                <c:set var="total" value="${total + dtoDetail.price * dtoDetail.quantity}"/>
                            </div>
                        </c:forEach>
                        <h2>Total : ${total} </h2> 
                        </form>
                    </div>
                </c:if>
            </div>
        </div>
    </body>
</html>
