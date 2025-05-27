package se.yrgo.dataaccess;


import se.yrgo.dataaccess.exceptions.MemberMissingException;
import se.yrgo.domain.GymClass;
import se.yrgo.domain.Member;

import java.util.List;

/**
 * @author Alrik-Mark Brillantes
 */
public interface MemberDao {
    void create(Member member);

    void update(Member updateMember);

    void delete(Member deleteMember);

    Member getById(String id) throws MemberMissingException;

    List<Member> getAll();

    List<Member> getByName(String name);

    Member getFullDetail(String memberId);

    void addGymClass(String gymClass, String memberId);

    void deleteGymClass(String oldGymClass, String memberId);

    List<GymClass> addedClasses(String memberId);
}
