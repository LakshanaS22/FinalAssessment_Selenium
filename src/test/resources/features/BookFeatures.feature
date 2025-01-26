Feature: Book a round-trip flight on Goibibo

  @Test @FlightBooking @RoundTrip
  Scenario: Book a round-trip flight from Coimbatore to Chennai
    Given the Goibibo website is opened
    When the user selects the From location as "Coimbatore" and To location as "Chennai"
    And the user selects the departure and return date
    And the user sets the number of travelers to adult and child
    And the user filters the departure and return timing
    And the user ensures the flight price is at least "1000" less than the maximum price
    And the user selects the second flight option from the filtered list
    And the user clicks "Book" to select Flight

  @Test @FareSelection
  Scenario: Select fare and verify saver option for departure
    When the user selects the Depature tab to select the fare
    Then verify that the saver option is chosen for Departure

    And the user selects the Return tab to select the fare
    Then verify that the saver option is chosen for Return

  @Test @FlightDetails
  Scenario: Print flight and baggage details
    And the system prints the flight details for Departure
    And the system prints the flight details for Return
    And the system retrieves and asserts baggage details for both flights

  @Test @TripSecure
  Scenario: Secure My Trip selection and enter details
    When the user selects Secure My Trip under trip secure
    And the user enters the billing address details:
      | Field   | Value        |
      | Address | "123"        |
      | Pincode | "641036"     |
      | State   | "Tamil Nadu" |

  @Test @PassengerDetails
  Scenario: Fill in adult and child passenger details
    When the user fills in the adult's details
      | Field     | Value |
      | Gender    | Male  |
      | FirstName | Arjun |
      | LastName  | Reddy |
    And the user fills in the child's details
      | Field     | Value   |
      | Gender    | Female  |
      | FirstName | Aswathy |
      | LastName  | Reddy   |

    And the user fills in contact details
      | Field        | Value                |
      | Mobile code  | +91                  |
      | Email        | arjunreddy@gmail.com |
      | MobileNumber | 9876543210           |

    Then validate the details

  @Test @SeatSelection
  Scenario: Proceed to seat selection
    And the user clicks proceed to Seat Selection

  @Test @FinalAssertion
  Scenario: Assert details are correctly displayed
    Then finally assert the details are correctly displayed
