package com.dailycoder.libraryManagement.services;

import com.dailycoder.libraryManagement.entities.Member;
import org.springframework.stereotype.Service;

import java.util.List;

public interface MemberService {
    // Add methods for member-related operations

     List<Member> getAllMembers();
     Member getMemberById(int id);
     void addMember(Member member);
     void updateMember(int id, Member member);
     void deleteMember(int id);
}
