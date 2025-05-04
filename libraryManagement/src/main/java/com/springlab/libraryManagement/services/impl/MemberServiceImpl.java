package com.springlab.libraryManagement.services.impl;

import com.springlab.libraryManagement.entities.Member;
import com.springlab.libraryManagement.repositories.MemberRepository;
import com.springlab.libraryManagement.services.MemberService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class MemberServiceImpl implements MemberService {
    @Autowired
    private MemberRepository memberRepository;

    @Override
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    public Member getMemberById(int id) {
        return memberRepository.getReferenceById(id);
    }

    @Override
    public void addMember(Member member) {
        memberRepository.save(member);
    }

    @Override
    public void updateMember(int id, Member member) {
        memberRepository.update(id, member);
    }

    @Override
    public void deleteMember(int id) {
        memberRepository.deleteById(id);
    }
}
