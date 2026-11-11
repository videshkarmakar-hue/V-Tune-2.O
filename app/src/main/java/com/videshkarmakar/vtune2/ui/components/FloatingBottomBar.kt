package com.videshkarmakar.vtune2.ui.components

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.AnimationSpec
import androidx.compose.animation.core.snap
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandHorizontally
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkHorizontally
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectHorizontalDragGestures
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.widthIn
import androidx.compose.foundation.layout.windowInsetsPadding
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.union
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberUpdatedState
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.videshkarmakar.vtune2.data.settings.AppSettings
import com.videshkarmakar.vtune2.ui.haptics.Haptic
import com.videshkarmakar.vtune2.ui.haptics.rememberHaptics
import com.videshkarmakar.vtune2.ui.theme.BrandGreen
import com.videshkarmakar.vtune2.ui.theme.HAIRLINE
import com.videshkarmakar.vtune2.ui.theme.cardHairlineColor
import dev.chrisbanes.haze.HazeState
import kotlin.math.abs

data class BottomTab(
    val label: String,
    val icon: ImageVector,
)

/**
 * The gap between the pill's edge and the items inside it.
 *
 * The pill's own padding: enough that the circles inside it are not touching
 * the edge it curves away from.
 */
internal val PILL_INSET = 6.dp

/**
 * How far the floating bars stand off the bottom edge: the system navigation
 * bar's inset, but never less than a gesture bar's.
 *
 * With the gesture hint (or the navigation bar itself) switched off that inset
 * is zero, and the tab bar dropped onto the screen's very edge. It keeps the
 * height it has with the hint showing instead. Three-button navigation is
 * taller than the floor, so it still wins there.
 */
internal val floatingBarInsets: WindowInsets
    @Composable get() = WindowInsets.navigationBars.union(WindowInsets(bottom = GESTURE_BAR_FLOOR))

/** A gesture bar's inset — 45px at the test phone's 3x density. */
private val GESTURE_BAR_FLOOR = 15.dp

/**
 * Each tab's own vertical padding, and the counterweight to [PILL_INSET].
 *
 * The pill has no height of its own — it is whatever its contents come to — so
 * taking 2dp off the inset above would have shortened the whole bar by 4. The
 * same 2dp is added back here instead, which leaves the bar's outer height
 * exactly where it was and moves the boundary rather than the bar. The two
 * numbers are a pair: change one and the bar's height moves unless the other
 * moves against it.
 */
internal val TAB_VERTICAL_PADDING = 9.dp

/**
 * The gap between a tab's glyph and its label, in both bars.
 *
 * The flat bar sets its label beside its circle and does not use this; the
 * glass bar stacks the two and does.
 */
internal val TAB_ICON_LABEL_GAP = 2.dp

/**
 * The spring the selection indicator and the tab glyphs both travel on.
 *
 * Damping 0.72 rather than the 0.5 it was: half-damped overshoots two or three
 * times, and a run of diminishing bounces is what makes a control read as a
 * spring rather than as a material. This settles on the second approach — one
 * soft pass beyond the mark and done — which is the difference between bouncy
 * and alive.
 *
 * Stiffness 320 puts the whole movement at roughly a third of a second, quick
 * enough that the tap and the arrival feel like one event.
 */
internal val GlassSpring = spring<Float>(dampingRatio = 0.72f, stiffness = 320f)

/**
 * The diameter of a destination's circle.
 *
 * Comfortably past the 48dp a touch target has to be, so the circle *is* the
 * target rather than something smaller sitting inside one.
 */
private val NAV_ITEM_SIZE = 52.dp

/** The glyph inside that circle. */
private val NAV_ICON_SIZE = 25.dp

/** The gap between the selected circle and the label it reveals. */
private val NAV_LABEL_GAP = 12.dp

/** How far the label may run before it is cut short. */
private val NAV_LABEL_MAX_WIDTH = 128.dp

/**
 * The pill's own margin from the screen's edges, above the bar's gutters.
 */
private val NAV_PILL_MARGIN = 8.dp

/**
 * The gap between the pill's edge and the circles inside it.
 *
 * Tight, the way the reference draws it: the circle almost fills the pill's
 * height, so the pill reads as a container for the circles rather than as a
 * bar they sit in.
 */
private val NAV_PILL_INSET = 4.dp

/**
 * How long the label takes to open and close.
 *
 * Long enough to be read as a movement rather than a flicker, short enough
 * that the tap and the arrival are still one event.
 */
private const val NAV_LABEL_MILLIS = 190

/**
 * The bottom navigation.
 *
 * A segmented pill: every destination is a circle, and the selected one is
 * filled with the brand green and opens out to show its name beside it, while
 * the rest stay closed and muted. The selected circle does not move to the
 * left of the bar — it stays where it sits in the order and the bar's items
 * make room for it, so the destinations keep their places and the bar reads as
 * the same four things in the same sequence however one of them is chosen.
 *
 * The pill is a flat surface with a hairline round it rather than a blur of
 * whatever is scrolling beneath it: on a near-black page there is nothing to
 * blur that would tell you anything, and a hairline says the same thing for a
 * fraction of the cost.
 */
@Composable
fun FloatingBottomBar(
    tabs: List<BottomTab>,
    selectedIndex: Int,
    onTabSelected: (Int) -> Unit,
    @Suppress("UNUSED_PARAMETER") hazeState: HazeState,
    modifier: Modifier = Modifier,
) {
    val pillShape = RoundedCornerShape(percent = 50)
    val haptics = rememberHaptics()
    val currentOnTabSelected by rememberUpdatedState(onTabSelected)
    // "Reduce animation" drops the easing and not the movement: the label
    // still opens and closes, it simply arrives rather than travels.
    val reduceAnimation by AppSettings.reduceAnimation.collectAsStateWithLifecycle()
    val colorMillis = if (reduceAnimation) 0 else NAV_LABEL_MILLIS

    // The swipe that moves between destinations. A drag of more than half a
    // destination's width changes to the next one, and back if the drag went
    // the other way — one gesture, one move, no scrubbing through the middle.
    var dragTotal by remember { mutableFloatStateOf(0f) }

    Column(
        modifier = modifier
            .windowInsetsPadding(floatingBarInsets)
            .fillMaxWidth(),
    ) {
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = BAR_GUTTER)
                .padding(vertical = NAV_PILL_MARGIN),
            contentAlignment = Alignment.Center,
        ) {
            Row(
                modifier = Modifier
                    // Capped at a phone's width: the bar's items are spread
                    // across it, and run right across a tablet the four
                    // destinations would end up a hand apart with nothing
                    // between them.
                    .widthIn(max = FLOATING_BAR_MAX_WIDTH)
                    .fillMaxWidth()
                    .clip(pillShape)
                    .background(MaterialTheme.colorScheme.surface)
                    .border(HAIRLINE, cardHairlineColor(), pillShape)
                    .padding(NAV_PILL_INSET)
                    .pointerInput(tabs.size) {
                        detectHorizontalDragGestures(
                            onDragCancel = { dragTotal = 0f },
                            onDragEnd = { dragTotal = 0f },
                            onHorizontalDrag = { _, delta ->
                                dragTotal += delta
                                val threshold = size.width / tabs.size / 2f
                                if (abs(dragTotal) > threshold && tabs.size > 1) {
                                    val step = if (dragTotal > 0) -1 else 1
                                    val next = (selectedIndex + step).coerceIn(0, tabs.lastIndex)
                                    if (next != selectedIndex) {
                                        haptics.play(Haptic.Select)
                                        currentOnTabSelected(next)
                                    }
                                    dragTotal = 0f
                                }
                            },
                        )
                    },
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically,
            ) {
                tabs.forEachIndexed { index, tab ->
                    NavItem(
                        tab = tab,
                        selected = index == selectedIndex,
                        colorMillis = colorMillis,
                        onClick = {
                            if (index != selectedIndex) haptics.play(Haptic.Select)
                            currentOnTabSelected(index)
                        },
                    )
                }
            }
        }
    }
}

/**
 * One destination: a circle, and — when it is the chosen one — its name.
 *
 * The circle carries the state, not the label: it is the only part of the row
 * that changes when the selection moves, so the eye finds the selection in one
 * place instead of hunting for it between a filled shape and a word.
 */
@Composable
private fun NavItem(
    tab: BottomTab,
    selected: Boolean,
    colorMillis: Int,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
) {
    val circleColor by animateColorAsState(
        targetValue = if (selected) BrandGreen else MaterialTheme.colorScheme.surfaceContainerHighest,
        animationSpec = tween(colorMillis),
        label = "navCircle",
    )
    val glyphTint by animateColorAsState(
        targetValue = if (selected) MaterialTheme.colorScheme.background else MaterialTheme.colorScheme.onSurfaceVariant,
        animationSpec = tween(colorMillis),
        label = "navGlyph",
    )

    Row(
        modifier = modifier
            // Deliberately not clipped: the row's rounded ends would shave the
            // last characters off the label, which runs to the row's very edge.
            // The circle brings its own round, and the tap has no ripple to
            // contain.
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null,
                onClick = onClick,
            ),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Box(
            modifier = Modifier
                .size(NAV_ITEM_SIZE)
                .clip(CircleShape)
                .background(circleColor),
            contentAlignment = Alignment.Center,
        ) {
            Icon(
                imageVector = tab.icon,
                // The chosen destination says its own name in the label beside
                // it; the closed ones have nothing but the glyph to be named by.
                contentDescription = if (selected) null else tab.label,
                tint = glyphTint,
                modifier = Modifier.size(NAV_ICON_SIZE),
            )
        }
        AnimatedVisibility(
            visible = selected,
            enter = expandHorizontally(
                animationSpec = tween(colorMillis),
                expandFrom = Alignment.Start,
            ) + fadeIn(tween(colorMillis)),
            exit = shrinkHorizontally(
                animationSpec = tween(colorMillis),
                shrinkTowards = Alignment.Start,
            ) + fadeOut(tween(colorMillis)),
        ) {
            Text(
                text = tab.label,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .padding(start = NAV_LABEL_GAP)
                    .widthIn(max = NAV_LABEL_MAX_WIDTH),
            )
        }
    }
}
