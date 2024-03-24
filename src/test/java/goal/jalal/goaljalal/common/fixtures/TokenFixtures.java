package goal.jalal.goaljalal.common.fixtures;

public class TokenFixtures {

    public static final String MALFORMED_JWT_TOKEN = "abcdef";

    /**
     * WRONG_ACCESS_TOKEN
     */
    public static final String MISSING_CLAIM_ACCESS_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE3MTEyODc2ODcsImV4cCI6NDg2NzA0NzY4N30.qwsUen_GhxsfV9etyH5vKlZlsg17LEra34K6Jm4qpac";
    public static final String EXPIRED_ACCESS_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJfaWQiOjEzNDE1MzQyMzQxMjMsImlhdCI6MTcxMTI4NjQxNCwiZXhwIjoxNzExMjg2NDE0fQ.671Xqc_eTwTsIx_EeoI2oY27E_ihP2636epk8MzxfFw";

    /**
     * CORRECT_ACCESS_TOKEN / 카카오ID - 1341534234123L / 만료 기한 - 2124년 03월 24일
     */
    public static final String CORRECT_REISSUE_JOHN_ACCESS_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJfaWQiOjEzNDE1MzQyMzQxMjMsImlhdCI6MTcxMTI4NjU4NCwiZXhwIjo0ODY3MDQ2NTg0fQ.xUwUn1Um-HO3yFHcBWeLKiAh-CpmCFMMQ4CBhwyGQEE";

    /**
     * WRONG_REFRESH_TOKEN
     */
    public static final String MISSING_CLAIM_REFRESH_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJpYXQiOjE3MTEyODc3MTYsImV4cCI6NDg2NzA0NzcxNn0.cgdsm0lpzvTu2xskZNq-86TY11vSRA-M7xUZaKpyOG0";
    public static final String EXPIRED_REFRESH_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJfaWQiOjEzNDE1MzQyMzQxMjMsImlhdCI6MTcxMTI4Njc4NywiZXhwIjoxNzExMjg2Nzg3fQ.VY8LdgL0POasV269dcTj0lzLMPnNcrL4p5RaTrarRo0";

    /**
     * CORRECT_REFRESH_TOKEN / 카카오ID - 1341534234123L / 만료 기한 - 2124년 03월 24일
     */
    public static final String CORRECT_JOHN_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6InRlc3RAdGVzdC5jb20iLCJpYXQiOjE2OTA0MzEzMDIsImV4cCI6NDAwNDAzNDgwMH0.iXJeMmgyfuF5aRrK1X4bKjsq5AvRK9d0UFtVJbI7NZQ";
    public static final String CORRECT_ORIGINAL_JOHN_REFRESH_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6InBpaWx5YW5nLmRldkBnbWFpbC5jb20iLCJpYXQiOjE2OTA0MzEzMDIsImV4cCI6NDAwNDAzNDgwMH0.pl5hFuqjcIe3U5SIpui9peVfvBc6ukMV73_aPshz7Fk";
    public static final String CORRECT_REISSUE_JOHN_REFRESH_TOKEN = "eyJhbGciOiJIUzI1NiJ9.eyJlbWFpbCI6InBpaWx5YW5nLmRldkBnbWFpbC5jb20iLCJpYXQiOjE2OTA0MzEzMDIsImV4cCI6NDAwNDEzNDgwMH0.tk5FTknmPOSrvqXJOqDZpacUMM_chAuCbjdgzsiPfuo";

}
