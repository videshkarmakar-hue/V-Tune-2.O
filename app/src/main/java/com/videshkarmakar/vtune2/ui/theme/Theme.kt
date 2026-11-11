package com.videshkarmakar.vtune2.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Shapes
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.material3.Typography
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.videshkarmakar.vtune2.R

// Apple Music's signature red. No longer the primary accent, but kept for the
// spots (Replay's rank badge) that want that specific red regardless of theme.
val AccentRed = Color(0xFFFA2D48)

/**
 * The surface ladder.
 *
 * A deep, near-black canvas with three steps above it and a hairline between
 * them — the reference's own arithmetic, read off the cards it draws: the page
 * is the floor, a card sits one step above it, and an icon tile inside that card
 * sits one step above that. Nothing is more than three steps off the page, so
 * nothing has to invent a colour to sit on it.
 */
private val DarkColors = darkColorScheme(
    primary = Color(0xFFFFFFFF),
    onPrimary = Color(0xFF0A0A0C),
    secondary = Color(0xFF8A8A93),
    onSecondary = Color(0xFF0A0A0C),
    tertiary = Color(0xFFB7B7BF),
    background = Color(0xFF0A0A0C),
    onBackground = Color(0xFFF4F4F6),
    surface = Color(0xFF131316),
    onSurface = Color(0xFFF4F4F6),
    surfaceVariant = Color(0xFF1C1C21),
    onSurfaceVariant = Color(0xFF8A8A93),
    surfaceContainerLowest = Color(0xFF07070A),
    surfaceContainerLow = Color(0xFF101013),
    surfaceContainer = Color(0xFF131316),
    surfaceContainerHigh = Color(0xFF1C1C21),
    surfaceContainerHighest = Color(0xFF24242A),
    surfaceTint = Color(0xFFFFFFFF),
    outline = Color(0xFF2A2A31),
    outlineVariant = Color(0xFF1E1E24),
    error = Color(0xFFFF6B6B),
    onError = Color(0xFF0A0A0C),
    scrim = Color(0xFF000000),
)

private val LightColors = lightColorScheme(
    primary = Color(0xFF0A0A0C),
    onPrimary = Color(0xFFFFFFFF),
    secondary = Color(0xFF6C6C74),
    onSecondary = Color(0xFFFFFFFF),
    tertiary = Color(0xFF8A8A93),
    background = Color(0xFFFFFFFF),
    onBackground = Color(0xFF0A0A0C),
    surface = Color(0xFFF7F7F9),
    onSurface = Color(0xFF0A0A0C),
    surfaceVariant = Color(0xFFEFEFF2),
    onSurfaceVariant = Color(0xFF6C6C74),
    surfaceContainerLowest = Color(0xFFFFFFFF),
    surfaceContainerLow = Color(0xFFFAFAFB),
    surfaceContainer = Color(0xFFF4F4F6),
    surfaceContainerHigh = Color(0xFFEFEFF2),
    surfaceContainerHighest = Color(0xFFE9E9ED),
    surfaceTint = Color(0xFF0A0A0C),
    outline = Color(0xFFE1E1E6),
    outlineVariant = Color(0xFFEBEBEF),
    error = Color(0xFFD93A3A),
    onError = Color(0xFFFFFFFF),
    scrim = Color(0xFF000000),
)

/**
 * SF Pro Display, the face Apple Music itself is set in. Only the weights the
 * type scale actually asks for are bundled; Compose synthesises nothing, so a
 * missing weight would silently fall back to the nearest one shipped.
 */
val SFProDisplay = FontFamily(
    Font(R.font.sf_pro_display_regular, FontWeight.W400),
    Font(R.font.sf_pro_display_medium, FontWeight.W500),
    Font(R.font.sf_pro_display_semibold, FontWeight.W600),
    Font(R.font.sf_pro_display_bold, FontWeight.W700),
    Font(R.font.sf_pro_display_heavy, FontWeight.W800),
)

/**
 * The type scale.
 *
 * Small, tight and precise: the reference sets its captions at 11–13sp with
 * generous leading and never lets a line run long, which is what keeps a dark
 * page legible without it becoming a wall of grey. The weights stay heavy —
 * that is the Apple Music backbone — but the sizes come down a step and the
 * tracking comes in tighter to match.
 */
private val VTuneTypography = Typography(
    displayLarge = TextStyle(fontWeight = FontWeight.W800, fontSize = 30.sp, lineHeight = 34.sp, letterSpacing = (-0.6).sp),
    displayMedium = TextStyle(fontWeight = FontWeight.W800, fontSize = 26.sp, lineHeight = 30.sp, letterSpacing = (-0.5).sp),
    displaySmall = TextStyle(fontWeight = FontWeight.W700, fontSize = 22.sp, lineHeight = 26.sp, letterSpacing = (-0.4).sp),
    headlineLarge = TextStyle(fontWeight = FontWeight.W800, fontSize = 25.sp, lineHeight = 29.sp, letterSpacing = (-0.6).sp),
    headlineMedium = TextStyle(fontWeight = FontWeight.W700, fontSize = 19.sp, lineHeight = 24.sp, letterSpacing = (-0.35).sp),
    headlineSmall = TextStyle(fontWeight = FontWeight.W700, fontSize = 17.sp, lineHeight = 22.sp, letterSpacing = (-0.3).sp),
    titleLarge = TextStyle(fontWeight = FontWeight.W700, fontSize = 17.sp, lineHeight = 22.sp, letterSpacing = (-0.25).sp),
    titleMedium = TextStyle(fontWeight = FontWeight.W600, fontSize = 15.sp, lineHeight = 20.sp, letterSpacing = (-0.15).sp),
    titleSmall = TextStyle(fontWeight = FontWeight.W600, fontSize = 13.sp, lineHeight = 17.sp, letterSpacing = (-0.1).sp),
    bodyLarge = TextStyle(fontWeight = FontWeight.W400, fontSize = 15.sp, lineHeight = 21.sp),
    bodyMedium = TextStyle(fontWeight = FontWeight.W400, fontSize = 13.sp, lineHeight = 18.sp),
    bodySmall = TextStyle(fontWeight = FontWeight.W400, fontSize = 12.sp, lineHeight = 16.sp),
    labelLarge = TextStyle(fontWeight = FontWeight.W600, fontSize = 13.sp, lineHeight = 17.sp, letterSpacing = (-0.1).sp),
    labelMedium = TextStyle(fontWeight = FontWeight.W600, fontSize = 11.sp, lineHeight = 14.sp, letterSpacing = 0.1.sp),
    labelSmall = TextStyle(fontWeight = FontWeight.W600, fontSize = 10.sp, lineHeight = 13.sp, letterSpacing = 0.2.sp),
).withFamily(SFProDisplay)

/** Applies [family] to every style in the scale, so nothing is left on Roboto. */
private fun Typography.withFamily(family: FontFamily) = Typography(
    displayLarge = displayLarge.copy(fontFamily = family),
    displayMedium = displayMedium.copy(fontFamily = family),
    displaySmall = displaySmall.copy(fontFamily = family),
    headlineLarge = headlineLarge.copy(fontFamily = family),
    headlineMedium = headlineMedium.copy(fontFamily = family),
    headlineSmall = headlineSmall.copy(fontFamily = family),
    titleLarge = titleLarge.copy(fontFamily = family),
    titleMedium = titleMedium.copy(fontFamily = family),
    titleSmall = titleSmall.copy(fontFamily = family),
    bodyLarge = bodyLarge.copy(fontFamily = family),
    bodyMedium = bodyMedium.copy(fontFamily = family),
    bodySmall = bodySmall.copy(fontFamily = family),
    labelLarge = labelLarge.copy(fontFamily = family),
    labelMedium = labelMedium.copy(fontFamily = family),
    labelSmall = labelSmall.copy(fontFamily = family),
)

/**
 * The corner radii.
 *
 * The reference's own progression: a square icon tile is cut tighter than the
 * card that holds it, and the card is cut tighter than the large sheets behind
 * them. Eight for the smallest of them, then 12, 16, 20 and 28.
 */
private val VTuneShapes = Shapes(
    extraSmall = RoundedCornerShape(8.dp),
    small = RoundedCornerShape(12.dp),
    medium = RoundedCornerShape(16.dp),
    large = RoundedCornerShape(20.dp),
    extraLarge = RoundedCornerShape(28.dp),
)

@Composable
fun VTuneTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    MaterialTheme(
        colorScheme = if (darkTheme) DarkColors else LightColors,
        typography = VTuneTypography,
        shapes = VTuneShapes,
        content = content,
    )
}

/**
 * Draws the status and navigation bar glyphs dark or light.
 *
 * `enableEdgeToEdge()` decides this from the *system* dark-mode setting, which
 * is the wrong input the moment the in-app theme disagrees with it: Light theme
 * on a phone in dark mode left white icons on a white bar, invisible. The bars
 * have to follow the content the app is actually painting. The player supplies
 * its own stable light-icon value and paints contrast behind it; every other
 * surface follows the theme. Hence a parameter rather than reading it here.
 */
@Composable
fun SystemBarIcons(dark: Boolean) {
    val view = LocalView.current
    if (view.isInEditMode) return
    val window = findWindow(view) ?: return
    SideEffect {
        WindowCompat.getInsetsController(window, view).apply {
            isAppearanceLightStatusBars = dark
            isAppearanceLightNavigationBars = dark
        }
    }
}
