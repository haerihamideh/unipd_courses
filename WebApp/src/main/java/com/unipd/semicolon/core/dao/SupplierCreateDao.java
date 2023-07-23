package com.unipd.semicolon.core.dao;

import com.unipd.semicolon.core.entity.Supplier;
import jakarta.persistence.EntityNotFoundException;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SupplierCreateDao extends AbstractDAO<List<Supplier>> {

    private final Supplier supplier;

    public SupplierCreateDao(Connection con, final Supplier supplier) {
        super(con);

        if (supplier == null) {
            LOGGER.error("The supplier cannot be null.");
            throw new EntityNotFoundException("Supplier was null.");
        }

        this.supplier = supplier;

    }

    @Override
    protected void doAccess() throws Exception {

        PreparedStatement pstmt = null;

        final String CREATE = "INSERT INTO supplier (name, address, telephone_number, email) VALUES (?, ?, ?, ?)";

        try {
            pstmt = con.prepareStatement(CREATE);

            pstmt.setString(1, supplier.getName());
            pstmt.setString(2, supplier.getAddress());
            pstmt.setString(3, supplier.getTelephoneNumber());
            pstmt.setString(4, supplier.getEmail());

            pstmt.executeUpdate();
            LOGGER.info("Supplier %d successfully stored.", supplier.getName().toString());
        } finally {
            if (pstmt != null) {
                pstmt.close();
            }
        }
    }
}