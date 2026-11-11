package com.videshkarmakar.vtune2.ui.screens

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.Palette
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.videshkarmakar.vtune2.BuildConfig
import com.videshkarmakar.vtune2.R

/**
 * The About page.
 *
 * What the app is, what it is made of, and where to go for the things that
 * have to be looked up: the version, the licence, the people behind it. It is
 * a page about the product rather than a list of settings, so it stands on its
 * own rather than being another group in [SettingsScreen].
 */
@Composable
fun AboutScreen(
    contentPadding: PaddingValues,
    onOpenDeveloperCredits: () -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(contentPadding),
    ) {
        // The mark, then the name, then the promise under it.
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                painter = painterResource(R.drawable.about_hero),
                contentDescription = null,
                modifier = Modifier.size(148.dp),
            )
        }
        Spacer(Modifier.height(18.dp))
        Text(
            text = stringResource(R.string.about_title),
            style = MaterialTheme.typography.displayMedium.copy(
                fontWeight = FontWeight.W800,
                letterSpacing = 2.sp,
            ),
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(Modifier.height(6.dp))
        Text(
            text = stringResource(R.string.about_tagline),
            style = MaterialTheme.typography.titleMedium,
            color = SettingsAccent,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
        )
        Spacer(Modifier.height(24.dp))
        AboutParagraph(R.string.about_body_1)
        AboutParagraph(R.string.about_body_2)
        AboutParagraph(R.string.about_body_3)

        // ---- App information ------------------------------------------------
        AboutSection(R.string.about_section_app_info) {
            AboutSpecRow(stringResource(R.string.about_application), stringResource(R.string.about_application_value))
            AboutSpecRow(stringResource(R.string.about_version), BuildConfig.VERSION_NAME)
            AboutSpecRow(stringResource(R.string.about_platform), stringResource(R.string.about_platform_value))
            AboutSpecRow(
                stringResource(R.string.about_designed_by),
                stringResource(R.string.about_designed_by_value),
            )
        }

        // ---- About the app --------------------------------------------------
        AboutSection(R.string.about_section_about) {
            AboutParagraph(R.string.about_about_1)
            AboutParagraph(R.string.about_about_2)
            AboutParagraph(R.string.about_about_3)
            AboutParagraph(R.string.about_about_4)
            Spacer(Modifier.height(4.dp))
            Text(
                text = stringResource(R.string.about_pull_quote),
                style = MaterialTheme.typography.headlineSmall,
                color = SettingsAccent,
                textAlign = TextAlign.Center,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 28.dp, vertical = 8.dp),
            )
            AboutParagraph(R.string.about_about_closing)
        }

        // ---- Version and build ----------------------------------------------
        AboutSection(R.string.about_section_version) {
            Text(
                text = stringResource(R.string.about_version_build, BuildConfig.VERSION_NAME),
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 24.dp, vertical = 14.dp),
            )
            Text(
                text = stringResource(R.string.about_copyright_line),
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 24.dp, end = 24.dp, bottom = 16.dp),
            )
        }

        // The page next to this one: who made it.
        AboutLinkRow(
            icon = Icons.Outlined.Palette,
            title = stringResource(R.string.developer_credits),
            description = stringResource(R.string.developer_credits_tagline),
            onClick = onOpenDeveloperCredits,
        )

        // The colophon.
        Spacer(Modifier.height(28.dp))
        Text(
            text = stringResource(R.string.about_footer_name),
            style = MaterialTheme.typography.titleSmall.copy(
                fontWeight = FontWeight.W800,
                letterSpacing = 4.sp,
            ),
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(Modifier.height(4.dp))
        Text(
            text = stringResource(R.string.about_footer_tagline),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
                .padding(bottom = 4.dp),
        )
        Text(
            text = stringResource(R.string.footer_credit),
            style = MaterialTheme.typography.labelSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp)
                .padding(bottom = 32.dp),
        )
    }
}

internal fun openLink(context: android.content.Context, url: String) {
    runCatching {
        context.startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(url)))
    }
}

/** A paragraph of prose, set to the measure a phone reads best at. */
@Composable
internal fun AboutParagraph(resId: Int) {
    Text(
        text = stringResource(resId),
        style = MaterialTheme.typography.bodyLarge,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 6.dp),
    )
}

/**
 * A titled section.
 *
 * A band of the page's own surface with the section's name in it, and the
 * section's body on a block below it — the same arrangement the Settings page
 * uses, so the two read as one app.
 */
@Composable
internal fun AboutSection(titleRes: Int, content: @Composable () -> Unit) {
    Spacer(Modifier.height(28.dp))
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.surfaceVariant),
    ) {
        Text(
            text = stringResource(titleRes),
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.padding(start = 24.dp, end = 24.dp, top = 9.dp, bottom = 9.dp),
        )
    }
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(MaterialTheme.colorScheme.background),
    ) {
        content()
    }
}

/**
 * One line of the app-information table: what it is, and what it is here.
 *
 * Both cells are weighted, and that is the point of the row. A Row measures its
 * unweighted children first, giving each the row's whole width as its limit,
 * and only then hands the weighted ones what is left. Left unweighted, a value
 * as long as the product line ("V-Tune - Lightweight Music. Limitless
 * Experience.") therefore takes the entire row and leaves its label a width of
 * zero, which stacks the label one letter per line and pushes the value out
 * past the right edge. Splitting the width up front keeps the label readable
 * however long a value becomes. Short rows are unaffected: the value is
 * end-aligned either way, so it still sits against the same right margin.
 */
@Composable
internal fun AboutSpecRow(label: String, value: String) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 13.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.weight(0.55f),
        )
        Spacer(Modifier.width(16.dp))
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.W600),
            color = MaterialTheme.colorScheme.onBackground,
            maxLines = 3,
            overflow = TextOverflow.Ellipsis,
            textAlign = TextAlign.End,
            modifier = Modifier.weight(0.45f),
        )
    }
}

/**
 * A row that leads somewhere: the glyph, what it is, and what it is for.
 *
 * The description is under the title rather than beside it, because these
 * rows' titles are short and their descriptions are sentences, and a sentence
 * beside a title reads as a value the row holds rather than as an explanation
 * of the row.
 */
@Composable
internal fun AboutLinkRow(
    icon: androidx.compose.ui.graphics.vector.ImageVector,
    title: String,
    description: String,
    onClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable(onClick = onClick)
            .padding(start = 24.dp, end = 18.dp, top = 13.dp, bottom = 13.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Icon(
            imageVector = icon,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onBackground,
            modifier = Modifier.size(20.dp),
        )
        Spacer(Modifier.width(16.dp))
        Column(Modifier.weight(1f)) {
            Text(
                text = title,
                style = MaterialTheme.typography.bodyLarge.copy(fontWeight = FontWeight.W600),
                color = MaterialTheme.colorScheme.onBackground,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
            )
            Spacer(Modifier.height(2.dp))
            Text(
                text = description,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
            )
        }
        Spacer(Modifier.width(10.dp))
        Icon(
            imageVector = Icons.Outlined.ChevronRight,
            contentDescription = null,
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier.size(18.dp),
        )
    }
}
