package bex.training.actor;

import java.util.Set;

import com.psddev.dari.db.Recordable;

public interface ActorAssociation extends Recordable {

    Set<Actor> getAssociatedActors();
}
