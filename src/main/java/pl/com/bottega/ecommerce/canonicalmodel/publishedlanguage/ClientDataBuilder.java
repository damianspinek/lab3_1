package pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage;

public class ClientDataBuilder {
	private Id id = Id.generate();
	private String name="";
	
	public void setId(Id id){
		this.id = id;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public ClientData build(){
		return new ClientData(this.id, name);
	}
}
