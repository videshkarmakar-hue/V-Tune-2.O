package com.videshkarmakar.vtune2.ui.theme

import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

/**
 * The corner radii, as one progression.
 *
 * The reference cuts its corners on a strict ladder: a square icon tile is
 * tighter than the card holding it, the card is tighter than the sheet behind
 * it, and the sheet is tighter than the largest panel on the page. Everything
 * drawn in the app is one of those four, so a row of tiles reads as a row of
 * tiles rather than as a row of unrelated boxes.
 */
val TILE_RADIUS = 12.dp
val CARD_RADIUS = 20.dp
val SHEET_RADIUS = 28.dp

/** The radius of a text field: between a tile and a card, as it should be. */
val FIELD_RADIUS = 14.dp

/** The width of the hairline a card is outlined with. */
val HAIRLINE = 1.dp

/**
 * The brand green.
 *
 * The colour the app's own mark is drawn in — the light green of the V — and
 * so the one colour the product owns. It is what marks the selected tab, and
 * it is deliberately the only saturated thing on a near-black page: everything
 * else is a grey between black and white, so the green reads as a decision
 * rather than as decoration.
 */
val BrandGreen = Color(0xFFA8C29F)

/**
 * The hairline a card is outlined with.
 *
 * One pixel of the surface's own text colour at a tenth — not a border so much
 * as the edge of a surface catching the light. It is what separates a card from
 * the page behind it without the two having to differ in value, which is the
 * whole trick of a near-black palette: the cards are the same colour, and only
 * the line says where one stops.
 */
@Composable
fun cardHairlineColor(): Color = MaterialTheme.colorScheme.onSurface.copy(alpha = 0.08f)
