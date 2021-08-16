package com.university.selectioncommittee.connection;

import com.university.selectioncommittee.exception.DaoException;
import com.university.selectioncommittee.util.ConnectionFactory;
import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.ReentrantLock;

public class CustomConnectionPool {
    private static final Logger logger = LogManager.getLogger();

    private static final String DB_PROPERTIES = "database/db.properties";
    private static final String DB_POOLSIZE = "poolsize";
    private final static int POOL_SIZE = 5;

    private static CustomConnectionPool instance;

    private static final AtomicBoolean isCreated = new AtomicBoolean(false);
    private static final ReentrantLock locker = new ReentrantLock(true);

    private final BlockingQueue<ProxyConnection> freeConnections;
    private final Queue<ProxyConnection> occupiedConnections;

    // private int connectionsNumber;

    public CustomConnectionPool() {

        freeConnections = new LinkedBlockingQueue<>(POOL_SIZE);
        occupiedConnections = new ArrayDeque<>();

        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                ProxyConnection connection = new ProxyConnection(ConnectionFactory.getConnection());
                freeConnections.offer(connection);
            } catch (SQLException e) {
                logger.log(Level.FATAL, "Can`t create connections ");
                throw new RuntimeException("Can`t create connections" + e);
            }
        }
    }

    public static CustomConnectionPool getInstance() {
        if (!isCreated.get()) {
            locker.lock();
            try {
                if (!isCreated.get()) {
                    instance = new CustomConnectionPool();
                    isCreated.set(true);
                    logger.log(Level.DEBUG, "Connection pool was created, number of connection {}", POOL_SIZE);
                }
            } finally {
                locker.unlock();
            }
        }
        return instance;
    }

    public Connection getConnection() {
        ProxyConnection connection = null;
        try {
            connection = freeConnections.take();
            occupiedConnections.offer(connection);
        } catch (InterruptedException e) {
            logger.log(Level.ERROR, "Connection is interrupted");
            // throw new DaoException("Connection is interrupted" + e);
        }
        return connection;
    }

    public void releaseConnection(ProxyConnection connection) {
        if (connection.getClass() == ProxyConnection.class) {
            freeConnections.remove(connection);
            occupiedConnections.offer(connection);
        } else {
            logger.log(Level.ERROR, "Wrong connection");
        }
    }

    public void destroyConnectionPool() {
        for (int i = 0; i < POOL_SIZE; i++) {
            try {
                freeConnections.take().reallyClose();
            } catch (SQLException | InterruptedException e) {
                logger.log(Level.ERROR, "Can`t close connection pool");
            }
        }
    }

    public void deregisterDriver() {
        DriverManager.getDrivers().asIterator().forEachRemaining(driver -> {
            try {
                DriverManager.deregisterDriver(driver);
            } catch (SQLException e) {
                logger.log(Level.ERROR, "Can`t deregistered driver");
                //throw new DaoException("Can`t deregistered driver" + e);
            }
        });
    }

}
