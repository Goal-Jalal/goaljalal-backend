package goal.jalal.goaljalal.post.domain.vo;

import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
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
        this.city = city;
        this.county = county;
        this.district = district;
    }

    public String getFullAddress() {
        return String.format("%s %s %s", this.city, this.county, this.district).trim();
    }
}
