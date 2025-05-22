package se.yrgo.dataaccess;

import org.springframework.stereotype.Repository;
import se.yrgo.domain.GymClass;
import se.yrgo.domain.Instructor;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
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
    public void modifyInstructor(Instructor changedInstructor) throws InstructorNotFoundException {
        Instructor existing = em.find(Instructor.class, changedInstructor.getInstructorId());
        if (existing == null) {
            throw new InstructorNotFoundException("Instructor not found");
        }
        em.merge(changedInstructor);
    }

    @Override
    public void removeInstructor(Instructor deletedInstructor) throws InstructorNotFoundException {
        Instructor instructor = em.find(Instructor.class, deletedInstructor.getInstructorId());
        if (instructor == null) {
            throw new InstructorNotFoundException("Instructor not found");
        }
        em.remove(instructor);
    }

    @Override
    public Instructor getInstructorById(String id) throws InstructorNotFoundException {
        try {
            return em.createQuery("select i from Instructor as i where i.instructorId =:id", Instructor.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new InstructorNotFoundException("Instructor with id: " + id + " not found");
        }
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
    public List<GymClass> getGymClasses(String id) throws InstructorNotFoundException {
        return em.createQuery("select g from GymClass as g where g.instructor.instructorId =:id", GymClass.class)
                .setParameter("id", id)
                .getResultList();
    }

    @Override
    public int getNumberOfClassesForInstructor(Instructor instructor) {
        return (int) em.createQuery("select count(g) FROM GymClass as g WHERE g.instructor = :instructor")
                .setParameter("instructor", instructor)
                .getSingleResult();
    }
}
