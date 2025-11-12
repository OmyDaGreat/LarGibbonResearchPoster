package xyz.malefic.staticsite.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.background
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.boxShadow
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.left
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.position
import com.varabyte.kobweb.compose.ui.modifiers.right
import com.varabyte.kobweb.compose.ui.modifiers.top
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.modifiers.zIndex
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.compose.ui.toAttrs
import kotlinx.browser.document
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.background
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.marginBottom
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.rgba
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.Button
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.Span
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.HTMLElement

data class MenuSection(
    val id: String,
    val title: String,
    val icon: String,
)

@Composable
fun SideMenu(sections: List<MenuSection>) {
    var isOpen by remember { mutableStateOf(true) }

    // Side menu container
    Div(
        attrs =
            Modifier
                .position(Position.Fixed)
                .top(20.px)
                .left(if (isOpen) 0.px else (-280).px)
                .width(280.px)
                .height(90.percent)
                .zIndex(1000)
                .styleModifier {
                    property("transition", "left 0.3s ease-in-out")
                }.toAttrs(),
    ) {
        // Menu content
        Div(
            attrs =
                Modifier
                    .width(250.px)
                    .height(100.percent)
                    .background(Color("#2c3e50"))
                    .borderRadius(0.px, 15.px, 15.px, 0.px)
                    .boxShadow(4.px, 0.px, 15.px, 0.px, rgba(0, 0, 0, 0.3))
                    .padding(20.px, 10.px)
                    .styleModifier {
                        property("overflow-y", "auto")
                    }.toAttrs(),
        ) {
            H3(
                attrs = {
                    style {
                        color(Color("#ffffff"))
                        fontSize(18.px)
                        marginBottom(20.px)
                        property("text-align", "center")
                        property("font-weight", "bold")
                    }
                },
            ) {
                Text("📋 Navigation")
            }

            sections.forEach { section ->
                MenuButton(section)
            }
        }

        // Toggle button
        Button(
            attrs =
                Modifier
                    .position(Position.Absolute)
                    .top(50.percent)
                    .right((-40).px)
                    .width(40.px)
                    .height(80.px)
                    .background(Color("#2c3e50"))
                    .styleModifier {
                        property("border", "none")
                        property("border-radius", "0 10px 10px 0")
                        color(Color("#ffffff"))
                        fontSize(24.px)
                        property("cursor", "pointer")
                        property("transform", "translateY(-50%)")
                        property("box-shadow", "2px 0 10px rgba(0,0,0,0.2)")
                    }.toAttrs {
                        onClick { isOpen = !isOpen }
                    },
        ) {
            Text(if (isOpen) "◀" else "▶")
        }
    }
}

@Composable
private fun MenuButton(section: MenuSection) {
    Button(
        attrs = {
            style {
                width(100.percent)
                padding(12.px, 15.px)
                marginBottom(8.px)
                background("rgba(255, 255, 255, 0.1)")
                property("border", "none")
                property("border-radius", "8px")
                color(Color("#ffffff"))
                fontSize(14.px)
                property("text-align", "left")
                property("cursor", "pointer")
                property("transition", "all 0.3s ease")
                property("display", "flex")
                property("align-items", "center")
                property("gap", "10px")
            }
            onClick {
                val element = document.getElementById(section.id) as? HTMLElement
                element?.scrollIntoView(js("({ behavior: 'smooth', block: 'start' })"))
            }
            onMouseOver {
                (it.target as? HTMLElement)?.style?.apply {
                    background = "rgba(255, 255, 255, 0.2)"
                    transform = "translateX(5px)"
                }
            }
            onMouseOut {
                (it.target as? HTMLElement)?.style?.apply {
                    background = "rgba(255, 255, 255, 0.1)"
                    transform = "translateX(0)"
                }
            }
        },
    ) {
        Span(
            attrs = {
                style {
                    fontSize(18.px)
                }
            },
        ) {
            Text(section.icon)
        }
        Span {
            Text(section.title)
        }
    }
}
