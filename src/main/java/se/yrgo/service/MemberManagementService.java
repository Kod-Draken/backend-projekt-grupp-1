package se.yrgo.service;

import se.yrgo.domain.Member;

import java.util.List;

public interface MemberManagementService {

    public void newMember(Member newMember);

    public void editMember(Member changedMember);

    public void deleteMember(Member deletedMember);

    public Member getMemberById(String id) throws Exception;

    public List<Member> getAllMembers();

    public List<Member> getMembersByName(String name);

    public Member getFullMemberDetail(String memberId);

}
