package xyz.malefic.staticsite.components

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.HTMLElement

@Composable
fun CollapsibleSection(
    id: String,
    title: String,
    defaultExpanded: Boolean = true,
    content: @Composable () -> Unit,
) {
    var isExpanded by remember { mutableStateOf(defaultExpanded) }

    Div(
        attrs =
            Modifier
                .fillMaxWidth()
                .padding(30.px)
                .background(Color("#ffffff"))
                .borderRadius(15.px)
                .boxShadow(0.px, 4.px, 12.px, 0.px, rgba(0, 0, 0, 0.1))
                .margin(top = 30.px)
                .styleModifier {
                    property("transition", "all 0.3s ease")
                }.toAttrs {
                    id(id)
                    onMouseOver {
                        (it.target as? HTMLElement)?.style?.boxShadow = "0 6px 20px rgba(0,0,0,0.15)"
                    }
                    onMouseOut {
                        (it.target as? HTMLElement)?.style?.boxShadow = "0 4px 12px rgba(0,0,0,0.1)"
                    }
                },
    ) {
        // Header with click to expand/collapse
        Div(
            attrs = {
                style {
                    property("display", "flex")
                    property("align-items", "center")
                    property("justify-content", "space-between")
                    property("cursor", "pointer")
                    property("user-select", "none")
                }
                onClick { isExpanded = !isExpanded }
            },
        ) {
            H2(
                attrs = {
                    style {
                        fontSize(28.px)
                        property("font-weight", "bold")
                        color(Color("#2c3e50"))
                        marginBottom(if (isExpanded) 20.px else 0.px)
                        property("border-bottom", if (isExpanded) "3px solid #3498db" else "none")
                        paddingBottom(10.px)
                        property("flex", "1")
                        property("transition", "all 0.3s ease")
                    }
                },
            ) {
                Text(title)
            }

            Div(
                attrs = {
                    style {
                        fontSize(24.px)
                        color(Color("#3498db"))
                        property("transition", "transform 0.3s ease")
                        property("transform", if (isExpanded) "rotate(180deg)" else "rotate(0deg)")
                    }
                },
            ) {
                Text("▼")
            }
        }

        // Content with animation
        if (isExpanded) {
            Div(
                attrs = {
                    style {
                        property("animation", "fadeInDown 0.5s ease-out")
                        property("overflow", "hidden")
                    }
                },
            ) {
                content()
            }
        }
    }
}
