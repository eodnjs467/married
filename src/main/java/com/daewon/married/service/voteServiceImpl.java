package com.daewon.married.service;

import com.daewon.married.dto.VoteDTO;
import com.daewon.married.entity.Vote;
import com.daewon.married.repository.VoteRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Log4j2
public class voteServiceImpl implements VoteService {

    private final VoteRepository voteRepository;

    @Transactional
    public void voteRecord(VoteDTO voteDTO) {
        Vote voteResult = Vote.builder()
                .vno(voteDTO.getVno())
                .empId(voteDTO.getEmpId())
                .chooseYn(voteDTO.getChooseYn())
                .build();
        voteRepository.save(voteResult);
        log.info(voteResult.getEmpId() + "투표 완료");
    }
}
