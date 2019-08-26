<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="ko">
<head>
    <title>Student List</title>
    <script>
        function f_register() {
            location.href = "/student/register";
        }
    </script>
    <style>
        table {
            text-align: center;
        }
    </style>
</head>
<body>
    <h1>Student List</h1>
    <hr />

    <table>
        <thead>
            <tr>
                <th>seq</th>
                <th>name</th>
                <th>grade</th>
                <th>age</th>
                <th>hobby</th>
            </tr>
        </thead>
        <tbody>
            <tr th:each="student : ${students}">
                <td th:text="${student.seq}"></td>
                <td><a th:href="@{'/student/view/'+${student.seq}}" th:text="${student.name}"></a></td>
                <td th:text="${student.grade}"></td>
                <td th:text="${student.age}"></td>
                <td th:text="${student.hobby}"></td>
            </tr>
        </tbody>
    </table>

    <hr />

    <button onclick="f_register()">등록</button>

</body>
</html>