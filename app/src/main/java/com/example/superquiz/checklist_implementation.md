# ✅ CHECKLIST D'IMPLÉMENTATION - Mpikabary Quiz

## 📁 Structure des dossiers

```
app/src/main/
├── java/com/example/superquiz/
│   ├── MainActivity.java
│   ├── WelcomeFragment.java
│   ├── KabaryInfoPagerFragment.java
│   ├── KabaryInfoPageFragment.java
│   ├── QuizFragment.java
│   ├── ResultFragment.java
│   │
│   ├── model/
│   │   └── Question.java
│   │
│   ├── database/
│   │   └── DatabaseHelper.java
│   │
│   └── manager/
│       └── QuizManager.java
│
└── res/
    ├── layout/
    │   ├── activity_main.xml
    │   ├── fragment_welcome.xml
    │   ├── fragment_kabary_info_pager.xml
    │   ├── fragment_kabary_info_page.xml
    │   ├── fragment_quiz.xml
    │   └── fragment_result.xml
    │
    └── drawable/
        ├── bordered_beige_text.xml
        ├── ic_speech.xml
        ├── ic_checklist.xml
        ├── ic_structure.xml
        ├── ic_lightbulb.xml
        ├── ic_trophy_gold.xml
        ├── ic_trophy_silver.xml
        ├── ic_trophy_bronze.xml
        ├── ic_book.xml
        ├── ic_info.xml
        └── logo_mpikabary.png (à ajouter manuellement)
```

---

## 🔥 ÉTAPES D'INSTALLATION (ORDRE IMPORTANT)

### Étape 1️⃣ : Créer les packages
- [ ] Créer package `model`
- [ ] Créer package `database`
- [ ] Créer package `manager`

### Étape 2️⃣ : Supprimer l'ancien code
- [ ] Supprimer `MainActivity.kt`
- [ ] Supprimer l'ancien `QuizFragment.java` (s'il existe)
- [ ] Supprimer l'ancien `WelcomeFragment.java` (s'il existe)

### Étape 3️⃣ : Ajouter les fichiers Java

#### Package principal (`com.example.superquiz`)
- [ ] `MainActivity.java`
- [ ] `WelcomeFragment.java`
- [ ] `KabaryInfoPagerFragment.java`
- [ ] `KabaryInfoPageFragment.java`
- [ ] `QuizFragment.java`
- [ ] `ResultFragment.java`

#### Package `model`
- [ ] `Question.java`

#### Package `database`
- [ ] `DatabaseHelper.java`

#### Package `manager`
- [ ] `QuizManager.java`

### Étape 4️⃣ : Ajouter les layouts XML

#### Dans `res/layout/`
- [ ] `activity_main.xml`
- [ ] `fragment_welcome.xml`
- [ ] `fragment_kabary_info_pager.xml`
- [ ] `fragment_kabary_info_page.xml`
- [ ] `fragment_quiz.xml`
- [ ] `fragment_result.xml`

### Étape 5️⃣ : Ajouter les drawables

#### Dans `res/drawable/`
- [ ] `bordered_beige_text.xml`
- [ ] `ic_speech.xml`
- [ ] `ic_checklist.xml`
- [ ] `ic_structure.xml`
- [ ] `ic_lightbulb.xml`
- [ ] `ic_trophy_gold.xml`
- [ ] `ic_trophy_silver.xml`
- [ ] `ic_trophy_bronze.xml`
- [ ] `ic_book.xml`
- [ ] `ic_info.xml`

### Étape 6️⃣ : Logo (optionnel mais recommandé)

#### Créer un logo simple
- [ ] Ajouter `logo_mpikabary.png` dans `res/drawable/`

**Si tu n'as pas de logo**, tu peux utiliser une icône temporaire :

```xml
<!-- Dans fragment_welcome.xml, remplace : -->
android:src="@drawable/logo_mpikabary"
<!-- par : -->
android:src="@drawable/ic_speech"
```

### Étape 7️⃣ : Mise à jour Gradle
- [ ] Ouvrir `build.gradle.kts (Module: app)`
- [ ] Remplacer tout le contenu par le fichier fourni
- [ ] Cliquer sur **"Sync Now"**

### Étape 8️⃣ : Vérifications

#### AndroidManifest.xml
Vérifie que MainActivity est bien déclarée :

```xml
<activity
    android:name=".MainActivity"
    android:exported="true">
    <intent-filter>
        <action android:name="android.intent.action.MAIN" />
        <category android:name="android.intent.category.LAUNCHER" />
    </intent-filter>
</activity>
```

### Étape 9️⃣ : Build
- [ ] **Build → Clean Project**
- [ ] **Build → Rebuild Project**
- [ ] Attendre la fin du build (sans erreurs)

### Étape 🔟 : Test
- [ ] Lancer l'app sur émulateur/téléphone
- [ ] Tester le bouton "Découvrir" → Pages d'info
- [ ] Tester la navigation entre les pages (swipe + boutons)
- [ ] Tester "Passer" pour revenir à l'accueil
- [ ] Entrer un nom et cliquer "Commencer l'aventure"
- [ ] Répondre aux 6 questions
- [ ] Vérifier l'écran de résultats
- [ ] Tester les boutons "Recommencer" et "Accueil"

---

## 🐛 Résolution de problèmes courants

### Problème 1 : "Cannot resolve symbol R"
**Solution** : 
1. Vérifier que tous les XML sont sans erreur
2. Build → Clean Project
3. File → Invalidate Caches / Restart

### Problème 2 : "ViewPager2 not found"
**Solution** :
1. Vérifier `build.gradle.kts` contient : `implementation("androidx.viewpager2:viewpager2:1.0.0")`
2. Sync Gradle

### Problème 3 : App crash au démarrage
**Solution** :
1. Ouvrir Logcat
2. Chercher l'erreur rouge
3. Vérifier que tous les layouts existent
4. Vérifier les noms de packages dans les imports

### Problème 4 : "logo_mpikabary not found"
**Solution** :
Dans `fragment_welcome.xml`, remplacer :
```xml
android:src="@drawable/logo_mpikabary"
```
par :
```xml
android:src="@drawable/ic_speech"
```

### Problème 5 : Les icônes info ne s'affichent pas
**Solution** :
Tous les drawables doivent être créés. Si une icône manque, remplacer par :
```xml
android:src="@drawable/ic_info"
```

### Problème 6 : Database error
**Solution** :
1. Désinstaller complètement l'app
2. Réinstaller (pour recréer la BDD)

---

## 🎯 Points d'attention

### ⚠️ IMPORTANT - ViewBinding
Assurez-vous que dans `build.gradle.kts`, ViewBinding est **DÉSACTIVÉ** :

```kotlin
buildFeatures {
    // viewBinding = true  // ← DOIT être commenté ou supprimé
}
```

### ⚠️ Packages corrects
Vérifiez que tous les fichiers Java ont le bon package en première ligne :

```java
// Fichiers principaux
package com.example.superquiz;

// Dans model/
package com.example.superquiz.model;

// Dans database/
package com.example.superquiz.database;

// Dans manager/
package com.example.superquiz.manager;
```

### ⚠️ Imports
Android Studio devrait auto-importer, mais si erreurs :
- **Alt + Enter** (Windows/Linux)
- **Option + Enter** (Mac)

---

## 🎨 Personnalisation future

### Ajouter plus de questions
Dans `DatabaseHelper.java`, méthode `insertInitialQuestions()` :

```java
insertQuestion(db,
    "Ta nouvelle question ?",
    "Réponse A",
    "Réponse B", 
    "Réponse C",
    "Réponse D",
    "B",  // Bonne réponse
    "Ton indice",
    1  // Difficulté
);
```

### Changer les couleurs
Dans les fichiers XML, modifier les valeurs hexadécimales :
- `#F5E6D3` → Background beige
- `#8D6E63` → Marron principal
- `#4CAF50` → Vert validation
- `#3E2723` → Texte foncé

### Ajouter plus de pages d'info
1. Dans `KabaryInfoPagerFragment.java`, changer `getItemCount()` : `return 5;` au lieu de `4`
2. Ajouter un `case 4:` dans `KabaryInfoPageFragment.java`

---

## 📊 Statistiques du projet

- **9 fichiers Java** (logique métier)
- **6 fichiers XML Layout** (interface)
- **10 fichiers XML Drawable** (icônes)
- **6 questions** pré-chargées en BDD
- **4 pages** d'information éducative
- **5 fragments** pour la navigation

---

## 🚀 Prêt à lancer !

Une fois tous les fichiers en place :
1. ✅ Clean & Rebuild
2. ✅ Lancer l'app
3. ✅ Tester toutes les fonctionnalités
4. 🎉 **Profiter de ton quiz Mpikabary !**

---

**Bonne chance ! 🎭📱**
