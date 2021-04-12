package com.emergentes.controlador;

import com.emergentes.modelo.GestorProductos;
import com.emergentes.modelo.productos;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "Controller", urlPatterns = {"/Controller"})
public class Controller extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        productos objProducto = new productos();
        int id;
        int pos;
        String op = request.getParameter("op");
        
        if (op.equals("nuevo")){
            HttpSession ses = request.getSession();
            GestorProductos producto = (GestorProductos) ses.getAttribute("producto");
            objProducto.setId(producto.obtieneId());
            request.setAttribute("op", op);
            request.setAttribute("miProducto", objProducto);
            request.getRequestDispatcher("editar.jsp").forward(request, response);
        }
        
        if (op.equals("modificar")){
            id = Integer.parseInt(request.getParameter("id"));
            HttpSession ses = request.getSession();
            GestorProductos producto = (GestorProductos) ses.getAttribute("producto");
            pos = producto.ubicarProductos(id);
            objProducto = producto.getLista().get(pos);
            
            request.setAttribute("op", op);
            request.setAttribute("miProducto", objProducto);
            request.getRequestDispatcher("editar.jsp").forward(request, response);
        }
        
        if (op.equals("eliminar")){
            id = Integer.parseInt(request.getParameter("id"));
            HttpSession ses = request.getSession();
            GestorProductos producto = (GestorProductos) ses.getAttribute("producto");
            pos = producto.ubicarProductos(id);
            producto.eliminarProductos(pos);
            ses.setAttribute("producto", producto);
            response.sendRedirect("index.jsp");
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        productos objProducto = new productos();
        int pos;
        String op = request.getParameter("op");
        
        if (op.equals("grabar")){
            //recuperar valores del formulario
            //verifica si es nuevo o es una modificacion
            objProducto.setId(Integer.parseInt(request.getParameter("id")));
            objProducto.setProducto(request.getParameter("producto"));
            objProducto.setPrecio(Integer.parseInt(request.getParameter("precio")));
            objProducto.setCantidad(Integer.parseInt(request.getParameter("cantidad")));
            
            HttpSession ses = request.getSession();
            GestorProductos producto = (GestorProductos) ses.getAttribute("producto");
            
            String opg = request.getParameter("opg");
            if (opg.equals("nuevo")){
                producto.insertarProductos(objProducto);
            }else{
                pos = producto.ubicarProductos(objProducto.getId());
                producto.modificarProductos(pos, objProducto);
            }
            ses.setAttribute("producto", producto);
            response.sendRedirect("index.jsp");
        }
    }
}
