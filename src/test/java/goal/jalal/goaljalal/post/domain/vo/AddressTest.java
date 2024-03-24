package goal.jalal.goaljalal.post.domain.vo;


import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class AddressTest {

    @Test
    @DisplayName("주소가 올바르게 생성되는지 검증합니다.")
    void address_Creation_With_Valid_Values() {
        //given
        final String city = "Seoul";
        final String county = "Gangnam-gu";
        final String district = "Samseong-dong";
        final String expectedValue = "Seoul Gangnam-gu Samseong-dong";

        //when
        Address address = new Address(city, county, district);

        //then
        assertThat(city).isEqualTo(address.getCity());
        assertThat(county).isEqualTo(address.getCounty());
        assertThat(district).isEqualTo(address.getDistrict());
        assertThat(address.getFullAddress()).isEqualTo(expectedValue);
    }

    @Test
    @DisplayName("주소(시, 구, 동)가 null 이면 null을 반환해야 합니다.")
    void address_Creation_With_Null_Values() {
        //given
        final String cityNull = null;
        final String countyNull = null;
        final String district = null;

        //when
        Address address = new Address(cityNull, countyNull, district);

        //then
        assertThat(address.getCity()).isNull();
        assertThat(address.getCounty()).isNull();
        assertThat(address.getDistrict()).isNull();
        assertThat(address.getFullAddress()).isEqualTo("");
    }

    @ParameterizedTest
    @ValueSource(strings = {" Seoul", "Seoul ", "   Seoul   ", "Seoul\t"})
    @DisplayName("주소에 공백이 들어가면 공백이 제거되어야 합니다.")
    void address_Creation_With_Blank_Values(final String value) {
        //given
        final String expectedValue = "Seoul";
        final String expectedFullAddress = "Seoul Seoul Seoul";

        //when
        Address address = new Address(value, value, value);

        //then
        assertThat(address.getCity()).isEqualTo(expectedValue);
        assertThat(address.getCounty()).isEqualTo(expectedValue);
        assertThat(address.getDistrict()).isEqualTo(expectedValue);
        assertThat(address.getFullAddress()).isEqualTo(expectedFullAddress);
    }

}