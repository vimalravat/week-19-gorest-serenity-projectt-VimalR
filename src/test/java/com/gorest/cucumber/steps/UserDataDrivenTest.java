package com.gorest.cucumber.steps;

import com.gorest.testbase.TestBase;
import com.gorest.userinfo.UserSteps;
import net.thucydides.core.annotations.Steps;
import net.thucydides.core.annotations.Title;
import org.junit.Test;

/**
 * Created by Jay
 */
/*@Concurrent(threads = "8x")
@UseTestDataFrom("src/test/java/resources/testdata/userinfo.csv")
@RunWith(SerenityParameterizedRunner.class)*/
public class UserDataDrivenTest extends TestBase {

    private String name;
    private String email;
    private String gender;
    private String status;

    @Steps
    UserSteps userSteps;

    @Title("Data driven test for adding multiple students to the application")
    @Test
    public void createMultipleStudents() {

        userSteps.createUser(name,email,gender,status ).statusCode(201);
    }

}


