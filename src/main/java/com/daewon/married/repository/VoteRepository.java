package com.daewon.married.repository;

import com.daewon.married.entity.Vote;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class VoteRepository {

    private final EntityManager em;

    /**
     * 이상형 투표.
     * @param vote
     */
    public void register(Vote vote) {
        if (vote.getChooseYn() != null){
            em.persist(vote);
        }else{
            em.merge(vote);
        }
    }

    /**
     * 매칭 결과 조회
     * @param empId
     * @return
     */
    public List<String> selectMatchingResultByEmpId(String empId) {
        return em.createQuery("select v.targetEmpId from Vote v where v.empId =:empId and v.chooseYn =:chooseYn")
                .setParameter("empId", empId)
                .setParameter("chooseYn", "Y")
                .getResultList();
    }

    /**
     * 매칭 검사
     * @param empId
     * @param targetEmpId
     * @return
     */
    public String isOk(String empId, String targetEmpId){
        String result = em.createQuery("select v.chooseYn from Vote v where v.empId=:empId and v.targetEmpId=:targetEmpId") // 근데 ...SELECT emp_id FROM vote WHERE target_emp_id="MARRIED_79" AND choose_yn="Y"; 이렇게하면ㄷ ㅚㅈ ㅣ않냐 .. 나 바본가
                .setParameter("empId", empId)                                                                                   // select v.empId from Vote v where  아ㅣ니구나 ㅋㅋㅋㅋㅋㅋㅋ 사용자기준으로 한느거니까 ㅇㅇ
                .setParameter("targetEmpId", targetEmpId)
                .getSingleResult().toString();
        return result;
    }
}
