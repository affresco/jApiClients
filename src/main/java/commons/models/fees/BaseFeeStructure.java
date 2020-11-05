package commons.models.fees;

import com.fasterxml.jackson.databind.ser.Serializers;

public abstract class BaseFeeStructure {

    protected BaseFeeStructure(Builder<?> builder){ }

    public BaseFeeStructure(BaseFeeStructure feeStructure){ }

    public abstract static class Builder<T extends Builder<T>>{

        protected abstract BaseFeeStructure build();
        protected abstract T self();
    }

}
