package com.videshkarmakar.vtune2.ui.screens

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
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Album
import androidx.compose.material.icons.outlined.CameraAlt
import androidx.compose.material.icons.outlined.ChevronRight
import androidx.compose.material.icons.outlined.Gavel
import androidx.compose.material.icons.outlined.Groups
import androidx.compose.material.icons.outlined.Language
import androidx.compose.material.icons.outlined.PlayCircle
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
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
 * Where the developer's own places are — the five the Credits page lists and
 * the Settings strip links to, kept in one place so the two cannot drift.
 */
internal object DeveloperLinks {
    const val WEBSITE = "https://videshkarmakar.carrd.co/"
    const val SPOTIFY = "https://open.spotify.com/artist/2eJEzi0mzJB60zVJf0OjWP"
    const val INSTAGRAM = "https://www.instagram.com/code.red_videsh/"
    const val YOUTUBE = "https://www.youtube.com/@videshkarmakar2000"
    const val FACEBOOK = "https://www.facebook.com/gfx.videsh"
}

/**
 * The Developer Credits page.
 *
 * Who made it, what they made it with, and where to find them. It stands
 * beside the About page rather than inside it: one is about the software and
 * the other is about the person, and folding the two together would bury the
 * second under the first.
 */
@Composable
fun DeveloperCreditsScreen(
    contentPadding: PaddingValues,
    onOpenTerms: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val context = LocalContext.current
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(contentPadding),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(top = 24.dp),
            contentAlignment = Alignment.Center,
        ) {
            Image(
                painter = painterResource(R.drawable.developer_hero),
                contentDescription = null,
                modifier = Modifier.size(148.dp),
            )
        }
        Spacer(Modifier.height(18.dp))
        Text(
            text = stringResource(R.string.developer_credits_title),
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
            text = stringResource(R.string.developer_credits_tagline),
            style = MaterialTheme.typography.titleMedium,
            color = SettingsAccent,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
        )
        Spacer(Modifier.height(22.dp))
        Text(
            text = stringResource(R.string.developer_credits_name),
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(Modifier.height(3.dp))
        Text(
            text = stringResource(R.string.developer_credits_role),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier.fillMaxWidth(),
        )
        Spacer(Modifier.height(22.dp))
        AboutParagraph(R.string.developer_credits_body_1)
        AboutParagraph(R.string.developer_credits_body_2)
        AboutParagraph(R.string.developer_credits_body_3)

        // ---- Connect with the developer ---------------------------------------
        AboutSection(R.string.developer_credits_section_connect) {
            AboutParagraph(R.string.developer_credits_connect_intro)
            CreditsLinkRow(
                icon = Icons.Outlined.Language,
                title = stringResource(R.string.developer_credits_website_title),
                value = stringResource(R.string.developer_credits_website_value),
                onClick = { openLink(context, DeveloperLinks.WEBSITE) },
            )
            CreditsLinkRow(
                icon = Icons.Outlined.Album,
                title = stringResource(R.string.developer_credits_spotify_title),
                value = stringResource(R.string.developer_credits_spotify_value),
                onClick = { openLink(context, DeveloperLinks.SPOTIFY) },
            )
            CreditsLinkRow(
                icon = Icons.Outlined.CameraAlt,
                title = stringResource(R.string.developer_credits_instagram_title),
                value = stringResource(R.string.developer_credits_instagram_value),
                onClick = { openLink(context, DeveloperLinks.INSTAGRAM) },
            )
            CreditsLinkRow(
                icon = Icons.Outlined.PlayCircle,
                title = stringResource(R.string.developer_credits_youtube_title),
                value = stringResource(R.string.developer_credits_youtube_value),
                onClick = { openLink(context, DeveloperLinks.YOUTUBE) },
            )
            CreditsLinkRow(
                icon = Icons.Outlined.Groups,
                title = stringResource(R.string.developer_credits_facebook_title),
                value = stringResource(R.string.developer_credits_facebook_value),
                onClick = { openLink(context, DeveloperLinks.FACEBOOK) },
            )
        }

        // ---- Development and design ------------------------------------------
        AboutSection(R.string.developer_credits_section_development) {
            AboutSpecRow(
                stringResource(R.string.developer_credits_product),
                stringResource(R.string.developer_credits_product_value),
            )
            AboutSpecRow(
                stringResource(R.string.developer_credits_designed_by),
                stringResource(R.string.developer_credits_designed_by_value),
            )
            AboutSpecRow(
                stringResource(R.string.developer_credits_design),
                stringResource(R.string.developer_credits_design_value),
            )
            AboutSpecRow(
                stringResource(R.string.developer_credits_platform),
                stringResource(R.string.developer_credits_platform_value),
            )
            AboutSpecRow(
                stringResource(R.string.developer_credits_version),
                BuildConfig.VERSION_NAME,
            )
        }

        // ---- Acknowledgement ---------------------------------------------------
        AboutSection(R.string.developer_credits_section_acknowledgement) {
            AboutParagraph(R.string.developer_credits_ack_1)
            AboutParagraph(R.string.developer_credits_ack_2)
            AboutParagraph(R.string.developer_credits_ack_3)
        }

        // The page next to this one: the terms it comes with.
        CreditsLinkRow(
            icon = Icons.Outlined.Gavel,
            title = stringResource(R.string.terms),
            value = stringResource(R.string.terms_tagline),
            onClick = onOpenTerms,
        )

        // The colophon.
        Spacer(Modifier.height(28.dp))
        Text(
            text = stringResource(R.string.developer_credits_footer_name),
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
            text = stringResource(R.string.developer_credits_footer_tagline),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
        )
        Spacer(Modifier.height(6.dp))
        Text(
            text = stringResource(R.string.developer_credits_copyright),
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

/**
 * A row that leads somewhere: the place, and the handle it is at.
 *
 * The handle is what the row is for — the place's name alone would leave the
 * reader to go and find it, and half of these are only reachable by typing the
 * handle in.
 */
@Composable
private fun CreditsLinkRow(
    icon: ImageVector,
    title: String,
    value: String,
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
                text = value,
                style = MaterialTheme.typography.bodySmall,
                color = MaterialTheme.colorScheme.onSurfaceVariant,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
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
