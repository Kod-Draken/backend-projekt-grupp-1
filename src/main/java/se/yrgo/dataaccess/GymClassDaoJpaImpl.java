package se.yrgo.dataaccess;

import org.springframework.stereotype.Repository;
import se.yrgo.dataaccess.exceptions.GymClassNotFoundException;
import se.yrgo.dataaccess.exceptions.InstructorNotFoundException;
import se.yrgo.domain.GymClass;
import se.yrgo.domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * @author Mattias
 * This is a DAO for GymClass objects, lets us do CRUD operations with the DB.
 */
@Repository
public class GymClassDaoJpaImpl implements GymClassDao {
    @PersistenceContext
    private EntityManager em;

    /**
     * Saves a GymClass to the DB
     * @param gymClass a new instance of GymClass to be saved to the DB
     */
    @Override
    public void createGymClass(GymClass gymClass) {
        em.persist(gymClass);
    }

    /**
     * Update an existing GymClass.
     * @param updatedGymClass that exists but has been edited.
     * @throws GymClassNotFoundException the specified GymClass could not be found.
     */
    @Override
    public void updateGymClass(GymClass updatedGymClass) throws GymClassNotFoundException {
        GymClass existing = em.find(GymClass.class, updatedGymClass.getId());
        if (existing == null) {
            throw new GymClassNotFoundException("Gym class not found");
        }
        em.merge(updatedGymClass);
    }

    /**
     * Identifies an existing GymClass in the DB and deletes it
     * @param gymClassToDelete a disconnected GymClass, used to find its counterpart in the DB
     * @throws GymClassNotFoundException a matching GymClass in the DB was not found
     */
    @Override
    public void deleteGymClass(GymClass gymClassToDelete) throws GymClassNotFoundException {
        GymClass existing = em.find(GymClass.class, gymClassToDelete.getId());
        if (existing == null) {
            throw new GymClassNotFoundException("Gym class not found");
        }
        em.remove(existing);
    }

    /**
     * Find a GymClass by its id
     * @param classId a String ID unique to the GymClass
     * @return a matching GymClass from the database
     * @throws GymClassNotFoundException a GymClass with the provided ID doesn't exist in the DB
     */
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

    /**
     * Find databases by a string and match it with GymClasses by name.
     * @param name a String to search by in the DB
     * @return a List of matching GymClasses
     */
    @Override
    public List<GymClass> getGymClassesByName(String name) {
        return em.createQuery("select c from GymClass c where c.name like :name", GymClass.class)
                .setParameter("name", "%" + name + "%")
                .getResultList();
    }

    /**
     * Find gym classes connected to a specific Instructor
     * @param instructorId is a unique String identifier for an Instructor
     * @return the GymClasses lead by the specified Instructor.
     */
    @Override
    public List<GymClass> getGymClassesByInstructor(String instructorId) {
        return em.createQuery("select c from GymClass c where c.instructor.id = :instructorId", GymClass.class)
                .setParameter("instructorId", instructorId)
                .getResultList();
    }

    /**
     * @return every GymClass in the database as a List
     */
    @Override
    public List<GymClass> getAllGymClasses() {
        return em.createQuery("select c from GymClass c", GymClass.class).getResultList();
    }

    /**
     * Get all attendants signed up for a GymClass
     * @param gymClassId the id of the class we're looking for
     * @return a list of Members signed up to the specified GymClass
     */
    @Override
    public List<Member> getAllAttendants(String gymClassId) {
        return em.createQuery(
                        "select m from Member m join m.bookedClasses g WHERE g.classId = :gymClassId", Member.class)
                .setParameter("gymClassId", gymClassId)
                .getResultList();
    }
}

