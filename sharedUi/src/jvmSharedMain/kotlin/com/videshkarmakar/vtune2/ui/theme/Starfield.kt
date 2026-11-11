package com.videshkarmakar.vtune2.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import kotlin.random.Random

/** One star: where it sits in the field, how big it is, how bright. */
private class Star(val x: Float, val y: Float, val radius: Float, val alpha: Float)

/**
 * The starfield the reference draws behind its pages.
 *
 * A near-black screen is flat, and flat is what makes a dark interface look
 * unfinished rather than quiet: the reference solves it with a scatter of very
 * small, very dim points — not a texture, not a gradient, a handful of specks
 * that read as depth. They are drawn once and never move, because a starfield
 * that drifts is wallpaper, and wallpaper is a different idea from the one
 * this is.
 *
 * The field is fixed by [seed] rather than left to chance, so the same screen
 * has the same stars in it every time it is opened, and a recomposition does
 * not reshuffle them under the reader.
 *
 * Only the dark theme gets one: on a light page the same points would read as
 * dust rather than as sky.
 */
@Composable
fun Modifier.starfield(
    seed: Int = 7,
    stars: Int = 130,
    maxRadius: Dp = 1.1.dp,
    alpha: Float = 0.5f,
): Modifier {
    val isDark = MaterialTheme.colorScheme.background.luminance() < 0.5f
    val field = remember(seed, stars) {
        val random = Random(seed)
        List(stars) {
            Star(
                x = random.nextFloat(),
                y = random.nextFloat(),
                // Most of them are barely there; a handful are a size up, which
                // is what keeps the scatter from reading as a regular pattern.
                radius = 0.3f + random.nextFloat() * 0.7f,
                alpha = 0.22f + random.nextFloat() * 0.78f,
            )
        }
    }
    if (!isDark) return this
    return this.drawBehind {
        val radius = maxRadius.toPx()
        field.forEach { star ->
            drawCircle(
                color = Color.White.copy(alpha = alpha * star.alpha),
                radius = radius * star.radius,
                center = Offset(star.x * size.width, star.y * size.height),
            )
        }
    }
}
