package pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage;

public class ClientDataBuilder {
	private Id id = Id.generate();
	private String name="";
	
	public ClientDataBuilder withId(Id id){
		this.id = id;
		return this;
	}
	
	public ClientDataBuilder withName(String name){
		this.name = name;
		return this;
	}
	
	public ClientData build(){
		return new ClientData(this.id, name);
		
	}
}
