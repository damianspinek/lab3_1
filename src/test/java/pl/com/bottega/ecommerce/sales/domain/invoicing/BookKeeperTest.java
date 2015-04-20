package pl.com.bottega.ecommerce.sales.domain.invoicing;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.Test;
import org.mockito.Mockito;

import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.ClientData;
import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.ClientDataBuilder;
import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.Id;
import pl.com.bottega.ecommerce.sales.domain.productscatalog.ProductData;
import pl.com.bottega.ecommerce.sales.domain.productscatalog.ProductDataBuilder;
import pl.com.bottega.ecommerce.sales.domain.productscatalog.ProductType;
import pl.com.bottega.ecommerce.sharedkernel.Money;

public class BookKeeperTest {

	@Test
	public void bookKeeper_issuanceTest_giveOnePosition_ExpectedOnePosition() {
		ClientData client = new ClientDataBuilder().build();
		BookKeeper bookKeeper = new BookKeeper( new InvoiceFactory());
		Money money = new Money(1);
		ProductData productData =  new ProductDataBuilder().build();
		RequestItem requestItem = new RequestItemBuilder().build();
		
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
	public void bookKeeper_issuanceTest_giveTwoPosition_shouldCallCalculateTaxTwice(){
		ClientData client = new ClientDataBuilder().build();
		BookKeeper bookKeeper = new BookKeeper( new InvoiceFactory());
		Money money = new Money(1);
		ProductData productData =  new ProductDataBuilder().build();;
		RequestItem requestItem = new RequestItemBuilder().build();
		
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
	
	@Test
	public void bookKeeper_issuanceTest_giveZeroPosition_ExpectedZeroPosition() {
		ClientData client = new ClientDataBuilder().build();
		BookKeeper bookKeeper = new BookKeeper( new InvoiceFactory());
		Money money = new Money(1);
		ProductData productData =  new ProductDataBuilder().build();
		RequestItem requestItem = new RequestItemBuilder().build();
		
		InvoiceFactory invoiceFactory = mock(InvoiceFactory.class);
		when(invoiceFactory.create((ClientData)Mockito.any())).thenReturn(new Invoice(Id.generate(), client));
		TaxPolicy taxPolicy = mock(TaxPolicy.class);
		when(taxPolicy.calculateTax((ProductType)Mockito.any(), (Money)Mockito.any())).thenReturn(new Tax(money, ""));
		
		InvoiceRequest invoiceRequest = new InvoiceRequest(client);
		
		Invoice invoice = bookKeeper.issuance(invoiceRequest, taxPolicy);
		
		assertThat(invoice.getItems().size(), is(0));		
	}
	
	@Test
	public void bookKeeper_issuanceTest_giveZeroPosition_shouldNotCallCalculateTax(){
		ClientData client = new ClientDataBuilder().build();
		BookKeeper bookKeeper = new BookKeeper( new InvoiceFactory());
		Money money = new Money(1);
		ProductData productData =  new ProductDataBuilder().build();
		RequestItem requestItem = new RequestItemBuilder().build();
		
		InvoiceFactory invoiceFactory = mock(InvoiceFactory.class);
		when(invoiceFactory.create((ClientData)Mockito.any())).thenReturn(new Invoice(Id.generate(), client));
		TaxPolicy taxPolicy = mock(TaxPolicy.class);
		when(taxPolicy.calculateTax((ProductType)Mockito.any(), (Money)Mockito.any())).thenReturn(new Tax(money, ""));
		
		InvoiceRequest invoiceRequest = new InvoiceRequest(client);
		
		Invoice invoice = bookKeeper.issuance(invoiceRequest, taxPolicy);
		
		Mockito.verify(taxPolicy, Mockito.times(0)).calculateTax((ProductType)Mockito.any(), (Money)Mockito.any());
		
	}

}
