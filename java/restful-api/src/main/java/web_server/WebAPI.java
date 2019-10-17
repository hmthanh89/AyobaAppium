package web_server;

import api_object.Authentication;
import protocol.RestAssured_Protocol;

public class WebAPI  extends RestAssured_Protocol
{
	public WebAPI() {}
	public WebAPI(String sBaseURI) { super(sBaseURI); }
	public WebAPI(String sBaseURI, Authentication oAuthen) { super(sBaseURI, oAuthen); }

}
