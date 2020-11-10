package apps.calibration.models;

import commons.models.data.SimpleQuote;
import commons.models.data.SortedDataStructure;
import commons.models.data.VolatilitySmile;

import java.time.Instant;
import java.util.ArrayList;

public class SviCalibration extends VolatilitySmile implements CalibrationObservable {

    // ##################################################################
    // ATTRIBUTES
    // ##################################################################

    private final String id;
    private final ArrayList<CalibrationObserver> observers;


    // ##################################################################
    // CONSTRUCTORS
    // ##################################################################

    public SviCalibration(SimpleQuote referenceSpot, SimpleQuote referenceForward, SortedDataStructure bid, SortedDataStructure ask, Instant expiry) {
        this(referenceSpot, referenceForward, bid, ask, expiry, Instant.now());
    }

    public SviCalibration(SimpleQuote referenceSpot, SimpleQuote referenceForward, SortedDataStructure bid, SortedDataStructure ask, Instant expiry, Instant filtration) {
        super(referenceSpot, referenceForward, bid, ask, expiry, filtration);
        this.id = utilities.Identity.generateId();
        this.observers = new ArrayList<>();
    }

    // ##################################################################
    // GETTERS
    // ##################################################################

    public String getId() {
        return new String(id);
    }

    // ##################################################################
    // CORE
    // ##################################################################

    public void update(VolatilitySmile sviCalibration) {

        // TODO: Do something with the data!!
        // pass

        // Notify all observers of this change
        notifyObservers();
    }

    // ##################################################################
    // OBSERVABLE INTERFACE
    // ##################################################################

    @Override
    public void registerObserver(CalibrationObserver observer) {
        if(!observers.contains(observer)){
            observers.add(observer);
        }
    }

    @Override
    public void unregisterObserver(CalibrationObserver observer) {
        observers.remove(observer);
    }

    @Override
    public void notifyObservers() {
        for(CalibrationObserver observer: observers){
            observer.update(this);
        }
    }
}
