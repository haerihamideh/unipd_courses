package com.unipd.semicolon.api.servlet;

import com.unipd.semicolon.api.model.Message;
import com.unipd.semicolon.business.service.Imp.SupplierServiceImp;
import com.unipd.semicolon.business.service.SupplierService;
import com.unipd.semicolon.core.entity.Supplier;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

@WebServlet(urlPatterns = "/suppliers", loadOnStartup = 1)
public class SupplierListServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    private SupplierService supplierService;

    public void init() {
        supplierService = new SupplierServiceImp();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Message m = null;

        List<Supplier> suppliers = null;


        try {
            suppliers = supplierService.getSupplierList();
        } catch (SQLException ex) {
            m = new Message("Cannot list the Suppliers: unexpected error while accessing the database.", "E200",
                    ex.getMessage());

        } catch (Exception ex) {
            m = new Message("exception",
                    "E300", ex.getMessage());
        }

        try {
            request.setAttribute("message", m);
            request.setAttribute("suppliers", suppliers);
            request.getRequestDispatcher("/WEB-INF/jsp/SupplierList.jsp").forward(request, response);
        } catch (Exception ex) {
            throw ex;
        }
    }
}
