package bex.training.actor;

import brightspot.core.page.AbstractContentPageViewModel;
import brightspot.core.tool.RichTextUtils;
import com.psddev.cms.view.PageEntryView;
import com.psddev.dari.db.Database;
import com.psddev.styleguide.training.actor.ActorPageView;
import com.psddev.styleguide.training.actor.ActorPageViewBiographyField;
import com.psddev.styleguide.training.actor.ActorPageViewImageField;

public class ActorPageViewModel extends AbstractContentPageViewModel<Actor> implements ActorPageView,
        PageEntryView {

    @Override
    public Iterable<? extends ActorPageViewBiographyField> getBiography() {

        // This is how you would generate inline RichText (creates a CharSequence)
        //
        // return RichTextUtils.buildInlineHtml(Database.Static.getDefault(), model.getShortBiography(), this::createView);

        return RichTextUtils.buildHtml(Database.Static.getDefault(), model.getLongBiography(), s -> createView(
                ActorPageViewBiographyField.class, s));
    }

    @Override
    public Iterable<? extends ActorPageViewImageField> getImage() {
        return createViews(ActorPageViewImageField.class, model.getImage());
    }

    @Override
    public CharSequence getName() {
        return model.getName();
    }

    @Override
    public CharSequence getPageSubHeading() {
        return null;
    }
}
