package data.JIRA;

public class Enums {

	public enum IssueType {
		BUG("Bug"), 
		TASK("Task"),
		EPIC("Epic"),
		IMPROVEMENT("Improvement"),
		NEW_FEATURE("New Feature");

		private String value;
		private long id;
		
		private IssueType(String value) {
			this.value = value;
			this.id = -1L;
		}
		public void setId(long id) {
			this.id = id;
		}
		public String getValue() {
			return value;
		}
		public long getId() {
			return id;
		}
	}
	
	public enum Priority {
		LOWEST("Lowest"), 
		LOW("Low"),
		MEDIUM("Medium"),
		HIGH("High"),
		HIGHEST("Highest");

		private String value;
		private long id;
		
		private Priority(String value) {
			this.value = value;
			this.id = -1L;
		}
		public void setId(long id) {
			this.id = id;
		}
		public String getValue() {
			return value;
		}
		public long getId() {
			return id;
		}
	}

}
