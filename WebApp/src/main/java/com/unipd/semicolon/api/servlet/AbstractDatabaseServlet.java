/*
 * Copyright 2018-2023 University of Padua, Italy
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.unipd.semicolon.api.servlet;

import com.unipd.semicolon.AppConfig;
import jakarta.servlet.ServletConfig;
import jakarta.servlet.http.HttpServlet;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.StringFormatterMessageFactory;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public abstract class AbstractDatabaseServlet extends HttpServlet {


    protected static final Logger LOGGER = LogManager.getLogger(AbstractDatabaseServlet.class,
            StringFormatterMessageFactory.INSTANCE);


    private DataSource ds;

    public void init(ServletConfig config) {
        ds = new AppConfig().dataSource();
        LOGGER.info("Connection pool to the database pool successfully acquired.");
    }

    public void destroy() {
        ds = null;
        LOGGER.info("Connection pool to the database pool successfully released.");
    }

    protected final Connection getConnection() throws SQLException {
        try {
            ds = new AppConfig().dataSource();
            return ds.getConnection();
        } catch (final SQLException e) {
            LOGGER.error("Unable to acquire the connection from the pool.", e);
            throw e;
        }
    }

}
