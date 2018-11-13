package bex.training.actor;

import brightspot.core.image.ImageOption;
import brightspot.core.tool.MediumRichTextToolbar;
import brightspot.core.tool.SmallRichTextToolbar;
import com.psddev.cms.db.Content;
import com.psddev.cms.db.ToolUi;

public class Actor extends Content {

    private String firstName;

    private String lastName;

    private ImageOption image;

    @ToolUi.RichText(toolbar = SmallRichTextToolbar.class)
    private String shortBiography;

    @ToolUi.RichText(toolbar = MediumRichTextToolbar.class)
    private String longBiography;
}
