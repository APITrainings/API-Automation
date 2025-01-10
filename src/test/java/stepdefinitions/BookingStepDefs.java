package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import utilities.UtilityClass;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class BookingStepDefs {

    String strResponse;
    RequestSpecification request;
    Response response;
    UtilityClass utilityClass = new UtilityClass();

    @Given("the user submits all mandatory details to book a hotel room")
    public void theUserSubmitsAllMandatoryDetailsToBookAHotelRoom() throws IOException {
        RequestSpecification request = given();
        request.header("Content-Type", "application/json");
        response = request.given()
                .body(utilityClass.readValidJsonInputFile())
                .post(utilityClass.getValueOf("post.booking.url"))
                .then()
                .log().all()
                .extract().response();
    }

    @Then("the user should be returned with a booking confirmation")
    public void theUserShouldBeReturnedWithABookingConfirmation() {
        Assert.assertEquals(response.getStatusCode(), 201);
    }

    @Given("the user submits all mandatory details except email address to book a hotel room")
    public void theUserSubmitsAllMandatoryDetailsExceptEmailAddressToBookAHotelRoom() throws IOException {
        request = given();
        request.header("Content-Type", "application/json");
        response = request.given()
                .body(utilityClass.readInvalidJsonInputFile())
                .post(utilityClass.getValueOf("post.booking.url"))
                .then()
                .log().all()
                .extract().response();
    }

    @Then("the user should not be returned with a booking confirmation")
    public void theUserShouldNotBeReturnedWithABookingConfirmation() {
        Assert.assertNotEquals(response.getStatusCode(), 201);
    }

    @Given("the user submits all mandatory details with invalid phone number to book a hotel room")
    public void theUserSubmitsAllMandatoryDetailsWithInvalidPhoneNumberToBookAHotelRoom() throws IOException {
        request = given();
        request.header("Content-Type", "application/json");
        response = request.given()
                .body(utilityClass.readInvalidPhoneNumber())
                .post(utilityClass.getValueOf("post.booking.url"))
                .then()
                .log().all()
                .extract().response();
    }
}