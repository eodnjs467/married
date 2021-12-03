package com.daewon.married.service;

import com.daewon.married.dto.VoteDTO;
import com.daewon.married.entity.Vote;
import com.daewon.married.repository.MemberRepository;
import com.daewon.married.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class voteServiceImpl implements VoteService {

    private final VoteRepository voteRepository;

    private final MemberRepository memberRepository;

    @Transactional
    public void voteRecord(VoteDTO voteDTO) {
        Vote voteResult = Vote.builder()
                .vno(voteDTO.getVno())
                .empId(voteDTO.getEmpId())
                .chooseYn(voteDTO.getChooseYn())
                .build();
        voteRepository.register(voteResult);
        log.info(voteResult.getEmpId() + "투표 완료");
    }

    @Override
    public List<Vote> selectVoteResult(String empId) {
        List<Vote> resultList = voteRepository.selectVoteResult(empId);
        return resultList;
    }

    public String[] selectMatchResult(String matchYnList){

        String[] arr = matchYnList.split(",");

        return arr;
    }

    public String match(String empId){
        String matchYnList = null;
        List<Vote> result = selectVoteResult(empId); //사용자

        for (Vote v : result){
            if (voteRepository.selectByVoteEmpId(empId, v.getTargetEmpId()) == empId){
                System.out.println("매칭 성공");
                matchYnList = v.getTargetEmpId() + ", ";
            }else{
                System.out.println("다음 기회에...");
            }
        }
        return matchYnList;
    }
}
