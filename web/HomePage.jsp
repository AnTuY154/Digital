<%-- 
    Document   : Header
    Created on : Apr 11, 2019, 8:37:36 PM
    copyright by fu hub
    link group https://www.facebook.com/groups/498752080529382/
--%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Home Page</title>
        <link href="css/style.css" rel="stylesheet" type="text/css"/>
    </head>
    <body> 
        <div class="container">
            <jsp:include page="Header.jsp"/>
            <div class="content">
                <div class="main">
                    <div class="padding"> 
                        <div class="titleNews">
                            ${top1.title}
                        </div>
                        <div class="image">
                            <c:if test="${ErrPath==null}">
                                <img src="${top1.image}"/> 
                            </c:if>
                            <c:if test="${ErrPath!=null}">
                                <h2>${ErrPath} </h2>
                            </c:if>
                        </div>
                        <div class="text">
                            ${top1.description} 
                        </div>
                        <div class="maargin-top">
                           
                                <div class="signature">
                                    
                                    <div class="icon1"></div>
                                    <div class="icon2"></div>
                                    <div class="text"> By ${top1.author} | ${top1.dateConvert}</div>
                                </div>
                           
                        </div>
                    </div>
                </div>

                <jsp:include page="Right.jsp"/> 
            </div>
            <jsp:include page="Footer.jsp"/>
        </div>
    </body>
</html>
