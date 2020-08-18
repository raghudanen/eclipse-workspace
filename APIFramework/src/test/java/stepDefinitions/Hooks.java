package stepDefinitions;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
	@Before ("@Dep")
	public void beforeScenarion() throws IOException {
		System.out.println("Inside Hooks the value of place_Id is "+StepDefinition. place_Id);
		//write a code to give place Id
		// execute this delete only when we have place id is null
		StepDefinition m = new StepDefinition();

		if (StepDefinition. place_Id == null){

			m.add_place_payload_with("Shetty", "French", "Asia");
			m.user_calls_with_http_request("addPlaceAPI", "POST");
			m.verify_place_id_created_maps_to_using("Shetty", "getPlaceAPI");
		}
	}
}
