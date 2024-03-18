package goal.jalal.goaljalal.club.domain.vo;

import goal.jalal.goaljalal.club.exception.ClubMatchHistoryException;
import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MatchResult {
    WIN(0),
    TIE(1),
    LOSE(2);

    private final int matchResultNumber;

    public static MatchResult findMatchResult(final int matchResultNumber) {
        return Arrays.stream(values())
            .filter(matchResult -> matchResult.matchResultNumber == matchResultNumber)
            .findAny()
            .orElseThrow(
                () -> new ClubMatchHistoryException.ClubMatchResultNotExistException(
                    matchResultNumber));
    }
}
