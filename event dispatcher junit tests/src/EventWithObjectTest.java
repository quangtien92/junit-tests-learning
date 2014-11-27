import static org.junit.Assert.*;

import java.awt.Point;
import java.lang.reflect.InvocationTargetException;

import org.junit.Before;
import org.junit.Test;

import com.eposi.eventdriven.Event;
import com.eposi.eventdriven.exceptions.InvalidHandlerMethod;
import com.eposi.eventdriven.exceptions.NoContextToExecute;
import com.eposi.eventdriven.implementors.BaseEventListener;

public class EventWithObjectTest {
	private SimpleEvent simpleEvent = null;
	private boolean isEventDispatch = false;
	private Point point;
	private Event eventResult;
	private SampleObject resultObject;

	@Before
	public void setupTest() {
		simpleEvent = new SimpleEvent();
		simpleEvent.addEventListener("test", new BaseEventListener(this,
				"onEventDispath"));
		this.point = new Point(100, 20);
		new Thread(new Runnable() {


			@Override
			public void run() {
				try {
					Thread.sleep(1000);
					resultObject = new SampleObject(1, "string", 2.2f, point);
					simpleEvent.dispatchEvent(new Event("test", resultObject));
				} catch (InterruptedException | InvocationTargetException
						| IllegalAccessException | NoSuchMethodException
						| InvalidHandlerMethod | NoContextToExecute e) {
					e.printStackTrace();
				}
			}
		}).start();
	}
	
	@Test
	public void test() {
		while (!isEventDispatch) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		SampleObject sampleObject = null;
		try {
			sampleObject = (SampleObject) eventResult.getTarget();
		} catch (Exception e) {
			
		}
		assertEquals(this.resultObject, sampleObject);;
	}

	@Test
	public void testInt() {
		while (!isEventDispatch) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		boolean isSuccess = false;
		try {
			SampleObject sampleObject = (SampleObject) eventResult.getTarget();
			if (sampleObject.getIntTest() == 1) isSuccess = true;
		} catch (Exception e) {
			
		}
		assertTrue(isSuccess);
	}
	@Test
	public void testString() {
		while (!isEventDispatch) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		boolean isSuccess = false;
		try {
			SampleObject sampleObject = (SampleObject) eventResult.getTarget();
			if (sampleObject.getStrParam().equalsIgnoreCase("string")) isSuccess = true;
		} catch (Exception e) {
			
		}
		assertTrue(isSuccess);
	}
	@Test
	public void testFloat() {
		while (!isEventDispatch) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		boolean isSuccess = false;
		try {
			SampleObject sampleObject = (SampleObject) eventResult.getTarget();
			if (sampleObject.getFloatParam() == 2.2f) isSuccess = true;
		} catch (Exception e) {
			
		}
		assertTrue(isSuccess);
	}
	@Test
	public void testObject() {
		while (!isEventDispatch) {
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		Object objectParam = null;
		try {
			SampleObject sampleObject = (SampleObject) eventResult.getTarget();
			objectParam = sampleObject.getObjectParam();
		} catch (Exception e) {
			
		}
		assertEquals(objectParam, point);
	}

	@Deprecated
	public void onEventDispath(Event event) {
		this.eventResult = event;
		this.isEventDispatch = true;
	}
}
