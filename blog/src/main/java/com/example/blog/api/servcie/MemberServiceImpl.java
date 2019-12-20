package com.example.blog.api.servcie;

import java.util.List;
import java.util.stream.Collectors;

import com.example.blog.api.dto.MemberDto;
import com.example.blog.api.entity.Member;
import com.example.blog.api.repository.MemberRepository;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class MemberServiceImpl implements MemberService {

    private MemberRepository memberRepository;

    @Transactional
    @Override
    public String insert(MemberDto dto) {
        return memberRepository.save(dto.toEntity()).getId();
    }

    @Transactional
    @Override
    public Member searchById(String id){
         return memberRepository.findById(id).get();
    }

    @Transactional
    @Override
    public List<MemberDto> getAll() {
        return memberRepository.findAll().stream()
                                  .map(MemberDto::new)
                                  .collect(Collectors.toList());
    }

    @Transactional
    @Override
    public void update(MemberDto dto) {

    }

    @Transactional
    @Override
    public void delete(String id) {

    }
    
}