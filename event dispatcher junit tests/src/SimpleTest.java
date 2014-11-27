import static org.junit.Assert.*;

import java.lang.reflect.InvocationTargetException;

import org.junit.Before;
import org.junit.Test;

import com.eposi.eventdriven.Event;
import com.eposi.eventdriven.exceptions.InvalidHandlerMethod;
import com.eposi.eventdriven.exceptions.NoContextToExecute;
import com.eposi.eventdriven.implementors.BaseEventListener;

public class SimpleTest {
	private SimpleEvent simpleEvent = null;
	private boolean isEventDispatch = false;
	private boolean isSuccess = false;

	@Before
	public void setupTest() {
		simpleEvent = new SimpleEvent();
		simpleEvent.addEventListener("test", new BaseEventListener(this,
				"onEventDispath"));
		new Thread(new Runnable() {

			@Override
			public void run() {
				try {
					Thread.sleep(1000);
					simpleEvent.dispatchEvent(new Event("test"));
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
		assertTrue(isSuccess);
	}

	@Deprecated
	public void onEventDispath(Event event) {
		this.isSuccess = event.getType() == "test" ? true : false;
		this.isEventDispatch = true;
	}
}
