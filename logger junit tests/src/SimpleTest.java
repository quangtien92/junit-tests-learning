import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class SimpleTest {
	private Logger logger; 
	@Before
	public void setUp() {
		logger = LoggerFactory.getLogger(SimpleTest.class);
	}

	@Test
	public void test() {
		logger.error("Test error message");
		logger.debug("Test debug message");
		logger.info("Test info message");
		logger.warn("Test warn message");
		logger.trace("Test trace message");
		assertTrue(true);
	}

}
