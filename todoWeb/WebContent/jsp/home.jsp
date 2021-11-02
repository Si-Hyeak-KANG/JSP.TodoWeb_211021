<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"
	isELIgnored="false"%>

<%
	request.setCharacterEncoding("UTF-8");
%>

<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:set var="contextPath" value="${pageContext.request.contextPath }" />
<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <title>TODO List</title>
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
                <button type="button" onclick="fn_plusBtn()" class="plus_icn" ><i class="fas fa-plus"></i></button>
                <button type="button" onclick="fn_minusBtn()" class="minus_icn" ><i class="fas fa-minus"></i></button>
            </div>

            <!-- content -->
            <div class="content">
            	<c:choose>
            		<c:when test="${empty lists}"> 
            		<br><br><p>등록된 글이 없습니다.<p><br>
            		<p>플러스 버튼을 클릭하여 글을 추가해주세요.</p>
            		</c:when>
            		<c:otherwise> 
	                <table>
						<c:forEach var="list" items="${lists}" varStatus="count">
							<tr class="list_block">
		                        <td class="chk_box">
		                            <input type="checkbox" onclick="fn_chkList(${list.writeNum})" id="chkList" name ="chkList" value=""/>
		                            <input type="hidden" name="complete_info" value="${list.complete}"/>
		                        </td>
		                        <td>
		                           	<input type="text" name="content" class="get_list" value="${list.content}" disabled/>
		                        </td>
		                        <td class="trash_icn">
		                           <i class="fas fa-trash" onclick="fn_delOne(${list.writeNum})"></i>
		                        </td>
		                    </tr>
						</c:forEach>
	                </table>            		
            		</c:otherwise>
            	</c:choose>

                <input type="button" onClick="fn_delAll()" class="allDel_btn"value="전체삭제" />
            </div>

            <!-- add form zone -->
            <div class="add_form_zone">
                <input type="text" name="inputText" class="input_text" placeholder="글을 작성해주세요."/>
                <input type="button" onclick="fn_input()" name="inputBtn"  class="input_btn" value="입력"/>
            </div>
        </form>    
    </div>

<script type="text/javascript">
	function fn_plusBtn() {
		var todoFrm = document.todoFrm;
		var inputText = todoFrm.inputText;
		var inputBtn = todoFrm.inputBtn;
		inputText.style.visibility="visible";
		inputBtn.style.visibility="visible";
	}
	
	function fn_minusBtn() {
		var todoFrm = document.todoFrm;
		var inputText = todoFrm.inputText;
		var inputBtn = todoFrm.inputBtn;
		inputText.style.visibility="hidden";
		inputBtn.style.visibility="hidden";
	}
	
	function fn_input() {
		var todoFrm = document.todoFrm;
		var inputText = todoFrm.inputText.value;
		if(inputText.length==0 || inputText=="") {
			alert('오류;글을 작성하고 입력을 눌러주세요.');
		} else {
			todoFrm.method="post";
			todoFrm.action="${contextPath}/todo/newContent.do";
			todoFrm.submit();
		}
	}
	function fn_chkList(num) {
		var todoFrm = document.todoFrm;
		var complete = todoFrm.complete_info.value;
		
		if(complete=="n"){
			todoFrm.method="post";
			todoFrm.action="${contextPath}/todo/chkComplete.do?complete=y&writeNum="+num;
		} else {
			todoFrm.method="post";
			todoFrm.action="${contextPath}/todo/chkComplete.do?complete=n&writeNum="+num;
		}
		todoFrm.submit();
	}
	
	function fn_delOne(num) {
		var todoFrm = document.todoFrm;
		var chkBox = document.getElementById("chkList");
		var chkMsg = window.confirm("완료하지 않은 글 입니다. 정말 삭제하시겠습니까?");
		
		if(chkBox.checked) {
			todoFrm.method="post";
			todoFrm.action="${contextPath}/todo/delOne.do?writeNum="+num;
			todoFrm.submit();
		} else  {
			if(chkMsg) {
				todoFrm.method="post";
				todoFrm.action="${contextPath}/todo/delOne.do?writeNum="+num;
				todoFrm.submit();
			} else {
				return;
			}
		}
	}
	
	function fn_delAll() {
		var todoFrm = document.todoFrm;
		var chkMsg = window.confirm("전체 삭제하시겠습니까?");
		
		if (chkMsg) {
			todoFrm.method="post";
			todoFrm.action="${contextPath}/todo/delAll.do";
			todoFrm.submit();
		}
	}

	

</script>
</body>
</html>