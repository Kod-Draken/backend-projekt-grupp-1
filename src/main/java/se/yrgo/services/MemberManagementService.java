package se.yrgo.services;

import se.yrgo.domain.Member;

import java.util.List;

public interface MemberManagementService {

    public void newMember(Member newMember);

    public void editMember(Member changedMember);

    public void deleteMember(Member deletedMember);

    public Member findMemberById(int id) throws MemberIdNotFoundException;

    public List<Member> getAllMembers();

    public List<Member> getMembersByName(String name);

    public Member getFullMemberDetail(String memberId);

    public void bookGymClass(String bookingGymClassName, String memberId);

    public void cancelBooking(String bookingGymClassName, String memberId);

}
