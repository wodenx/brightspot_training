package bex.training.actor;

import brightspot.core.permalink.ExpressDefaultPermalinkRule;
import com.psddev.cms.db.Site;
import com.psddev.dari.db.Record;
import com.psddev.dari.util.ObjectUtils;

public class ActorPermalinkRule extends Record implements ExpressDefaultPermalinkRule {

    @Override
    public String createPermalink(Site site, Object object) {

        if (!(object instanceof Actor)) {
            return null;
        }

        Actor actor = (Actor) object;
        String actorSlug = actor.asSluggableData().getSlug();
        return ObjectUtils.isBlank(actorSlug) ? null : "/actors/" + actorSlug;
    }
}
