package by.htp.ellib.dao.impl;

	import java.sql.Array;
	import java.sql.Blob;
	import java.sql.CallableStatement;
	import java.sql.Clob;
	import java.sql.Connection;
	import java.sql.DatabaseMetaData;
	import java.sql.DriverManager;
	import java.sql.NClob;
	import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLClientInfoException;
	import java.sql.SQLException;
	import java.sql.SQLWarning;
	import java.sql.SQLXML;
	import java.sql.Savepoint;
	import java.sql.Statement;
	import java.sql.Struct;
	import java.util.Map;
	import java.util.Properties;
	import java.util.ResourceBundle;
	import java.util.concurrent.ArrayBlockingQueue;
	import java.util.concurrent.BlockingQueue;
	import java.util.concurrent.Executor;


	public final class ConnectionPool {
		
		
		public static ConnectionPool getInstance() {
			return instance;
		}	
		
		private BlockingQueue <Connection> connectionQueue;
		private BlockingQueue<Connection> givenAwayConQueue;
		
		private String driverName; 
		private String url; 
		private String login; 
		private String password;
		private int poolSize;
		
		public ConnectionPool() {
			DbManager dbManager = DbManager.getInstance();
			
					
			this.driverName = dbManager.getValue(DbParameter.DB_DRIVER);;
			this.url = dbManager.getValue(DbParameter.DB_URL);
			this.login = dbManager.getValue(DbParameter.DB_LOGIN);
			this.password = dbManager.getValue(DbParameter.DB_PASSWORD);
			this.poolSize=Integer.parseInt(dbManager.getValue(DbParameter.DB_POOLSIZE));
			try {
				initPoolData();
			} catch (ConnectionPoolException e) {
				e.printStackTrace();
			}		
		}
		
		private static final ConnectionPool instance = new ConnectionPool();
		
		
		public void initPoolData() throws ConnectionPoolException {
			try {
				Class.forName(driverName);
				
				connectionQueue = new ArrayBlockingQueue<Connection>(poolSize);
				givenAwayConQueue = new ArrayBlockingQueue<Connection>(poolSize);
				
				for (int i = 0; i < poolSize; i++) {
					Connection connection = DriverManager.getConnection(url, login, password);
					PooledConnection pooledConnection = new PooledConnection(connection);
					connectionQueue.add(pooledConnection);				
				}
				
			} catch (ClassNotFoundException e) {
				throw new ConnectionPoolException("Where is your driver?", e);
			} catch (SQLException e) {
				throw new ConnectionPoolException("SQLException in Connection pool", e);
			}		
		}
		
		//clean
		public void dispose () {
			clearConnectionQueue();
		}
		
		private void clearConnectionQueue() {
			try {
				closeConnestionsQueue(givenAwayConQueue);
				closeConnestionsQueue(connectionQueue);
			} catch (SQLException e) {
				// logger.log
			}		
		}
		
		public Connection takeConnection() throws ConnectionPoolException {
			Connection connection = null;
			try {
				connection = connectionQueue.take();
				givenAwayConQueue.add(connection);
			} catch (InterruptedException e) {
				throw new ConnectionPoolException("Connection failed ", e);
			}
			return connection;		
		}
		
		public void closeConnection (ResultSet rs, PreparedStatement st, Connection con) {
			try {
				if (rs != null) {			
						rs.close();
					}
				} catch (SQLException e) {
				}
			try {
				if (st != null) {			
						st.close();
					}
				} catch (SQLException e) {
				}
			try {
				if (con != null) {			
						con.close();
					}
				} catch (SQLException e) {
				}			
			}
		
		public void closeConnection (PreparedStatement st, Connection con) {
			try {
				if (st != null) {			
						st.close();
					}
				} catch (SQLException e) {
					//logger.log	
				}
			try {
				if (con != null) {			
						con.close();
					}
				} catch (SQLException e) {
					//logger.log	
				}			
			}
		
		private void closeConnestionsQueue(BlockingQueue<Connection> queue) throws SQLException {
			Connection connection;
			
			while ((connection = queue.poll()) != null) {
				if (!connection.getAutoCommit()) {
					connection.commit();
				}
				((PooledConnection) connection).reallyClose();
			}
		}
		
		
		private class PooledConnection implements Connection {
			
			private Connection connection;		

			public PooledConnection(Connection c) throws SQLException {
				
				this.connection = c;
				this.connection.setAutoCommit(true);
			}
			
			public void reallyClose() throws SQLException {
				connection.close();
			}
			
			public void clearWarnings() throws SQLException {
				connection.clearWarnings();			
			}
			
			public void close() throws SQLException {
				if (connection.isClosed()) {
					throw new SQLException("Ошибка, подключение уже закрыто");
				}
				if (connection.isReadOnly()) {
					connection.setReadOnly(false);
				}
				if (!givenAwayConQueue.remove(this)) {
					throw new SQLException("Ошибка удаления из подключений");
				}
				if (!connectionQueue.offer(this)) {
					throw new SQLException("Ошибка размещения");
				}
			}
			
			public void commit() throws SQLException {
				connection.commit();			
			}

			

			public boolean isWrapperFor(Class<?> arg0) throws SQLException {
				return connection.isWrapperFor(arg0);
			}

			public <T> T unwrap(Class<T> arg0) throws SQLException {
				return connection.unwrap(arg0);
			}

			public void abort(Executor executor) throws SQLException {
				connection.abort(executor);			
			}
			
			public Array createArrayOf(String typeName, Object[] elements) throws SQLException {
				return connection.createArrayOf(typeName, elements);
			}

			public Blob createBlob() throws SQLException {
				return connection.createBlob();
			}

			public Clob createClob() throws SQLException {
				return connection.createClob();
			}

			public NClob createNClob() throws SQLException {
				return connection.createNClob();
			}

			public SQLXML createSQLXML() throws SQLException {
				return connection.createSQLXML();
			}

			public Statement createStatement() throws SQLException {
				return connection.createStatement();
			}

			public Statement createStatement(int resultSetType, int resultSetConcurrency) throws SQLException {
				return connection.createStatement(resultSetType, resultSetConcurrency);
			}

			public Statement createStatement(int resultSetType, int resultSetConcurrency, int resultSetHoldability)
					throws SQLException {
				return connection.createStatement(resultSetType, resultSetConcurrency, resultSetHoldability);
			}

			public Struct createStruct(String typeName, Object[] attributes) throws SQLException {
				return connection.createStruct(typeName, attributes);
			}

			public boolean getAutoCommit() throws SQLException {
				return connection.getAutoCommit();
			}

			public String getCatalog() throws SQLException {
				return connection.getCatalog();
			}

			public Properties getClientInfo() throws SQLException {
				return connection.getClientInfo();
			}

			public String getClientInfo(String name) throws SQLException {
				return connection.getClientInfo(name);
			}

			public int getHoldability() throws SQLException {
				return connection.getHoldability();
			}

			public DatabaseMetaData getMetaData() throws SQLException {
				return connection.getMetaData();
			}

			public int getNetworkTimeout() throws SQLException {
				return connection.getNetworkTimeout();
			}

			public String getSchema() throws SQLException {
				return connection.getSchema();
			}

			public int getTransactionIsolation() throws SQLException {
				return connection.getTransactionIsolation();
			}

			public Map<String, Class<?>> getTypeMap() throws SQLException {
				return connection.getTypeMap();
			}

			public SQLWarning getWarnings() throws SQLException {
				return connection.getWarnings();
			}

			public boolean isClosed() throws SQLException {
				return connection.isClosed();
			}

			public boolean isReadOnly() throws SQLException {
				return connection.isReadOnly();
			}

			public boolean isValid(int timeout) throws SQLException {
				return connection.isValid(timeout);
			}

			public String nativeSQL(String sql) throws SQLException {
				return connection.nativeSQL(sql);
			}

			public CallableStatement prepareCall(String sql) throws SQLException {
				return connection.prepareCall(sql);
			}

			public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency)
					throws SQLException {
				return connection.prepareCall(sql, resultSetType, resultSetConcurrency);
			}

			public CallableStatement prepareCall(String sql, int resultSetType, int resultSetConcurrency,
					int resultSetHoldability) throws SQLException {
				return connection.prepareCall(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
			}

			public PreparedStatement prepareStatement(String sql) throws SQLException {
				return connection.prepareStatement(sql);
			}

			public PreparedStatement prepareStatement(String sql, int autoGeneratedKeys) throws SQLException {
				return connection.prepareStatement(sql, autoGeneratedKeys);
			}

			public PreparedStatement prepareStatement(String sql, int[] columnIndexes) throws SQLException {
				return connection.prepareStatement(sql, columnIndexes);
			}

			public PreparedStatement prepareStatement(String sql, String[] columnNames) throws SQLException {
				return connection.prepareStatement(sql, columnNames);
			}

			public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency)
					throws SQLException {
				return connection.prepareStatement(sql, resultSetType, resultSetConcurrency);
			}

			public PreparedStatement prepareStatement(String sql, int resultSetType, int resultSetConcurrency,
					int resultSetHoldability) throws SQLException {
				return connection.prepareStatement(sql, resultSetType, resultSetConcurrency, resultSetHoldability);
			}

			public void releaseSavepoint(Savepoint savepoint) throws SQLException {
				connection.releaseSavepoint(savepoint);		
			}

			public void rollback() throws SQLException {
				connection.rollback();			
			}

			public void rollback(Savepoint savepoint) throws SQLException {
				connection.rollback(savepoint);			
			}

			public void setAutoCommit(boolean autoCommit) throws SQLException {
				connection.setAutoCommit(autoCommit);		
			}

			public void setCatalog(String catalog) throws SQLException {
				connection.setCatalog(catalog);			
			}

			public void setClientInfo(Properties properties) throws SQLClientInfoException {
				connection.setClientInfo(properties);			
			}

			public void setClientInfo(String name, String value) throws SQLClientInfoException {
				connection.setClientInfo(name, value);
			}

			public void setHoldability(int holdability) throws SQLException {
				connection.setHoldability(holdability);
			}

			public void setNetworkTimeout(Executor executor, int milliseconds) throws SQLException {
				connection.setNetworkTimeout(executor, milliseconds);
			}

			public void setReadOnly(boolean readOnly) throws SQLException {
				connection.setReadOnly(readOnly);			
			}

			public Savepoint setSavepoint() throws SQLException {
				return connection.setSavepoint();
			}

			public Savepoint setSavepoint(String name) throws SQLException {
				return connection.setSavepoint(name);
			}

			public void setSchema(String schema) throws SQLException {
				connection.setSchema(schema);
			}

			public void setTransactionIsolation(int level) throws SQLException {
				connection.setTransactionIsolation(level);
			}

			public void setTypeMap(Map<String, Class<?>> map) throws SQLException {
				connection.setTypeMap(map);
			}
			
			
			
		}
	}

