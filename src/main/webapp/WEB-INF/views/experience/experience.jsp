<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="../dynamic/header.jspf" %>


<!-- Page Content-->
<div class="container-fluid p-0">
    <!-- Experience-->
    <section class="resume-section" id="experience">
        <div class="resume-section-content">
            <h2 class="mb-5">Experience</h2>

            <c:forEach items="${experienceModel}" var="title">

            <div class="d-flex flex-column flex-md-row justify-content-between mb-5">
                <div class="flex-grow-1">
                    <h3 class="mb-0">${title.title} </h3>
                    <div class="subheading mb-3">${title.subTitle}</div>
                    <p>${title.description}</p>
                </div>
                <div class="flex-shrink-0">
                    <span class="text-primary">
                        <fmt:formatDate pattern="MM/yyyy" value="${title.dateFrom}"/>
                        - <fmt:formatDate pattern="MM/yyyy" value="${title.dateTo}"/>
                    </span></div>
            </div>


                <%--funkcja edycji i usuwania--%>
            <security:authorize access="hasAnyRole('ADMIN')">

            <form method="post" action='<c:url value="/editExperience/${title.id}"/>'>

                    <%--edycja--%>
                <div class="form-group row">
                    <label class="col-2 col-form-label">Edycja tytułu:</label>
                    <div class="col-10">
                        <textarea class="form-control" rows="1" id="start1" name="title"
                                  placeholder="tutaj opisz zadanie...">${title.title}</textarea>
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-2 col-form-label">Edycja podtytułu:</label>
                    <div class="col-10">
                        <textarea class="form-control" rows="1" id="start2" name="subTitle"
                                  placeholder="tutaj opisz zadanie...">${title.subTitle}</textarea>
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-2 col-form-label">Edycja treści:</label>
                    <div class="col-10">
                        <textarea class="form-control" rows="1" id="start3" name="description"
                                  placeholder="tutaj opisz zadanie...">${title.description}</textarea>
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-2 col-form-label">Edycja daty rozpoczęcia:</label>
                    <div class="col-10">

                        <input class="form-control" type="date" name="dateFrom"
                               value="<fmt:formatDate pattern = "yyyy-MM-dd" value = "${title.dateFrom}"/>">

                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-2 col-form-label">Edycja daty zakończenia:</label>
                    <div class="col-10">

                        <input class="form-control" type="date" name="dateTo"
                               value="<fmt:formatDate pattern = "yyyy-MM-dd" value = "${title.dateTo}"/>">

                    </div>
                </div>
                    <%--edycja--%>
                <p></p>

                <div class="btn-group" role="group" aria-label="Basic example">
                    <input class="btn btn-success pull-left" type="submit" value="Wyślij" id="searchButton"></input>
            </form>
            <form method="post" action='<c:url value="/remove/${title.id}"/>'>
                <input class="btn btn-danger pull-left" type="submit" value="Usuń" id="removeButton"/>
            </form>
        </div>

        <p></p>

        </security:authorize>
        </c:forEach>


</div>


</section>



    <security:authorize access="hasAnyRole('ADMIN')">
        <section class="resume-section" id="experience">
        <%--                dodanie formularza zapisujacego--%>
        <div class="container">
            <form method="post" action='<c:url value="/experience"/>'>
                <div class="form-group row">
                    <label class="col-2" for="exampleFormControlInput1">Tytuł</label>
                    <div class="col-10">
                        <input type="text" class="form-control" id="exampleFormControlInput1" name="title"
                               placeholder="uzupełnij tytuł">
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-2" for="exampleFormControlInput2">Podtytuł</label>
                    <div class="col-10">
                        <input type="text" class="form-control" id="exampleFormControlInput2" name="subTitle"
                               placeholder="uzupełnij podtytuł">
                    </div>
                </div>


                <div class="form-group row">
                    <label class="col-2" for="exampleFormControlInput3">Treść</label>
                    <div class="col-10">
                    <textarea type="text" class="form-control" rows="5" id="exampleFormControlInput3" name="description"
                              placeholder="uzupełnij treść"></textarea>
                    </div>
                </div>


                <div class="form-group row">
                    <label class="col-2 col-form-label">Data rozpoczęcia pracy</label>
                    <div class="col-10">
                        <input class="form-control" type="date" name="dateFrom"
                               value="<fmt:formatDate pattern = "yyyy-MM-dd" value = "${now}"/>">
                    </div>
                </div>

                <div class="form-group row">
                    <label class="col-2 col-form-label">Data zakończenia pracy</label>
                    <div class="col-10">
                        <input class="form-control" type="date" name="dateTo" max="3000-12-31"
                               min="<fmt:formatDate pattern = "yyyy-MM-dd" value = "${now}"/>">
                        <label class="col-2 col-form-label"></label>
                    </div>
                </div>

                <input type="submit" class="btn btn-success" value="Dodaj nowy">
            </form>
        </div>
        </div>

        </div>
        </section>
    </security:authorize>




<!-- Bootstrap core JS-->
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"></script>
<!-- Core theme JS-->
<script src="js/scripts.js"></script>
</body>
</html>
