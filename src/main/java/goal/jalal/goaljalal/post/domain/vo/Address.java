package goal.jalal.goaljalal.post.domain.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import java.util.Objects;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import lombok.AccessLevel;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@EqualsAndHashCode
public class Address {

    @Column(name = "city")
    private String city;

    @Column(name = "county")
    private String county;

    @Column(name = "district")
    private String district;

    public Address(
        final String city,
        final String county,
        final String district
    ) {
        this.city = validate(city);
        this.county = validate(county);
        this.district = validate(district);
    }

    private String validate(final String value) {
        if (Objects.isNull(value) || value.isBlank()) {
            return null;
        }
        return value.trim();
    }

    public String getFullAddress() {
        return Stream.of(this.city, this.county, this.district)
            .filter(Objects::nonNull)
            .collect(Collectors.joining(" ")).trim();
    }
}
