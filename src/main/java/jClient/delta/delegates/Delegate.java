package jClient.delta.delegates;

import jClient.base.MessageHandler;
import jClient.delta.DeltaClientCore;

public abstract class Delegate {

    // ##################################################################
    // ATTRIBUTES
    // ##################################################################

    protected DeltaClientCore client;

    // ##################################################################
    // CONSTRUCTORS
    // ##################################################################

    public Delegate(DeltaClientCore client) {
        this.client = client;
    }

    // ##################################################################
    // ABSTRACT
    // ##################################################################

    public abstract MessageHandler getHandler();

}
