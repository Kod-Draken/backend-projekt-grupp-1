package se.yrgo.services;

import se.yrgo.domain.Member;
import java.util.List;

public interface MemberManagementService {

    void newMember(Member newMember);

    void editMember(Member changedMember);

    void deleteMember(Member deletedMember);

    Member findMemberById(String id);

    List<Member> getAllMembers();

    List<Member> getMembersByName(String name);

    Member getFullMemberDetail(String memberId);
}
