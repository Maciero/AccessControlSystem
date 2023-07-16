<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="../dynamic/header.jspf" %>

<!-- Page Content-->
<div class="container-fluid p-0">
    <!-- Education-->
    <section class="resume-section" id="education">
        <div class="resume-section-content">
            <h2 class="mb-5">Building</h2>

            <c:forEach items="${buildingModel}" var="title">

            <div class="d-flex flex-column flex-md-row justify-content-between mb-5">
                <div class="flex-grow-1">
                    <h3 class="mb-0">${title.name}</h3>
                </div>
                <span><a href='<c:url value="/editEducation/${title.id}"/>'
                         class="btn-right btn btn-primary" role="button">Edytuj</a>
                        </span>
        </c:forEach>
</div>

</section>


</div>

<%--<security:authorize access="hasAnyRole('ADMIN')">--%>
    <section class="resume-section" id="experience">
            <%--                dodanie formularza zapisujacego--%>
                <h2 class="mb-5">Dodawanie budynku</h2>
        <div class="container">
            <form method="post" action='<c:url value="/building"/>'>
                <div class="form-group row">
                    <label class="col-2" for="exampleFormControlInput1">Name</label>
                    <div class="col-10">
                        <input type="text" class="form-control" id="exampleFormControlInput1" name="name"
                               placeholder="uzupełnij nazwę">
                    </div>
                </div>
                <input type="submit" class="btn btn-success" value="Dodaj nowy">
            </form>
        </div>

    </section>
<%--</security:authorize>--%>




</div>
