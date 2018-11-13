package bex.training.actor;

import java.util.HashSet;
import java.util.Set;

import brightspot.classification.AbstractClassificationDynamicQueryModifier;
import com.psddev.cms.db.ToolUi;
import com.psddev.dari.db.Recordable;

@Recordable.FieldInternalNamePrefix("actor.list.")
public class ActorDynamicQueryModifier
        extends AbstractClassificationDynamicQueryModifier<ActorDynamicQueryModifiable, ActorOrCurrentActor, Actor, CurrentActor> {

    @ToolUi.DropDown
    @ToolUi.Cluster("Classification")
    private Set<ActorOrCurrentActor> actors;

    public Set<ActorOrCurrentActor> getActors() {

        if (actors == null) {
            actors = new HashSet<>();
        }
        return actors;
    }

    public void setActors(Set<ActorOrCurrentActor> actors) {
        this.actors = actors;
    }

    @Override
    public Set<ActorOrCurrentActor> getItems() {
        return getActors();
    }

    @Override
    public Class<Actor> getConcreteClass() {
        return Actor.class;
    }

    @Override
    public Class<CurrentActor> getCurrentClass() {
        return CurrentActor.class;
    }

}
