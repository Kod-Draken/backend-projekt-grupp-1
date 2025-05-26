package se.yrgo.dataaccess;

import org.springframework.stereotype.Repository;
import se.yrgo.dataaccess.exceptions.GymClassNotFoundException;
import se.yrgo.dataaccess.exceptions.MemberMissingException;
import se.yrgo.domain.GymClass;
import se.yrgo.domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class MemberDaoJpaImpl implements MemberDao{
    @PersistenceContext
    private EntityManager em;

    /**
     *
     * @param member save new Member object into Repo
     */
    @Override
    public void create(Member member){
        em.persist(member);
    }

    /**
     *
     * @param updateMember update existing Member object
     */
    @Override
    public void update(Member updateMember){
        Member existing = em.find(Member.class, updateMember.getId());
        if(existing == null){
            throw new GymClassNotFoundException("Gym class not found");
        }
        em.merge(updateMember);
    }

    /**
     *
     * @param deleteMember delete existing member object
     */
    @Override
    public void delete(Member deleteMember){
        em.remove(deleteMember);
    }

    /**
     *
     * @param id referred object id
     * @return found Member object otherwise
     * @throws MemberMissingException if not found
     */
    @Override
    public Member getById(String id) throws MemberMissingException{
        try {
            return em.createQuery("select m from Member m where m.memberId =:id ", Member.class).setParameter("id", id).getSingleResult();
        }catch(NoResultException e){
            throw new MemberMissingException("Member not found");
        }

    }

    /**
     *
     * @return all members
     */
    @Override
    public List<Member> getAll(){
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    /**
     *
     * @param name referred member name
     * @return Member object with name
     */
    @Override
    public List<Member> getByName(String name){
        return em.createQuery("select m from Member m where m.name = :name", Member.class).setParameter("name",name).getResultList();
    }

    /**
     *
     * @param memberId referred member id
     * @return Member object with member id
     */
    @Override
    public Member getFullDetail(String memberId){
        return em.createQuery("select m from Member m where m.memberId = :memberId", Member.class).setParameter("memberId", memberId).getSingleResult();
    }

    /**
     *
     * @param newGymClass referred GymClass name
     * @param memberId referred member id
     * adds new GymClass object into found Member object's GymClass Array
     */
    @Override
    public void addGymClass(String newGymClass, String memberId){
        Member mem = em.createQuery("select m from Member m where m.memberId = :memberId", Member.class).setParameter("memberId", memberId).getSingleResult();
        mem.getAllBookedClasses().add(em.createQuery("select gc from GymClass gc where gc.classId = :newGymClass", GymClass.class).setParameter("newGymClass", newGymClass).getSingleResult());
    }

    /**
     *
     * @param oldGymClass existing referred Gym Class name
     * @param memberId referred member id
     * delete existing GymClass object from found Member object's GymClass Array
     */
    @Override
    public void deleteGymClass(String oldGymClass, String memberId){
        Member mem = em.createQuery("select m from Member m where m = :memberId", Member.class).setParameter("memberId", memberId).getSingleResult();
        mem.getAllBookedClasses().remove(em.createQuery("select gc from GymClass gc where gc.classId = :oldGymClass", GymClass.class).setParameter("oldGymClass", oldGymClass).getSingleResult());
    }
    @Override
    public List<GymClass> addedClasses(String memberId){
        return em.createQuery("select g from Member m join m.bookedClasses g where m.memberId = :memberId", GymClass.class).setParameter("memberId", memberId).getResultList();
    }

}
