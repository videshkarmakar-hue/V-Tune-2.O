package com.videshkarmakar.vtune2.ui.components

import com.videshkarmakar.vtune2.sharedui.resources.*
import com.videshkarmakar.vtune2.ui.theme.FIELD_RADIUS
import org.jetbrains.compose.resources.stringResource
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Close
import androidx.compose.material.icons.rounded.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.SolidColor
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.Alignment
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController

/**
 * The search field used at the top of the search page — a hint that gives way
 * to typed text, a clear button once there is something to clear, and a
 * magnifier that runs the search. Reused wherever else the app needs the same
 * "search this" affordance, such as filtering the settings list.
 *
 * The magnifier sits at the *end* of the field rather than the head of it: the
 * reference puts it there, and it reads as the field's own action rather than
 * as an ornament before the text — the search is something you finish, not
 * something you start with.
 */
@Composable
fun SearchField(
    query: String,
    onQueryChange: (String) -> Unit,
    onSubmit: () -> Unit,
    focusRequester: FocusRequester = remember { FocusRequester() },
    /** What the empty field says it is for — the one part that changes per screen. */
    placeholder: String = stringResource(Res.string.search_hint),
    modifier: Modifier = Modifier,
) {
    val focusManager = LocalFocusManager.current
    val keyboardController = LocalSoftwareKeyboardController.current
    // Both ways of saying "search this" do the same two things, so they're
    // written once here rather than twice.
    val submit = {
        onSubmit()
        focusManager.clearFocus()
    }
    Row(
        modifier = modifier
            .fillMaxWidth()
            // Fixed height prevents the row from growing when text is entered
            .height(46.dp)
            .background(MaterialTheme.colorScheme.surfaceVariant, RoundedCornerShape(FIELD_RADIUS))
            .padding(start = 16.dp, end = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(Modifier.weight(1f)) {
            if (query.isEmpty()) {
                Text(
                    text = placeholder,
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    // A narrow field (the phone's, beside the account photo)
                    // must cut the hint short, not wrap it out of the field.
                    maxLines = 1,
                    overflow = TextOverflow.Ellipsis,
                )
            }
            BasicTextField(
                value = query,
                onValueChange = onQueryChange,
                singleLine = true,
                textStyle = MaterialTheme.typography.bodyMedium.copy(
                    color = MaterialTheme.colorScheme.onBackground,
                ),
                cursorBrush = SolidColor(MaterialTheme.colorScheme.primary),
                keyboardOptions = KeyboardOptions(imeAction = ImeAction.Search),
                keyboardActions = KeyboardActions(onSearch = { submit() }),
                modifier = Modifier
                    .fillMaxWidth()
                    .focusRequester(focusRequester),
            )
        }
        // Emptying the field is also how the recent searches are got back to,
        // so it needs to be one tap rather than a held backspace.
        if (query.isNotEmpty()) {
            Box(
                modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape)
                    .clickable {
                        onQueryChange("")
                        // Clearing is an edit, not a dismissal: leave the user
                        // ready to immediately type their next search.
                        focusRequester.requestFocus()
                        keyboardController?.show()
                    },
                contentAlignment = Alignment.Center,
            ) {
                Icon(
                    Icons.Rounded.Close,
                    contentDescription = stringResource(Res.string.clear_search),
                    tint = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.size(16.dp),
                )
            }
            Spacer(Modifier.width(4.dp))
        }
        // The search button. It reads as one — a magnifier is the search
        // affordance on every platform — and pressing it is the only thing that
        // runs a search, so leaving it decorative would mean the keyboard's own
        // key was the single way in.
        Icon(
            Icons.Rounded.Search,
            contentDescription = stringResource(Res.string.search),
            tint = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier = Modifier
                .size(34.dp)
                .clip(CircleShape)
                .clickable(enabled = query.isNotBlank(), onClick = submit)
                .padding(7.dp),
        )
    }
}
