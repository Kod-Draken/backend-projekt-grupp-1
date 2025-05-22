package se.yrgo.services;

import se.yrgo.domain.Member;
import se.yrgo.dataaccess.MemberDao;
import java.util.List;

public class MemberManagementProdImpl implements MemberManagementService{
    private final MemberDao memDao;

    public MemberManagementProdImpl(MemberDao dao) {
        this.memDao = dao;
    }

    @Override
    public void newMember(Member newMember){
        memDao.create(newMember);
    }
    @Override
    public void editMember(Member changedMember){
        memDao.update(changedMember);
    }
    @Override
    public void deleteMember(Member deletedMember){
        memDao.delete(deletedMember);
    }
    @Override
    public Member findMemberById(int id) throws MemberIdNotFoundException{return memDao.getById(id);}
    @Override
    public List<Member> getAllMembers(){
        return memDao.getAll();
    }
    @Override
    public List<Member> getMembersByName(String name){
        return memDao.getByName(name);
    }
    @Override
    public Member getFullMemberDetail(String memberId){
        return memDao.getFullDetail(memberId);
    }
    @Override
    public void bookGymClass(String bookingGymClassName, String memberId){ memDao.addGymClass(bookingGymClassName, memberId);}
    @Override
    public void cancelBooking(String bookingGymClassName, String memberId){ memDao.deleteGymClass(bookingGymClassName, memberId);}
}
