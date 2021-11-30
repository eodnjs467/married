package com.daewon.married.dto;

import lombok.*;

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class VoteDTO {

    private Long vno;

    private String empId;

    private String targetEmpId;

    private String chooseYn;

}
