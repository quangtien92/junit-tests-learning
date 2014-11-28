import static org.junit.Assert.*;

import java.awt.Point;
import java.lang.reflect.InvocationTargetException;

import org.junit.Before;
import org.junit.Test;

import com.eposi.eventdriven.Event;
import com.eposi.eventdriven.exceptions.InvalidHandlerMethod;
import com.eposi.eventdriven.exceptions.NoContextToExecute;
import com.eposi.eventdriven.implementors.BaseEventListener;

public class EventManagerTest {
	private boolean isEventDispatchHander1 = false;
	private boolean isEventDispatchHander2 = false;
	private Point point;
	private Event eventResult;
	private SampleObject resultObject;

	@Before
	public void setupTest() {
		EventDestination.getInstance().addEventListener(EventType.TEST1, new BaseEventListener(this,
				"onEventDispathHander1"));
		EventDestination.getInstance().addEventListener(EventType.TEST2, new BaseEventListener(this,
				"onEventDispathHander2"));
		this.point = new Point(100, 20);
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(1000);
					resultObject = new SampleObject(1, "string", 2.2f, point);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				EventDestination.getInstance().dispatchEvent(EventType.TEST1, resultObject);
			}
		}).start();
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				EventDestination.getInstance().dispatchEvent(EventType.TEST2, 1);
			}
		}).start();
	}
	
	@Test
	public void test() {
		while (!isEventDispatchHander1) {
			try {
				Thread.sleep(50);
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
	public void test2() {
		while (!isEventDispatchHander2) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		int result = 0;
		try {
			result = (int) eventResult.getTarget();
		} catch (Exception e) {
			
		}
		assertEquals(1, result);;
	}
	
	@Deprecated
	public void onEventDispathHander1(Event event) {
		System.out.println(EventType.TEST1.toString());
		this.eventResult = event;
		this.isEventDispatchHander1 = true;
	}
	
	@Deprecated
	public void onEventDispathHander2(Event event) {
		System.out.println(EventType.TEST2.toString());
		this.eventResult = event;
		this.isEventDispatchHander2 = true;
	}
}
