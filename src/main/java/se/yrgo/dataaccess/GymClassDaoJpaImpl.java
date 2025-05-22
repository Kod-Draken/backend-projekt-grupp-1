package se.yrgo.dataaccess;

import org.springframework.stereotype.Repository;
import se.yrgo.domain.GymClass;

import javax.persistence.EntityManager;
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
    public void updateGymClass(GymClass gymClass) {
        em.merge(gymClass);
    }

    @Override
    public void deleteGymClass(GymClass gymClass) {
        em.remove(gymClass);
    }

    @Override
    public GymClass getGymClassById(int id) {
        return em.find(GymClass.class, id);
    }

    @Override
    public List<GymClass> getGymClassesByName(String name) {
        return em.createQuery("select c from GymClass c where c.name like %:name%", GymClass.class).getResultList();
    }

    @Override
    public List<GymClass> getGymClassesByInstructor(String instructorId) {
        return em.createQuery("select c from GymClass c where c.instructor.id = :instructorId", GymClass.class).getResultList();
    }

    @Override
    public List<GymClass> getAllGymClasses() {
        return em.createQuery("select c from GymClass c", GymClass.class).getResultList();
    }
}
