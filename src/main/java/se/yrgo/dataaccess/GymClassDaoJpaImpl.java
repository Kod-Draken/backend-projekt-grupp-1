package se.yrgo.dataaccess;

import org.springframework.stereotype.Repository;
import se.yrgo.dataaccess.exceptions.GymClassNotFoundException;
import se.yrgo.domain.GymClass;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class GymClassDaoJpaImpl implements GymClassDao {
    @PersistenceContext
    private EntityManager em;


    @Override
    public void createGymClass(GymClass gymClass) {
        em.persist(gymClass);
    }

    @Override
    public void updateGymClass(GymClass updatedGymClass) throws GymClassNotFoundException {
        GymClass existing = em.find(GymClass.class, updatedGymClass.getId());
        if(existing == null){
            throw new GymClassNotFoundException("Gym class not found");
        }
        em.merge(updatedGymClass);
    }

    @Override
    public void deleteGymClass(GymClass gymClassToDelete) throws GymClassNotFoundException {
        GymClass existing = em.find(GymClass.class, gymClassToDelete.getId());
        if(existing == null){
            throw new GymClassNotFoundException("Gym class not found");
        }
        em.remove(gymClassToDelete);
    }

    @Override
    public GymClass getGymClassById(String classId) throws GymClassNotFoundException {
        try {
            return em.createQuery("select c from GymClass c where c.classId = :classId", GymClass.class)
                    .setParameter("classId", classId)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new GymClassNotFoundException("Gym class with id: " + classId + " not found");
        }

    }

    @Override
    public List<GymClass> getGymClassesByName(String name) {
        return em.createQuery("select c from GymClass c where c.name like :name", GymClass.class)
                .setParameter("name", "%" + name + "%")
                .getResultList();
    }

    @Override
    public List<GymClass> getGymClassesByInstructor(String instructorId) {
        return em.createQuery("select c from GymClass c where c.instructor.id = :instructorId", GymClass.class)
                .setParameter("instructorId", instructorId)
                .getResultList();
    }

    @Override
    public List<GymClass> getAllGymClasses() {
        return em.createQuery("select c from GymClass c", GymClass.class).getResultList();
    }
}
