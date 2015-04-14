package pl.com.bottega.ecommerce.sales.application.api.handler;

import org.junit.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;
import pl.com.bottega.ecommerce.canonicalmodel.publishedlanguage.Id;
import pl.com.bottega.ecommerce.sales.application.api.command.AddProductCommand;
import pl.com.bottega.ecommerce.sales.domain.productscatalog.ProductType;
import pl.com.bottega.ecommerce.sharedkernel.Money;

public class AddProductCommandHandlerTest {

	@Test
	public void addProductCommandHandler_handleTest() {
		AddProductCommand addProductCommand = new AddProductCommand(Id.generate(), Id.generate(), 1);
		AddProductCommandHandler addProductCommandHandler= mock(AddProductCommandHandler.class);
		addProductCommandHandler.handle(addProductCommand);
		Mockito.verify(addProductCommandHandler, Mockito.times(1)).handle((AddProductCommand)Mockito.any());
	}

}
