package api.Pinterest.functions;

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

	public String createPin(Board board, Pin pin) {
		String sboard = Constants.USERNAME + "/" + board.getBoardName();
		String body = "{\"board\": \"%s\",\"note\": \"%s\",\"image_url\":\"%s\"}";
		body = String.format(body, sboard, pin.getNote(), pin.getImageUrl());

		ResponseMessage response = api.createPin(body);

		return response.getOriginalContent(Response.class).getBody().jsonPath().get("message");
	}

	public String createPin(Pin pin) {
		String body = "{\"note\": \"%s\",\"image_url\":\"%s\"}";
		body = String.format(body, pin.getNote(), pin.getImageUrl());

		ResponseMessage response = api.createPin(body);

		return response.getOriginalContent(Response.class).getBody().jsonPath().get("message");
	}

}
