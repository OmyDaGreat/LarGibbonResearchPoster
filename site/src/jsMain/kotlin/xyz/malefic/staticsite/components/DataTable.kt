package xyz.malefic.staticsite.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.background
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.boxShadow
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.marginBottom
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.rgba
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text

@Composable
fun DataTable(
    title: String,
    headers: List<String>,
    rows: List<List<String>>,
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
                    property("animation", "slideIn 0.8s ease-out")
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

        Div(
            attrs = {
                style {
                    width(100.percent)
                    property("overflow", "auto")
                }
            },
        ) {
            Div(
                attrs = {
                    style {
                        display(DisplayStyle.Grid)
                        property("grid-template-columns", "repeat(${headers.size}, 1fr)")
                        property("gap", "1px")
                    }
                },
            ) {
                headers.forEach { header ->
                    Div(
                        attrs = {
                            style {
                                padding(12.px)
                                property("background", "#3498db")
                                color(Color("#ffffff"))
                                property("font-weight", "bold")
                                property("text-align", "center")
                                fontSize(14.px)
                            }
                        },
                    ) {
                        Text(header)
                    }
                }

                rows.forEachIndexed { rowIndex, row ->
                    row.forEach { cell ->
                        Div(
                            attrs = {
                                style {
                                    padding(12.px)
                                    property("background", if (rowIndex % 2 == 0) "#f8f9fa" else "#ffffff")
                                    color(Color("#2c3e50"))
                                    property("text-align", "center")
                                    fontSize(13.px)
                                }
                            },
                        ) {
                            Text(cell)
                        }
                    }
                }
            }
        }
    }
}
