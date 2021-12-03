package com.daewon.married.service;

import com.daewon.married.dto.VoteDTO;
import com.daewon.married.entity.Vote;

import java.util.List;

public interface VoteService {

    void voteRecord(VoteDTO voteDTO);

    String match(String empId);

    String[] selectMatchResult(String matchYnList);

    List<Vote> selectVoteResult(String empId);
}
