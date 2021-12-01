package com.daewon.married.service;

import com.daewon.married.dto.VoteDTO;

import java.util.List;

public interface VoteService {

    void voteRecord(VoteDTO voteDTO);

    List<Object> selectVoteResult(String empId);
}
