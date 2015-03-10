package core.client;

public class ProductInfo {

	private String authors = "Mike Brisbin, Jeffrey Radaszkiewicz, Mathew Higgins, Steve Cantwell";
	private String copywrite = "NO COPYWRITE";
	private String description = " A description";
	private String title = "Chess Online!";
	private String releaseDate = "3/10/2014";
	private String version = "Version 1.0a";

	public ProductInfo(String fileName) {
		// open file and pass object to extract
		extractInfo();
	}

	private void extractInfo() {

	}

	public String getAuthors() {
		return authors;
	}

	public String getCopywrite() {
		return copywrite;
	}

	public String getDescription() {
		return description;
	}

	public String getTitle() {
		return title;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public String getVersion() {
		return version;
	}
}
