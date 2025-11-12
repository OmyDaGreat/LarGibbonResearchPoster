package xyz.malefic.staticsite.components

import androidx.compose.runtime.Composable
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
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.height
import org.jetbrains.compose.web.css.marginBottom
import org.jetbrains.compose.web.css.marginTop
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.rgba
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text

@Composable
fun PieChart(
    title: String,
    data: List<Pair<String, Double>>,
    colors: List<String>,
    modifier: Modifier = Modifier,
) {
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
                }.toAttrs(),
    ) {
        Div(
            attrs = {
                style {
                    fontSize(20.px)
                    property("font-weight", "bold")
                    marginBottom(15.px)
                    property("text-align", "center")
                    color(Color("#2c3e50"))
                }
            },
        ) {
            Text(title)
        }

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
                        property(
                            "background",
                            data
                                .mapIndexed { index, (_, value) ->
                                    val percentage = (value / total) * 100
                                    val startAngle = currentAngle
                                    currentAngle += (percentage * 3.6)
                                    "conic-gradient(from ${startAngle}deg, ${colors.getOrElse(
                                        index,
                                    ) { "#cccccc" }} 0deg ${percentage * 3.6}deg)"
                                }.joinToString(", "),
                        )
                    }.toAttrs(),
        ) {}

        Div(
            attrs = {
                style {
                    marginTop(20.px)
                    display(DisplayStyle.Flex)
                    property("flex-direction", "column")
                    property("gap", "8px")
                }
            },
        ) {
            data.forEachIndexed { index, (label, value) ->
                Div(
                    attrs = {
                        style {
                            display(DisplayStyle.Flex)
                            property("align-items", "center")
                            property("gap", "10px")
                        }
                    },
                ) {
                    Div(
                        attrs = {
                            style {
                                width(20.px)
                                height(20.px)
                                property("border-radius", "3px")
                                property("background", colors.getOrElse(index) { "#cccccc" })
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
        }
    }
}
