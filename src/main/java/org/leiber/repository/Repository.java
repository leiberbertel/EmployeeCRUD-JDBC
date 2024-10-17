package org.leiber.repository;

import java.util.List;

/**
 * Generic repository interface in charge of database operations <br>
 * Created on 10/10/2024 at 10:40 p.m.
 *
 * @author Leiber Bertel
 */
public interface Repository<T> {

    List<T> findAll();

    T getById(Integer id);

    T save(T t);

    boolean delete(Integer id);
}
