package stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import utilities.UtilityClass;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class BookingStepDefs {

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

    @Given("the user submits the request with only mandatory details to book a hotel room")
    public void theUserSubmitsTheRequestWithOnlyMandatoryDetailsToBookAHotelRoom() throws IOException {
        request = given();
        request.header("Content-Type", "application/json");
        response = request.given()
                .body(utilityClass.readOnlyMandatoryJsonInputFile())
                .post(utilityClass.getValueOf("post.booking.url"))
                .then()
                .log().all()
                .extract().response();
    }

    @Given("the user submits all mandatory details including {int} digit phone number to book a hotel room")
    public void theUserSubmitsAllMandatoryDetailsIncludingDigitPhoneNumberToBookAHotelRoom(int arg0) throws IOException {
        request = given();
        request.header("Content-Type", "application/json");
        response = request.given()
                .body(utilityClass.readMaxDigitPhoneNumberJsonInputFile())
                .post(utilityClass.getValueOf("post.booking.url"))
                .then()
                .log().all()
                .extract().response();
    }

    @Given("the customer provides all necessary booking details")
    public void theCustomerProvidesAllNecessaryBookingDetails() throws IOException {
        RequestSpecification request = given();
        request.header("Content-Type", "application/json");
        response = (Response) request.given()
                .body(utilityClass.readValidJsonInputFile());

    }

    @When("the booking request is submitted")
    public void theBookingRequestIsSubmitted() {
        request.post(utilityClass.getValueOf("post.booking.url"))
                .then()
                .log().all()
                .extract().response();
    }

    @Then("the booking should be confirmed with a unique booking ID")
    public void theBookingShouldBeConfirmedWithAUniqueBookingID() {
        request.post(utilityClass.getValueOf("get.booking.url"))
                .then()
                .log().all()
                .extract().response();
    }

    @And("the customer should see their booking details reflected correctly")
    public void theCustomerShouldSeeTheirBookingDetailsReflectedCorrectly() {
        Assert.assertEquals(response.getStatusCode(), 201);
    }
}