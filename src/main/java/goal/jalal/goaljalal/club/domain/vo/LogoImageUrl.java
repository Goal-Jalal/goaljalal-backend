package goal.jalal.goaljalal.club.domain.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.Objects;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public class LogoImageUrl {

    @Column(name = "logoImageUrl", nullable = false)
    private String value;

    private LogoImageUrl(final String value) {
        validateNull(value);
        this.value = value;
    }

    private void validateNull(final String value) {
        if (Objects.isNull(value)) {
            throw new NullPointerException("로고 이미지 Url은 null일 수 없습니다.");
        }
    }
}
