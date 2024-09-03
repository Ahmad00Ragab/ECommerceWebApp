package gov.iti.jets.admin;

import gov.iti.jets.genericDao.GenericDAO;
import gov.iti.jets.genericDao.GenericDaoImpl;

import java.util.Set;

public class AdminRepository extends GenericDaoImpl<Admin> {


    public AdminRepository(Class<Admin> entityClass) {
        super(entityClass);
    }



}
