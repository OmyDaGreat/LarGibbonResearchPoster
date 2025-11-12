package xyz.malefic.staticsite.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.alignItems
import com.varabyte.kobweb.compose.ui.modifiers.background
import com.varabyte.kobweb.compose.ui.modifiers.borderBottom
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.boxShadow
import com.varabyte.kobweb.compose.ui.modifiers.display
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.flexDirection
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.justifyContent
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.FlexDirection
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.LineStyle
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.marginBottom
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.rgba
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text
import kotlin.math.roundToInt

@Composable
fun BarChart(
    title: String,
    data: List<Pair<String, Double>>,
    color: String = "#3498db",
    modifier: Modifier = Modifier,
) {
    val maxValue = data.maxOfOrNull { it.second } ?: 1.0

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

        Div(
            attrs =
                Modifier
                    .display(DisplayStyle.Flex)
                    .justifyContent(JustifyContent.SpaceAround)
                    .alignItems(AlignItems.End)
                    .height(300.px)
                    .padding(20.px)
                    .borderBottom(2.px, LineStyle.Solid, Color("#e0e0e0"))
                    .toAttrs(),
        ) {
            data.forEach { (label, value) ->
                Div(
                    attrs =
                        Modifier
                            .display(DisplayStyle.Flex)
                            .flexDirection(FlexDirection.Column)
                            .alignItems(AlignItems.Center)
                            .gap(10.px)
                            .toAttrs(),
                ) {
                    Div(
                        attrs = {
                            style {
                                fontSize(14.px)
                                property("font-weight", "bold")
                                color(Color("#2c3e50"))
                                marginBottom(5.px)
                            }
                        },
                    ) {
                        Text(value.roundToInt().toString())
                    }

                    Div(
                        attrs =
                            Modifier
                                .width(60.px)
                                .height(((value / maxValue) * 250).px)
                                .background(Color(color))
                                .borderRadius(5.px, 5.px, 0.px, 0.px)
                                .styleModifier {
                                    property("animation", "barGrow 1s ease-out")
                                }.toAttrs(),
                    ) {}

                    Div(
                        attrs = {
                            style {
                                fontSize(12.px)
                                color(Color("#555555"))
                                property("text-align", "center")
                                width(80.px)
                            }
                        },
                    ) {
                        Text(label)
                    }
                }
            }
        }
    }
}
