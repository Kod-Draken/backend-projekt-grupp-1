package se.yrgo.dataaccess;

import se.yrgo.domain.Member;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

public class MemberDaoImpl implements MemberDao{
    @PersistenceContext
    private EntityManager em;

    public void create(Member member){
        em.persist(member);
    }

    public void update(Member updateMember){
        em.merge(updateMember);
    }

    public void delete(Member deleteMember){
        em.remove(deleteMember);
    }

    public Member getById(String id) throws Exception{
        return em.find(Member.class, id);
    }

    public List<Member> getAll(){
        return em.createQuery("select m from Member m", Member.class).getResultList();
    }

    public List<Member> getByName(String name){
        return em.createQuery("select m from Member m where m.name = :name", Member.class).getResultList();

    }

    public Member getFullDetail(String memberId){
        return em.createQuery("select m from Member m where m.memberId = :memberId", Member.class).getSingleResult();
    }
}
