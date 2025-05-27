package se.yrgo.integrationtests;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import se.yrgo.domain.Member;
import se.yrgo.services.MemberManagementService;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @author Alrik
 */
@Transactional
@ExtendWith(SpringExtension.class)
@ContextConfiguration( {"/other-tiers-test.xml", "/datasource-test.xml" } )
public class MemberServiceTest{

    @Autowired
    private MemberManagementService mms;
    private final Member mb;
    public MemberServiceTest(){
        this.mb = new Member("S001", "Janne Björnsson", "0758293153");
    }

    @Test
    public void testNewMember(){
        mms.newMember(mb);
        assertEquals("S001", mms.findMemberById("S001").getMemberId());
    }
    @Test
    public void testUpdateMember(){
        mms.newMember(mb);
        mb.setName("Doris Zelmerlöv");
        mms.editMember(mb);
        assertEquals("Doris Zelmerlöv", mms.findMemberById(mb.getMemberId()).getName());
    }
    @Test
    public void testDeleteMember(){
        mms.newMember(mb);
        assertEquals(1, mms.getAllMembers().size());

        mms.deleteMember(mb);
        assertEquals(0, mms.getAllMembers().size());
    }
    @Test
    public void testFindMemberById(){
        mms.newMember(mb);
        assertEquals("S001", mms.findMemberById("S001").getMemberId());
    }
    @Test
    public void testGetAllMembers(){
        mms.newMember(mb);
        assertEquals(1, mms.getAllMembers().size());
    }

    @Test
    public void testGetMembersByName(){
        mms.newMember(mb);
        List<Member> members = new ArrayList<>();
        members.add(mb);
        assertEquals(members, mms.getMembersByName(mb.getName()));
    }
    @Test
    public void testGetFullMemberDetails(){
        mms.newMember(mb);
        assertEquals(mb, mms.getFullMemberDetail(mb.getMemberId()));
    }
}

