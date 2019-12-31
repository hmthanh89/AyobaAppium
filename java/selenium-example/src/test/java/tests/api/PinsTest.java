package tests.api;

import java.util.HashMap;

import org.testng.Assert;
import org.testng.annotations.Test;

import api.Pinterest.functions.BoardFunc;
import api.Pinterest.functions.PinFunc;
import data.Pinterest.Board;
import data.Pinterest.Pin;
import utils.helper.Logger;

public class PinsTest {

	PinFunc apiPin = new PinFunc();
	BoardFunc apiBoard = new BoardFunc();
	
	@Test(description = "Create pin without board. Verify the error message in response")
	public void TC01_Create_Pin_Without_Board() {
		Pin pin = new Pin();
		
		Logger.info("Create pin without board");
		String message = apiPin.createPin(pin);

		Logger.verify("Verify the error message in response");
		Assert.assertEquals(message, "Parameter 'board' is required.");
	}
	
	@Test(description = "Create pin with deleted board. Verify the error message in response")
	public void TC02_Create_Pin_With_Deleted_Board() {
		Board board = new Board();
		Pin pin = new Pin();
		HashMap<String, String> responseInfo = new HashMap<String, String>();
		
		Logger.info("Create board");
		apiBoard.createBoard(board);
		
		Logger.info("Delete created board");
		apiBoard.deleteBoard(board.getBoardName());
		
		Logger.info("Create pin with deleted board name");
		responseInfo = apiPin.createPin(board, pin);
		
		Logger.verify("Verify the error message in response");
		Assert.assertEquals(responseInfo.get("message"), "Board not found.");
	}
	
	@Test(description = "Create pin without optional parameter (link or image or image_url). Verify the response is success")
	public void TC03_Create_Pin_Without_Optional_Parameter() {
		Board board = new Board();
		Pin pin = new Pin();
		HashMap<String, String> responseInfo = new HashMap<String, String>();
		
		Logger.info("Create board");
		apiBoard.createBoard(board);
		
		Logger.info("Create pin without optional parameter");
		responseInfo = apiPin.createPin(board, pin);
		
		Logger.verify("Verify the response is success");
		Assert.assertEquals(responseInfo.get("statusCode"), "201");
	}
	
}
