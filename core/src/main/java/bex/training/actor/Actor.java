package bex.training.actor;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

import bex.training.util.TrainingUtils;
import brightspot.core.image.ImageOption;
import brightspot.core.link.Linkable;
import brightspot.core.permalink.AutoPermalink;
import brightspot.core.promo.PromotableWithOverrides;
import brightspot.core.share.Shareable;
import brightspot.core.slug.Sluggable;
import brightspot.core.tag.Tag;
import brightspot.core.tag.Taggable;
import brightspot.core.tool.MediumRichTextToolbar;
import brightspot.core.tool.RichTextUtils;
import brightspot.core.tool.SmallRichTextToolbar;
import com.psddev.cms.db.Content;
import com.psddev.cms.db.Seo;
import com.psddev.cms.db.ToolUi;
import com.psddev.dari.util.ObjectUtils;
import com.psddev.dari.util.StringUtils;

@Seo.TitleFields("getSeoTitle")
@Seo.DescriptionFields("getSeoDescription")
@Seo.KeywordsFields("getSeoKeywords")
@ToolUi.Main
public class Actor extends Content implements ActorOrCurrentActor,
        AutoPermalink,
        Linkable,
        PromotableWithOverrides,
        Shareable,
        Sluggable,
        Taggable {

    @Indexed
    private String firstName;

    @Indexed
    private String lastName;

    private ImageOption image;

    @ToolUi.Placeholder(dynamicText = "${content.getShortBiographyFallback()}", editable = true)
    @ToolUi.RichText(toolbar = SmallRichTextToolbar.class)
    private String shortBiography;

    @ToolUi.RichText(toolbar = MediumRichTextToolbar.class, inline = false)
    private String longBiography;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public ImageOption getImage() {
        return image;
    }

    public void setImage(ImageOption image) {
        this.image = image;
    }

    public String getShortBiography() {
        return ObjectUtils.firstNonBlank(shortBiography, getShortBiographyFallback());
    }

    public void setShortBiography(String shortBiography) {
        this.shortBiography = shortBiography;
    }

    public String getLongBiography() {
        return longBiography;
    }

    public void setLongBiography(String longBiography) {
        this.longBiography = longBiography;
    }

    //
    // Linkable Implementation
    //
    @Override
    public String getLinkableText() {
        return getName();
    }

    //
    // PromotableWithOverrides Implementation
    //
    @Override
    public String getPromotableTitleFallback() {
        return getName();
    }

    @Override
    public String getPromotableDescriptionFallback() {

        return RichTextUtils.richTextToPlainText(getShortBiography());
    }

    @Override
    public ImageOption getPromotableImageFallback() {
        return getImage();
    }

    //
    // Shareable Implementation
    //
    @Override
    public String getShareableTitleFallback() {
        return getPromotableTitleFallback();
    }

    @Override
    public String getShareableDescriptionFallback() {
        return getPromotableDescriptionFallback();
    }

    @Override
    public ImageOption getShareableImageFallback() {
        return getPromotableImageFallback();
    }

    //
    // Sluggable Implementation
    //
    @Override
    public String getSluggableSlugFallback() {
        return StringUtils.toNormalized(getName());
    }

    //
    // SEO Support.
    //
    @Ignored(false)
    @ToolUi.Hidden
    public String getSeoTitle() {
        return getName();
    }

    @Ignored(false)
    @ToolUi.Hidden
    public String getSeoDescription() {
        return RichTextUtils.richTextToPlainText(getShortBiography());
    }

    @Ignored(false)
    @ToolUi.Hidden
    public Set<String> getSeoKeywords() {

        Set<String> keywords = new HashSet<>();

        keywords.add(getName());

        // Get a set of all tags name keywords.
        keywords.addAll(asTaggableData().getTags().stream()
                .map(Tag::getDisplayName)
                .flatMap(tagDisplayName -> Arrays.stream(tagDisplayName.split(" ")))
                .collect(Collectors.toSet()));

        return keywords;
    }

    @Ignored(false)
    @Indexed
    @ToolUi.DisplayAfter("lastName")
    @ToolUi.DisplayBefore("image")
    public String getName() {

        return TrainingUtils.joinNonBlankStrings(" ",
                firstName,
                lastName);
    }

    @Override
    public String getLabel() {
        return getName();
    }

    public String getShortBiographyFallback() {

        return RichTextUtils.getFirstBodyParagraph(getLongBiography());
    }
}
