# Virasat Namma Guide (Tourism & Travelling)

Virasat Namma Guide is a modern, AI-powered travel companion application dedicated to exploring the rich cultural heritage, majestic landscapes, and historical wonders of Karnataka, India. Built with Jetpack Compose and powered by Gemini AI, it serves as a digital guide for every traveller.

## ✨ Key Features

- **🤖 AI Trip Planner**: Generate personalized, day-wise itineraries for your Karnataka adventures. Just tell the AI where you want to go and for how long.
- **💬 Virasat AI Assistant**: A conversational AI guide ready to answer questions about any destination, historical facts, or travel tips in real-time.
- **🏟️ Explore Karnataka**: Categorized discovery of places, including UNESCO World Heritage sites, Royal Heritage palaces, Wildlife sanctuaries, Hill stations, and pristine Beaches.
- **📖 Rich History & Insights**: Each destination comes with a detailed history, description, and curated insights to enrich your visit.
- **📍 Smart Navigation**: Integrated with Google Maps for seamless one-tap directions to any historical site or attraction.
- **🔐 Secure Access**: User authentication powered by Firebase for a personalized and secure experience.
- **🎨 Premium UI/UX**: A beautiful, responsive interface designed with Material 3, featuring smooth animations and high-quality visuals.

## 🛠️ Tech Stack

- **Language**: [Kotlin](https://kotlinlang.org/)
- **UI Framework**: [Jetpack Compose](https://developer.android.com/jetpack/compose)
- **Architecture**: MVVM (Model-View-ViewModel)
- **Design System**: Material Design 3
- **AI Integration**: [Google Gemini AI (Generative AI SDK)](https://ai.google.dev/)
- **Backend**: [Firebase](https://firebase.google.com/) (Authentication & Common SDK)
- **Navigation**: Compose Navigation
- **Image Loading**: [Coil](https://coil-kt.github.io/coil/) (Optimized for performance)
- **Networking**: OkHttp & Retrofit

## 🚀 Getting Started

### Prerequisites

- Android Studio Ladybug or later.
- A Google Gemini API Key.
- A Firebase project (for Authentication).

### Setup

1. **Clone the repository**:
   ```bash
   git clone https://github.com/your-username/VirasatNammaGuide.git
   ```

2. **API Key Setup**:
   Create a `local.properties` file in the root directory (if not already present) and add your Gemini API key:
   ```properties
   API_KEY=your_gemini_api_key_here
   ```

3. **Firebase Setup**:
   - Add your `google-services.json` file to the `app/` directory.
   - Enable Email/Password authentication in your Firebase Console.

4. **Build and Run**:
   Open the project in Android Studio and run it on a physical device or emulator.

## 📸 Screenshots

*(Add your screenshots here to showcase the beautiful UI!)*

## 🗺️ Why "Virasat Namma Guide"?

*Virasat* (Heritage) and *Namma* (Our) reflect our mission: to bring "Our Heritage" closer to everyone through modern technology, making Karnataka's tourism more accessible, informative, and engaging for the global traveller.

## 🤝 Contributing

Contributions are welcome! If you'd like to improve the AI prompts, add more destinations, or enhance the UI, feel free to open a Pull Request.

## 📄 License

This project is licensed under the MIT License.
