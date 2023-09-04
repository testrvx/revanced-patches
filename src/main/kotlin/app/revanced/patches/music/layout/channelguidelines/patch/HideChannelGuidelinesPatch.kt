package app.revanced.patches.music.layout.carouselshelf.patch

import app.revanced.patcher.annotation.Description
import app.revanced.patcher.annotation.Name
import app.revanced.patcher.data.BytecodeContext
import app.revanced.patcher.patch.BytecodePatch
import app.revanced.patcher.patch.annotations.DependsOn
import app.revanced.patcher.patch.annotations.Patch
import app.revanced.patches.music.utils.annotations.MusicCompatibility
import app.revanced.patches.music.utils.litho.patch.LithoFilterPatch
import app.revanced.patches.music.utils.settings.resource.patch.SettingsPatch
import app.revanced.util.enum.CategoryType
import app.revanced.util.integrations.Constants.MUSIC_ADS_PATH

@Patch
@Name("Hide channel guidelines")
@Description("Hides channel guidelines at the top of comments.")
@DependsOn(
    [
        LithoFilterPatch::class,
        SettingsPatch::class
    ]
)
@MusicCompatibility
class HideChannelGuidelinesPatch : BytecodePatch() {
    override fun execute(context: BytecodeContext) {

        SettingsPatch.addMusicPreference(
            CategoryType.LAYOUT,
            "revanced_hide_channel_guidelines",
            "true"
        )

        LithoFilterPatch.addFilter(FILTER_CLASS_DESCRIPTOR)

    }

    private companion object {
        private const val FILTER_CLASS_DESCRIPTOR =
            "$MUSIC_ADS_PATH/ChannelGuidelinesFilter;"
    }
}
