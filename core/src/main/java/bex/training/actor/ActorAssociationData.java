package bex.training.actor;

import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Optional;
import java.util.Set;

import brightspot.core.classification.ClassificationDataItem;
import brightspot.core.tool.StateUtils;
import com.psddev.cms.db.ToolUi;
import com.psddev.dari.db.Modification;
import com.psddev.dari.db.Recordable;

@Recordable.FieldInternalNamePrefix(ActorAssociationData.PREFIX)
public class ActorAssociationData extends Modification<ActorAssociation> implements ClassificationDataItem {

    public static final String PREFIX = "actor.association.";

    public static final String FIELD = ActorAssociationData.class.getName() + "/"
            + PREFIX + "getFilterableItems()";

    @Indexed
    @ToolUi.Hidden
    public Set<Actor> getFilterableItems() {
        return StateUtils.resolveAndGet(this, resolved ->
                Optional.ofNullable(resolved.as(ActorAssociation.class))
                        .map(ActorAssociation::getAssociatedActors)
                        .orElseGet(Collections::emptySet));
    }

    @Override
    public Set<Recordable> getClassificationAttributes() {
        return new LinkedHashSet<>(getFilterableItems());
    }
}
