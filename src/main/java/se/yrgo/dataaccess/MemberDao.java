package se.yrgo.dataaccess;

import se.yrgo.domain.Member;

import java.util.List;

public interface MemberDao {
    public void Create(Member member);

    public void Update(Member updateMember);

    public void Delete(Member deleteMember);

    public Member getById(String id) throws Exception;

    public List<Member> getAllMembers();

    public List<Member> getByName(String name);

    public Member getFullMemberDetail(String memberId);
}
