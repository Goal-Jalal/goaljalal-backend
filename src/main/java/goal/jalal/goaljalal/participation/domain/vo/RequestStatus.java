package goal.jalal.goaljalal.participation.domain.vo;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum RequestStatus {
    PENDING, // 대기 중
    APPROVED, // 승인됨
    REJECTED // 거절됨
}
