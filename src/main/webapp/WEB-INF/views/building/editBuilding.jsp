<%--<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<%@include file="../dynamic/header.jspf" %>




<!-- Page Content-->
<div class="container-fluid p-0">

    <div class="container">
        <p>Edytuj edukację</p>
        <form method="post" action='<c:url value="/editBuilding/${buildingModel.id}"/>'>
            <div class="form-group row">
                <label class="col-2" for="exampleFormControlInput1">Tytuł</label>
                <div class="col-10">
                    <input type="text" class="form-control" id="exampleFormControlInput1" name="title" value="${educationModel.title}">
                </div>
            </div>

            <div class="form-group row">
                <label class="col-2" for="exampleFormControlInput2">Podtytuł</label>
                <div class="col-10">
                    <input type="text" class="form-control" id="exampleFormControlInput2" name="subtitle" value="${educationModel.subtitle}">
                </div>
            </div>


            <div class="form-group row">
                <label class="col-2" for="exampleFormControlInput3">Treść</label>
                <div class="col-10">
                    <textarea type="text" class="form-control" rows="5" id="exampleFormControlInput3" name="description" > ${educationModel.description} </textarea>
                </div>
            </div>
            <div class="form-group row">
                <label class="col-2" for="exampleFormControlInput4">Początek</label>
                <div class="col-10">
                    <c:set var="formattedDateFrom" value="<fmt:formatDate pattern='yyyy-MM-dd' value='${educationModel.dateFrom}'/>" />
                    <input class="form-control" type="date" name="dateFrom" max="3000-12-31" id="exampleFormControlInput4" />
                </div>
            </div>
            <script>
                const formattedDateFrom = "${formattedDateFrom}";
                const dateFrom = new Date(formattedDateFrom);
                const inputDateFrom = document.getElementById("exampleFormControlInput4");
                inputDateFrom.value = formatDate(dateFrom);

                function formatDate(date) {
                    const year = date.getFullYear();
                    const month = ("0" + (date.getMonth() + 1)).slice(-2);
                    const day = ("0" + date.getDate()).slice(-2);
                    return year + "-" + month + "-" + day;
                }
            </script>
            <div class="form-group row">
                <label class="col-2" for="exampleFormControlInput5">Koniec</label>
                <div class="col-10">
                    <c:set var="formattedDateTo" value="<fmt:formatDate pattern='yyyy-MM-dd' value='${educationModel.dateTo}'/>" />
                    <input class="form-control" type="date" name="dateTo" max="3000-12-31" id="exampleFormControlInput5" />
                </div>
            </div>
            <script>
                const formattedDateTo = "${formattedDateTo}";
                const dateTo = new Date(formattedDateTo);
                const inputDateTo = document.getElementById("exampleFormControlInput5");
                inputDateTo.value = formatDate(dateTo);

                function formatDate(date) {
                    const year = date.getFullYear();
                    const month = ("0" + (date.getMonth() + 1)).slice(-2);
                    const day = ("0" + date.getDate()).slice(-2);
                    return year + "-" + month + "-" + day;
                }
            </script>


            <input type="submit" class="btn btn-success" value="Zapisz zmiany" id="searchButton">
            <!-- Button to Open the Modal -->
            <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#exampleModal">
                Usuń
            </button>

        </form>


        <!-- Button trigger modal -->


        <!-- Modal -->
        <div class="modal fade" id="exampleModal" tabindex="-1" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title" id="exampleModalLabel">Czy napewno chcesz usunąć?</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="modal-body">
                        Potwierdź!
                    </div>
                    <div class="modal-footer">
                        <button type="button" class="btn btn-secondary" data-bs-dismiss="modal">Close</button>
                        <form method="post" action='<c:url value="/del/${educationModel.id}"/>'>
                            <input type="submit" class="btn btn-danger" value = "Usuń"/>
                        </form>
                    </div>
                </div>
            </div>
        </div>


    </div>


</div>

<!-- Bootstrap core JS-->
<%@include file="../dynamic/footer.jspf" %>
