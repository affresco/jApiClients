package apps.calibration.models;

import java.time.Instant;

public interface CalibrationObservable {

    // Get the filtration corresponding to this calibration
    Instant getFiltration();

    // Register oneself as an observer
    void registerObserver(CalibrationObserver observer);

    // Unregister oneself as an observer
    void unregisterObserver(CalibrationObserver observer);

    // Unregister oneself as an observer
    void notifyObservers();


}
