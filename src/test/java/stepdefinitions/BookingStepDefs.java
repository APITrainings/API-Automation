package stepdefinitions;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.path.json.JsonPath;
import io.restassured.specification.RequestSpecification;
import org.testng.Assert;
import utilities.UtilityClass;

import java.io.IOException;

import static io.restassured.RestAssured.given;

public class BookingStepDefs {

    String strResponse;
    UtilityClass utilityClass = new UtilityClass();

    @Given("the user submits all mandatory details to book a hotel room")
    public void theUserSubmitsAllMandatoryDetailsToBookAHotelRoom() throws IOException {
        RequestSpecification request = given();
        request.header("Content-Type", "application/json");
        strResponse = request.given()
                .body(utilityClass.readValidJsonInputFile())
                .post(utilityClass.getValueOf("post.booking.url"))
                .then()
                .log().all()
                .extract().response().body().asString();
    }

    @Then("the user should be returned with a booking confirmation")
    public void theUserShouldBeReturnedWithABookingConfirmation() {
        try {
            JsonPath jsonPath = new JsonPath(strResponse);
            Assert.assertNotEquals(jsonPath.getString("bookingid"), null);
        } catch (Exception e) {
            throw new RuntimeException("Error while parsing the JSON response. Booking ID is not created");
        }
    }
}