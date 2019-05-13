package com.be.ac.ulb.g05.Model;

import com.be.ac.ulb.g05.*;
import java.sql.Connection;

/**
 * @author @mouscb
 * @codereview @vtombou
 */
public abstract class DAO<T> {

    final Connection connect = ConnexionSQLite.getInstance();

    /**
     * Create an entry in the database
     * relative to an object
     * @param obj obj
     */
    public abstract void add(T obj);
    public abstract boolean loginExist(T obj);
    public abstract boolean mailExist(T obj);
    public abstract boolean checkLoginAndPassword(T obj);

}
