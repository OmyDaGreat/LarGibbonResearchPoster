package xyz.malefic.staticsite.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.css.cursor
import com.varabyte.kobweb.compose.css.fontWeight
import com.varabyte.kobweb.compose.css.textAlign
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.background
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.boxShadow
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.marginBottom
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.rgba
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.HTMLElement

private val activityColorMap =
    mapOf(
        "Relaxing" to "#FF6B6B",
        "Eating" to "#4ECDC4",
        "Hanging" to "#FFE66D",
        "Playing with Plants" to "#95E1D3",
        "Brachiation" to "#A8E6CF",
        "Walking" to "#FF8B94",
        "Climbing" to "#C7CEEA",
    )

@Composable
fun InteractivePieChart(
    title: String,
    data: List<Pair<String, Double>>,
    modifier: Modifier = Modifier,
    hidden: Boolean = false,
) {
    var selectedIndex by remember { mutableStateOf<Int?>(null) }
    var isRevealed by remember { mutableStateOf(!hidden) }
    val colors =
        remember(data) {
            data.map { (label, _) ->
                activityColorMap[label] ?: generateFallbackColor(label)
            }
        }

    Div(
        attrs =
            modifier
                .fillMaxWidth()
                .padding(20.px)
                .borderRadius(15.px)
                .background(Color("#ffffff"))
                .boxShadow(0.px, 4.px, 12.px, 0.px, rgba(0, 0, 0, 0.1))
                .styleModifier {
                    property("animation", "fadeIn 0.8s ease-in-out")
                    property("transition", "transform 0.3s ease")
                }.toAttrs {
                    onMouseOver {
                        (it.target as? HTMLElement)?.style?.transform = "scale(1.02)"
                    }
                    onMouseOut {
                        (it.target as? HTMLElement)?.style?.transform = "scale(1)"
                    }
                },
    ) {
        Div(
            attrs = {
                style {
                    fontSize(20.px)
                    fontWeight(FontWeight.Bold)
                    marginBottom(15.px)
                    textAlign(TextAlign.Center)
                    color(Color("#2c3e50"))
                    cursor(if (hidden) Cursor.Pointer else Cursor.Default)
                }
                if (hidden) {
                    onClick { isRevealed = !isRevealed }
                    onMouseOver {
                        (it.target as? HTMLElement)?.style?.color = "#3498db"
                    }
                    onMouseOut {
                        (it.target as? HTMLElement)?.style?.color = "#2c3e50"
                    }
                }
            },
        ) {
            Text(title + if (hidden && !isRevealed) " 🔒 (Click to reveal)" else "")
        }

        if (isRevealed) {
            val total = data.sumOf { it.second }
            var currentAngle = 0.0

            // Build the conic-gradient string
            val gradientStops =
                buildList {
                    data.forEachIndexed { index, (_, value) ->
                        val percentage = (value / total) * 100
                        val startAngle = currentAngle
                        currentAngle += (percentage * 3.6)
                        val endAngle = currentAngle
                        val color = colors.getOrElse(index) { "#cccccc" }
                        val opacity = if (selectedIndex == null || selectedIndex == index) "1" else "0.5"
                        val rgbColor = hexToRgb(color)

                        add("rgba($rgbColor, $opacity) ${startAngle}deg")
                        add("rgba($rgbColor, $opacity) ${endAngle}deg")
                    }
                }.joinToString(", ")

            val backgroundValue = "conic-gradient(from 0deg, $gradientStops)"

            Div(
                attrs =
                    Modifier
                        .width(250.px)
                        .height(250.px)
                        .styleModifier {
                            property("margin", "0 auto")
                            property("position", "relative")
                        }.toAttrs(),
            ) {
                // The pie chart itself
                Div(
                    attrs =
                        Modifier
                            .width(250.px)
                            .height(250.px)
                            .borderRadius(50.percent)
                            .styleModifier {
                                property("background", backgroundValue)
                                property("transition", "all 0.3s ease")
                            }.toAttrs(),
                ) {}

                // Hover zones for each slice with tooltips
                currentAngle = 0.0
                data.forEachIndexed { index, (label, value) ->
                    val percentage = (value / total) * 100
                    val startAngle = currentAngle
                    currentAngle += (percentage * 3.6)

                    PieSliceWithTooltip(
                        label = label,
                        percentage = percentage,
                        startAngle = startAngle,
                        color = colors.getOrElse(index) { "#cccccc" },
                        isHovered = selectedIndex == index,
                        onHover = { selectedIndex = index },
                        onLeave = { selectedIndex = null },
                    )
                }
            }
        } else {
            Div(
                attrs = {
                    style {
                        property("text-align", "center")
                        padding(40.px)
                        fontSize(48.px)
                    }
                },
            ) {
                Text("🔒")
            }
        }
    }
}

@Composable
private fun PieSliceWithTooltip(
    label: String,
    percentage: Double,
    startAngle: Double,
    color: String,
    isHovered: Boolean,
    onHover: () -> Unit,
    onLeave: () -> Unit,
) {
    val endAngle = startAngle + (percentage * 3.6)

    // Calculate the middle angle for tooltip positioning
    val midAngle = startAngle + (percentage * 3.6 / 2)
    val rad = kotlin.math.PI * (midAngle - 90) / 180.0

    // Position tooltip at 70% of the radius from center
    val tooltipDistance = 35.0 // 70% of 50% radius
    val tooltipX = 50.0 + tooltipDistance * kotlin.math.cos(rad)
    val tooltipY = 50.0 + tooltipDistance * kotlin.math.sin(rad)

    // Hover zone
    Div(
        attrs =
            Modifier
                .width(250.px)
                .height(250.px)
                .styleModifier {
                    property("position", "absolute")
                    property("top", "0")
                    property("left", "0")
                    property("cursor", "pointer")
                    property("clip-path", buildClipPath(startAngle, endAngle))
                }.toAttrs {
                    onMouseEnter { onHover() }
                    onMouseLeave { onLeave() }
                },
    ) {}

    // Tooltip
    if (isHovered) {
        Div(
            attrs =
                Modifier
                    .styleModifier {
                        property("position", "absolute")
                        property("left", "$tooltipX%")
                        property("top", "$tooltipY%")
                        property("transform", "translate(-50%, -50%)")
                        property("background", "rgba(44, 62, 80, 0.95)")
                        property("color", "#ffffff")
                        property("padding", "8px 12px")
                        property("border-radius", "6px")
                        property("font-size", "13px")
                        property("font-weight", "bold")
                        property("white-space", "nowrap")
                        property("pointer-events", "none")
                        property("z-index", "100")
                        property("box-shadow", "0 2px 8px rgba(0,0,0,0.3)")
                        property("animation", "fadeIn 0.2s ease-in")
                    }.toAttrs(),
        ) {
            val roundedPercentage = (percentage * 10).toInt() / 10.0
            Text("$label\n$roundedPercentage%")
        }
    }
}

private fun buildClipPath(
    startAngle: Double,
    endAngle: Double,
): String {
    val centerX = 50.0
    val centerY = 50.0
    val radius = 50.0

    val points = mutableListOf("$centerX% $centerY%")

    val steps = maxOf(2, ((endAngle - startAngle) / 5).toInt())
    for (i in 0..steps) {
        val angle = startAngle + ((endAngle - startAngle) * i / steps)
        val rad = kotlin.math.PI * (angle - 90) / 180.0
        val x = centerX + radius * kotlin.math.cos(rad)
        val y = centerY + radius * kotlin.math.sin(rad)
        points.add("$x% $y%")
    }

    return "polygon(${points.joinToString(", ")})"
}

private fun hexToRgb(hex: String): String {
    val cleanHex = hex.removePrefix("#")
    val r = cleanHex.take(2).toInt(16)
    val g = cleanHex.substring(2, 4).toInt(16)
    val b = cleanHex.substring(4, 6).toInt(16)
    return "$r, $g, $b"
}

private fun generateFallbackColor(label: String): String {
    console.log("Generating fallback color for label: $label")
    // Generate a consistent color based on the label's hash
    val hash = label.hashCode()
    val hue = (hash % 360).toDouble() / 360.0
    val saturation = 0.65 + ((hash / 360) % 3) * 0.1
    val lightness = 0.5 + ((hash / 1080) % 2) * 0.1
    return hslToHex(hue.coerceIn(0.0, 1.0), saturation, lightness)
}

private fun hslToHex(
    h: Double,
    s: Double,
    l: Double,
): String {
    val c = (1 - kotlin.math.abs(2 * l - 1)) * s
    val x = c * (1 - kotlin.math.abs(((h * 6) % 2) - 1))
    val m = l - c / 2

    val (r1, g1, b1) =
        when ((h * 6).toInt()) {
            0 -> Triple(c, x, 0.0)
            1 -> Triple(x, c, 0.0)
            2 -> Triple(0.0, c, x)
            3 -> Triple(0.0, x, c)
            4 -> Triple(x, 0.0, c)
            else -> Triple(c, 0.0, x)
        }

    val r = ((r1 + m) * 255).toInt().coerceIn(0, 255)
    val g = ((g1 + m) * 255).toInt().coerceIn(0, 255)
    val b = ((b1 + m) * 255).toInt().coerceIn(0, 255)

    return "#${r.toString(16).padStart(2, '0')}${g.toString(16).padStart(2, '0')}${b.toString(16).padStart(2, '0')}"
}
