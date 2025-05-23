package se.yrgo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import se.yrgo.domain.Member;
import se.yrgo.dataaccess.MemberDao;
import se.yrgo.services.exceptions.MemberIdNotFoundException;

import java.util.List;
@Transactional
@Service
public class MemberManagementProdImpl implements MemberManagementService{
    private final MemberDao memDao;

    @Autowired
    public MemberManagementProdImpl(MemberDao memDao) {
        this.memDao = memDao;
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
    public Member findMemberById(String id) throws MemberIdNotFoundException {
        return memDao.getById(id);
    }
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
    public void bookGymClass(String bookingGymClassName, String memberId){
        memDao.addGymClass(bookingGymClassName, memberId);
    }
    @Override
    public void cancelBooking(String bookingGymClassName, String memberId){
        memDao.deleteGymClass(bookingGymClassName, memberId);
    }
}
