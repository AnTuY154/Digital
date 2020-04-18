<%-- 
    Document   : Header
    Created on : Apr 11, 2019, 8:37:36 PM
    copyright by fu hub
    link group https://www.facebook.com/groups/498752080529382/
--%>


<%@page import="java.util.List"%>
<%@page import="entity.Digital"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Right</title>
        <link href="css/right.css" rel="stylesheet" type="text/css" />
    </head>
    <body>
        <div class="right">
            <div class="padding">
            <div>
                <div class="titleNews">
                    <a href="Home">Digital News</a>
                </div>
                <c:if test="${RightErrNew==null}">
                    <div class="text">
                        ${one.shortDes}
                    </div>
                </c:if>
                <h3>${RightErrNew}</h3>
            </div>
            <div class="newst">
                <div class="titleNews">
                    Search
                </div>
                <div>${RightSearchErr}</div>
                <form action="Search" method="GET">
                    <input class="searchBox" type="text" name="txtSearch" size="15" value="${LastTxtSearch}" required>
                    <input class="searchButton" type="submit"  value="Go">
                </form>                        
            </div>
                <div class="lastArticles">
                <c:if test="${RigtErr==null}">
                    <div class="titleNews">
                        <span>Last Articles</span><br>
                    </div>
                    <c:forEach items="${top5}" var="x">
                        <div class="text">
                            <a href="Detail?id=${x.id}">${x.title}</a>
                        </div>
                 
                    </c:forEach>
                </c:if>
                <h2>${RightErr}</h2>
            </div>
            </div>
        </div>    
    </body>
</html>
