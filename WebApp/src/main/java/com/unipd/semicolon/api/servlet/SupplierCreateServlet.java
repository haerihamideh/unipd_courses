package com.unipd.semicolon.api.servlet;

import com.unipd.semicolon.api.model.Message;
import com.unipd.semicolon.business.exception.PharmacyExistsException;
import com.unipd.semicolon.business.service.Imp.SupplierServiceImp;
import com.unipd.semicolon.business.service.SupplierService;
import com.unipd.semicolon.core.entity.Supplier;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Part;
import org.apache.logging.log4j.core.util.IOUtils;
import org.apache.logging.log4j.message.StringFormattedMessage;
import org.springframework.beans.factory.annotation.Autowired;

import java.awt.datatransfer.MimeTypeParseException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.sql.SQLException;
import java.util.Collection;

@WebServlet(urlPatterns = "/supplier", loadOnStartup = 1)
public class SupplierCreateServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Autowired
    private SupplierService supplierService;

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Forward the request to the JSP file
        request.getRequestDispatcher("/WEB-INF/jsp/createSupplierResult.jsp")
                .forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {

        // model
        Supplier e = null;
        Message m = null;

        e = parseRequest(req);

        try {
            supplierService.create(e.getName(), e.getAddress(), e.getEmail(), e.getTelephoneNumber(), "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiIxIiwiUm9sZSI6InVzZXIiLCJpYXQiOjE2ODI2MzczMDcsImV4cCI6MTcxODYzNzMwN30.OCsiF_pXCHjhZMTfkyTn7sNDnzVP5qUeDV8M3UavmVo");

            m = new Message(String.format("Supplier %d successfully created.",
                    1));

            req.setAttribute("supplier", e);
            req.setAttribute("message", m);

            // forwards the control to the create-employee-result JSP
            String message = "Supplier data saved successfully.";

            // Set the message as a response attribute
            res.setHeader("supplier-message", message);

            // Generate a JavaScript script that displays the message in a popup dialog
            String script = "<script>" +
                    "alert('" + message + "');" +
                    "setTimeout(function() {" +
                    "  window.location.href = 'http://localhost:8081/suppliers';" +
                    "}, 2000);" +
                    "</script>";

            // Write the script to the response
            res.setContentType("text/html");
            PrintWriter out = res.getWriter();
            out.println(script);
        } catch (PharmacyExistsException ex) {
            String message = "Supplier exists";

            // Set the message as a response attribute
            res.setHeader("supplier-message", message);

            String script = "<script>" +
                    "alert('" + message + "');" +
                    "setTimeout(function() {" +
                    "  window.location.href = 'http://localhost:8081/supplier';" +
                    "}, 2000);" +
                    "</script>";

            // Write the script to the response
            res.setContentType("text/html");
            PrintWriter out = res.getWriter();
            out.println(script);
            try {
                Thread.sleep(2000);
            } catch (InterruptedException exc) {
                throw new RuntimeException(exc);
            }
           // req.getRequestDispatcher("/WEB-INF/jsp/createSupplierResult.jsp").forward(req, res);
        } catch (Exception ex) {
            throw new RuntimeException(ex);
        }
    }

    private Supplier parseRequest(HttpServletRequest req) {

        // request parameters
        String name = req.getParameter("supplierName");
        String address = req.getParameter("supplierAddress");
        String email = req.getParameter("supplierEmail");
        String telephoneNumber = req.getParameter("supplierTelephoneNumber");

        return new Supplier(name, address, email, telephoneNumber);
    }


}
