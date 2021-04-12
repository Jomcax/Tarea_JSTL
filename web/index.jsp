<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import="com.emergentes.modelo.productos"%>
<%@page import="com.emergentes.modelo.GestorProductos"%>
<%
    if (session.getAttribute("producto") == null){
        GestorProductos objeto1 = new GestorProductos();
        
        objeto1.insertarProductos(new productos(1,"Coca Cola", 8,100));
        objeto1.insertarProductos(new productos(2,"Pepsi", 11,50));
        objeto1.insertarProductos(new productos(3,"Frnak", 6,100));
        
        session.setAttribute("producto", objeto1);
    }   
%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Productos</title>
    </head>
    <body>
        <h1>Lista de Productos</h1>
        <a href="Controller?op=nuevo">Nuevo Producto</a>
        <table border="1">
            <tr>
                <th>Id</th>
                <th>Producto</th>
                <th>Precio</th>
                <th>Cantidad</th>
                <th></th>
                <th></th>
            </tr>
            <c:forEach var="item" items="${sessionScope.producto.getLista()}">
            <tr>
                <td>${item.id}</td>
                <td>${item.producto}</td> 
                <td>${item.precio}</td>
                <td>${item.cantidad}</td> 
                <td><a href="Controller?op=modificar&id=${item.id}">Editar</a></td>
                <td><a href="Controller?op=eliminar&id=${item.id}">Eliminar</a></td>
            </tr>
            </c:forEach>
        </table>
    </body>
</html>
