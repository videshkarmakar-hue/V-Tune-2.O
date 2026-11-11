package com.videshkarmakar.vtune2.ui.screens

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Album
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.PlayCircle
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.videshkarmakar.vtune2.R

/**
 * The accent the legal pages are drawn in — the one saturate colour left in the
 * app, used for the small caps section labels on About, Credits and Terms.
 *
 * The Settings page itself is monochrome: it is a list of switches, and a
 * coloured accent there competed with the artwork the rest of the app is about.
 */
val SettingsAccent = Color(0xFFE0009E)

/**
 * The Settings page's canvas, in the dark theme.
 *
 * The reference is drawn on navy rather than on the app's neutral near-black,
 * and that is most of why it reads the way it does: the rows sit in a cool
 * field instead of in the dark. The palette is scoped to this page — see
 * [settingsColorScheme] — so no other screen inherits it.
 *
 * The wash runs from a slightly lifted tone at the head of the page to a
 * deeper one at its foot. It is the reference's own canvas, measured off it,
 * and it is quiet enough not to read as an effect: the page is one colour that
 * happens to fall away slightly, not a gradient competing with the rows.
 */
internal val SettingsNavyTop = Color(0xFF22344C)
internal val SettingsNavyBottom = Color(0xFF16202F)

/** The mid tone: what a sheet opened over the page, or a lifted surface, is. */
internal val SettingsNavy = Color(0xFF1B2A41)

/** The same page on paper, for the light theme. */
internal val SettingsPaperTop = Color(0xFFFAFBFD)
internal val SettingsPaperBottom = Color(0xFFF2F5F9)
internal val SettingsPaper = Color(0xFFF6F8FB)

/**
 * The palette the Settings page draws itself in.
 *
 * Everything the page puts on screen comes from one scheme — the rows, the
 * search field, the segmented track, the sheets it opens — so a navy page is
 * not filled with controls cut out of the app's near-black. It is derived from
 * the app's scheme rather than written out, so the theme's own choices about
 * the parts this page never colours still hold.
 */
@Composable
internal fun settingsColorScheme(): ColorScheme {
    val base = MaterialTheme.colorScheme
    return if (base.background.luminance() < 0.5f) {
        base.copy(
            background = SettingsNavy,
            onBackground = Color(0xFFFFFFFF),
            surface = SettingsNavy,
            onSurface = Color(0xFFFFFFFF),
            surfaceVariant = Color(0xFF24344E),
            onSurfaceVariant = Color(0xFF8C99AD),
            surfaceContainerLowest = Color(0xFF152238),
            surfaceContainerLow = Color(0xFF192740),
            surfaceContainer = Color(0xFF1F2F49),
            surfaceContainerHigh = Color(0xFF24344E),
            surfaceContainerHighest = Color(0xFF2A3B57),
            outline = Color(0xFF30425E),
            outlineVariant = Color(0xFF273850),
            primary = Color(0xFFFFFFFF),
            onPrimary = SettingsNavy,
            error = Color(0xFFFF7C7C),
            onError = Color(0xFF2B0B0B),
        )
    } else {
        base.copy(
            background = SettingsPaper,
            onBackground = Color(0xFF101828),
            surface = SettingsPaper,
            onSurface = Color(0xFF101828),
            surfaceVariant = Color(0xFFECF0F6),
            onSurfaceVariant = Color(0xFF5F6B7A),
            surfaceContainerLowest = Color(0xFFFFFFFF),
            surfaceContainerLow = Color(0xFFF9FAFC),
            surfaceContainer = SettingsPaper,
            surfaceContainerHigh = Color(0xFFECF0F6),
            surfaceContainerHighest = Color(0xFFE4E9F1),
            outline = Color(0xFFD9DFE8),
            outlineVariant = Color(0xFFE4E9F1),
            primary = Color(0xFF101828),
            onPrimary = Color(0xFFFFFFFF),
            error = Color(0xFFD92D20),
            onError = Color(0xFFFFFFFF),
        )
    }
}

/**
 * The canvas itself, painted from the top of the window to the bottom of it.
 *
 * Two stops and nothing else: the page's colour does not change from column to
 * column, so the wash is a single vertical one.
 */
@Composable
internal fun settingsCanvasBrush(): Brush =
    if (MaterialTheme.colorScheme.background.luminance() < 0.5f) {
        Brush.verticalGradient(listOf(SettingsNavyTop, SettingsNavyBottom))
    } else {
        Brush.verticalGradient(listOf(SettingsPaperTop, SettingsPaperBottom))
    }

/**
 * The colour the shared top and bottom scrims paint while the Settings page is
 * showing, at the edge they touch.
 *
 * The bar's haze is drawn above the page from the app's scheme, so on its own
 * it would band the app's near-black across the top of a navy screen. Reading
 * it from the page's own wash keeps the two one colour at each edge: the head
 * of the page at the top bar, the foot of it under the tab bar.
 */
@Composable
internal fun settingsCanvasTop(): Color =
    if (MaterialTheme.colorScheme.background.luminance() < 0.5f) SettingsNavyTop else SettingsPaperTop

@Composable
internal fun settingsCanvasBottom(): Color =
    if (MaterialTheme.colorScheme.background.luminance() < 0.5f) SettingsNavyBottom else SettingsPaperBottom

/**
 * The Settings page's name, set as a page title rather than as a bar title:
 * large, left-aligned, and the first thing under the bar.
 *
 * The bar's own centre title is off on every page, so this is the only place
 * the page is named and it can afford the weight.
 */
@Composable
internal fun SettingsPageTitle(
    title: String,
    modifier: Modifier = Modifier,
) {
    Text(
        text = title,
        style = MaterialTheme.typography.displayLarge.copy(
            fontWeight = FontWeight.W700,
            letterSpacing = (-0.8).sp,
        ),
        color = MaterialTheme.colorScheme.onBackground,
        maxLines = 1,
        overflow = TextOverflow.Ellipsis,
        modifier = modifier,
    )
}

/**
 * The switch.
 *
 * A small pill with a thumb that slides across it, rather than Material's own
 * switch: the two are the same idea drawn at different scales, and this one is
 * the size the reference sets its rows at, so a row's height is its row's
 * height and the thumb is not the loudest thing on the line.
 *
 * On is the theme's ink — white on a dark page, near-black on a light one —
 * which is how the reference draws it: no accent colour on the control at all.
 */
@Composable
internal fun SettingsSwitch(
    checked: Boolean,
    onCheckedChange: (Boolean) -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
) {
    val track by animateColorAsState(
        targetValue = if (checked) {
            MaterialTheme.colorScheme.primary
        } else {
            MaterialTheme.colorScheme.outline
        },
        animationSpec = tween(180),
        label = "switchTrack",
    )
    val thumb by animateColorAsState(
        targetValue = if (checked) {
            MaterialTheme.colorScheme.background
        } else {
            MaterialTheme.colorScheme.onSurfaceVariant
        },
        animationSpec = tween(180),
        label = "switchThumbColour",
    )
    val travel by animateFloatAsState(
        targetValue = if (checked) 1f else 0f,
        animationSpec = spring(dampingRatio = 0.85f, stiffness = 600f),
        label = "switchThumb",
    )
    val interactionSource = remember { MutableInteractionSource() }
    Box(
        modifier = modifier
            .size(SWITCH_WIDTH, SWITCH_HEIGHT)
            .clip(RoundedCornerShape(percent = 50))
            .background(track.copy(alpha = if (enabled) 1f else 0.4f))
            .clickable(
                enabled = enabled,
                interactionSource = interactionSource,
                indication = null,
                onClick = { onCheckedChange(!checked) },
            ),
    ) {
        Box(
            modifier = Modifier
                .padding(horizontal = 2.dp, vertical = 2.dp)
                .offset(x = (SWITCH_WIDTH - SWITCH_THUMB - 4.dp) * travel)
                .size(SWITCH_THUMB)
                .clip(CircleShape)
                .background(thumb.copy(alpha = if (enabled) 1f else 0.4f)),
        )
    }
}

private val SWITCH_WIDTH = 34.dp
private val SWITCH_HEIGHT = 20.dp
private val SWITCH_THUMB = 16.dp

/**
 * The strip that sits above the Settings page's own title: the portrait, the
 * name, and the five places to find him.
 *
 * It is a band, not a section: one row, no heading, and the links are its
 * smallest part — they are the way through to somewhere else, not the point of
 * the line, so they stay a row of hairline glyphs rather than becoming a second
 * block of settings. Everything is set below the size the page's title is set
 * at, so the title still opens the page and the strip only signs it.
 */
@Composable
internal fun DeveloperStrip(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    Row(
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 20.dp, end = 14.dp, top = 14.dp, bottom = 6.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = painterResource(R.drawable.developer_avatar),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(32.dp)
                .clip(CircleShape),
        )
        Spacer(Modifier.width(10.dp))
        Text(
            text = stringResource(R.string.developer_credits_name),
            style = MaterialTheme.typography.titleSmall,
            fontSize = 13.sp,
            fontWeight = FontWeight.W600,
            letterSpacing = 0.2.sp,
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.weight(1f),
        )
        Spacer(Modifier.width(4.dp))
        DeveloperLink(Icons.Outlined.Language, R.string.developer_credits_website_title) {
            openLink(context, DeveloperLinks.WEBSITE)
        }
        DeveloperLink(Icons.Outlined.Album, R.string.developer_credits_spotify_title) {
            openLink(context, DeveloperLinks.SPOTIFY)
        }
        DeveloperLink(Icons.Outlined.CameraAlt, R.string.developer_credits_instagram_title) {
            openLink(context, DeveloperLinks.INSTAGRAM)
        }
        DeveloperLink(Icons.Outlined.PlayCircle, R.string.developer_credits_youtube_title) {
            openLink(context, DeveloperLinks.YOUTUBE)
        }
        DeveloperLink(Icons.Outlined.Groups, R.string.developer_credits_facebook_title) {
            openLink(context, DeveloperLinks.FACEBOOK)
        }
    }
}

/** One of the strip's links: a glyph, and the target it names for a screen reader. */
@Composable
private fun DeveloperLink(
    icon: ImageVector,
    labelRes: Int,
    onClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .size(28.dp)
            .clip(CircleShape)
            .clickable(onClick = onClick),
        contentAlignment = Alignment.Center,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = stringResource(labelRes),
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(16.dp),
        )
    }
}
