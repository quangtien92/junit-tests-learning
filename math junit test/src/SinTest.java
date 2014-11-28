import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class SinTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void test() {
		double max = 0d, min = 0d;
		for (int i = -1000; i < 1000; i++) {
			double radians = Math.toRadians(i);
			double sin = Math.cos(radians);
			if (sin == 1)
				System.out.println("angle of max: " + i);
			max = Math.max(sin, max);
			min = Math.min(sin, min);
		}
		System.out.println("max: " + max + ", min" + min);
		assertTrue("max: " + max + ", min" + min, max == 1d && min == -1d);
	}

}
