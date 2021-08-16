package com.university.selectioncommittee.dao;

import com.university.selectioncommittee.entity.User;
import com.university.selectioncommittee.exception.DaoException;

public interface UserDao {

    User findUserById(int userId) throws DaoException;

    User findUserByUsername(String login) throws DaoException;

    User findUserByName(String name) throws DaoException;

    User findUserBySurname(String surname) throws DaoException;

    boolean updateUserPassword(User user) throws DaoException;

    boolean deleteUser(int userId) throws DaoException;

}
