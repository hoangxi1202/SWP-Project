<%-- 
    Document   : viewTroubleEmployee
    Created on : May 26, 2022, 9:38:32 PM
    Author     : Nhat Linh
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Trouble Page</title>
        <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <link rel="stylesheet" href="css/viewTrouble.css"/>
    </head>
    <body>
        <!--Not fix updateTrouble trả về trang hiện tại (đang update trang 2 sẽ trả về trang 1) cần fix đang trang 2 update trouble vẫn về trang 2-->
        <!--Execute-->
        <div class="container">
            <div class="row">
                <div class="col-md-12">
                    <h2 class="text-center">
                        Sự cố
                    </h2>
                </div>
                <c:if test="${requestScope.LIST_TROUBLE != null}">
                    <c:if test="${not empty requestScope.LIST_TROUBLE }">
                        <div class="table-responsive" id="no-more-tables">
                            <table class="table col-sm-12 table-bordered table-striped table-condensed cf">
                                <thead class="cf">
                                    <tr>
                                        <th>Sự cố số</th>
                                        <th>Căn hộ số</th>
                                        <th>Chủ hộ</th>
                                        <th>Ngày báo cáo</th>
                                        <th>Loại sự cố</th>
                                        <th>Chi tiết</th>
                                        <th>Trạng thái</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="trouble" items="${requestScope.LIST_TROUBLE}" varStatus="counter">
                                        <tr>
                                            <td data-title="Sự cố số: " >${trouble.troubleId}                                

                                            </td>
                                            <td  data-title="Căn hộ số: " >
                                                ${trouble.apartment}
                                            </td>
                                            <td data-title="Chủ hộ: " >
                                                ${trouble.ownerName}
                                            </td>
                                            <td  data-title="Ngày báo cáo: " >
                                                ${trouble.date}
                                            </td>
                                            <td data-title="Loại sự cố: " >${trouble.typeName}</td>
                                            <td  data-title="Chi tiết: " >${trouble.detail}</td>
                                            <td  data-title="Trạng thái: " >
                                                <form action="MainController">
                                                    <select name="status">
                                                        <c:if test ="${!trouble.status}">
                                                            <option value="false" selected>Not Yet</option>
                                                            <option value="true">Done</option>
                                                        </c:if>
                                                        <c:if test ="${trouble.status}">
                                                            <option value="true" selected>Done</option>
                                                            <option value="false">Not Yet</option>
                                                        </c:if>
                                                    </select>
                                                    <input type="hidden" name="troubleId" value="${trouble.troubleId}"/>
                                                    <input type="submit" name="action" value="UpdateTrouble"/> 

                                                </form>


                                            </td>

                                        </tr>
                                    </c:forEach>                           
                                </tbody>

                            </table>
                        </div>
                    </c:if>
                </c:if>
            </div>
        </div>
        <!--End-->
        <nav aria-label="Page navigation example">
            <ul class="pagination justify-content-center">
                <c:if test="${tag-1==0}">
                    <li class="page-item disabled">
                        <a class="page-link" href="MainController?action=ViewTrouble&index=${tag-1}">Previous</a>
                    </li>
                </c:if>
                <c:if test="${tag-1!=0}">
                    <li class="page-item">
                        <a class="page-link" href="MainController?action=ViewTrouble&index=${tag-1}">Previous</a>
                    </li>
                </c:if>
                <c:forEach begin="1" end="${endP}" var="i">
                    <li class="page-item" ${tag==i?"active":""}><a class="page-link" href="MainController?action=ViewTrouble&index=${i}">${i}</a></li>
                    </c:forEach>
                <li class="page-item">
                    <a class="page-link" href="MainController?action=ViewTrouble&index=${tag+1}">Next</a>
                </li>
            </ul>
        </nav>
    </body>
    <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.12.9/dist/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@4.0.0/dist/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>

</html>
