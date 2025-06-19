# 📝 Smart Notes

Smart Notes is a modern Android application built using **Jetpack Compose** that provides rich text editing capabilities along with image insertion, markdown preview, and font customization.

---

## 🚀 Features

- ✍️ **Rich Text Editor** (Font size, color, family customization)
- 🖼️ **Insert Images at Cursor Position**
- 👀 **Live Markdown Preview Mode**
- 🎨 **Font customization panel**
- 🗂️ **Edit & Save notes with Room DB**
- 🔄 **Toggle between Preview & Edit modes**

---

## 🛠️ Tech Stack

| Layer              | Technology                     |
|--------------------|--------------------------------|
| **Language**       | Kotlin                         |
| **UI**             | Jetpack Compose                |
| **Architecture**   | MVVM                           |
| **DI**             | Hilt                           |
| **State Handling** | StateFlow, MutableState        |
| **Database**       | Room DB                        |
| **Image Loading**  | Coil                           |
| **Build System**   | Gradle (KTS)                   |

---

## 📦 Libraries Used

- [Jetpack Compose](https://developer.android.com/jetpack/compose)
- [Hilt](https://dagger.dev/hilt/)
- [Coil](https://coil-kt.github.io/coil/)
- [Room](https://developer.android.com/jetpack/androidx/releases/room)
- [MarkdownText](https://github.com/jeziellago/compose-markdown) for preview
- [Activity Compose](https://developer.android.com/jetpack/compose/interop/interop-activity)

---

## 📁 Folder Structure

├── presentation
│ ├── addNote # UI and editor screen
│ └── viewmodel # NoteViewModel with DI
├── data
│ ├── local # Room database and DAO
│ └── model # Note data model
└── di
└── AppModule # Hilt modules


---

## 🧠 How to Contribute

1. Fork the repository
2. Create a feature branch (`git checkout -b feature/myFeature`)
3. Commit your changes
4. Push and create a PR

---

## 📥 Setup Instructions

```bash
git clone https://github.com/yourusername/smart-notes.git
cd smart-notes
./gradlew build
```

