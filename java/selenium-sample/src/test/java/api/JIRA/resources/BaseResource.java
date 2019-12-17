package api.JIRA.resources;

import utils.helper.PropertiesHelper;
import web.server.WebAPI;


public class BaseResource {

	public WebAPI oWebService = null;
	private String _username = "";
	private String _password = "";
	private String _baseURI = "";

	public BaseResource() {
		if (loadInfo()) {
			oWebService = new WebAPI(_baseURI);
			oWebService.Author().BasicAuthor(_username, _password);
		} else
			oWebService = new WebAPI();
	}

	public BaseResource(String sBaseURI) {
		_baseURI = sBaseURI;
		oWebService = new WebAPI(sBaseURI);
	}

	public BaseResource(String sBaseURI, String sUser, String sPW) {
		_username = sUser;
		_password = sPW;
		_baseURI = sBaseURI;

		oWebService = new WebAPI(_baseURI);
		oWebService.Author().BasicAuthor(_username, _password);
	}

	public boolean loadInfo() {
		try {
			_username = PropertiesHelper.getPropValue("profile.user.name");
			_password = PropertiesHelper.getPropValue("profile.user.password");
			_baseURI = PropertiesHelper.getPropValue("profile.api.host");
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
