Feature: Hotel Room Booking
  The purpose of these tests are to cover isolated tests for customer to book a room/suite.

#  Booking website swagger URL : https://automationintesting.online/booking/swagger-ui/index.html#/

  Scenario: Successfully book a hotel room.
    Given the user submits all mandatory details to book a hotel room
    Then the user should be returned with a booking confirmation

  Scenario: User is unable to book a hotel room.
    Given the user submits all mandatory details except email address to book a hotel room
    Then the user should not be returned with a booking confirmation

  Scenario: User is unable to book a hotel room.
    Given the user submits all mandatory details with invalid phone number to book a hotel room
    Then the user should not be returned with a booking confirmation

  Scenario: User successfully books a hotel room with only mandatory values.
    Given the user submits the request with only mandatory details to book a hotel room
    Then the user should be returned with a booking confirmation