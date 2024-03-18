package goal.jalal.goaljalal.club.domain.vo;

import goal.jalal.goaljalal.club.exception.ClubMatchHistoryException;
import java.util.Arrays;
import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MatchForm {
    THREE_VS_THREE("3 vs 3"),
    FOUR_VS_FOUR("4 vs 4"),
    FIVE_VS_FIVE("5 vs 5"),
    SIX_VS_SIX("6 vs 6");

    private final String matchFormat;

    public static MatchForm findMatchForm(final String matchFormat) {
        return Arrays.stream(values())
            .filter(matchForm -> matchForm.matchFormat.equals(matchFormat))
            .findAny()
            .orElseThrow(
                () -> new ClubMatchHistoryException.ClubMatchFormNotExistException(matchFormat));
    }
}
