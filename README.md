# 🦍 Interactive Lar Gibbon Research Poster

A fully interactive, animated research poster built with Kobweb (Kotlin/JS) showcasing vocal communication patterns in captive lar gibbons.

## ✨ Key Features

### 🎮 **Fully Interactive Experience**
- **Fixed side navigation menu** with smooth scrolling
- **Collapsible sections** for content organization
- **Hidden content** requiring user interaction to discover
- **Interactive charts and tables** with hover effects and animations
- **30+ interactive elements** throughout the poster

### 📊 **Data Visualizations**
- **4 Interactive Pie Charts** with hover highlighting and locked content
- **3 Interactive Bar Charts** with dynamic scaling and value reveals
- **2 Interactive Data Tables** with multidimensional highlighting
- **Animated transitions** on all data elements

### 🎨 **Rich Animations**
- Fade-in, slide-in, and scale animations
- Pulse, bounce, and glow effects
- Smooth transitions on all interactions
- Custom scrollbar styling
- Hardware-accelerated transforms

### 🔐 **Hidden Content System**
Some content is hidden behind interactions to encourage exploration:
- **2 pie charts** - Click title to reveal (🔒)
- **1 bar chart** - Click title to reveal (🔒)
- **1 data table** - Double-click title to reveal (🔒)
- **Multiple collapsible sections** - Click headers to expand

## 📁 Project Structure

```
site/src/jsMain/kotlin/xyz/malefic/staticsite/
├── pages/
│   └── Index.kt                    # Main poster page with all content
└── components/
    ├── SideMenu.kt                 # Fixed navigation sidebar
    ├── CollapsibleSection.kt       # Expandable/collapsible sections
    ├── InteractivePieChart.kt      # Hover & click interactive pie charts
    ├── InteractiveBarChart.kt      # Hover & value display bar charts
    ├── InteractiveDataTable.kt     # Multi-highlight data tables
    ├── AnimatedSection.kt          # Animation wrapper component
    ├── PieChart.kt                 # Base pie chart component
    ├── BarChart.kt                 # Base bar chart component
    └── DataTable.kt                # Base data table component

site/src/jsMain/resources/public/
└── styles.css                      # CSS animations and styling
```

## 🎯 Interactive Elements Guide

### Side Navigation Menu
- **Toggle button** (◀/▶) to show/hide menu
- **10 section links** with icons
- **Smooth scroll** to any section
- **Hover effects** on menu items

### Charts
- **Hover** over pie chart legends to highlight slices
- **Hover** over bars to see exact values
- **Click** locked charts to reveal hidden data
- **Smooth animations** on all interactions

### Tables
- **Hover** over cells for row/column highlighting
- **Intersection highlighting** in green
- **Double-click** titles for hidden tables
- **Scale effects** on active cells

### Sections
- **Click headers** to expand/collapse
- **Animated arrows** indicate state
- **Smooth transitions** on expand/collapse
- **Strategic defaults** (some start collapsed)

## 📊 Content Overview

### Research Content
- **Title**: Vocal Communication Patterns in Captive Lar Gibbons
- **Authors**: 3 researchers with affiliations
- **Methodology**: Study design and data collection methods
- **4 Gibbon Images**: Placeholder cards with animations
- **Results**: 6 key findings
- **Analysis**: 3 paragraphs of discussion
- **Conclusion**: Implications and future research
- **References**: 6 academic citations

### Data Included
- **Vocalization type distribution** (5 categories)
- **Time of day patterns** (4 time slots)
- **Caller identity** (4 individuals)
- **Environmental triggers** (4 contexts)
- **Monthly trends** (6 months)
- **Vocalization durations** (5 types)
- **Weekly patterns** (7 days)
- **Acoustic parameters** (5 call types)
- **Social contexts** (6 scenarios)

## 🚀 Running the Project

### Prerequisites
- Java/JDK installed
- Kotlin support
- Node.js and npm
- Kobweb CLI

### Build & Run
```bash
# Navigate to project root
cd /home/malefic/Code/LarGibbonResearchPoster

# Clean build
./gradlew clean

# Build project
./gradlew build

# Run with Kobweb (once webpack issue is resolved)
cd site
kobweb run

# Alternative: Export static site
./gradlew :site:kobwebExport
```

## ⚠️ Known Issues

### Webpack Module Resolution
The project currently has a webpack module resolution issue. See `WEBPACK_FIX_README.md` for:
- Detailed problem description
- What was attempted
- 4 potential solution approaches
- Workaround options

**Status**: The Kotlin/JS code compiles successfully. Only the development server startup is affected.

## 🎨 Styling & Design

### Color Scheme
- **Primary**: #2c3e50 (Dark blue-gray)
- **Secondary**: #3498db (Bright blue)
- **Accent**: #e74c3c (Red), #2ecc71 (Green), #f39c12 (Orange)
- **Background**: #f0f4f8 (Light blue-gray)
- **Cards**: #ffffff (White)

### Typography
- **Headers**: Bold, sized 28-48px
- **Body**: 14-16px with 1.6-1.8 line-height
- **Font**: Arial, sans-serif

### Animations
- **Duration**: 0.3s - 2s depending on effect
- **Easing**: ease-in-out, ease-out
- **Delays**: Staggered 0.1s - 1.0s intervals

## 📝 Implementation Details

### Technology Stack
- **Framework**: Kobweb (Kotlin/JS)
- **UI**: Compose for Web
- **Styling**: CSS + Kotlin DSL
- **Animations**: CSS keyframes
- **State Management**: Compose runtime state

### Interactive Component Features
- **Hover tracking** with `remember { mutableStateOf() }`
- **Click handlers** for reveals and toggles
- **Smooth transitions** via CSS
- **Event listeners** for mouse interactions
- **Dynamic styling** based on state

### Performance Optimizations
- CSS-based animations (GPU accelerated)
- Efficient state updates
- Lazy content rendering
- Transition delays for staggered effects

## 🎓 Educational Value

This poster demonstrates:
- **Scientific presentation** of research data
- **Interactive data visualization** techniques
- **User engagement** through hidden content
- **Progressive disclosure** of information
- **Modern web technologies** in academic contexts

## 🔄 Future Enhancements

Potential additions:
- [ ] Audio playback of gibbon vocalizations
- [ ] Video clips of observed behaviors
- [ ] Interactive timeline of observations
- [ ] Downloadable PDF version
- [ ] More detailed acoustic spectrograms
- [ ] Comparative data with wild populations
- [ ] User annotations and notes
- [ ] Share functionality

## 📄 License & Credits

Created for educational demonstration of interactive research posters using modern web technologies.

**Research Content**: Example data for demonstration purposes
**Framework**: Kobweb by Varabyte
**Development**: Interactive poster template

## 📞 Support

For issues related to:
- **Webpack**: See `WEBPACK_FIX_README.md`
- **Interactive features**: See `INTERACTIVE_FEATURES.md`
- **General setup**: See standard Kobweb documentation

---

**Total Lines of Code**: ~2000+ lines across 10+ files
**Interactive Elements**: 30+
**Hidden Content Items**: 4
**Animations**: 12+ types
**Sections**: 10 navigable
**Visualizations**: 9 charts/tables
