<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Users</title>
       <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js" integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
    </head>
   <body>
       
   <h1>Yousung's User Management System</h1>
         
    <h4>User list</h4>
        <form action="user" method="POST">
                <div class="">
                 <table class="table">
                    <thead>
                        <tr>
                            <th>Email</th>
                            <th>First name</th>
                            <th>Last name</th>
                            <th>Active</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="user" items="${users}">
                            <tr>
                                <td>${user.email}</td>
                                <td>${user.firstName}</td>
                                <td>${user.lastName}</td>
                                <td>${user.active ? "Y" : "N"}</td>
                                <td>
                                    <button type="submit" class="btn-primary " name="action"  value="edit?${user.email}">Edit</button>
                                    <button type="submit" class="btn-secondary" name="action" value="delete${user.email}">Delete</button>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </form>
        </div>
    
    <h4>Add User</h4>   
    <div class="container">
        <div class="row">
            <div class="col">
                <form action="user" method="POST">
                     <table class="table">
                        <thead>
                            <tr>
                                <th>Email</th>
                                <th>First name</th>
                                <th>Last name</th>
                                <th>Password</th>
                                <th>Roles</th>
                                <th>Active</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr>
                                <td>
                                    <input type="email" name="email">
                                </td>
                                <td>
                                    <input type="text" name="firstName">
                                </td>
                                <td>
                                    <input type="text" name="lastName">
                                </td>
                                <td>
                                    <input type="password" name="password">
                                </td>
                                <td>
                                    <select name="role">
                                        <option value="">Roles</option>
                                        <c:forEach var="role" items="${roles}">
                                        <option value="${role.name}">${role.name}</option>
                                        </c:forEach>
                                    </select>
                                </td>
                                <td>
                                    <input type="checkbox" name="active">
                                </td>
                                <td>
                                    <button type="submit" class="btn-primary" name="action" value="add">Add</button>
                                </td>
                            </tr>
                        </tbody>
                    </table>
                </form>
            </div>
        </div>        
    </div>    

    <h4>Update User</h4>            
    <div class="container">
        <div class="row">
            <div class="col">
            <form action="user" method="POST">
                <table class="table">
                    <thead>
                        <tr>
                            <th>Email</th>
                            <th>First name</th>
                            <th>Last name</th>
                            <th>Password</th>
                            <th>Roles</th>
                            <th>Active</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td>
                                <input type="email" name="email" value="${user.email}" readonly>
                            </td>
                            <td>
                                <input type="text" name="firstName" value="${user.firstName}">
                            </td>
                            <td>
                                <input type="text" name="lastName" value="${user.lastName}">
                            </td>
                            <td>
                                <input type="password" name="password" value="${user.password}">
                            </td>
                            <td>
                                <select name="role">
                                    <option value="">Roles</option>
                                    <c:forEach var="role" items="${roles}">
                                        <option value="${role.name}">${role.name}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td>
                                <input type="checkbox" name="active">
                            </td>
                            <td>
                                <button type="submit" class="btn-primary" name="action" value="update">Update</button>
                            </td>
                        </tr>
                    </tbody>
                </table>
            </form>
                    <p>${message}</p>
            </div>               
        </div>        
    </div>     
         
    </body>
</html>
