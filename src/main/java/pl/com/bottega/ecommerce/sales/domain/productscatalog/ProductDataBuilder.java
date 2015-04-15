package pl.com.bottega.ecommerce.sales.domain.productscatalog;

import java.util.Date;

import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.Id;
import pl.com.bottega.ecommerce.sharedkernel.Money;

public class ProductDataBuilder {
	private Id productId = Id.generate();
	private Money price = new Money(0);	
	private String name = "";	
	private Date snapshotDate = new Date();		
	private ProductType type = ProductType.STANDARD;
	
	public void setProductId(Id productId) {
		this.productId = productId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setSnapshotDate(Date snapshotDate) {
		this.snapshotDate = snapshotDate;
	}
	public void setType(ProductType type) {
		this.type = type;
	}
	
	public ProductData build(){
		return new ProductData(productId, price, name, type, snapshotDate);
	}
}
