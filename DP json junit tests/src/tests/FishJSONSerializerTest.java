package tests;

import static org.junit.Assert.*;

import java.lang.reflect.Type;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import javax.xml.transform.ErrorListener;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.URIResolver;

import org.eclipse.jetty.util.ajax.JSON;
import org.junit.Before;
import org.junit.Test;

import vn.edu.hust.student.dynamicpool.bll.model.Boundary;
import vn.edu.hust.student.dynamicpool.bll.model.CycleTrajectory;
import vn.edu.hust.student.dynamicpool.bll.model.ETrajectoryType;
import vn.edu.hust.student.dynamicpool.bll.model.Fish;
import vn.edu.hust.student.dynamicpool.bll.model.FishFactory;
import vn.edu.hust.student.dynamicpool.bll.model.FishType;
import vn.edu.hust.student.dynamicpool.bll.model.LineTrajectory;
import vn.edu.hust.student.dynamicpool.bll.model.NoneTrajectory;
import vn.edu.hust.student.dynamicpool.bll.model.SinTrajectory;
import vn.edu.hust.student.dynamicpool.bll.model.Trajectory;
import vn.edu.hust.student.dynamicpool.dal.statics.Field;
import flexjson.JSONDeserializer;
import flexjson.JSONSerializer;
import flexjson.ObjectBinder;
import flexjson.ObjectFactory;

public class FishJSONSerializerTest {
	JSONSerializer serializer = new JSONSerializer();
	JSONDeserializer<Fish> deserializer = new JSONDeserializer<Fish>();
	Map<String, Object> data = null;
	String message = null;
	private JSON json = new JSON();

	@Before
	public void setUp() throws Exception {
		data = new HashMap<>();
		data.put(Field.SUCCESSFUL, true);
		data.put(Field.CLIENT_NAME, "test");
		data.put(Field.FISH, FishFactory.createFishWithLineTrajectory(100, 51,
				FishType.FISH1));
		message = serializer.exclude("*.class").serialize(data);
		// message =
		// "{\"clientName\":\"55b28052-d17c-4adb-a3a7-37fc8de6b835\",\"fish\":{\"boundary\":{\"height\":51.0,\"location\":{\"x\":400.0,\"y\":240.0},\"maxX\":500.0,\"maxY\":291.0,\"minX\":400.0,\"minY\":240.0,\"width\":100.0},\"fishId\":-34228131,\"fishState\":\"INSIDE\",\"fishType\":\"FISH1\",\"ignoreUpdateLocation\":false,\"trajectory\":{\"fishBoundary\":{\"height\":51.0,\"location\":{\"x\":400.0,\"y\":240.0},\"maxX\":500.0,\"maxY\":291.0,\"minX\":400.0,\"minY\":240.0,\"width\":100.0},\"horizontalDirection\":\"RIGHT\",\"timeState\":0.0,\"trajectoryType\":\"LINE\"}},\"cmd\":\"createFish\"}";
		System.out.println(message);
	}

	@Test
	public void test() {
		Map<String, Object> params = (Map<String, Object>) json
				.fromJSON(message);
		boolean isSuccess = (boolean) params.get(Field.SUCCESSFUL);
		assertTrue(isSuccess);
		String clientName = (String) params.get(Field.CLIENT_NAME);
		assertTrue(clientName.equals("test"));
		// String fishString =
		// serializer.exclude("*.class").serialize(params.get(Field.FISH));
		String fishString = "{\"boundary\":{\"minY\":240.0,\"minX\":400.0,\"maxY\":291.0,\"maxX\":500.0,\"width\":100.0,\"location\":{\"x\":400.0,\"y\":240.0},\"height\":51.0},\"fishState\":\"INSIDE\",\"fishType\":\"FISH1\",\"fishId\":1115479042,\"trajectory\":{\"trajectoryType\":\"LINE\",\"timeState\":0.0,\"horizontalDirection\":\"RIGHT\",\"fishBoundary\":{\"minY\":240.0,\"minX\":400.0,\"maxY\":291.0,\"maxX\":500.0,\"width\":100.0,\"location\":{\"x\":400.0,\"y\":240.0},\"height\":51.0}},\"ignoreUpdateLocation\":false}";
		Fish fish = deserializer.use("trajectory", LineTrajectory.class).deserialize(fishString, Fish.class);
		System.out.println(fish.getTrajectory().getClass().getName());
		assertTrue(Fish.class.isInstance(fish));
	}

}
