package com.daewon.married.service;

import com.daewon.married.dto.VoteDTO;
import com.daewon.married.entity.Vote;

import java.util.List;

public interface VoteService {

    void voteRecord(VoteDTO voteDTO);

    List<String> selectMatchingResultByEmpId(String empId);

}
