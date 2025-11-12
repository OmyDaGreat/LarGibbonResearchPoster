package xyz.malefic.staticsite.components

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.ui.Modifier
import org.jetbrains.compose.web.dom.Div

@Composable
fun AnimatedSection(
    animation: String = "fadeInUp",
    delay: Double = 0.0,
    modifier: Modifier = Modifier,
    content: @Composable () -> Unit,
) {
    val animStyle =
        when (animation) {
            "fadeInLeft" -> "fadeInLeft 0.8s ease-out ${delay}s both"
            "fadeInRight" -> "fadeInRight 0.8s ease-out ${delay}s both"
            else -> "fadeInUp 0.8s ease-out ${delay}s both"
        }

    Div(
        attrs =
            {
                style {
                    property("animation", animStyle)
                }
            },
    ) {
        content()
    }
}
