package fixtures;

import models.Constants;
import models.LoginResponse;
import models.User;
import org.apache.http.HttpStatus;

import static io.restassured.RestAssured.given;

public class ApiFixture {

    public static io.restassured.response.Response createUserHandler(User userData){
        return  given().
                header("Content-type", "application/json").
                body(userData).
                post(Constants.USER_CREATE_ROUTE);
    }

    public static String getAuthToken(User user){
         var loginResponse = given().
                header("Content-type", "application/json").
                body(user).
                post(Constants.USER_LOGIN_ROUTE).
                then().assertThat().
                extract().as(LoginResponse.class);
         return loginResponse.getAccessToken();
    }

    public static void deleteUser(User userData){
        String authToken = getAuthToken(userData);
        given().
                header("Content-type", "application/json").
                header("Authorization", authToken).
                delete(Constants.USER_ROUTE).then().statusCode(HttpStatus.SC_ACCEPTED);
    }
}
