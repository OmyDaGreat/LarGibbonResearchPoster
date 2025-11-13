package xyz.malefic.staticsite.components

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.css.Cursor
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.alignItems
import com.varabyte.kobweb.compose.ui.modifiers.background
import com.varabyte.kobweb.compose.ui.modifiers.border
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.boxShadow
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.cursor
import com.varabyte.kobweb.compose.ui.modifiers.display
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.left
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.overflow
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.position
import com.varabyte.kobweb.compose.ui.modifiers.right
import com.varabyte.kobweb.compose.ui.modifiers.textAlign
import com.varabyte.kobweb.compose.ui.modifiers.top
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.modifiers.zIndex
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.compose.ui.toAttrs
import kotlinx.browser.document
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.Position
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.rgba
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
fun SideMenu(
    sections: List<MenuSection>,
    onToggle: (Boolean) -> Unit = {},
) {
    var isOpen by remember { mutableStateOf(true) }

    // Side menu container
    Div(
        attrs =
            Modifier
                .position(Position.Fixed)
                .top(20.px)
                .left(if (isOpen) 0.px else (-250).px)
                .width(250.px)
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
                    .width(100.percent)
                    .height(100.percent)
                    .background(Color("#2c3e50"))
                    .borderRadius(0.px, 15.px, 15.px, 0.px)
                    .boxShadow(4.px, 0.px, 15.px, 0.px, rgba(0, 0, 0, 0.3))
                    .padding(20.px, 10.px)
                    .overflow { y(Overflow.Auto) }
                    .toAttrs(),
        ) {
            H3(
                attrs =
                    Modifier
                        .color(Color("#ffffff"))
                        .fontSize(18.px)
                        .margin(bottom = 20.px)
                        .textAlign(TextAlign.Center)
                        .fontWeight(FontWeight.Bold)
                        .toAttrs(),
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
                    .border(width = 0.px)
                    .borderRadius(topLeft = 0.px, topRight = 10.px, bottomRight = 10.px, bottomLeft = 0.px)
                    .color(Color("#ffffff"))
                    .fontSize(24.px)
                    .cursor(Cursor.Pointer)
                    .boxShadow(offsetX = 2.px, offsetY = 0.px, blurRadius = 10.px, color = rgba(0, 0, 0, 0.2))
                    .styleModifier {
                        property("transform", "translateY(-50%)")
                    }.toAttrs {
                        onClick {
                            isOpen = !isOpen
                            onToggle(isOpen)
                        }
                    },
        ) {
            Text(if (isOpen) "◀" else "▶")
        }
    }
}

@Composable
private fun MenuButton(section: MenuSection) {
    Button(
        attrs =
            Modifier
                .width(100.percent)
                .padding(topBottom = 12.px, leftRight = 15.px)
                .margin(bottom = 8.px)
                .background(Color("rgba(255, 255, 255, 0.1)"))
                .border(width = 0.px)
                .borderRadius(8.px)
                .color(Color("#ffffff"))
                .fontSize(14.px)
                .textAlign(TextAlign.Left)
                .cursor(Cursor.Pointer)
                .styleModifier {
                    property("transition", "all 0.3s ease")
                }.display(DisplayStyle.Flex)
                .alignItems(AlignItems.Center)
                .gap(10.px)
                .toAttrs {
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
