public class ScotlandHistoric {
	private String REFERENCE;
	private String ADDRESS;
	private String CATEGORY;
	private String LISTED;
	private String COUNCIL;
	private String PARISH;

	public ScotlandHistoric(String r, String a, String c, String l, String cl, String p) {
		REFERENCE = r.trim();
		ADDRESS = a.trim();
		CATEGORY = c.trim();
		LISTED = l.trim();
		COUNCIL = cl.trim();
		PARISH = p.trim();
	}

	public String getReference() {
		return REFERENCE;
	}

	public String getAddress() {
		return ADDRESS;
	}

	public String getCategory() {
		return CATEGORY;
	}

	public String getListed() {
		return LISTED;
	}

	public String getCouncil() {
		return COUNCIL;
	}

	public String getParish() {
		return PARISH;
	}

}