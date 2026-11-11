package com.videshkarmakar.vtune2.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.videshkarmakar.vtune2.sharedui.resources.Res
import com.videshkarmakar.vtune2.sharedui.resources.footer_credit
import org.jetbrains.compose.resources.stringResource

/**
 * The line that closes a section: who made the app, set as a colophon rather
 * than as content.
 *
 * It is deliberately the smallest thing on the page — a line of 11 sp muted
 * type, centred, with no rule, card or logo beside it — so it signs the end of
 * a list without becoming a row to read or a thing to tap. The spacing above it
 * is the gap that makes it read as the foot of the section rather than as the
 * last item in it.
 */
@Composable
fun CreditFooter(
    modifier: Modifier = Modifier,
    text: String = stringResource(Res.string.footer_credit),
) {
    Text(
        text = text,
        style = MaterialTheme.typography.labelSmall.copy(
            fontSize = 11.sp,
            fontWeight = FontWeight.W500,
            letterSpacing = 0.7.sp,
        ),
        color = MaterialTheme.colorScheme.onSurfaceVariant.copy(alpha = 0.72f),
        textAlign = TextAlign.Center,
        maxLines = 1,
        modifier = modifier
            .fillMaxWidth()
            .padding(start = 24.dp, end = 24.dp, top = 22.dp, bottom = 22.dp),
    )
}
