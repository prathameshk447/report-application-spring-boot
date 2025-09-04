<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<html>
<head>
    <title>Plan Details</title>
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css">
</head>
<body class="container">

<h2 class="text-center mt-3">Search Plans</h2>

<form:form action="searchData" method="post" modelAttribute="search">

    <table class="table table-borderless">
        <tr>
            <td>Plan Name:</td>
            <td>
                <form:select path="planName" class="form-select">
                    <form:option value="">-Select-</form:option>
                    <form:options items="${names}"/>
                </form:select>
            </td>

            <td>Plan Status:</td>
            <td>
                <form:select path="planStatus" class="form-select">
                    <form:option value="">-Select-</form:option>
                    <form:options items="${status}"/>
                </form:select>
            </td>

            <td>Gender:</td>
            <td>
                <form:select path="gender" class="form-select">
                    <form:option value="">-Select-</form:option>
                    <form:option value="Male">Male</form:option>
                    <form:option value="Fe-Male">Fe-Male</form:option>
                </form:select>
            </td>
        </tr>

        <tr>
            <td>Start Date:</td>
            <td><form:input path="startDate" type="date" class="form-control"/></td>

            <td>End Date:</td>
            <td><form:input path="endDate" type="date" class="form-control"/></td>
        </tr>

        <tr>
            <td colspan="6" class="text-center">
                <input type="submit" value="Search" class="btn btn-primary"/>
                <!-- Updated Reset button -->
                <a href="reset" class="btn btn-secondary">Reset</a>
            </td>
        </tr>
    </table>
</form:form>

<hr/>

<div class="text-center mb-3">
    <a href="excel" class="btn btn-success btn-sm">Export to Excel</a>
    <a href="pdf" class="btn btn-danger btn-sm">Export to PDF</a>
</div>

<h3 class="text-center">Plan Results</h3>
<table class="table table-striped table-hover">
    <thead>
        <tr>
            <th>Id</th>
            <th>Holder Name</th>
            <th>Gender</th>
            <th>Plan Name</th>
            <th>Plan Status</th>
            <th>Start Date</th>
            <th>End Date</th>
            <th>Benefit Amt</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${plans}" var="plan" varStatus="index">
            <tr>
                <td>${index.count}</td>
                <td>${plan.citizenName}</td>
                <td>${plan.gender}</td>
                <td>${plan.planName}</td>
                <td>${plan.planStatus}</td>
                <td>${plan.planStartDate}</td>
                <td>${plan.planEndDate}</td>
                <td>${plan.benefitAmt}</td>
            </tr>
        </c:forEach>
        <c:if test="${empty plans}">
            <tr>
                <td colspan="8" style="text-align: center">No Records found</td>
            </tr>
        </c:if>
    </tbody>
</table>

</body>
</html>
