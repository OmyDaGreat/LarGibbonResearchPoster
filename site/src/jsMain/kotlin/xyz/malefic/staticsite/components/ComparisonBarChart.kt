package xyz.malefic.staticsite.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.css.AlignItems
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.Transition
import com.varabyte.kobweb.compose.css.WhiteSpace
import com.varabyte.kobweb.compose.css.alignItems
import com.varabyte.kobweb.compose.css.fontWeight
import com.varabyte.kobweb.compose.css.transition
import com.varabyte.kobweb.compose.css.whiteSpace
import com.varabyte.kobweb.compose.css.zIndex
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.background
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.boxShadow
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.css.AnimationTimingFunction
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.FlexDirection
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.background
import org.jetbrains.compose.web.css.bottom
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.flexDirection
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.left
import org.jetbrains.compose.web.css.marginBottom
import org.jetbrains.compose.web.css.marginTop
import org.jetbrains.compose.web.css.opacity
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.position
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.rgba
import org.jetbrains.compose.web.css.s
import org.jetbrains.compose.web.css.top
import org.jetbrains.compose.web.css.unaryMinus
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text

data class ComparisonData(
    val label: String,
    val wildMean: Double,
    val wildSEM: Double,
    val captivityMean: Double,
    val captivitySEM: Double,
)

@Composable
fun ComparisonBarChart(
    title: String,
    data: List<ComparisonData>,
    modifier: Modifier = Modifier,
) {
    var hoveredIndex by remember { mutableStateOf<Int?>(null) }
    var hoveredType by remember { mutableStateOf<String?>(null) }

    // Find max value to scale bars appropriately (mean + SEM)
    val rawMaxValue =
        data.maxOfOrNull {
            maxOf(it.wildMean + it.wildSEM, it.captivityMean + it.captivitySEM)
        } ?: 1.0

    // Add 10% padding for comfort and calculate nice round max value for Y-axis
    val paddedMaxValue = rawMaxValue * 1.1

    // Calculate nice round number for steps (divide by 5 for 5 intervals)
    val roughStep = paddedMaxValue / 5.0

    // Round step up to a nice number, handling small values
    val niceStep =
        when {
            roughStep <= 0.1 -> kotlin.math.ceil(roughStep * 100) / 100.0
            roughStep <= 0.2 -> 0.2
            roughStep <= 0.5 -> 0.5
            roughStep <= 1.0 -> 1.0
            roughStep <= 2.0 -> 2.0
            roughStep <= 5.0 -> 5.0
            roughStep <= 10.0 -> 10.0
            roughStep <= 20.0 -> 20.0
            roughStep <= 50.0 -> 50.0
            else -> kotlin.math.ceil(roughStep / 10.0) * 10.0
        }

    val maxValue = niceStep * 5.0

    Div(
        attrs =
            modifier
                .fillMaxWidth()
                .padding(20.px)
                .borderRadius(15.px)
                .background(Color("#ffffff"))
                .boxShadow(0.px, 4.px, 12.px, 0.px, rgba(0, 0, 0, 0.1))
                .toAttrs(),
    ) {
        // Title
        Div(
            attrs = {
                style {
                    fontSize(20.px)
                    property("font-weight", "bold")
                    marginBottom(20.px)
                    property("text-align", "center")
                    color(Color("#2c3e50"))
                }
            },
        ) {
            Text(title)
        }

        // Legend
        Div(
            attrs = {
                style {
                    property("display", "flex")
                    property("justify-content", "center")
                    property("gap", "30px")
                    marginBottom(15.px)
                }
            },
        ) {
            LegendItem("Wild", "#2ecc71")
            LegendItem("Captivity", "#e74c3c")
        }

        // Chart area with grid
        Div(
            attrs = {
                style {
                    property("position", "relative")
                    property("padding-left", "60px")
                    property("padding-right", "20px")
                    property("padding-top", "40px")
                }
            },
        ) {
            // Y-axis and grid lines
            YAxisWithGrid(maxValue)

            // Bars container - positioned above baseline
            Div(
                attrs = {
                    style {
                        property("display", "flex")
                        property("justify-content", "space-around")
                        height(350.px)
                        property("position", "relative")
                        property("z-index", "1")
                    }
                },
            ) {
                data.forEachIndexed { index, item ->
                    ComparisonBarGroup(
                        label = item.label,
                        wildMean = item.wildMean,
                        wildSEM = item.wildSEM,
                        captivityMean = item.captivityMean,
                        captivitySEM = item.captivitySEM,
                        maxValue = maxValue,
                        isHovered = hoveredIndex == index,
                        hoveredType = if (hoveredIndex == index) hoveredType else null,
                        onHover = { type ->
                            hoveredIndex = index
                            hoveredType = type
                        },
                        onLeave = {
                            hoveredIndex = null
                            hoveredType = null
                        },
                    )
                }
            }

            // Labels container - below baseline
            Div(
                attrs = {
                    style {
                        property("display", "flex")
                        property("justify-content", "space-around")
                        property("position", "relative")
                        property("z-index", "1")
                        marginTop(15.px)
                        marginBottom(20.px)
                    }
                },
            ) {
                data.forEachIndexed { index, item ->
                    Div(
                        attrs = {
                            style {
                                fontSize(13.px)
                                color(Color("#555555"))
                                property("text-align", "center")
                                width(130.px)
                                property("font-weight", if (hoveredIndex == index) "bold" else "normal")
                            }
                        },
                    ) {
                        Text(item.label)
                    }
                }
            }
        }
    }
}

@Composable
private fun YAxisWithGrid(maxValue: Double) {
    // Calculate grid lines
    val numLines = 5
    val step = maxValue / numLines

    Div(
        attrs = {
            style {
                property("position", "absolute")
                property("left", "60px")
                property("top", "40px")
                property("width", "calc(100% - 80px)")
                height(350.px)
                property("pointer-events", "none")
            }
        },
    ) {
        // Y-axis vertical line
        Div(
            attrs = {
                style {
                    property("position", "absolute")
                    property("left", "-10px")
                    property("bottom", "0")
                    width(2.px)
                    height(350.px)
                    property("background", "#333333")
                }
            },
        ) {}

        // Draw horizontal grid lines and Y-axis labels
        for (i in 0..numLines) {
            val value = step * i
            val percentFromBottom = (i.toDouble() / numLines) * 100

            Div(
                attrs = {
                    style {
                        property("position", "absolute")
                        property("bottom", "$percentFromBottom%")
                        property("left", "-55px")
                        property("right", "0")
                        property("display", "flex")
                        property("align-items", "center")
                        property("transform", "translateY(50%)")
                    }
                },
            ) {
                // Y-axis label
                Div(
                    attrs = {
                        style {
                            width(45.px)
                            fontSize(12.px)
                            color(Color("#666666"))
                            property("text-align", "right")
                        }
                    },
                ) {
                    val roundedValue = (value * 10).toInt() / 10.0
                    Text(roundedValue.toString())
                }

                // Grid line
                Div(
                    attrs = {
                        style {
                            property("flex", "1")
                            height(if (i == 0) 2.px else 1.px)
                            property("background", if (i == 0) "#333333" else "#e0e0e0")
                            property("margin-left", "10px")
                        }
                    },
                ) {}
            }
        }
    }
}

@Composable
private fun LegendItem(
    label: String,
    color: String,
) {
    Div(
        attrs = {
            style {
                property("display", "flex")
                property("align-items", "center")
                property("gap", "8px")
            }
        },
    ) {
        Div(
            attrs = {
                style {
                    width(20.px)
                    height(20.px)
                    property("background", color)
                    property("border-radius", "3px")
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
            Text(label)
        }
    }
}

@Composable
private fun ComparisonBarGroup(
    label: String,
    wildMean: Double,
    wildSEM: Double,
    captivityMean: Double,
    captivitySEM: Double,
    maxValue: Double,
    isHovered: Boolean,
    hoveredType: String?,
    onHover: (String) -> Unit,
    onLeave: () -> Unit,
) {
    // Bars container - starts from baseline at bottom
    Div(
        attrs = {
            style {
                property("display", "flex")
                property("gap", "20px")
                property("align-items", "flex-end")
                height(350.px)
            }
        },
    ) {
        // Wild bar
        BarWithError(
            mean = wildMean,
            sem = wildSEM,
            maxValue = maxValue,
            color = "#2ecc71",
            isHovered = isHovered && hoveredType == "wild",
            onHover = { onHover("wild") },
            onLeave = onLeave,
        )

        // Captivity bar
        BarWithError(
            mean = captivityMean,
            sem = captivitySEM,
            maxValue = maxValue,
            color = "#e74c3c",
            isHovered = isHovered && hoveredType == "captivity",
            onHover = { onHover("captivity") },
            onLeave = onLeave,
        )
    }
}

@Composable
private fun BarWithError(
    mean: Double,
    sem: Double,
    maxValue: Double,
    color: String,
    isHovered: Boolean,
    onHover: () -> Unit,
    onLeave: () -> Unit,
) {
    val barHeight = (mean / maxValue) * 350.0
    val errorBarTotalHeight = (2 * sem / maxValue) * 350.0

    Div(
        attrs = {
            style {
                property("display", "flex")
                property("flex-direction", "column")
                property("align-items", "center")
                property("position", "relative")
                property("cursor", "pointer")
                property("justify-content", "flex-end")
                height(350.px)
            }
            onMouseEnter { onHover() }
            onMouseLeave { onLeave() }
        },
    ) {
        // Error bar (mean - SEM to mean + SEM) - positioned absolutely at top of bar
        Div(
            attrs = {
                style {
                    position(Position.Absolute)
                    bottom((barHeight - (sem / maxValue) * 350.0).px)
                    display(DisplayStyle.Flex)
                    flexDirection(FlexDirection.Column)
                    alignItems(AlignItems.Center)
                    zIndex(1)
                }
            },
        ) {
            // Top cap (mean + SEM)
            Div(
                attrs = {
                    style {
                        width(45.px)
                        height(2.px)
                        background("#333333")
                    }
                },
            ) {}

            // Vertical line from mean + SEM down to mean - SEM
            Div(
                attrs = {
                    style {
                        width(2.px)
                        height(errorBarTotalHeight.px)
                        background("#333333")
                    }
                },
            ) {}

            // Bottom cap (mean - SEM)
            Div(
                attrs = {
                    style {
                        width(45.px)
                        height(2.px)
                        background("#333333")
                    }
                },
            ) {}
        }

        // Main bar starting from bottom (y=0)
        Div(
            attrs = {
                style {
                    width(if (isHovered) 55.px else 50.px)
                    height(barHeight.px)
                    background(color)
                    transition(Transition.all(0.3.s, AnimationTimingFunction.Ease))
                    property("box-shadow", if (isHovered) "0 -4px 12px rgba(0,0,0,0.2)" else "none")
                    position(Position.Relative)
                }
            },
        ) {
            // Value label (shows on hover) - positioned above error bar
            Div(
                attrs = {
                    style {
                        fontSize(13.px)
                        fontWeight(FontWeight.Bold)
                        color(Color("#2c3e50"))
                        position(Position.Absolute)
                        top(-((sem / maxValue) * 350.0 + errorBarTotalHeight + 50).px)
                        left(50.percent)
                        property("transform", "translateX(-50%)")
                        opacity(if (isHovered) 1 else 0)
                        property("transition", "opacity 0.3s ease")
                        whiteSpace(WhiteSpace.NoWrap)
                    }
                },
            ) {
                val roundedMean = (mean * 1000).toInt() / 1000.0
                val roundedSEM = (sem * 1000).toInt() / 1000.0
                Text("$roundedMean ± $roundedSEM")
            }
        }
    }
}
