package api.Pinterest.functions;

import java.util.HashMap;

import api.Pinterest.resources.PinApi;
import api_object.ResponseMessage;
import data.Pinterest.Board;
import data.Pinterest.Pin;
import io.restassured.response.Response;
import utils.common.Constants;

public class PinFunc {

	private PinApi api;

	public PinFunc() {
		api = new PinApi();
	}

	public HashMap<String, String> createPin(Board board, Pin pin) {
		HashMap<String, String> responseInfo = new HashMap<String, String>();
		String sboard = Constants.USERNAME + "/" + board.getBoardName().toLowerCase().replaceAll(" ", "-");
		String body = "{\"board\": \"%s\",\"note\": \"%s\",\"image_url\":\"%s\"}";
		body = String.format(body, sboard, pin.getNote(), pin.getImageUrl());

		ResponseMessage response = api.createPin(body);
		if (response==null) {
			response = api.createPin(body);
		}

		responseInfo.put("message", response.getOriginalContent(Response.class).getBody().jsonPath().get("message"));
		responseInfo.put("id", response.getOriginalContent(Response.class).getBody().jsonPath().get("id"));
		responseInfo.put("statusCode", "" + response.getStatusCode());
		return responseInfo;
	}

	public String createPin(Pin pin) {
		String body = "{\"note\": \"%s\",\"image_url\":\"%s\"}";
		body = String.format(body, pin.getNote(), pin.getImageUrl());

		ResponseMessage response = api.createPin(body);
		if (response==null) {
			response = api.createPin(body);
		}

		return response.getOriginalContent(Response.class).getBody().jsonPath().get("message");
	}

}
