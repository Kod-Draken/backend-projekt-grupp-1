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

    public Member getById(int id) throws Exception;

    public List<Member> getAll();

    public List<Member> getByName(String name);

    public Member getFullDetail(String memberId);
}
