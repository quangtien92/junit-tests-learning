import static org.junit.Assert.*;

import java.awt.Point;
import java.lang.reflect.InvocationTargetException;

import org.junit.Before;
import org.junit.Test;

import com.eposi.eventdriven.Event;
import com.eposi.eventdriven.exceptions.InvalidHandlerMethod;
import com.eposi.eventdriven.exceptions.NoContextToExecute;
import com.eposi.eventdriven.implementors.BaseEventListener;

public class EventWithMultiHander {
	private SimpleEvent simpleEvent = null;
	private boolean isEventDispatchHander1 = false;
	private boolean isEventDispatchHander2 = false;
	private Point point;
	private Event eventResult;
	private SampleObject resultObject;

	@Before
	public void setupTest() {
		simpleEvent = new SimpleEvent();
		simpleEvent.addEventListener("test", new BaseEventListener(this,
				"onEventDispathHander1"));
		simpleEvent.addEventListener("test", new BaseEventListener(this,
				"onEventDispathHander2"));
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
		SampleObject sampleObject = null;
		try {
			sampleObject = (SampleObject) eventResult.getTarget();
		} catch (Exception e) {
			
		}
		assertEquals(this.resultObject, sampleObject);;
	}
	
	@Deprecated
	public void onEventDispathHander1(Event event) {
		this.eventResult = event;
		this.isEventDispatchHander1 = true;
	}
	
	@Deprecated
	public void onEventDispathHander2(Event event) {
		this.eventResult = event;
		this.isEventDispatchHander2 = true;
	}
}
