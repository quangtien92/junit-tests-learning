import static org.junit.Assert.*;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.nio.Buffer;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import vn.edu.hust.student.dynamicpool.bll.model.DeviceInfo;
import vn.edu.hust.student.dynamicpool.dal.ClientDataAccessLayerImpl;
import vn.edu.hust.student.dynamicpool.dal.DataAccessLayer;
import vn.edu.hust.student.dynamicpool.dal.HostDataAccessLayerImpl;


public class ServerTest {
	DataAccessLayer hostDataAccessLayer = new HostDataAccessLayerImpl();
	DataAccessLayer clientdataAccessLayer = new ClientDataAccessLayerImpl();

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		
	}

	@Before
	public void setUp() throws Exception {
		hostDataAccessLayer.createHost();
		DeviceInfo deviceInfo = new DeviceInfo(1366, 768, 5);
		hostDataAccessLayer.addDevice(deviceInfo);
	}

	@Test
	public void testJoinHost() {
		BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
		String key = null;
		try {
			key = bufferedReader.readLine();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		clientdataAccessLayer.joinHost(key);
		DeviceInfo deviceInfo = new DeviceInfo(800, 480, 5);
		clientdataAccessLayer.addDevice(deviceInfo);
	}
}
