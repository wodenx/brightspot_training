package bex.training.actor;

import com.psddev.cms.db.Site;
import com.psddev.dari.db.Record;
import com.psddev.dari.db.Singleton;

public class CurrentActor extends Record implements ActorOrCurrentActor,
        Singleton {

    @Override
    protected void beforeSave() {
        super.beforeSave();
        as(Site.ObjectModification.class).setGlobal(true);
    }

    @Override
    public String getLabel() {
        // zero width space to affect sorting
        return '\u200B' + "Current Actor";
    }
}
