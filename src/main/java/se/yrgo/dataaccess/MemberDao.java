package se.yrgo.dataaccess;

import se.yrgo.domain.Member;

import java.util.List;

/**
 * @author Alrik-Mark Brillantes
 */
public interface MemberDao {
    public void create(Member member);

    public void update(Member updateMember);

    public void delete(Member deleteMember);

    public Member getById(String id) throws MemberMissingException;

    public List<Member> getAll();

    public List<Member> getByName(String name);

    public Member getFullDetail(String memberId);

    public void addGymClass(String gymClass, String memberId);

    public void deleteGymClass(String oldGymClass, String memberId);
}
