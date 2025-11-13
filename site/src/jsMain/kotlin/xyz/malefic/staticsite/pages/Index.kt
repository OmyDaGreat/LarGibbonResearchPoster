package xyz.malefic.staticsite.pages

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.varabyte.kobweb.compose.css.FontWeight
import com.varabyte.kobweb.compose.css.ObjectFit
import com.varabyte.kobweb.compose.css.Overflow
import com.varabyte.kobweb.compose.css.TextAlign
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.alignItems
import com.varabyte.kobweb.compose.ui.modifiers.background
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.boxShadow
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.display
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.flexDirection
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.fontWeight
import com.varabyte.kobweb.compose.ui.modifiers.gap
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.id
import com.varabyte.kobweb.compose.ui.modifiers.justifyContent
import com.varabyte.kobweb.compose.ui.modifiers.lineHeight
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.maxWidth
import com.varabyte.kobweb.compose.ui.modifiers.objectFit
import com.varabyte.kobweb.compose.ui.modifiers.overflow
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.textAlign
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.graphics.Image
import org.jetbrains.compose.web.css.AlignItems
import org.jetbrains.compose.web.css.CSSKeywordValue
import org.jetbrains.compose.web.css.CSSSizeValue
import org.jetbrains.compose.web.css.CSSUnit
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.FlexDirection
import org.jetbrains.compose.web.css.JustifyContent
import org.jetbrains.compose.web.css.backgroundColor
import org.jetbrains.compose.web.css.margin
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.rgba
import org.jetbrains.compose.web.dom.Div
import org.jetbrains.compose.web.dom.H1
import org.jetbrains.compose.web.dom.H2
import org.jetbrains.compose.web.dom.H3
import org.jetbrains.compose.web.dom.I
import org.jetbrains.compose.web.dom.Li
import org.jetbrains.compose.web.dom.Ol
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Style
import org.jetbrains.compose.web.dom.Text
import org.jetbrains.compose.web.dom.Ul
import xyz.malefic.staticsite.components.AnimatedSection
import xyz.malefic.staticsite.components.CollapsibleSection
import xyz.malefic.staticsite.components.ComparisonBarChart
import xyz.malefic.staticsite.components.ComparisonData
import xyz.malefic.staticsite.components.InteractiveDataTable
import xyz.malefic.staticsite.components.InteractivePieChart
import xyz.malefic.staticsite.components.MenuSection
import xyz.malefic.staticsite.components.SideMenu

@Page
@Composable
fun HomePage() {
    var isMenuOpen by remember { mutableStateOf(true) }

    Style {
        "body" style {
            backgroundColor(Color("#f0f4f8"))
            margin(0.px)
            property("padding", "0")
            property("font-family", "Arial, sans-serif")
        }
    }

    // Side Menu
    SideMenu(
        sections =
            listOf(
                MenuSection("header", "Title", "📋"),
                MenuSection("authors", "Authors", "👥"),
                MenuSection("introduction", "Introduction", "📖"),
                MenuSection("methodology", "Methodology", "🔬"),
                MenuSection("images", "Gibbons", "🦍"),
                MenuSection("data", "Data", "📊"),
                MenuSection("results", "Results", "✨"),
                MenuSection("analysis", "Analysis", "🧠"),
                MenuSection("conclusion", "Conclusion", "🎯"),
                MenuSection("references", "References", "📚"),
            ),
        onToggle = { isMenuOpen = it },
    )

    Box(
        Modifier
            .fillMaxSize()
            .overflow(Overflow.Auto)
            .padding(left = if (isMenuOpen) 270.px else 0.px)
            .styleModifier {
                property("transition", "padding-left 0.3s ease-in-out")
            },
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .maxWidth(1400.px)
                    .margin(
                        topBottom = 0.px,
                        leftRight = CSSKeywordValue("auto") as CSSSizeValue<CSSUnit.px>,
                    ).padding(40.px, 20.px),
            horizontalAlignment = Alignment.CenterHorizontally,
        ) {
            AnimatedSection {
                Header()
            }

            AnimatedSection(delay = 0.2) {
                Authors()
            }

            AnimatedSection("fadeInLeft", 0.3) {
                CollapsibleSection("introduction", "Introduction", defaultExpanded = true) {
                    IntroductionContent()
                }
            }

            AnimatedSection("fadeInRight", 0.4) {
                CollapsibleSection("methodology", "Methodology", defaultExpanded = false) {
                    MethodologyContent()
                }
            }

            AnimatedSection(delay = 0.5) {
                CollapsibleSection("images", "Images from the SA Zoo - Gibbons & Enclosure") {
                    GibbonImages()
                }
            }

            AnimatedSection(delay = 0.6) {
                CollapsibleSection("data", "Data & Results", defaultExpanded = true) {
                    DataVisualization()
                }
            }

            AnimatedSection("fadeInLeft", 0.7) {
                CollapsibleSection("results", "Key Results") {
                    ResultsContent()
                }
            }

            AnimatedSection("fadeInRight", 0.8) {
                CollapsibleSection("analysis", "Analysis & Discussion") {
                    AnalysisContent()
                }
            }

            AnimatedSection(delay = 0.9) {
                CollapsibleSection("conclusion", "Conclusion") {
                    ConclusionContent()
                }
            }

            AnimatedSection(delay = 1.0) {
                CollapsibleSection("references", "Key References") {
                    ReferencesContent()
                }
            }
        }
    }
}

@Composable
private fun Header() {
    Div(
        attrs =
            Modifier
                .id("header")
                .fillMaxWidth()
                .padding(40.px, 20.px)
                .background(Color("#2c3e50"))
                .borderRadius(20.px)
                .boxShadow(0.px, 8.px, 24.px, 0.px, rgba(0, 0, 0, 0.15))
                .textAlign(TextAlign.Center)
                .styleModifier { property("animation", "pulse 2s infinite") }
                .toAttrs(),
    ) {
        H1(
            attrs =
                Modifier
                    .color(Color("#ffffff"))
                    .fontSize(48.px)
                    .fontWeight(FontWeight.Bold)
                    .margin(0.px)
                    .margin(bottom = 10.px)
                    .toAttrs(),
        ) {
            Text("White-Handed Gibbon Activity in Captivity")
        }
        H2(
            attrs =
                Modifier
                    .color(Color("#ecf0f1"))
                    .fontSize(24.px)
                    .fontWeight(FontWeight.Normal)
                    .margin(0.px)
                    .toAttrs(),
        ) {
            Text("A Behavioral Study at Santa Ana Zoo")
        }
    }
}

@Composable
private fun Authors() {
    Div(
        attrs =
            Modifier
                .id("authors")
                .fillMaxWidth()
                .padding(20.px)
                .background(Color("#ffffff"))
                .borderRadius(15.px)
                .boxShadow(0.px, 4.px, 12.px, 0.px, rgba(0, 0, 0, 0.1))
                .margin(top = 20.px)
                .textAlign(TextAlign.Center)
                .toAttrs(),
    ) {
        H3(
            attrs =
                Modifier
                    .fontSize(18.px)
                    .color(Color("#34495e"))
                    .margin(topBottom = 10.px, leftRight = 0.px)
                    .toAttrs(),
        ) {
            Text("Om Gupta, Esther Li, Alfredo Magallon, Cindy Nguyen")
        }
    }
}

@Composable
private fun IntroductionContent() {
    P(
        attrs =
            Modifier
                .fontSize(16.px)
                .lineHeight(1.6)
                .color(Color("#2c3e50"))
                .toAttrs(),
    ) {
        Text(
            "White-handed gibbons in captivity, such as in zoos, will not have the same experiences and freedoms as their wild counterparts. While well-intentioned zoos attempt to bridge this gap, it often leads to different, even detrimental, behaviors in captive animals compared to in the wild. This first-semester capstone project will focus on how the activity budget of white-handed gibbons in captivity (SA ZOO) compares to that of those in the wild.",
        )
    }
    P(
        attrs =
            Modifier
                .fontSize(16.px)
                .lineHeight(1.6)
                .color(Color("#2c3e50"))
                .margin(top = 15.px)
                .toAttrs(),
    ) {
        I { Text("Hypothesis: ") }
        Text(
            "White-handed gibbons in captivity will show less activity and less varied behavior than their wild counterparts.",
        )
    }
}

@Composable
private fun MethodologyContent() {
    P(
        attrs =
            Modifier
                .fontSize(16.px)
                .lineHeight(1.6)
                .color(Color("#2c3e50"))
                .toAttrs(),
    ) {
        Text(
            "Before collecting data, the team conducted online research to understand and compare the typical wild behaviors and typical zoo behaviors of white-handed gibbons. Then, going to the Santa Ana Zoo to observe their two white-handed gibbons, the team conducted two types of observation: scan sampling and focal sampling. Two members chose scan sampling over one-minute intervals and two members chose focal sampling for one of the two white-handed gibbons. One observation was conducted in the morning after 11 a.m., the second in the afternoon after 1 p.m.",
        )
    }
}

@Composable
private fun GibbonImages() {
    Div(
        attrs =
            Modifier
                .fillMaxWidth()
                .margin(top = 20.px)
                .display(DisplayStyle.Grid)
                .styleModifier {
                    property("grid-template-columns", "repeat(auto-fit, minmax(250px, 1fr))")
                }.gap(20.px)
                .toAttrs(),
    ) {
        ImageCard("Dark-colored Gibbon (M1)", "/images/dark_gibbon.png")
        ImageCard("Light-colord Gibbon (M2)", "/images/light_gibbon.png")
        ImageCard("Enclosure Picture 1", "/images/enclosure1.png")
        ImageCard("Enclosure Picture 2", "/images/enclosure2.png")
    }
}

@Composable
private fun ImageCard(
    caption: String,
    image: String,
) {
    Div(
        attrs =
            Modifier
                .borderRadius(10.px)
                .overflow(Overflow.Hidden)
                .styleModifier {
                    property("transition", "transform 0.3s ease")
                }.boxShadow(0.px, 4.px, 12.px, 0.px, rgba(0, 0, 0, 0.1))
                .background(Color("#ffffff"))
                .toAttrs {
                    onMouseOver {
                        (it.target as? org.w3c.dom.HTMLElement)?.style?.transform = "scale(1.05) rotate(2deg)"
                    }
                    onMouseOut {
                        (it.target as? org.w3c.dom.HTMLElement)?.style?.transform = "scale(1) rotate(0deg)"
                    }
                },
    ) {
        Div(
            attrs =
                Modifier
                    .width(100.percent)
                    .height(200.px)
                    .background(
                        Color("#e0e0e0"),
                    ).display(DisplayStyle.Flex)
                    .alignItems(AlignItems.Center)
                    .justifyContent(JustifyContent.Center)
                    .fontSize(64.px)
                    .styleModifier { property("animation", "bounce 2s infinite") }
                    .toAttrs(),
        ) {
            Image(
                image,
                alt = "Image",
                modifier =
                    Modifier.fillMaxSize().objectFit(ObjectFit.Contain).styleModifier {
                        property("object-position", "center")
                    },
            )
        }
        Div(
            attrs =
                Modifier
                    .padding(15.px)
                    .fontSize(13.px)
                    .color(Color("#555555"))
                    .textAlign(TextAlign.Center)
                    .toAttrs(),
        ) {
            Text(caption)
        }
    }
}

@Composable
private fun DataVisualization() {
    H3(
        attrs =
            Modifier
                .fontSize(22.px)
                .margin(bottom = 20.px)
                .color(Color("#2c3e50"))
                .toAttrs(),
    ) {
        Text("Pie Chart Analysis")
    }

    Div(
        attrs =
            Modifier
                .fillMaxWidth()
                .margin(bottom = 40.px)
                .display(DisplayStyle.Grid)
                .styleModifier {
                    property("grid-template-columns", "repeat(auto-fit, minmax(350px, 1fr))")
                }.gap(25.px)
                .toAttrs(),
    ) {
        InteractivePieChart(
            title = "M1 Activity Budget (Scan)",
            data =
                listOf(
                    "Relaxing" to 39.4,
                    "Eating" to 18.2,
                    "Hanging" to 15.2,
                    "Playing with Plants" to 12.1,
                    "Brachiation" to 6.1,
                    "Walking" to 6.1,
                    "Climbing" to 3.0,
                ),
        )

        InteractivePieChart(
            title = "M1 Activity Budget (Focal)",
            data =
                listOf(
                    "Relaxing" to 37.5,
                    "Walking" to 21.4,
                    "Eating" to 21.4,
                    "Climbing" to 7.1,
                    "Brachiation" to 5.4,
                    "Hanging" to 5.4,
                    "Playing with Plants" to 1.8,
                ),
        )

        InteractivePieChart(
            title = "M2 Activity Budget (Scan)",
            data =
                listOf(
                    "Hanging" to 36.4,
                    "Relaxing" to 24.2,
                    "Climbing" to 12.1,
                    "Playing with Plants" to 12.1,
                    "Brachiation" to 9.1,
                    "Walking" to 3.0,
                    "Eating" to 3.0,
                ),
        )

        InteractivePieChart(
            title = "M2 Activity Budget (Focal)",
            data =
                listOf(
                    "Hanging" to 23.6,
                    "Relaxing" to 20.0,
                    "Brachiation" to 18.2,
                    "Eating" to 14.5,
                    "Walking" to 10.9,
                    "Climbing" to 9.1,
                    "Playing with Plants" to 3.6,
                ),
        )
    }

    H3(
        attrs =
            Modifier
                .fontSize(22.px)
                .margin(top = 40.px)
                .margin(bottom = 20.px)
                .color(Color("#2c3e50"))
                .toAttrs(),
    ) {
        Text("Mean Percentage out of Total Daily Activity")
    }

    Div(
        attrs =
            Modifier
                .fillMaxWidth()
                .margin(bottom = 40.px)
                .display(DisplayStyle.Flex)
                .flexDirection(FlexDirection.Column)
                .gap(25.px)
                .toAttrs(),
    ) {
        ComparisonBarChart(
            title = "Mean Percentage out of Total Daily Activity",
            data =
                listOf(
                    ComparisonData("Locomotion/Movement", wildMean = 0.307, wildSEM = 0.067, captivityMean = 0.279, captivitySEM = 0.052),
                    ComparisonData("Resting", wildMean = 0.356, wildSEM = 0.054, captivityMean = 0.5085, captivitySEM = 0.041),
                    ComparisonData("Feeding", wildMean = 0.338, wildSEM = 0.012, captivityMean = 0.21675, captivitySEM = 0.033),
                ),
        )
    }

    H3(
        attrs =
            Modifier
                .fontSize(22.px)
                .margin(top = 40.px)
                .margin(bottom = 20.px)
                .color(Color("#2c3e50"))
                .toAttrs(),
    ) {
        Text("Detailed Data Tables")
    }

    Div(
        attrs =
            Modifier
                .fillMaxWidth()
                .display(DisplayStyle.Flex)
                .flexDirection(FlexDirection.Column)
                .gap(25.px)
                .toAttrs(),
    ) {
        InteractiveDataTable(
            title = "Mean Percentages (as decimals) out of Total Daily Activity of Lar Gibbons",
            headers = listOf("      ", "Locomotion/Movement", "Resting", "Feeding"),
            rows =
                listOf(
                    listOf("Captivity", "0.279 ± 0.052", "0.509 ± 0.041", "0.217 ± 0.033"),
                    listOf("Wild", "0.307 ± 0.067", "0.356 ± 0.054", "0.338 ± 0.012"),
                ),
            doubleClickToReveal = false,
        )
    }
}

@Composable
private fun ResultsContent() {
    Div(
        attrs =
            Modifier
                .fontSize(16.px)
                .lineHeight(1.8)
                .color(Color("#2c3e50"))
                .toAttrs(),
    ) {
        Div(
            attrs =
                Modifier
                    .display(DisplayStyle.Flex)
                    .flexDirection(FlexDirection.Column)
                    .gap(0.px)
                    .toAttrs(),
        ) {
            val rows =
                listOf(
                    Triple("Locomotion/movement:", "brachiation, climbing, walking", Color("#f9fafb")),
                    Triple("Feeding:", "eating", Color("#ffffff")),
                    Triple("Resting:", "hanging, relaxing, playing with plants", Color("#f9fafb")),
                )

            rows.forEachIndexed { index, (label, value, bg) ->
                Div(
                    attrs =
                        Modifier
                            .display(DisplayStyle.Grid)
                            .styleModifier {
                                property("grid-template-columns", "max-content 1fr")
                                property("padding", "10px 12px")
                                property("align-items", "center")
                                property("border-bottom", if (index < rows.lastIndex) "1px solid #e5e7eb" else "none")
                            }.background(bg)
                            .toAttrs(),
                ) {
                    Div(attrs = Modifier.fontWeight(FontWeight.Bold).margin(right = 12.px).toAttrs()) { Text(label) }
                    Div { Text(value) }
                }
            }
        }

        Ul {
            Li { Text("Most time was allocated towards “resting” activities for monkeys in captivity (.509 + .041)") }
            Li { Text("The least amt. of time was allocated towards feeding (.217 + .033)") }
            Li { Text("CIs overlap for monkeys in captivity vs. wild for location/movement") }
            Li { Text("Statistical significance: Monkeys rest more in captivity, but feed less") }
        }
    }
}

@Composable
private fun AnalysisContent() {
    P(
        attrs =
            Modifier
                .fontSize(16.px)
                .lineHeight(1.6)
                .color(Color("#2c3e50"))
                .margin(top = 15.px)
                .toAttrs(),
    ) {
        Text(
            "There is overlap in the 95% confidence intervals for the locomotion/movement of the monkeys, indicating that there is no statistically significant difference between the physical activity of the lar gibbons in captivity vs. in the wild. However, there is a statistically significant difference in the amount of daily activity allocated towards feeding and resting for monekys in captivity vs. monkeys in the wild, with the former feeding less and resting more than the latter. In captivity, food is typically provided in a predictable way (e.g., delivered by keepers, stored in one part of the enclosure) which reduces the need for active foraging (less effort required to find food). Captive enclosures may restrict locomotion, reduce opportunities to move between widely spaced trees or search for food. As a result, rest replaces the time dedicated to foraging (White, 2019), as the environment removes the need for vigilance when diet is much more variable in the wild.",
        )
    }
    P(
        attrs =
            Modifier
                .fontSize(16.px)
                .lineHeight(1.6)
                .color(Color("#2c3e50"))
                .margin(top = 15.px)
                .toAttrs(),
    ) {
        Ul {
            Li {
                Text(
                    "More enrichment = more foraging behavior (Gronqvist et al., 2013).",
                )
            }
        }
    }
}

@Composable
private fun ConclusionContent() {
    P(
        attrs =
            Modifier
                .fontSize(16.px)
                .lineHeight(1.6)
                .color(Color("#2c3e50"))
                .toAttrs(),
    ) {
        Text(
            "Our hypothesis that gibbons in captivity show less active “activity” is supported by statistically significant difference in resting and feeding. Although there is similar time allocated for movement to monkeys in the wild, it’s important to note that much of the activity dedicated towards locomotion consisted of the stereotypic brachiation, in which monkeys swung repeatedly throughout the perimeter of the enclosure prior to noon. This repetitive behavior + the use of unnatural objects for enrichment (i.e. metal boxes, ropes) show how lack of complexity in environment + limited space can cause abnormal behavior. Stereotypies like these often indicate stress + frustration from the environ. constraints of poor welfare, CNS dysfunction. Although captive Lar gibbons still move in species-typical ways, the variety and purpose of these movements are reduced, emphasizing the need for more enriched habitats that promote natural foraging and movement to improve their well-being.",
        )
    }
}

@Composable
private fun ReferencesContent() {
    Ol(
        attrs =
            Modifier
                .fontSize(14.px)
                .lineHeight(1.8)
                .color(Color("#2c3e50"))
                .toAttrs(),
    ) {
        Li {
            Text(
                "White, K. 2019. Effects of captivity on the activity budgets of lar gibbons (Hylobates lar). Canopy 20(1), 43-45. (n.d.). Animal Welfare Institute. https://awionline.org/lab-animal-search/white-k-2019-effects-captivity-activity-budgets-lar-gibbons-hylobates-lar-canopy",
            )
        }
        Li {
            Text(
                "Gronqvist, G., Kingston-Jones, M., May, A., & Lehmann, J. (2013). The effects of three types of environmental enrichment on the behaviour of captive Javan gibbons (Hylobates moloch). Applied Animal Behaviour Science, 147(1-2), 214–223. https://doi.org/10.1016/j.applanim.2013.04.021",
            )
        }
    }
}
