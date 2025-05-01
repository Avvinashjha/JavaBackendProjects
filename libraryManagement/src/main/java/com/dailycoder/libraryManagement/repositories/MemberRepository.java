package com.dailycoder.libraryManagement.repositories;
import com.dailycoder.libraryManagement.entities.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface MemberRepository extends JpaRepository<Member, Integer> {
    @Query(value = "UPDATE Member m SET m.membershipType = ?1, m.membershipStartDate = ?2, m.membershipEndDate = ?3 WHERE m.memberId = ?4", nativeQuery = true)
    void update(int id, Member member);
}
