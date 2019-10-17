package utils.base;

import java.util.List;

public class TestClass {
	
	////////////////////////////////////////////////////////////////////////////
	// Load locators of the specified page from the Json file whose location is specified in DataFolder     
	// @ Parameter: sPage is name of the Page that needs to load locators
	// @ Parameter: sFolder is location to get the Json file 
	///////////////////////////////////////////////////////////////////////////
	public boolean loadPageControl(String sPage)
	{
		return PageObjectHelper.loadPageControl(sPage);
	}

	////////////////////////////////////////////////////////////////////////////
	// get list of locator of the specified page from dictionary of loaded pages 
	// @ Parameter: sPage is name of that needs to load locators   
	///////////////////////////////////////////////////////////////////////////
	public List<Control> getPageControls(String sPage) {
		return PageObjectHelper.getPageControls(sPage);
	}
	
	////////////////////////////////////////////////////////////////////////////
	// Create a page object with specified class type   
	// @ Parameter: oType is type of created Page object    
	///////////////////////////////////////////////////////////////////////////
	public <T> T $Page(Class<T> oType)
	{
		return PageObjectHelper.$Page(oType);
	}
	
	public void loadPage() {
		PageObjectHelper.loadPage(this);
	}
	
}
