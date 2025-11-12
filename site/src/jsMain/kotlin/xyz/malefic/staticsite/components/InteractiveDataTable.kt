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
fun InteractiveDataTable(
    title: String,
    headers: List<String>,
    rows: List<List<String>>,
    modifier: Modifier = Modifier,
    doubleClickToReveal: Boolean = false
) {
    var hoveredRow by remember { mutableStateOf<Int?>(null) }
    var hoveredCol by remember { mutableStateOf<Int?>(null) }
    var isRevealed by remember { mutableStateOf(!doubleClickToReveal) }
    var clickCount by remember { mutableStateOf(0) }
    
    Div(
        attrs = modifier
            .fillMaxWidth()
            .padding(20.px)
            .borderRadius(15.px)
            .background(Color("#ffffff"))
            .boxShadow(0.px, 4.px, 12.px, 0.px, rgba(0, 0, 0, 0.1))
            .styleModifier {
                property("animation", "slideIn 0.8s ease-out")
            }
            .toAttrs()
    ) {
        Div(
            attrs = {
                style {
                    fontSize(20.px)
                    property("font-weight", "bold")
                    marginBottom(15.px)
                    property("text-align", "center")
                    color(Color("#2c3e50"))
                    property("cursor", if (doubleClickToReveal) "pointer" else "default")
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
            }
        ) {
            Text(title + if (doubleClickToReveal && !isRevealed) " 🔒 (Double-click to reveal)" else "")
        }
        
        if (isRevealed) {
            Div(
                attrs = {
                    style {
                        width(100.percent)
                        property("overflow", "auto")
                    }
                }
            ) {
                Div(
                    attrs = {
                        style {
                            property("display", "grid")
                            property("grid-template-columns", "repeat(${headers.size}, 1fr)")
                            property("gap", "1px")
                        }
                    }
                ) {
                    // Headers
                    headers.forEachIndexed { colIndex, header ->
                        Div(
                            attrs = {
                                style {
                                    padding(12.px)
                                    property("background", "#3498db")
                                    color(Color("#ffffff"))
                                    property("font-weight", "bold")
                                    property("text-align", "center")
                                    fontSize(14.px)
                                    property("cursor", "pointer")
                                    property("transition", "all 0.3s ease")
                                    property("opacity", if (hoveredCol == colIndex || hoveredCol == null) "1" else "0.6")
                                }
                                onMouseEnter { hoveredCol = colIndex }
                                onMouseLeave { hoveredCol = null }
                            }
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
                                        property("background", when {
                                            hoveredRow == rowIndex && hoveredCol == colIndex -> "#e8f5e9"
                                            hoveredRow == rowIndex -> "#f5f5f5"
                                            hoveredCol == colIndex -> "#e3f2fd"
                                            rowIndex % 2 == 0 -> "#f8f9fa"
                                            else -> "#ffffff"
                                        })
                                        color(Color("#2c3e50"))
                                        property("text-align", "center")
                                        fontSize(13.px)
                                        property("cursor", "pointer")
                                        property("transition", "all 0.3s ease")
                                        property("transform", if (hoveredRow == rowIndex && hoveredCol == colIndex) 
                                            "scale(1.05)" else "scale(1)")
                                        property("font-weight", if (hoveredRow == rowIndex && hoveredCol == colIndex) 
                                            "bold" else "normal")
                                    }
                                    onMouseEnter {
                                        hoveredRow = rowIndex
                                        hoveredCol = colIndex
                                    }
                                    onMouseLeave {
                                        hoveredRow = null
                                        hoveredCol = null
                                    }
                                }
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
                        property("text-align", "center")
                        padding(40.px)
                        fontSize(48.px)
                    }
                }
            ) {
                Text("🔒")
            }
        }
    }
}
