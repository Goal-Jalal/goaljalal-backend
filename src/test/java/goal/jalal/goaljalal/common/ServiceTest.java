package goal.jalal.goaljalal.common;

import goal.jalal.goaljalal.common.builder.TestFixtureBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
@Transactional
//@Sql({"/h2-truncate.sql"})
public abstract class ServiceTest {

    @Autowired
    protected TestFixtureBuilder testFixtureBuilder;

}