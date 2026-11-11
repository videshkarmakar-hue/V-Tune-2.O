package com.videshkarmakar.vtune2.ui.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.videshkarmakar.vtune2.R

/**
 * The Terms & Conditions page.
 *
 * What the app agrees to and what it asks of the people using it. It stands on
 * its own rather than being a section of Settings: terms are read once, in
 * order, from the top — and a page that has to be scrolled past a list of
 * toggles to reach them is not read at all.
 */
@Composable
fun TermsScreen(
    contentPadding: PaddingValues,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState())
            .padding(contentPadding),
    ) {
        Spacer(Modifier.height(24.dp))
        Text(
            text = stringResource(R.string.terms_title),
            style = MaterialTheme.typography.displayMedium.copy(
                fontWeight = FontWeight.W800,
                letterSpacing = 1.sp,
            ),
            color = MaterialTheme.colorScheme.onBackground,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 20.dp),
        )
        Spacer(Modifier.height(6.dp))
        Text(
            text = stringResource(R.string.terms_tagline),
            style = MaterialTheme.typography.titleMedium,
            color = SettingsAccent,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
        )
        Spacer(Modifier.height(22.dp))
        AboutParagraph(R.string.terms_intro)

        TermClause(R.string.terms_clause_tos, R.string.terms_tos_body)
        TermClause(R.string.terms_clause_ip, R.string.terms_ip_body, R.string.terms_ip_body_2)
        TermClause(R.string.terms_clause_music, R.string.terms_music_body, R.string.terms_music_body_2)
        TermClause(R.string.terms_clause_services, R.string.terms_services_body)
        TermClause(R.string.terms_clause_privacy, R.string.terms_privacy_body)
        TermClause(
            R.string.terms_clause_availability,
            R.string.terms_availability_body,
            R.string.terms_availability_body_2,
        )
        TermClause(R.string.terms_clause_liability, R.string.terms_liability_body)
        TermClause(R.string.terms_clause_changes, R.string.terms_changes_body)
        TermClause(R.string.terms_clause_law, R.string.terms_law_body)
        TermClause(R.string.terms_clause_contact, R.string.terms_contact_body)

        // Who stands behind it.
        AboutSection(R.string.terms_section_developer) {
            AboutSpecRow(
                stringResource(R.string.terms_developer_label),
                stringResource(R.string.terms_developer_value),
            )
            AboutSpecRow(
                stringResource(R.string.terms_website_label),
                stringResource(R.string.terms_website_value),
            )
        }

        // The colophon.
        Spacer(Modifier.height(28.dp))
        Text(
            text = stringResource(R.string.terms_footer_name),
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
            text = stringResource(R.string.terms_footer_tagline),
            style = MaterialTheme.typography.bodySmall,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            textAlign = TextAlign.Center,
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 32.dp),
        )
        Spacer(Modifier.height(6.dp))
        Text(
            text = stringResource(R.string.terms_copyright),
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
 * One clause: its name, and the paragraphs under it.
 *
 * The name is the clause's own heading rather than a band, because the terms
 * read as a document — a heading marks a clause the way a heading marks a
 * section of one, and a grey strip between every pair of them would make the
 * page read as a list of settings instead.
 */
@Composable
private fun TermClause(titleRes: Int, vararg bodyRes: Int) {
    Spacer(Modifier.height(20.dp))
    Text(
        text = stringResource(titleRes),
        style = MaterialTheme.typography.titleMedium.copy(fontWeight = FontWeight.W700),
        color = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 24.dp, vertical = 2.dp),
    )
    bodyRes.forEach { AboutParagraph(it) }
}
