package api.Pinterest.resources;

import api_object.RequestMessage;
import api_object.ResponseMessage;

public class PinApi extends BaseResource {

	public PinApi() {
		super();
	}

	public ResponseMessage createPin(String body) {
		try {
			RequestMessage request = new RequestMessage();
			request.setResources("/v1/pins/");
			request.setBody(body);

			return webService.POST(request);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return null;
	}

}
