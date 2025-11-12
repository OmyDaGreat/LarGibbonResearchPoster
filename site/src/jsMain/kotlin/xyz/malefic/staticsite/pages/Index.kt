package xyz.malefic.staticsite.pages

import androidx.compose.runtime.Composable
import com.varabyte.kobweb.compose.foundation.layout.Box
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.modifiers.background
import com.varabyte.kobweb.compose.ui.modifiers.borderRadius
import com.varabyte.kobweb.compose.ui.modifiers.boxShadow
import com.varabyte.kobweb.compose.ui.modifiers.color
import com.varabyte.kobweb.compose.ui.modifiers.display
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxSize
import com.varabyte.kobweb.compose.ui.modifiers.fillMaxWidth
import com.varabyte.kobweb.compose.ui.modifiers.fontSize
import com.varabyte.kobweb.compose.ui.modifiers.height
import com.varabyte.kobweb.compose.ui.modifiers.margin
import com.varabyte.kobweb.compose.ui.modifiers.maxWidth
import com.varabyte.kobweb.compose.ui.modifiers.padding
import com.varabyte.kobweb.compose.ui.modifiers.width
import com.varabyte.kobweb.compose.ui.styleModifier
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import org.jetbrains.compose.web.css.Color
import org.jetbrains.compose.web.css.DisplayStyle
import org.jetbrains.compose.web.css.color
import org.jetbrains.compose.web.css.fontSize
import org.jetbrains.compose.web.css.margin
import org.jetbrains.compose.web.css.marginBottom
import org.jetbrains.compose.web.css.marginTop
import org.jetbrains.compose.web.css.percent
import org.jetbrains.compose.web.css.px
import org.jetbrains.compose.web.css.rgba
import org.jetbrains.compose.web.dom.B
import org.jetbrains.compose.web.dom.Br
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
import xyz.malefic.staticsite.components.InteractiveBarChart
import xyz.malefic.staticsite.components.InteractiveDataTable
import xyz.malefic.staticsite.components.InteractivePieChart
import xyz.malefic.staticsite.components.MenuSection
import xyz.malefic.staticsite.components.SideMenu

@Page
@Composable
fun HomePage() {
    Style {
        "body" style {
            property("background-color", "#f0f4f8")
            property("margin", "0")
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
    )

    Box(
        Modifier
            .fillMaxSize()
            .styleModifier {
                property("overflow", "auto")
                property("padding-left", "300px")
            },
    ) {
        Column(
            modifier =
                Modifier
                    .fillMaxWidth()
                    .maxWidth(1400.px)
                    .styleModifier {
                        property("margin", "0 auto")
                    }.padding(40.px, 20.px),
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
                CollapsibleSection("images", "Study Subjects - Lar Gibbons at Metro Zoo") {
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
                .fillMaxWidth()
                .padding(40.px, 20.px)
                .background(Color("#2c3e50"))
                .borderRadius(20.px)
                .boxShadow(0.px, 8.px, 24.px, 0.px, rgba(0, 0, 0, 0.15))
                .styleModifier {
                    property("text-align", "center")
                    property("animation", "pulse 2s infinite")
                }.toAttrs {
                    id("header")
                },
    ) {
        H1(
            attrs = {
                style {
                    color(Color("#ffffff"))
                    fontSize(48.px)
                    property("font-weight", "bold")
                    margin(0.px)
                    marginBottom(10.px)
                }
            },
        ) {
            Text("Vocal Communication Patterns in Captive Lar Gibbons")
        }
        H2(
            attrs = {
                style {
                    color(Color("#ecf0f1"))
                    fontSize(24.px)
                    property("font-weight", "normal")
                    margin(0.px)
                }
            },
        ) {
            Text("A Behavioral Study at Metro Zoo")
        }
    }
}

@Composable
private fun Authors() {
    Div(
        attrs =
            Modifier
                .fillMaxWidth()
                .padding(20.px)
                .background(Color("#ffffff"))
                .borderRadius(15.px)
                .boxShadow(0.px, 4.px, 12.px, 0.px, rgba(0, 0, 0, 0.1))
                .margin(top = 20.px)
                .styleModifier {
                    property("text-align", "center")
                }.toAttrs {
                    id("authors")
                },
    ) {
        H3(
            attrs = {
                style {
                    fontSize(18.px)
                    color(Color("#34495e"))
                    margin(10.px, 0.px)
                }
            },
        ) {
            Text("Dr. Sarah Johnson¹, Dr. Michael Chen², Dr. Emily Rodriguez³")
        }
        P(
            attrs = {
                style {
                    fontSize(14.px)
                    color(Color("#7f8c8d"))
                    margin(5.px, 0.px)
                }
            },
        ) {
            Text("¹Department of Primatology, University of Wildlife Studies")
            Br()
            Text("²Metro Zoo Research Division")
            Br()
            Text("³Center for Animal Communication Research")
        }
    }
}

@Composable
private fun IntroductionContent() {
    P(attrs = {
        style {
            fontSize(16.px)
            property("line-height", "1.6")
            color(Color("#2c3e50"))
        }
    }) {
        Text("Lar gibbons (")
        I { Text("Hylobates lar") }
        Text(
            ") are small apes known for their extraordinary vocal abilities and complex songs. This study examines the vocal communication patterns of a family group of lar gibbons housed at Metro Zoo over a six-month observation period. ",
        )
        Text(
            "Understanding their vocal behavior in captivity provides insights into social dynamics, welfare indicators, and species-specific communication strategies.",
        )
    }
    P(attrs = {
        style {
            fontSize(16.px)
            property("line-height", "1.6")
            color(Color("#2c3e50"))
            marginTop(15.px)
        }
    }) {
        Text(
            "Our research focuses on three primary objectives: (1) documenting the frequency and types of vocalizations, (2) identifying patterns in daily calling behavior, and (3) correlating vocal activity with environmental and social factors.",
        )
    }
}

@Composable
private fun MethodologyContent() {
    P(attrs = {
        style {
            fontSize(16.px)
            property("line-height", "1.6")
            color(Color("#2c3e50"))
        }
    }) {
        B { Text("Study Subjects: ") }
        Text("Four lar gibbons (2 adults, 2 juveniles) were observed in their naturalistic enclosure at Metro Zoo.")
    }
    P(attrs = {
        style {
            fontSize(16.px)
            property("line-height", "1.6")
            color(Color("#2c3e50"))
            marginTop(10.px)
        }
    }) {
        B { Text("Observation Period: ") }
        Text("Data collection occurred from January to June 2024, with daily observations conducted between 6:00 AM and 6:00 PM.")
    }
    P(attrs = {
        style {
            fontSize(16.px)
            property("line-height", "1.6")
            color(Color("#2c3e50"))
            marginTop(10.px)
        }
    }) {
        B { Text("Data Collection: ") }
        Text(
            "Vocalizations were recorded using directional microphones and video equipment. Each call was classified by type, duration, and context. Environmental factors (temperature, visitor presence, feeding times) were also documented.",
        )
    }
    P(attrs = {
        style {
            fontSize(16.px)
            property("line-height", "1.6")
            color(Color("#2c3e50"))
            marginTop(10.px)
        }
    }) {
        B { Text("Analysis: ") }
        Text(
            "Acoustic analysis was performed using Raven Pro software. Statistical analyses included frequency distributions, temporal patterns, and correlation analyses.",
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
                    property("gap", "20px")
                }.toAttrs(),
    ) {
        ImageCard("Gibbon family group during morning calls")
        ImageCard("Adult male performing territorial song")
        ImageCard("Juvenile gibbon observing adult vocalizations")
        ImageCard("Female gibbon in characteristic calling posture")
    }
}

@Composable
private fun ImageCard(caption: String) {
    Div(
        attrs =
            Modifier
                .borderRadius(10.px)
                .styleModifier {
                    property("overflow", "hidden")
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
                    .background(Color("#e0e0e0"))
                    .styleModifier {
                        property("display", "flex")
                        property("align-items", "center")
                        property("justify-content", "center")
                        property("font-size", "64px")
                        property("animation", "bounce 2s infinite")
                    }.toAttrs(),
        ) {
            Text("🦍")
        }
        Div(
            attrs =
                Modifier
                    .padding(15.px)
                    .fontSize(13.px)
                    .color(Color("#555555"))
                    .styleModifier {
                        property("text-align", "center")
                    }.toAttrs(),
        ) {
            Text(caption)
        }
    }
}

@Composable
private fun DataVisualization() {
    H3(
        attrs = {
            style {
                fontSize(22.px)
                marginBottom(20.px)
                color(Color("#2c3e50"))
            }
        },
    ) {
        Text("Vocalization Analysis")
    }

    Div(
        attrs =
            Modifier
                .fillMaxWidth()
                .margin(bottom = 40.px)
                .display(DisplayStyle.Grid)
                .styleModifier {
                    property("grid-template-columns", "repeat(auto-fit, minmax(350px, 1fr))")
                    property("gap", "25px")
                }.toAttrs(),
    ) {
        InteractivePieChart(
            title = "Vocalization Types Distribution",
            data =
                listOf(
                    "Great Calls" to 35.0,
                    "Duets" to 25.0,
                    "Alarm Calls" to 15.0,
                    "Contact Calls" to 20.0,
                    "Other" to 5.0,
                ),
            colors = listOf("#e74c3c", "#3498db", "#f39c12", "#2ecc71", "#9b59b6"),
        )

        InteractivePieChart(
            title = "Time of Day Distribution",
            data =
                listOf(
                    "Morning (6-10 AM)" to 45.0,
                    "Midday (10 AM-2 PM)" to 20.0,
                    "Afternoon (2-6 PM)" to 25.0,
                    "Evening (6-8 PM)" to 10.0,
                ),
            colors = listOf("#f39c12", "#e67e22", "#d35400", "#c0392b"),
            hidden = false,
        )

        InteractivePieChart(
            title = "Caller Identity",
            data =
                listOf(
                    "Adult Male" to 40.0,
                    "Adult Female" to 35.0,
                    "Juvenile 1" to 15.0,
                    "Juvenile 2" to 10.0,
                ),
            colors = listOf("#3498db", "#e74c3c", "#2ecc71", "#9b59b6"),
        )

        InteractivePieChart(
            title = "Environmental Context",
            data =
                listOf(
                    "Pre-feeding" to 30.0,
                    "Visitor Presence" to 25.0,
                    "Weather Changes" to 15.0,
                    "Spontaneous" to 30.0,
                ),
            colors = listOf("#16a085", "#27ae60", "#2980b9", "#8e44ad"),
            hidden = false,
        )
    }

    H3(
        attrs = {
            style {
                fontSize(22.px)
                marginTop(40.px)
                marginBottom(20.px)
                color(Color("#2c3e50"))
            }
        },
    ) {
        Text("Temporal and Frequency Patterns")
    }

    Div(
        attrs =
            Modifier
                .fillMaxWidth()
                .margin(bottom = 40.px)
                .display(DisplayStyle.Flex)
                .styleModifier {
                    property("flex-direction", "column")
                    property("gap", "25px")
                }.toAttrs(),
    ) {
        InteractiveBarChart(
            title = "Average Daily Vocalizations by Month",
            data =
                listOf(
                    "Jan" to 45.0,
                    "Feb" to 52.0,
                    "Mar" to 58.0,
                    "Apr" to 63.0,
                    "May" to 61.0,
                    "Jun" to 55.0,
                ),
            color = "#3498db",
        )

        InteractiveBarChart(
            title = "Vocalization Duration (seconds) by Type",
            data =
                listOf(
                    "Great Call" to 18.5,
                    "Duet" to 25.3,
                    "Alarm" to 3.2,
                    "Contact" to 2.8,
                    "Other" to 5.1,
                ),
            color = "#e74c3c",
            clickToReveal = false,
        )

        InteractiveBarChart(
            title = "Weekly Calling Frequency Pattern",
            data =
                listOf(
                    "Mon" to 48.0,
                    "Tue" to 53.0,
                    "Wed" to 51.0,
                    "Thu" to 55.0,
                    "Fri" to 62.0,
                    "Sat" to 68.0,
                    "Sun" to 59.0,
                ),
            color = "#2ecc71",
        )
    }

    H3(
        attrs = {
            style {
                fontSize(22.px)
                marginTop(40.px)
                marginBottom(20.px)
                color(Color("#2c3e50"))
            }
        },
    ) {
        Text("Detailed Data Tables")
    }

    Div(
        attrs =
            Modifier
                .fillMaxWidth()
                .styleModifier {
                    property("display", "flex")
                    property("flex-direction", "column")
                    property("gap", "25px")
                }.toAttrs(),
    ) {
        InteractiveDataTable(
            title = "Acoustic Parameters by Vocalization Type",
            headers = listOf("Call Type", "Frequency (Hz)", "Duration (s)", "Intensity (dB)", "Sample Size"),
            rows =
                listOf(
                    listOf("Great Call", "1200-2400", "18.5 ± 3.2", "85 ± 5", "142"),
                    listOf("Duet", "800-2200", "25.3 ± 4.1", "82 ± 4", "98"),
                    listOf("Alarm Call", "2000-3500", "3.2 ± 0.8", "90 ± 6", "67"),
                    listOf("Contact Call", "1500-2000", "2.8 ± 0.6", "75 ± 3", "156"),
                    listOf("Submissive", "600-1200", "5.1 ± 1.2", "70 ± 4", "34"),
                ),
        )

        InteractiveDataTable(
            title = "Social Context of Vocalizations",
            headers = listOf("Context", "Total Calls", "% of Total", "Avg Duration", "Primary Caller"),
            rows =
                listOf(
                    listOf("Territory Defense", "89", "22.5%", "19.2s", "Adult Male"),
                    listOf("Mate Bonding", "76", "19.2%", "26.1s", "Both Adults"),
                    listOf("Infant Care", "45", "11.4%", "8.3s", "Adult Female"),
                    listOf("Food Related", "112", "28.3%", "4.5s", "All Members"),
                    listOf("Alarm Response", "67", "16.9%", "3.1s", "Adult Male"),
                    listOf("Play Behavior", "7", "1.7%", "5.8s", "Juveniles"),
                ),
            doubleClickToReveal = false,
        )
    }
}

@Composable
private fun ResultsContent() {
    Ul(attrs = {
        style {
            fontSize(16.px)
            property("line-height", "1.8")
            color(Color("#2c3e50"))
        }
    }) {
        Li { Text("Morning vocalization peak: 68% of all calls occurred between 6-10 AM, coinciding with natural dawn chorus behavior") }
        Li { Text("Great calls and duets comprised 60% of total vocalizations, indicating strong pair bonding") }
        Li { Text("Significant increase in calling frequency during spring months (March-April), potentially related to breeding season") }
        Li { Text("Adult male produced 40% of vocalizations, establishing territorial presence") }
        Li { Text("Visitor presence correlated with 25% of vocal events, suggesting environmental sensitivity") }
        Li { Text("Juvenile participation in duets increased by 120% over the study period, showing vocal learning") }
    }
}

@Composable
private fun AnalysisContent() {
    P(attrs = {
        style {
            fontSize(16.px)
            property("line-height", "1.6")
            color(Color("#2c3e50"))
        }
    }) {
        Text(
            "The vocal patterns observed in this captive lar gibbon family closely mirror behaviors documented in wild populations, suggesting successful welfare conditions. ",
        )
        Text(
            "The pronounced morning calling peak aligns with territorial advertisement behaviors seen in natural habitats, despite the absence of competing groups.",
        )
    }
    P(attrs = {
        style {
            fontSize(16.px)
            property("line-height", "1.6")
            color(Color("#2c3e50"))
            marginTop(15.px)
        }
    }) {
        Text(
            "The high frequency of duetting behavior (25% of vocalizations) indicates strong pair bonding between the adult male and female. ",
        )
        Text(
            "This is a positive welfare indicator, as duets serve both social bonding and territorial functions. The seasonal increase in vocalizations during spring suggests that circannual rhythms remain intact despite captive conditions.",
        )
    }
    P(attrs = {
        style {
            fontSize(16.px)
            property("line-height", "1.6")
            color(Color("#2c3e50"))
            marginTop(15.px)
        }
    }) {
        Text("The gradual increase in juvenile participation demonstrates active vocal learning, a critical aspect of gibbon development. ")
        Text(
            "This finding has implications for captive breeding programs and reintroduction efforts, as it confirms that social learning can occur in zoo environments when family groups are maintained.",
        )
    }
}

@Composable
private fun ConclusionContent() {
    P(attrs = {
        style {
            fontSize(16.px)
            property("line-height", "1.6")
            color(Color("#2c3e50"))
        }
    }) {
        Text(
            "This study provides comprehensive documentation of vocal communication patterns in captive lar gibbons, demonstrating that many natural behaviors persist in well-managed zoo environments. ",
        )
        Text(
            "The maintenance of species-typical calling patterns, strong pair bonding, and successful intergenerational vocal transmission suggest excellent welfare conditions.",
        )
    }
    P(attrs = {
        style {
            fontSize(16.px)
            property("line-height", "1.6")
            color(Color("#2c3e50"))
            marginTop(15.px)
        }
    }) {
        B { Text("Implications: ") }
        Text("These findings support the use of vocal behavior as a non-invasive welfare assessment tool in captive gibbon populations. ")
        Text(
            "The data can inform enclosure design, visitor management strategies, and enrichment programs for lar gibbons in zoological settings.",
        )
    }
    P(attrs = {
        style {
            fontSize(16.px)
            property("line-height", "1.6")
            color(Color("#2c3e50"))
            marginTop(15.px)
        }
    }) {
        B { Text("Future Research: ") }
        Text(
            "Longitudinal studies tracking vocal development in offspring and comparative analyses across different captive facilities would provide valuable insights into optimizing gibbon husbandry practices.",
        )
    }
}

@Composable
private fun ReferencesContent() {
    Ol(attrs = {
        style {
            fontSize(14.px)
            property("line-height", "1.8")
            color(Color("#2c3e50"))
        }
    }) {
        Li { Text("Geissmann, T. (2002). Duet-splitting and the evolution of gibbon songs. Biological Reviews, 77(1), 57-76.") }
        Li {
            Text(
                "Koda, H., et al. (2013). Experimental evidence for vocal learning in a captive gibbon family. Primates, 54(3), 283-291.",
            )
        }
        Li {
            Text(
                "Mitani, J. C. (1988). Male gibbon (Hylobates agilis) singing behavior: Natural history, song variations and function. Ethology, 79(3), 177-194.",
            )
        }
        Li {
            Text(
                "Reichard, U. H. (2003). Social monogamy in gibbons: The male perspective. In Monogamy: Mating Strategies and Partnerships in Birds, Humans and Other Mammals (pp. 190-213).",
            )
        }
        Li {
            Text(
                "Tan, C. L., et al. (2020). Vocal communication in white-handed gibbons: Implications for conservation. International Journal of Primatology, 41, 456-478.",
            )
        }
        Li {
            Text(
                "Clarke, E., Reichard, U. H., & Zuberbühler, K. (2006). The syntax and meaning of wild gibbon songs. PLoS ONE, 1(1), e73.",
            )
        }
    }
}
