package se.yrgo.dataaccess;

import org.springframework.stereotype.Repository;
import se.yrgo.domain.GymClass;
import se.yrgo.domain.Instructor;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Najib Bardash
 *
 * This class links the database with InstructorManagementService
 */
@Repository
public class InstructorDaoJpaImpl implements InstructorDao {
    @PersistenceContext
    private EntityManager em;

    /**
     *
     * @param instructor that is added to the database
     */
    @Override
    public void addInstructor(Instructor instructor) {
        em.persist(instructor);
    }

    /**
     *
     * @param changedInstructor that is modified
     * @throws InstructorNotFoundException if the instructor cannot be found
     */
    @Override
    public void modifyInstructor(Instructor changedInstructor) throws InstructorNotFoundException {
        Instructor existing = em.find(Instructor.class, changedInstructor.getId());
        if (existing == null) {
            throw new InstructorNotFoundException("Instructor not found");
        }
        em.merge(changedInstructor);
    }

    /**
     *
     * @param deletedInstructor that will be deleted
     * @throws InstructorNotFoundException if the instructor cannot be found
     */
    @Override
    public void removeInstructor(Instructor deletedInstructor) throws InstructorNotFoundException {
        Instructor instructor = em.find(Instructor.class, deletedInstructor.getId());
        if (instructor == null) {
            throw new InstructorNotFoundException("Instructor not found");
        }
        em.remove(instructor);
    }

    /**
     *
     * @param instructorId of the instructor to be found
     * @return the instructor as an object by asking a query from the database
     * @throws InstructorNotFoundException if the instructor cannot be found
     */
    @Override
    public Instructor getInstructorById(String instructorId) throws InstructorNotFoundException {
        try {
            return em.createQuery("select i from Instructor i WHERE i.instructorId = :instructorId", Instructor.class)
                    .setParameter("instructorId", instructorId)
                    .getSingleResult();
        } catch (NoResultException e) {
            throw new InstructorNotFoundException("No instructor with instructorId: " + instructorId);
        }
    }

    /**
     *
     * @return all instructors
     */
    @Override
    public List<Instructor> getAllInstructors() {
        return em.createQuery("select i from Instructor as i", Instructor.class).getResultList();
    }

    /**
     *
     * @param name is the match that we want to look for
     * @return all instructors with matching name
     */
    @Override
    public List<Instructor> getInstructorsByName(String name) {
        return em.createQuery("select i from Instructor as i where i.name =:name", Instructor.class)
                .setParameter("name", name)
                .getResultList();
    }

    /**
     *
     * @param instructorId of the instructor to find corresponding gym classes for
     * @return all classes of matching instructor
     * @throws InstructorNotFoundException if the instructor has no classes
     */
    @Override
    public List<GymClass> getGymClasses(String instructorId) throws InstructorNotFoundException {
        try {
            return em.createQuery("select g from GymClass as g where g.instructor.instructorId =:instructorId", GymClass.class)
                    .setParameter("instructorId", instructorId)
                    .getResultList();
        } catch (NoResultException e) {
            throw new InstructorNotFoundException("Instructor with instructorId: " + instructorId + " has no gym classes booked");
        }
    }

    /**
     *
     * @param instructor to find classes for
     * @return the number of gym classes that the instructor has
     */
    @Override
    public int getNumberOfClassesForInstructor(Instructor instructor) {
        Long count = (Long) em.createQuery("select count(g) FROM GymClass as g WHERE g.instructor = :instructor")
                .setParameter("instructor", instructor)
                .getSingleResult();

        return count.intValue();
    }
}
