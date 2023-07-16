<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="../dynamic/header.jspf" %>

<!-- Page Content-->
<div class="container-fluid p-0">
    <!-- Interests-->
    <section class="resume-section" id="interests">
        <div class="resume-section-content">
            <h2 class="mb-5">Interests</h2>

            <c:forEach items="${interestsModel}" var="title">

            <p>${title.description}</p>

                <%--funkcja edycji i usuwania--%>
            <security:authorize access="hasAnyRole('ADMIN')">
            <form method="post" action='<c:url value="/editInterests/${title.id}"/>'>
                <div class="form-group row">
                    <label class="col-2 col-form-label">Edycja treści:</label>
                    <div class="col-10">
        <textarea class="form-control" rows="1" id="start3" name="description"
                  placeholder="tutaj opisz zadanie...">${title.description}</textarea>
                    </div>
                </div>

                    <%-- buttons for removing and saving --%>
                <div class="btn-group" role="group" aria-label="Basic example">
                    <input class="btn btn-success pull-left" type="submit" value="Wyślij" id="searchButton"></input>
            </form>
            <form method="post" action='<c:url value="/removeInterests/${title.id}"/>'>
                <input class="btn btn-danger pull-left" type="submit" value="Usuń" id="removeButton"/>
            </form>
        </div>
        <p></p>
            <%-- buttons for removing and saving --%>
            <%-- funkcja edycji i usuwania --%>
        </security:authorize>
        </c:forEach>
</div>
</section>


<security:authorize access="hasAnyRole('ADMIN')">
    <section class="resume-section" id="interests">
            <%--dodanie formularza zapisujacego--%>
        <div class="container">
            <form method="post" action='<c:url value="/interests"/>'>

                <div class="form-group row">
                    <label class="col-2" for="exampleFormControlInput3">Treść</label>
                    <div class="col-10">
                    <textarea type="text" class="form-control" rows="5" id="exampleFormControlInput3" name="description"
                              placeholder="uzupełnij treść"></textarea>
                    </div>
                </div>

                <input type="submit" class="btn btn-success" value="Dodaj nowy">
            </form>
        </div>
            <%--dodanie formularza zapisujacego--%>
    </section>
</security:authorize>


<!-- Bootstrap core JS-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Core theme JS-->
<script src="js/scripts.js"></script>
</body>
</html>
