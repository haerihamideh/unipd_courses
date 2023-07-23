/*
 * Copyright 2023 University of Padua, Italy
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
package com.unipd.semicolon.core.dao;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.message.StringFormatterMessageFactory;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class AbstractDAO<T> implements DataAccessObject<T> {

	protected static final Logger LOGGER = LogManager.getLogger(AbstractDAO.class,
			StringFormatterMessageFactory.INSTANCE);

	protected final Connection con;

	protected T outputParam = null;

	private boolean accessed = false;


	private final Object lock = new Object();

	protected AbstractDAO(final Connection con) {

		if (con == null) {
			LOGGER.error("The connection cannot be null.");
			throw new NullPointerException("The connection cannot be null.");
		}
		this.con = con;

		try {
			// ensure that autocommit is true
			con.setAutoCommit(true);
			LOGGER.debug("Auto-commit set to default value true.");
		} catch (final SQLException e) {
			LOGGER.warn("Unable to set connection auto-commit to true.", e);
		}

	}

	public final DataAccessObject<T> access() throws SQLException {

		synchronized (lock) {
			try {
				if (accessed) {
					LOGGER.error("Cannot use a DataAccessObject more than once.");
					throw new SQLException("Cannot use a DataAccessObject more than once.");
				}
			} finally {
				accessed = true;
			}
		}

		try {
			doAccess();

			try {
				con.close();
				LOGGER.debug("Connection successfully closed.");
			} catch (final SQLException e) {
				LOGGER.error("Unable to close the connection to the database.", e);
				throw e;
			}
		} catch (final Throwable t) {

			LOGGER.error("Unable to perform the requested database access operation.", t);

			try {
				if (!con.getAutoCommit()) {
					// autoCommit == false => transaction needs to be rolled back
					con.rollback();
					LOGGER.info("Transaction successfully rolled-back.");
				}
			} catch (final SQLException e) {
				LOGGER.error("Unable to roll-back the transaction.", e);
			} finally {
				try {
					con.close();
					LOGGER.debug("Connection successfully closed.");
				} catch (final SQLException e) {
					LOGGER.error("Unable to close the connection to the database.", e);
				}

			}

			if(t instanceof SQLException) {
				throw (SQLException) t;
			} else {
				throw new SQLException("Unable to perform the requested database access operation.", t);
			}
		}

		return this;
	}


	@Override
	public final T getOutputParam() {

		synchronized (lock) {
			if (!accessed) {
				LOGGER.error("Cannot retrieve the output parameter before accessing the database.");
				throw new IllegalStateException("Cannot retrieve the output parameter before accessing the database.");
			}
		}

		return outputParam;
	}

	protected abstract void doAccess() throws Exception;


}
