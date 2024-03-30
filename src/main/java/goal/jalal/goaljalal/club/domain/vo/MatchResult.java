package goal.jalal.goaljalal.club.domain.vo;

import goal.jalal.goaljalal.club.exception.clubmatchhistory.ClubMatchHistoryException;
import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MatchResult {
    WIN("WIN"),
    TIE("TIE"),
    LOSE("LOSE");

    private final String matchResultNumber;

    public static MatchResult findMatchResult(final String matchResultNumber) {
        return Arrays.stream(values())
            .filter(matchResult -> matchResult.matchResultNumber.equals(matchResultNumber))
            .findAny()
            .orElseThrow(
                () -> new ClubMatchHistoryException.ClubMatchResultNotExistException(
                    matchResultNumber));
    }
}
