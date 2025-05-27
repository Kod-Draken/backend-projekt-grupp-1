package se.yrgo.services;

import se.yrgo.domain.Member;
import se.yrgo.services.exceptions.MemberIdNotFoundException;
import java.util.List;

public interface MemberManagementService {

    void newMember(Member newMember);

    void editMember(Member changedMember);

    void deleteMember(Member deletedMember);

    Member findMemberById(String id) throws MemberIdNotFoundException;

    List<Member> getAllMembers();

    List<Member> getMembersByName(String name);

    Member getFullMemberDetail(String memberId);
}
