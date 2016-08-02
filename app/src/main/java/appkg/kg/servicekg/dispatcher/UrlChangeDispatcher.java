package appkg.kg.servicekg.dispatcher;

import java.util.ArrayList;

/**
 * Created by Suimonkul on 28-Jul-16.
 */
public class UrlChangeDispatcher {

    private ArrayList<UrlChangeListener> listeners = new ArrayList<>();
    private static UrlChangeDispatcher instance = new UrlChangeDispatcher();

    public static UrlChangeDispatcher getInstance() {
        return instance;
    }

    public void addListener(UrlChangeListener listener) {
        listeners.add(listener);
    }

    public void removeListener(UrlChangeListener listener) {
        listeners.remove(listener);
    }

    public void notifyListeners(String newUrl) {
        for (UrlChangeListener listener: listeners) {
            listener.onUrlChanged(newUrl);
        }
    }

    private UrlChangeDispatcher() {

    }
}
