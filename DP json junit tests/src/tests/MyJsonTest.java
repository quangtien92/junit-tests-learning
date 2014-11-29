package tests;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import org.eclipse.jetty.util.ajax.JSON;
import org.eclipse.jetty.util.ajax.JSONObjectConvertor;
import org.junit.Before;
import org.junit.Test;

import vn.edu.hust.student.dynamicpool.bll.model.Boundary;
import vn.edu.hust.student.dynamicpool.bll.model.FishFactory;
import vn.edu.hust.student.dynamicpool.bll.model.FishType;
import vn.edu.hust.student.dynamicpool.bll.model.IFish;
import vn.edu.hust.student.dynamicpool.bll.model.Point;

public class MyJsonTest {
	private IFish fish;
	JSON json = new JSON();
	@Before
	public void setUp() {
		fish = FishFactory.createFishWithLineTrajectory(100, 51, FishType.FISH1);
		JSON.registerConvertor(Boundary.class, new JSONObjectConvertor());
		JSON.registerConvertor(Point.class, new JSONObjectConvertor());
	}

	@Test
	public void testToJsonString() {
		String data = "123 456 ?@o-0 1";
		String expect = "\""+data+"\"";
		String output = json.toJSON(data);
		System.out.println(data);
		System.out.println(output);
		assertTrue(output.equals(expect));
	}
	
	@Test
	public void testToJsonInteger() {
		int data = 1053;
		String expect = data + "";
		String output = json.toJSON(data);
		System.out.println(data);
		System.out.println(output);
		assertTrue(output.equals(expect));
	}
	
	@Test
	public void testToJsonDouble() {
		double data = 53849.4324534;
		String expect = data + "";
		String output = json.toJSON(data);
		System.out.println(data);
		System.out.println(output);
		assertTrue(output.equals(expect));
	}
	
	@Test
	public void testToJsonArray() {
		Random random = new Random();
		int n = Math.abs(random.nextInt(30));
		int[] data = new int[n];
		String expect = "[";
		for (int i = 0; i < n; i++) {
			data[i] = random.nextInt();
			expect += data[i];
			if (i < n-1) expect+=",";
		}
		expect += "]";
		String output = json.toJSON(data);
		for (int i = 0; i < n; i++) {
			System.out.print(data[i] + ",");
		}
		System.out.println();
		System.out.println(output);
		assertTrue(output.equals(expect));
	}
	
	@Test
	public void testToJsonList() {
		Random random = new Random();
		int n = Math.abs(random.nextInt(30));
		ArrayList<Integer> data = new ArrayList<>();
		String expect = "[";
		for (int i = 0; i < n; i++) {
			int nextInt = random.nextInt();
			data.add(new Integer(nextInt));
			expect += nextInt;
			if (i < n-1) expect+=",";
		}
		expect += "]";
		String output = json.toJSON(data);
		for (Integer nextInt : data) {
			System.out.print(nextInt + ",");
		}
		System.out.println();
		System.out.println(output);
		assertTrue(output.equals(expect));
	}
	
	@Test
	public void testToJsonMap() {
		Map<String, Object> data = new HashMap<String, Object>();
		data.put("string", "just a sample text @&*&fksjf");
		data.put("integer", 2834);
		data.put("double", -128.23874);
		String expect = "{\"string\":\"just a sample text @&*&fksjf\",\"double\":-128.23874,\"integer\":2834}";
		String output = json.toJSON(data);
		System.out.println(output);
		assertTrue(output.equals(expect));
	}
	
	@Test
	public void testToJsonSimpleObject() {
		Boundary data = new Boundary(new Point(1034, -8346), 2834, 8627);
		String output = json.toJSON(data);
		System.out.println(output);
		Object input = json.fromJSON(output);
	}
}
