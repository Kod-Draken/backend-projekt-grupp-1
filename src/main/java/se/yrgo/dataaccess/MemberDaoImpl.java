package se.yrgo.dataaccess;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class MemberDaoImpl {
    @PersistenceContext
    private EntityManager em;
}
