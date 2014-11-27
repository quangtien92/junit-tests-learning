import java.lang.reflect.InvocationTargetException;

import com.eposi.eventdriven.Event;
import com.eposi.eventdriven.exceptions.InvalidHandlerMethod;
import com.eposi.eventdriven.exceptions.NoContextToExecute;
import com.eposi.eventdriven.implementors.BaseEventDispatcher;
import com.eposi.eventdriven.implementors.BaseEventListener;

public class EventDestination {
	private BaseEventDispatcher eventDispatcher = new BaseEventDispatcher();
	private static EventDestination _instance = null;

	public static EventDestination getInstance() {
		if (_instance == null)
			_instance = new EventDestination();
		return _instance;
	}

	public EventDestination() {

	}

	public void addEventListener(EventType eventType,
			BaseEventListener baseEventListener) {
		eventDispatcher.addEventListener(eventType.toString(), baseEventListener);
	}

	public void dispatchEvent(EventType eventType, Object targetObject) {
		try {
			eventDispatcher.dispatchEvent(new Event(eventType.toString(), targetObject));
		} catch (InvocationTargetException | IllegalAccessException
				| NoSuchMethodException | InvalidHandlerMethod
				| NoContextToExecute e) {
			e.printStackTrace();
		}
	}
}
