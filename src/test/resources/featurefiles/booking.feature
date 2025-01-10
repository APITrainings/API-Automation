Feature: API Test Automation - User books a room
  The purpose of these tests are to cover isolated tests for customer to book a room/suite.

#  Booking website swagger URL : https://automationintesting.online/booking/swagger-ui/index.html#/

  Scenario: User is able to book a room.
    Given the user submits all mandatory details to book a hotel room
    Then the user should be returned with a booking confirmation