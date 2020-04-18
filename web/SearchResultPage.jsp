<%-- 
    Document   : Header
    Created on : Apr 11, 2019, 8:37:36 PM
    copyright by fu hub
    link group https://www.facebook.com/groups/498752080529382/
--%>

<%@page import="javax.naming.Context"%>

<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Result Search</title>
        <link href="css/style.css" rel="stylesheet" type="text/css">
    </head>
    <body>
        <div class="container">
            <jsp:include page="Header.jsp"/>
            <div class="content">
                <div class="main">
                    <div class="padding">

                        <c:if test="${SearchErr==null}"> 
                            <c:forEach items="${list}" var="x">
                                <div>
                                    <div class="titleNews">
                                        <a href="Detail?id=${x.id}">      
                                            ${x.title}
                                        </a>
                                    </div>
                                    <div>
                                        <div class="image_search">
                                            <c:if test="${ErrPath==null}">
                                                <img src="${x.image}"/> 
                                            </c:if>
                                            <c:if test="${ErrPath!=null}">
                                                <h2>${ErrPath} </h2>
                                            </c:if>
                                            <div class="text">
                                                ${x.shortDes}
                                            </div>
                                            <div class="clear"></div>

                                        </div>

                                    </div>
                                </div>
                                <br>
                            </c:forEach>
                            <div class="paging">
                                <c:if test="${maxPage < 1}">
                                    <h3>Not Found !!</h3>
                                </c:if>
                                <c:if test="${maxPage > 1}">
                                    <a class="${1==index?"active":""}" href="Search?index=1&txtSearch=${txt}">${1==index?index:"First"}</a>
                                    <c:forEach begin="2" end="${maxPage-1}" var="i">
                                        <a class="${i==index?"active":""}" href="Search?index=${i}&txtSearch=${txt}">${i}</a>
                                    </c:forEach>
                                    <a class="${maxPage==index?"active":""}" href="Search?index=${maxPage}&txtSearch=${txt}">${maxPage==index?index:"Last"}</a>
                                </c:if>                  
                            </div>
                        </c:if>
                        <h2>${SearchErr}</h2>
                    </div>
                </div>
                <jsp:include page="Right.jsp"/> 

            </div>
            <jsp:include page="Footer.jsp"/>
        </div>

    </body>
</html>
