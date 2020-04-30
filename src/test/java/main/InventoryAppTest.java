package main;

import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.qa.controllers.MainController;
import com.qa.dto.Customer;
import com.qa.main.InventoryApp;
import com.qa.security.SessionHashMap;
import com.qa.services.CustomerService;
import com.qa.services.OrderService;
import com.qa.utils.ConnectionMySQL;

public class InventoryAppTest {
	
	@Before
	public void setUp() {
		ConnectionMySQL.setTestable(true);
	}
	
	@Test
	public void runInventory() {
		
		CustomerService customerService = mock(CustomerService.class);
		OrderService orderService = mock(OrderService.class);
		Customer customer = mock(Customer.class);
		customer.setName("Camo");
		
		MainController mainController = mock(MainController.class);
		
		InventoryApp app = new InventoryApp();
		app.setCustomerService(customerService);
		app.setOrderService(orderService);
		app.setCustomer(customer);
		app.setMainController(mainController);
		
		InventoryApp app2 = Mockito.spy(app);
		
		Mockito.doReturn(new SessionHashMap()).when(app2).getSessionHashMap();
		Mockito.doReturn("1").doReturn("Camo").when(app2).getInput();
		Mockito.doNothing().when(app2).setCustomer(new Customer(anyString()));
		Mockito.doNothing().when(app2).setCustomerService(new CustomerService());
		Mockito.doNothing().when(app2).setOrderService(new OrderService());
		
		Mockito.doNothing().when(app2).setMainController(MainController.getMainMenu());
		Mockito.doReturn("1").when(mainController).selectMenuOptions();
		
		app2.runInventory();
		verify(app2, times(1)).runInventory();
	
	} 
	
	@After
	public void onFinish() {
		ConnectionMySQL.closeConnection();
	}

}
