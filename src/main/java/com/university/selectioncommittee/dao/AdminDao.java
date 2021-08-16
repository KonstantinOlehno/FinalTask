package com.university.selectioncommittee.dao;

import com.university.selectioncommittee.entity.Admin;
import com.university.selectioncommittee.exception.DaoException;

import java.util.List;

public interface AdminDao  {
    Admin findAdminById(int adminId) throws DaoException;

    List<Admin> findAllAdmin() throws DaoException;




}
