package com.daewon.married.repository;

import com.daewon.married.dto.VoteDTO;
import com.daewon.married.entity.Vote;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class VoteRepository {

    private final EntityManager em;

    public void register(Vote vote) {
        if (vote.getChooseYn() != null){
            em.persist(vote);
        }else{
            em.merge(vote);
        }
    }

    public String selectByVoteEmpId(String empId, String targetEmpId){
        return em.createQuery("select v.targetEmpId from Vote v where v.empId =:targetEmpId and v.chooseYn =:chooseYn and v.targetEmpId =:empId").toString();
    }

    public List<Vote> selectVoteResult(String empId){
        return em.createQuery("select v from Vote v where v.empId =:empId and v.chooseYn =:chooseYn", Vote.class)
                .setParameter("chooseYn", "Y").getResultList();
    }
}
