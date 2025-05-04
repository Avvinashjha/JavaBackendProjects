package com.springlab.libraryManagement.services;

import com.springlab.libraryManagement.entities.Member;

import java.util.List;

public interface MemberService {
    // Add methods for member-related operations

     List<Member> getAllMembers();
     Member getMemberById(int id);
     void addMember(Member member);
     void updateMember(int id, Member member);
     void deleteMember(int id);
}
