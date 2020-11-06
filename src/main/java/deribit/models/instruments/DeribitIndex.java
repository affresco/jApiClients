package deribit.models.instruments;


import java.util.HashMap;

public class DeribitIndex extends DeribitInstrument {

    // ##################################################################
    // ATTRIBUTES
    // ##################################################################

    // This contains the instances present in the system
    private static HashMap<String, DeribitIndex> instances;

    // ##################################################################
    // CONSTRUCTORS
    // ##################################################################

    protected DeribitIndex(Builder builder) {
        super(builder);
    }

    // ##################################################################
    // BUILD (STATIC CLASS)
    // ##################################################################

    public static class Builder extends DeribitInstrument.Builder {

        @Override
        protected DeribitIndex.Builder self() {
            return this;
        }

        @Override
        protected DeribitIndex build() {
            return DeribitIndex.getInstance(this);
        }
    }

    // ##################################################################
    // CLASS METHODS
    // ##################################################################

    public static DeribitIndex getInstance(DeribitIndex.Builder builder){

        // Make sure we do have an instance of the HashMap
        if (instances == null) {
            synchronized (DeribitIndex.class) {
                instances = new HashMap<>();
            }
        }

        // Already present, just return
        String uniqueIdentifier = builder.getUniqueIdentifier();
        if (instances.containsKey(uniqueIdentifier)){
            return instances.get(uniqueIdentifier);
        }

        // Otherwise create it
        else {
            DeribitIndex newInstrument = new DeribitIndex(builder);
            synchronized (DeribitIndex.class) {
                instances.put(builder.getUniqueIdentifier(), newInstrument);
            }
            return newInstrument;
        }
    }

}

