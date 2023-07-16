<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8"%>
<%@include file="../dynamic/header.jspf"%>


        <!-- Page Content-->
        <div class="container-fluid p-0">
            <!-- Skills-->
            <section class="resume-section" id="skills">
                <div class="resume-section-content">
                    <h2 class="mb-5">Skills</h2>



                    <div class="subheading mb-3">Programming Languages & Tools</div>
                    <ul class="list-inline dev-icons">
                        <li class="list-inline-item"><i class="fab fa-html5"></i></li>
                        <li class="list-inline-item"><i class="fab fa-css3-alt"></i></li>
                        <li class="list-inline-item"><i class="fab fa-js-square"></i></li>
                        <li class="list-inline-item"><i class="fab fa-angular"></i></li>
                        <li class="list-inline-item"><i class="fab fa-react"></i></li>
                        <li class="list-inline-item"><i class="fab fa-node-js"></i></li>
                        <li class="list-inline-item"><i class="fab fa-java"></i></li>
                    </ul>
                    <div class="subheading mb-3">Workflow</div>
                    <ul class="fa-ul mb-0">
                        <li>
                            <span class="fa-li"><i class="fas fa-check"></i></span>
                            Mobile-First, Responsive Design
                        </li>
                        <li>
                            <span class="fa-li"><i class="fas fa-check"></i></span>
                            Cross Browser Testing & Debugging
                        </li>
                        <li>
                            <span class="fa-li"><i class="fas fa-check"></i></span>
                            Cross Functional Teams
                        </li>
                        <li>
                            <span class="fa-li"><i class="fas fa-check"></i></span>
                            Agile Development & Scrum
                        </li>
                    </ul>
                <p></p>

<c:forEach items="${skillsModel}" var="title">


                    <div class="d-flex flex-column flex-md-row justify-content-between mb-5">
                        <div class="flex-grow-1">
                            <div class="subheading mb-3">${title.subTitle}</div>
                            <p>${title.description}</p>
                        </div>
                    </div>


                    <security:authorize access="hasAnyRole('ADMIN')">
                    <form method="post" action='<c:url value="/editSkills/${title.id}"/>'>

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

                        <p></p>
                            <%-- buttons for removing and saving --%>
                        <div class="btn-group" role="group" aria-label="Basic example">
                            <input class="btn btn-success pull-left" type="submit" value="Wyślij" id="searchButton"></input>
                    </form>


                    <form method="post" action='<c:url value="/removeSkills/${title.id}"/>'>
                        <input class="btn btn-danger pull-left" type="submit" value="Usuń" id="removeButton"/>
                    </form>


                    </div>
                <p></p>
                   <%-- buttons for removing and saving --%>
                    <%-- funkcja edycji i usuwania --%>
                </security:authorize>
                </c:forEach>


            </section>
        </div>

<security:authorize access="hasAnyRole('ADMIN')">
    <section class="resume-section" id="skills">
            <%--dodanie formularza zapisujacego--%>
        <div class="container">
            <form method="post" action='<c:url value="/skills"/>'>

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

                <input type="submit" class="btn btn-success" value="Dodaj nowy">
            </form>
        </div>
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
