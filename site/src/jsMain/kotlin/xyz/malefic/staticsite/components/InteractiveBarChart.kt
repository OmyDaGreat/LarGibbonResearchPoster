package xyz.malefic.staticsite.components

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.HTMLElement
import kotlin.math.roundToInt

@Composable
fun InteractiveBarChart(
    title: String,
    data: List<Pair<String, Double>>,
    color: String = "#3498db",
    modifier: Modifier = Modifier,
    clickToReveal: Boolean = false
) {
    val maxValue = data.maxOfOrNull { it.second } ?: 1.0
    var hoveredIndex by remember { mutableStateOf<Int?>(null) }
    var isRevealed by remember { mutableStateOf(!clickToReveal) }
    
    Div(
        attrs = modifier
            .fillMaxWidth()
            .padding(20.px)
            .borderRadius(15.px)
            .background(Color("#ffffff"))
            .boxShadow(0.px, 4.px, 12.px, 0.px, rgba(0, 0, 0, 0.1))
            .styleModifier {
                property("transition", "transform 0.3s ease")
            }
            .toAttrs()
    ) {
        Div(
            attrs = {
                style {
                    fontSize(20.px)
                    property("font-weight", "bold")
                    marginBottom(20.px)
                    property("text-align", "center")
                    color(Color("#2c3e50"))
                    property("cursor", if (clickToReveal) "pointer" else "default")
                }
                if (clickToReveal) {
                    onClick { isRevealed = !isRevealed }
                    onMouseOver {
                        (it.target as? HTMLElement)?.style?.color = "#3498db"
                    }
                    onMouseOut {
                        (it.target as? HTMLElement)?.style?.color = "#2c3e50"
                    }
                }
            }
        ) {
            Text(title + if (clickToReveal && !isRevealed) " 🔒 (Click to reveal)" else "")
        }
        
        if (isRevealed) {
            Div(
                attrs = {
                    style {
                        property("display", "flex")
                        property("justify-content", "space-around")
                        property("align-items", "flex-end")
                        height(300.px)
                        padding(20.px)
                        property("border-bottom", "2px solid #e0e0e0")
                    }
                }
            ) {
                data.forEachIndexed { index, (label, value) ->
                    InteractiveBar(
                        label = label,
                        value = value,
                        maxValue = maxValue,
                        color = color,
                        isHovered = hoveredIndex == index,
                        onHover = { hoveredIndex = index },
                        onLeave = { hoveredIndex = null }
                    )
                }
            }
        } else {
            Div(
                attrs = {
                    style {
                        property("text-align", "center")
                        padding(60.px)
                        fontSize(48.px)
                    }
                }
            ) {
                Text("🔒")
            }
        }
    }
}

@Composable
private fun InteractiveBar(
    label: String,
    value: Double,
    maxValue: Double,
    color: String,
    isHovered: Boolean,
    onHover: () -> Unit,
    onLeave: () -> Unit
) {
    Div(
        attrs = {
            style {
                property("display", "flex")
                property("flex-direction", "column")
                property("align-items", "center")
                property("gap", "10px")
                property("cursor", "pointer")
                property("transition", "transform 0.3s ease")
                property("transform", if (isHovered) "scale(1.1)" else "scale(1)")
            }
            onMouseEnter { onHover() }
            onMouseLeave { onLeave() }
        }
    ) {
        // Value label (shows on hover)
        Div(
            attrs = {
                style {
                    fontSize(14.px)
                    property("font-weight", "bold")
                    color(Color("#2c3e50"))
                    marginBottom(5.px)
                    property("opacity", if (isHovered) "1" else "0")
                    property("transition", "opacity 0.3s ease")
                    property("transform", if (isHovered) "translateY(0)" else "translateY(10px)")
                }
            }
        ) {
            Text(value.roundToInt().toString())
        }
        
        // Bar
        Div(
            attrs = {
                style {
                    width(if (isHovered) 70.px else 60.px)
                    height(((value / maxValue) * 250).px)
                    property("background", if (isHovered) lightenColor(color) else color)
                    property("border-radius", "5px 5px 0 0")
                    property("animation", "barGrow 1s ease-out")
                    property("transition", "all 0.3s ease")
                    property("box-shadow", if (isHovered) "0 -4px 12px rgba(0,0,0,0.2)" else "none")
                }
            }
        ) {}
        
        // Label
        Div(
            attrs = {
                style {
                    fontSize(12.px)
                    color(Color("#555555"))
                    property("text-align", "center")
                    width(80.px)
                    property("font-weight", if (isHovered) "bold" else "normal")
                    property("transition", "font-weight 0.3s ease")
                }
            }
        ) {
            Text(label)
        }
    }
}

private fun lightenColor(hex: String): String {
    val cleanHex = hex.removePrefix("#")
    val r = cleanHex.substring(0, 2).toInt(16)
    val g = cleanHex.substring(2, 4).toInt(16)
    val b = cleanHex.substring(4, 6).toInt(16)
    
    val newR = minOf(255, (r * 1.2).toInt())
    val newG = minOf(255, (g * 1.2).toInt())
    val newB = minOf(255, (b * 1.2).toInt())
    
    return "#${newR.toString(16).padStart(2, '0')}${newG.toString(16).padStart(2, '0')}${newB.toString(16).padStart(2, '0')}"
}
