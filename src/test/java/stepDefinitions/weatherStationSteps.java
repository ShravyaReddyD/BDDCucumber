package stepDefinitions;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.hasItem;

import org.hamcrest.Matchers;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class weatherStationSteps {

	private Response response;
	private int expectedStatusCode;

	@Given("weather station API endpoint")
	public void weather_station_api_endpoint() {
		RestAssured.baseURI = "http://api.openweathermap.org";
	}

	@When("user calls registerStationAPI with post http request")
	public void user_calls_register_station_api_with_post_http_request() {

		response = given().header("Content-Type", "application/json")
				.body("{\r\n" + "  \"external_id\": \"SF_TEST001\",\r\n"
						+ "  \"name\": \"San Francisco Test Station\",\r\n" + "  \"latitude\": 37.76,\r\n"
						+ "  \"longitude\": -122.43,\r\n" + "  \"altitude\": 150\r\n" + "}")
				.when().post("data/3.0/stations");
	}

	@Then("the API call should give status code {int}")
	public void the_api_call_should_give_status_code(int expectedStatusCode) {

		this.expectedStatusCode = expectedStatusCode;

		response.then().assertThat().statusCode(expectedStatusCode);
	}

	@And("the test fail to match the expected message")
	public void the_test_fail_to_match_the_expected_message() {

		String expectedMessage = "{\"code\": 401,\"message\": \"Invalid API key. Please see http://openweathermap.org/faq#error401 for more info.\"}";
		response.then().body(Matchers.equalTo(expectedMessage));
	}

	@And("the response message body should match the expected message")
	public void the_response_message_body_should_match_the_expected_message() {

		String expectedMessage = "{\"cod\":401, \"message\": \"Invalid API key. Please see https://openweathermap.org/faq#error401 for more info.\"}";
		response.then().body(Matchers.equalTo(expectedMessage));
	}

	@When("^a request is made to register a weather station with details (.*),(.*),(.*),(.*) and (.*)$")
	public void a_request_is_made_to_register_a_weather_station_with_details(String externalId, String name,
			double latitude, double longitude, int altitude) {

		response = given().queryParam("appid", "f5aafc6cd6d6d4fd8efefc41d15c71e7")
				.header("Content-Type", "application/json")
				.body(createRequestBody(externalId, name, latitude, longitude, altitude)).when()
				.post("data/3.0/stations");

	}

	private String createRequestBody(String externalId, String name, double latitude, double longitude, int altitude) {
		return String.format(
				"{ \"external_id\": \"%s\", \"name\": \"%s\", \"latitude\": %s, \"longitude\": %s, \"altitude\": %s }",
				externalId, name, latitude, longitude, altitude);
	}

	@When("a rquest is made to get all the registered stations")
	public void a_rquest_is_made_to_get_all_the_registered_stations() {
		response = given().queryParam("appid", "f5aafc6cd6d6d4fd8efefc41d15c71e7")
				.header("Content-Type", "application/json").when().get("data/3.0/stations");

	}

	@And("^the response should get the same details sent in registration request (.*) and (.*)$")
	public void the_response_should_get_the_same_details_sent_in_registration_request(String externalId, String name) {

		response.then().body("external_id", hasItem(externalId));
		response.then().body("name", hasItem(name));

	}

}
