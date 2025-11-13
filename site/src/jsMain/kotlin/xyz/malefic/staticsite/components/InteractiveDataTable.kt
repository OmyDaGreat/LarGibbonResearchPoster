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
import com.varabyte.kobweb.compose.css.Transition
import com.varabyte.kobweb.compose.css.cursor
import com.varabyte.kobweb.compose.css.fontWeight
import com.varabyte.kobweb.compose.css.overflow
import com.varabyte.kobweb.compose.css.textAlign
import com.varabyte.kobweb.compose.css.transition
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.background
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.boxShadow
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.compose.ui.toAttrs
import org.jetbrains.compose.web.css.AnimationTimingFunction
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.background
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.display
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.marginBottom
import org.jetbrains.compose.web.css.opacity
import org.jetbrains.compose.web.css.padding
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.rgba
import org.jetbrains.compose.web.css.s
import org.jetbrains.compose.web.css.width
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.Text
import org.w3c.dom.HTMLElement

@Composable
fun InteractiveDataTable(
    title: String,
    headers: List<String>,
    rows: List<List<String>>,
    modifier: Modifier = Modifier,
    doubleClickToReveal: Boolean = false,
) {
    var hoveredRow by remember { mutableStateOf<Int?>(null) }
    var hoveredCol by remember { mutableStateOf<Int?>(null) }
    var isRevealed by remember { mutableStateOf(!doubleClickToReveal) }
    var clickCount by remember { mutableStateOf(0) }

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
                    fontWeight(FontWeight.Bold)
                    marginBottom(15.px)
                    textAlign(TextAlign.Center)
                    color(Color("#2c3e50"))
                    cursor(if (doubleClickToReveal) Cursor.Pointer else Cursor.Default)
                }
                if (doubleClickToReveal) {
                    onClick {
                        clickCount++
                        if (clickCount >= 2) {
                            isRevealed = !isRevealed
                            clickCount = 0
                        }
                    }
                    onMouseOver {
                        (it.target as? HTMLElement)?.style?.color = "#3498db"
                    }
                    onMouseOut {
                        (it.target as? HTMLElement)?.style?.color = "#2c3e50"
                    }
                }
            },
        ) {
            Text(title + if (doubleClickToReveal && !isRevealed) " 🔒 (Double-click to reveal)" else "")
        }

        if (isRevealed) {
            Div(
                attrs = {
                    style {
                        width(100.percent)
                        overflow(Overflow.Hidden)
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
                    // Headers
                    headers.forEachIndexed { colIndex, header ->
                        Div(
                            attrs = {
                                style {
                                    padding(12.px)
                                    background("#3498db")
                                    color(Color("#ffffff"))
                                    fontWeight(FontWeight.Bold)
                                    textAlign(TextAlign.Center)
                                    fontSize(14.px)
                                    cursor(Cursor.Pointer)
                                    transition(Transition.all(0.3.s, AnimationTimingFunction.Ease))
                                    opacity(if (hoveredCol == colIndex || hoveredCol == null) 1 else 0.6)
                                }
                                onMouseEnter { hoveredCol = colIndex }
                                onMouseLeave { hoveredCol = null }
                            },
                        ) {
                            Text(header)
                        }
                    }

                    // Rows
                    rows.forEachIndexed { rowIndex, row ->
                        row.forEachIndexed { colIndex, cell ->
                            Div(
                                attrs = {
                                    style {
                                        padding(12.px)
                                        background(
                                            when {
                                                hoveredRow == rowIndex && hoveredCol == colIndex -> "#e8f5e9"
                                                hoveredRow == rowIndex -> "#f5f5f5"
                                                hoveredCol == colIndex -> "#e3f2fd"
                                                rowIndex % 2 == 0 -> "#f8f9fa"
                                                else -> "#ffffff"
                                            },
                                        )
                                        color(Color("#2c3e50"))
                                        textAlign(TextAlign.Center)
                                        fontSize(13.px)
                                        cursor(Cursor.Pointer)
                                        transition(Transition.all(0.3.s, AnimationTimingFunction.Ease))
                                        property(
                                            "transform",
                                            if (hoveredRow == rowIndex && hoveredCol == colIndex) {
                                                "scale(1.05)"
                                            } else {
                                                "scale(1)"
                                            },
                                        )
                                        fontWeight(
                                            if (hoveredRow == rowIndex && hoveredCol == colIndex) {
                                                FontWeight.Bold
                                            } else {
                                                FontWeight.Normal
                                            },
                                        )
                                    }
                                    onMouseEnter {
                                        hoveredRow = rowIndex
                                        hoveredCol = colIndex
                                    }
                                    onMouseLeave {
                                        hoveredRow = null
                                        hoveredCol = null
                                    }
                                },
                            ) {
                                Text(cell)
                            }
                        }
                    }
                }
            }
        } else {
            Div(
                attrs = {
                    style {
                        textAlign(TextAlign.Center)
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
