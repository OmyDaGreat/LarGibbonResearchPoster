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

@Composable
fun InteractivePieChart(
    title: String,
    data: List<Pair<String, Double>>,
    colors: List<String>,
    modifier: Modifier = Modifier,
    hidden: Boolean = false,
) {
    var selectedIndex by remember { mutableStateOf<Int?>(null) }
    var isRevealed by remember { mutableStateOf(!hidden) }

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
                    property("font-weight", "bold")
                    marginBottom(15.px)
                    property("text-align", "center")
                    color(Color("#2c3e50"))
                    property("cursor", if (hidden) "pointer" else "default")
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

            Div(
                attrs =
                    Modifier
                        .width(250.px)
                        .height(250.px)
                        .borderRadius(50.percent)
                        .styleModifier {
                            property("margin", "0 auto")
                            property("position", "relative")
                            property(
                                "background",
                                data
                                    .mapIndexed { index, (_, value) ->
                                        val percentage = (value / total) * 100
                                        val startAngle = currentAngle
                                        currentAngle += (percentage * 3.6)
                                        val opacity = if (selectedIndex == null || selectedIndex == index) "1" else "0.5"
                                        "conic-gradient(from ${startAngle}deg, rgba(${hexToRgb(
                                            colors.getOrElse(index) { "#cccccc" },
                                        )}, $opacity) 0deg ${percentage * 3.6}deg)"
                                    }.joinToString(", "),
                            )
                            property("transition", "opacity 0.3s ease")
                            property("cursor", "pointer")
                        }.toAttrs(),
            ) {
                // Center circle for better visuals
                Div(
                    attrs =
                        Modifier
                            .width(80.px)
                            .height(80.px)
                            .borderRadius(50.percent)
                            .background(Color("#ffffff"))
                            .styleModifier {
                                property("position", "absolute")
                                property("top", "50%")
                                property("left", "50%")
                                property("transform", "translate(-50%, -50%)")
                                property("display", "flex")
                                property("align-items", "center")
                                property("justify-content", "center")
                                property("font-size", "32px")
                            }.toAttrs(),
                ) {
                    Text("🎵")
                }
            }

            Div(
                attrs =
                    Modifier
                        .margin(top = 20.px)
                        .styleModifier {
                            property("display", "flex")
                            property("flex-direction", "column")
                            property("gap", "8px")
                        }.toAttrs(),
            ) {
                data.forEachIndexed { index, (label, value) ->
                    InteractiveLegendItem(
                        label = label,
                        value = value,
                        total = total,
                        color = colors.getOrElse(index) { "#cccccc" },
                        isSelected = selectedIndex == index,
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
private fun InteractiveLegendItem(
    label: String,
    value: Double,
    total: Double,
    color: String,
    isSelected: Boolean,
    onHover: () -> Unit,
    onLeave: () -> Unit,
) {
    Div(
        attrs = {
            style {
                property("display", "flex")
                property("align-items", "center")
                property("gap", "10px")
                property("cursor", "pointer")
                property("transition", "all 0.3s ease")
                property("transform", if (isSelected) "scale(1.05)" else "scale(1)")
                property("font-weight", if (isSelected) "bold" else "normal")
            }
            onMouseEnter { onHover() }
            onMouseLeave { onLeave() }
        },
    ) {
        Div(
            attrs = {
                style {
                    width(20.px)
                    height(20.px)
                    property("border-radius", "3px")
                    property("background", color)
                    property("transition", "transform 0.3s ease")
                    property("transform", if (isSelected) "scale(1.3)" else "scale(1)")
                }
            },
        ) {}
        Div(
            attrs = {
                style {
                    fontSize(14.px)
                    color(Color("#555555"))
                }
            },
        ) {
            val percentage = ((value / total) * 1000).toInt() / 10.0
            Text("$label: $percentage%")
        }
    }
}

private fun hexToRgb(hex: String): String {
    val cleanHex = hex.removePrefix("#")
    val r = cleanHex.substring(0, 2).toInt(16)
    val g = cleanHex.substring(2, 4).toInt(16)
    val b = cleanHex.substring(4, 6).toInt(16)
    return "$r, $g, $b"
}
