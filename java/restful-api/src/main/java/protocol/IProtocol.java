package protocol;

import api_object.RequestMessage;
import api_object.ResponseMessage;

public interface IProtocol {
	
	  ResponseMessage GET(RequestMessage paramRequestMessage) throws Exception;
	  
	  ResponseMessage POST(RequestMessage paramRequestMessage) throws Exception;
	  
	  ResponseMessage PUT(RequestMessage paramRequestMessage) throws Exception;
	  
	  ResponseMessage DELETE(RequestMessage paramRequestMessage) throws Exception;
	  
	  ResponseMessage HEAD(RequestMessage paramRequestMessage) throws Exception;

}
