package com.daewon.married.service;

import com.daewon.married.dto.VoteDTO;
import com.daewon.married.entity.Vote;
import com.daewon.married.repository.MemberRepository;
import com.daewon.married.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Log4j2
public class voteServiceImpl implements VoteService {

    private final VoteRepository voteRepository;

    private final MemberRepository memberRepository;

    /*
    투표 기록.
     */
    @Transactional
    public void voteRecord(VoteDTO voteDTO) {
        Vote voteResult = Vote.builder()
                .vno(voteDTO.getVno())
                .empId(voteDTO.getEmpId())
                .targetEmpId(voteDTO.getTargetEmpId())  //수정 서비스에 맞게
                .chooseYn(voteDTO.getChooseYn())
                .build();
        voteRepository.register(voteResult);
        log.info(voteResult.getEmpId() + "투표 완료");
    }

    /*
    매칭 가능 상대 결과.
     */
    public List<String> selectMatchingResultByEmpId(String empId) {
        List<String> userChoose = voteRepository.selectMatchingResultByEmpId(empId);
        List<String> matchingResult = new ArrayList<>();

        for (String target : userChoose){
            if (voteRepository.isOk(target, empId).equals("Y")){
                matchingResult.add(target);
            }
        }
        return matchingResult;
    }


}
