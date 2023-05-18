package pl.kpp.tovarna.data.classes;

import jakarta.persistence.*;

public enum BuildState {

    NEW(0),

    IN_PROGRESS(1),

    WAIT_FOR_REQUIREMENT(2),

    DONE(4);

    private final int stateValue;

    BuildState(int stateValue) {
        this.stateValue = stateValue;
    }

    public int getValue() {
        return stateValue;
    }

    @Converter
    public static class BuildStateConverter implements AttributeConverter<BuildState, Integer> {

        @Override
        public Integer convertToDatabaseColumn(BuildState buildState) {
            return buildState.getValue();
        }

        @Override
        public BuildState convertToEntityAttribute(Integer intValue) {
            return switch (intValue) {
                case 0 -> BuildState.NEW;
                case 1 -> BuildState.IN_PROGRESS;
                case 2 -> BuildState.WAIT_FOR_REQUIREMENT;
                case 4 -> BuildState.DONE;
                default -> throw new IllegalArgumentException(String.format("Given value `%d` is incorrect.", intValue));
            };
        }
    }
}
