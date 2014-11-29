package tests;

import static org.junit.Assert.assertTrue;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jetty.util.ajax.JSON;
import org.junit.Before;
import org.junit.Test;

import vn.edu.hust.student.dynamicpool.bll.model.Boundary;
import vn.edu.hust.student.dynamicpool.bll.model.FishFactory;
import vn.edu.hust.student.dynamicpool.bll.model.FishType;
import vn.edu.hust.student.dynamicpool.bll.model.LineTrajectory;
import vn.edu.hust.student.dynamicpool.bll.model.Point;
import vn.edu.hust.student.dynamicpool.dal.statics.Field;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;

public class LineTrajectorySerializerTest {
	JSONSerializer serializer = new JSONSerializer();
	JSONDeserializer<Boundary> deserializer = new JSONDeserializer<Boundary>();
	Map<String, Object> data = null;
	String message = null;
	private JSON json = new JSON();
	@Before
	public void setUp() throws Exception {
		data = new HashMap<>();
		data.put(Field.SUCCESSFUL, true);
		data.put(Field.CLIENT_NAME, "test");
		Boundary original = new Boundary(new Point(1382, -187), 7348, 34826);
		data.put(Field.FISH, original);
		message = serializer.exclude("*.class").serialize(data);
		System.out.println(message);
	}

	@Test
	public void testParams() {
		Map<String, Object> params = (Map<String, Object>) json
				.fromJSON(message);
		boolean isSuccess = (boolean) params.get(Field.SUCCESSFUL);
		assertTrue(isSuccess);
		String clientName = (String) params.get(Field.CLIENT_NAME);
		assertTrue(clientName.equals("test"));
	}
	
	@Test
	public void testObject() {
		Map<String, Object> params = (Map<String, Object>) json
				.fromJSON(message);
		String textOfObject = serializer.exclude("*.class").serialize(params.get(Field.FISH));
		System.out.println(textOfObject);
		Boundary result = deserializer.deserialize(textOfObject);
		assertTrue(Boundary.class.isInstance(result));
	}

}
