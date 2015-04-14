package pl.com.bottega.ecommerce.sales.domain.invoicing;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import java.util.Date;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import org.junit.Test;
import org.mockito.Mockito;

import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.ClientData;
import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.Id;
import pl.com.bottega.ecommerce.sales.domain.productscatalog.ProductData;
import pl.com.bottega.ecommerce.sales.domain.productscatalog.ProductType;
import pl.com.bottega.ecommerce.sharedkernel.Money;

public class BookKeeperTest {

	@Test
	public void bookKeeper_issuanceTest_giveOnePosition_ExpectedOnePosition() {
		ClientData client = new ClientData(Id.generate(), "Spinek");
		BookKeeper bookKeeper = new BookKeeper( new InvoiceFactory());
		Money money = new Money(1);
		ProductData productData =  new ProductData(Id.generate(), money, "Chleb", ProductType.FOOD, new Date());
		RequestItem requestItem = new RequestItem(productData, 1, money);
		
		InvoiceFactory invoiceFactory = mock(InvoiceFactory.class);
		when(invoiceFactory.create((ClientData)Mockito.any())).thenReturn(new Invoice(Id.generate(), client));
		TaxPolicy taxPolicy = mock(TaxPolicy.class);
		when(taxPolicy.calculateTax((ProductType)Mockito.any(), (Money)Mockito.any())).thenReturn(new Tax(money, ""));
		
		InvoiceRequest invoiceRequest = new InvoiceRequest(client);
		invoiceRequest.add(requestItem);
		
		Invoice invoice = bookKeeper.issuance(invoiceRequest, taxPolicy);
		
		assertThat(invoice.getItems().size(), is(1));		
	}
	
	@Test
	public void bookKeeper_issuanceTest_giveTwoPosition_shouldCallCalculateTacTwice(){
		ClientData client = new ClientData(Id.generate(), "Spinek");
		BookKeeper bookKeeper = new BookKeeper( new InvoiceFactory());
		Money money = new Money(1);
		ProductData productData =  new ProductData(Id.generate(), money, "Chleb", ProductType.FOOD, new Date());
		RequestItem requestItem = new RequestItem(productData, 1, money);
		
		InvoiceFactory invoiceFactory = mock(InvoiceFactory.class);
		when(invoiceFactory.create((ClientData)Mockito.any())).thenReturn(new Invoice(Id.generate(), client));
		TaxPolicy taxPolicy = mock(TaxPolicy.class);
		when(taxPolicy.calculateTax((ProductType)Mockito.any(), (Money)Mockito.any())).thenReturn(new Tax(money, ""));
		
		InvoiceRequest invoiceRequest = new InvoiceRequest(client);
		invoiceRequest.add(requestItem);
		invoiceRequest.add(requestItem);
		
		Invoice invoice = bookKeeper.issuance(invoiceRequest, taxPolicy);
		
		Mockito.verify(taxPolicy, Mockito.times(2)).calculateTax((ProductType)Mockito.any(), (Money)Mockito.any());
		
	}

}
