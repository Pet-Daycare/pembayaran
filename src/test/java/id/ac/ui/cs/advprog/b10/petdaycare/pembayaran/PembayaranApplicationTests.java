package id.ac.ui.cs.advprog.b10.petdaycare.pembayaran;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

@SpringBootTest
class PembayaranApplicationTests {
	@Autowired
	private ApplicationContext context;

	@Test
	void contextLoads() {
		// Assert that the application context is not null
		assertNotNull(context);
	}

}
