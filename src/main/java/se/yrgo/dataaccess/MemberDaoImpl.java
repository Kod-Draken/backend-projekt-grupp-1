package se.yrgo.dataaccess;

import org.springframework.stereotype.Repository;
import se.yrgo.domain.GymClass;
import se.yrgo.domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.NoResultException;
import java.util.List;

@Repository
public class MemberDaoImpl implements MemberDao{
    @PersistenceContext
    private EntityManager em;
    @Override
    public void create(Member member){
        em.persist(member);
    }
    @Override
    public void update(Member updateMember){
        em.merge(updateMember);
    }
    @Override
    public void delete(Member deleteMember){
        em.remove(deleteMember);
    }
    @Override
    public Member getById(String id) throws MemberMissingException{
        try {
            return em.find(Member.class, id);
        }catch(NoResultException e){
            System.out.println("Error: "+e.getMessage());
            return null;
        }

    }
    @Override
    public List<Member> getAll(){
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }
    @Override
    public List<Member> getByName(String name){
        return em.createQuery("select m from Member m where m.name = :name", Member.class).getResultList();

    }
    @Override
    public Member getFullDetail(String memberId){
        return em.createQuery("select m from Member m where m.memberId = :memberId", Member.class).getSingleResult();
    }
    @Override
    public void addGymClass(String newGymClass, String memberId){
        Member mem = em.createQuery("select m from Member m where m = :memberId", Member.class).getSingleResult();
        mem.getAllBookedClasses().add(em.createQuery("select gc from GymClass gc where gc.name = :newGymClass", GymClass.class).getSingleResult());
    }
    @Override
    public void deleteGymClass(String oldGymClass, String memberId){
        Member mem = em.createQuery("select m from Member m where m = :memberId", Member.class).getSingleResult();
        mem.getAllBookedClasses().remove(em.createQuery("select gc from GymClass gc where gc.name = :oldGymClass", GymClass.class).getSingleResult());
    }
}
