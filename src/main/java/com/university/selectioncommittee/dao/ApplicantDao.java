package com.university.selectioncommittee.dao;


import com.university.selectioncommittee.entity.Applicant;
import com.university.selectioncommittee.exception.DaoException;

import java.util.List;

public interface ApplicantDao {
    Applicant createClient(Applicant Applicant) throws DaoException;

    boolean updateClient(Applicant Applicant) throws DaoException;

    boolean verification(int ApplicantId) throws DaoException;

    Applicant findApplicantById(int clientId) throws DaoException;

    List<Applicant> findAllApplicant() throws DaoException;

    List<Applicant> findAllApplicantByAnthroponym(String name, String surname) throws DaoException;

}
