package bex.training.actor;

import brightspot.core.search.FieldFilter;

public class ActorFilter extends FieldFilter {

    public static final String DEFAULT_HEADING = "Actor";

    @Override
    public String getField() {
        return ActorAssociationData.FIELD;
    }

    @Override
    public String getDefaultHeading() {
        return DEFAULT_HEADING;
    }

    @Override
    public boolean hasMutuallyExclusiveValues() {
        return false;
    }

    @Override
    public String getItemLabel(Object object) {

        return object instanceof Actor ? ((Actor) object).getLabel() : null;
    }
}
