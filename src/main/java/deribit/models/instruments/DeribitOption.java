package deribit.models.instruments;

public class DeribitOption extends DeribitInstrument {

    protected DeribitOption(Builder builder) {
        super(builder);
    }

    public static class Builder extends DeribitInstrument.Builder {
        @Override
        protected DeribitOption build() {
            return (DeribitOption) super.build();
        }
    }

}
