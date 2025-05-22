package se.yrgo.services;

import se.yrgo.domain.Member;
import se.yrgo.dataaccess.MemberDao;
import java.util.List;

public class MemberManagementProdImpl implements MemberManagementService{
    private final MemberDao memDao;
    public MemberManagementProdImpl(MemberDao dao) {
        this.memDao = dao;
    }

    public void newMember(Member newMember){
        memDao.create(newMember);
    }

    public void editMember(Member changedMember){
        memDao.update(changedMember);
    }

    public void deleteMember(Member deletedMember){
        memDao.delete(deletedMember);
    }

    public Member findMemberById(int id) throws Exception{
        return memDao.getById(id);
    }

    public List<Member> getAllMembers(){
        return memDao.getAll();
    }

    public List<Member> getMembersByName(String name){
        return memDao.getByName(name);
    }

    public Member getFullMemberDetail(String memberId){
        return memDao.getFullDetail(memberId);
    }
}
