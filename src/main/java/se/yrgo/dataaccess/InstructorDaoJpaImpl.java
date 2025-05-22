package se.yrgo.dataaccess;

import org.springframework.stereotype.Repository;
import se.yrgo.domain.GymClass;
import se.yrgo.domain.Instructor;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class InstructorDaoJpaImpl implements InstructorDao {
    @PersistenceContext
    private EntityManager em;

    @Override
    public void addInstructor(Instructor instructor) {
        em.persist(instructor);
    }

    @Override
    public void modifyInstructor(Instructor changedInstructor) {
        em.merge(changedInstructor);
    }

    @Override
    public void removeInstructor(Instructor deletedInstructor) {
        Instructor instructor = em.find(Instructor.class, deletedInstructor.getInstructorId());
        em.remove(instructor);
    }

    @Override
    public Instructor getInstructorById(String id) throws Exception {
        return em.createQuery("select i from Instructor as i where i.instructorId =:id", Instructor.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public List<Instructor> getAllInstructors() {
        return em.createQuery("select i from Instructor as i", Instructor.class).getResultList();
    }

    @Override
    public List<Instructor> getInstructorsByName(String name) {
        return em.createQuery("select i from Instructor as i where i.name =:name", Instructor.class)
                .setParameter("name", name)
                .getResultList();
    }

    @Override
    public List<GymClass> getGymClasses(String id) {
        return em.createQuery("select g from GymClass as g where g.instructor.instructorId =:id", GymClass.class)
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public int getNumberOfClassesForInstructor(Instructor instructor) {
        return  (int) em.createQuery("select count(g) FROM GymClass as g WHERE g.instructor = :instructor")
                .setParameter("instructor", instructor)
                .getSingleResult();
    }
}
