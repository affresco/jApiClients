package deribit.models.instruments;

public class DeribitFuture extends DeribitInstrument {

    protected DeribitFuture(Builder builder) {
        super(builder);
    }

    public static class Builder extends DeribitInstrument.Builder {
        @Override
        protected DeribitFuture build() {
            return (DeribitFuture) super.build();
        }
    }

}
