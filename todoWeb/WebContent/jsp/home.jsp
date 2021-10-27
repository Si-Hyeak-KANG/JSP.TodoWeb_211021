<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	isELIgnored="false" %>

<%
	request.setCharacterEncoding("UTF-8");
%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath}" />

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Todo home</title>
    <link rel="stylesheet" href="../css/home.css">
    <!-- font awesome icon -->
    <link rel="stylesheet"
    href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.8.2/css/all.min.css"/>
</head>
<body>
    <div class="main">
        <form name="todoFrm">
            
            <!-- title -->
            <div class="title">
                <h1>TODO</h1>
            </div>

            <!-- sec btn zone -->
            <div class="sec_btn_zone">
                <button type="button" class="plus_icn"><i class="fas fa-plus"></i></button>
                <button type="button" class="minus_icn"><i class="fas fa-minus"></i></button>
            </div>

            <!-- content -->
            <div class="content">
                <div class="list_block">
                    <p class="chk_box">                    
                        <input type="checkbox" id="chkList"/>
                        <label for="chkList"></label>
                    </p>
                    <input type="text" class="get_list" disabled/>
                    <p class="trash_icn">
                        <i class="fas fa-trash"></i>
                    </p>
                </div>
                <input type="button" class="allDel_btn"value="전체삭제"/>
            </div>

            <!-- add form zone -->
            <div class="add_form_zone">
                <input type="text"  class="input_text"/>
                <input type="button" class="input_btn" value="입력"/>
            </div>
        </form>    
    </div>

</body>
</html>