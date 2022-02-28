package com.ferdi.youcontribute.controller.requests;


import com.ferdi.youcontribute.models.IssueChallengeStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateChallengeStatusRequest {

    private IssueChallengeStatus status;
}
