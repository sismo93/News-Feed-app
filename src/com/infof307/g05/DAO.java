package com.infof307.g05;

import java.sql.Connection;
/**
 *@author @mouscb
 * @codereview @tvincent
 */
public abstract class DAO<T> {

    public Connection connect = ConnectionPostgreSQL.getInstance();



    /**
     * Create an entry in the database
     * relative to an object
     * @param obj
     */
    public abstract void add(T obj);
    public abstract boolean loginExist(T obj);
    public abstract boolean mailExist(T obj);
    public abstract boolean checkLoginAndPassword(T obj);



}
