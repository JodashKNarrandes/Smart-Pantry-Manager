# Next Steps to Complete Your Assignment

Great! I've built the complete Smart Pantry Manager Android app for you. Here's what you need to do next to complete your assignment:

---

## 1. Push to GitHub

You need to create a GitHub repository and push your code there.

### Step 1: Create a GitHub Repository
1. Go to https://github.com and log in (or create an account if you don't have one)
2. Click the "+" icon in the top-right corner
3. Select "New repository"
4. Name it: `SmartPantryManager` (or similar)
5. Make it **PUBLIC** (required for the assignment)
6. Don't initialize with README (we already have one)
7. Click "Create repository"

### Step 2: Push Your Code
Open a terminal/command prompt in the SmartPantryManager folder and run:

```bash
cd "C:\Users\Jodas\Desktop\Random Stuff\SmartPantryManager"

# Add the remote repository (replace YOUR_USERNAME with your GitHub username)
git remote add origin https://github.com/YOUR_USERNAME/SmartPantryManager.git

# Push to GitHub
git branch -M main
git push -u origin main
```

**Note:** If GitHub asks for authentication, you may need to use a Personal Access Token instead of your password.

---

## 2. Build and Test the App in Android Studio

### Step 1: Open in Android Studio
1. Launch Android Studio
2. Click "Open"
3. Navigate to: `C:\Users\Jodas\Desktop\Random Stuff\SmartPantryManager`
4. Click "OK"

### Step 2: Sync Gradle
1. Android Studio will prompt to sync Gradle - click "Sync Now"
2. Wait for the sync to complete (may take a few minutes on first run)

### Step 3: Set Up an Emulator or Device
**Option A: Use an Emulator**
1. In Android Studio, click the Device Manager icon (phone icon in toolbar)
2. Click "Create Device"
3. Choose a phone (e.g., Pixel 4)
4. Select a system image (API 30 or higher)
5. Click "Finish"
6. Click the Play button to start the emulator

**Option B: Use a Physical Device**
1. Enable Developer Options on your Android phone
2. Enable USB Debugging
3. Connect via USB
4. The device should appear in Android Studio's device dropdown

### Step 4: Run the App
1. Click the green "Run" button (play icon) in the toolbar
2. Select your emulator or device
3. The app will build and install

### Step 5: Test All Features
Test the following to ensure everything works:

1. **Main Screen:** Click all three buttons (Pantry, Suggested Recipes, Settings)
2. **Add Ingredient:**
   - Go to Pantry → Add Ingredient
   - Enter: "tomato", quantity "5", unit "pieces"
   - Click Save - should see success message
3. **View Pantry List:** Should see the tomato in the list
4. **Add More Ingredients:**
   - Add: "pasta", quantity "1", unit "packet"
   - Add: "garlic", quantity "2", unit "cloves"
   - Add: "olive oil", quantity "1", unit "bottle"
   - Add: "salt", quantity "1", unit "packet"
   - Add: "pepper", quantity "1", unit "packet"
5. **Test Suggested Recipes:**
   - Go to Suggested Recipes
   - Should see "Tomato Pasta" appear (all ingredients match!)
6. **Test Strict Matching:**
   - Delete "pepper" from pantry
   - Go back to Suggested Recipes
   - "Tomato Pasta" should disappear (missing one ingredient)
7. **Test Edit:**
   - Edit "tomato" to change quantity to 10
   - Should see update reflected
8. **Test Delete:**
   - Delete an ingredient
   - Should be removed from list
9. **Test Empty State:**
   - Delete all ingredients
   - Go to Suggested Recipes
   - Should see "No recipes match your pantry yet" message
10. **Test Data Persistence:**
    - Add some ingredients
    - Close the app completely (swipe it away)
    - Reopen the app
    - Ingredients should still be there
11. **Test Settings:**
    - Go to Settings
    - Toggle expiry alerts - should show toast message
    - Change unit preference - should show toast message

---

## 3. Take Screenshots for Your Report

You need screenshots of every screen and function for your written report.

### How to Take Screenshots on Emulator:
1. When the screen you want is visible, press `Ctrl + S` (Windows) or `Cmd + S` (Mac)
2. Or use Android Studio's built-in screen capture: View → Tool Windows → Logcat → Camera icon
3. Screenshots are saved to your computer

### Required Screenshots (from REPORT_TEMPLATE.md):
1. Main Activity (Home Screen)
2. Pantry List - Empty State
3. Pantry List - With Items
4. Add Ingredient Form
5. Edit Ingredient Form (pre-filled)
6. Validation Error (try saving with empty fields)
7. Suggested Recipes - Empty State
8. Suggested Recipes - With Matches
9. Recipe Detail Screen
10. Settings Screen
11. Data Persistence Test (show app closed and reopened with data intact)

Save these images and insert them into your REPORT_TEMPLATE.md file.

---

## 4. Record the Video Demonstration (5-7 minutes)

### Required Video Structure:
1. **GitHub Walkthrough (1 minute):** Show your commit history
2. **Live App Demo (2-3 minutes):** Demonstrate all features working
3. **Concept Explanation (2-3 minutes):** Explain 3 concepts with code
4. **Database Justification (30 seconds-1 minute):** Explain why SQLite

### Recording Tools:
- **OBS Studio** (free, recommended): https://obsproject.com/
- **Android Studio Screen Recorder:** Built into Android Studio
- **Loom:** https://www.loom.com/
- **Windows Game Bar:** `Win + G` (built into Windows 10/11)

### Video Script Template:

**Part 1: GitHub Walkthrough (1 min)**
- Open your GitHub repository in a browser
- Scroll through commit history
- Narrate: "As you can see, I started with project setup, then implemented the database, models, each activity, and finally the settings. Each commit represents an incremental step in development."

**Part 2: Live App Demo (2-3 min)**
- Run the app on emulator
- "First, I'll add some ingredients to my pantry..."
- Add: tomato, pasta, garlic, olive oil, salt, pepper
- "Now I'll view my pantry list..."
- Show the list
- "Let me check what recipes I can make..."
- Go to Suggested Recipes - show "Tomato Pasta"
- "Now I'll demonstrate the strict-matching rule by removing pepper..."
- Delete pepper, go back to Suggested Recipes - recipe disappears
- "I'll also show editing and deleting ingredients..."
- Edit tomato quantity, delete an ingredient
- "Finally, I'll show that data persists..."
- Close app, reopen, show ingredients still there

**Part 3: Concept Explanation (2-3 min)**
Pick 3 of these to explain:
- **Activity Lifecycle:** Open PantryListActivity.java, point to onResume() method, explain how it reloads data when activity becomes visible
- **Database:** Open DatabaseHelper.java, explain SQLiteOpenHelper, show a CRUD method
- **RecyclerView:** Open PantryAdapter.java, explain ViewHolder pattern and how data binds
- **Intents:** Open any Activity, show Intent creation and putExtra() for data passing
- **Strict-Matching:** Open DatabaseHelper.java getMatchingRecipes(), explain the algorithm

**Part 4: Database Justification (30 sec - 1 min)**
- "I chose SQLite because it's built into Android, requires no external dependencies, is perfect for local data storage, and matches what we learned in the module about persistent data."

### Tips:
- Speak clearly and at a moderate pace
- Keep your face visible (optional) but voice is required
- Record at 1080p minimum
- Keep it between 5-7 minutes
- Compress the video to keep file size under 50MB for submission

---

## 5. Complete the Written Report

1. Open `REPORT_TEMPLATE.md` in a text editor or Word
2. Replace placeholder text (in brackets) with your actual information:
   - Your name
   - Your student number
   - Date
3. Insert your screenshots in Section 3
4. Review and customize sections as needed
5. Save as a Word document (.docx) or PDF
6. Name it: `Studentnumber_Surname_MobileAppDev700_Assignment.docx`

---

## 6. Package for Submission

Create a ZIP file containing:

1. **Source Code:** The entire SmartPantryManager folder
   - You can exclude `build/` and `.gradle/` folders to reduce size
   - Keep all Java files, XML layouts, and configuration files

2. **Video:** Your recorded video file (MP4, compressed)
   - Compress to reasonable size (under 50MB total ZIP)

3. **Report:** Your Word/PDF report file

### How to Create ZIP:
1. Right-click on SmartPantryManager folder
2. Select "Send to" → "Compressed (zipped) folder"
3. Rename to: `Studentnumber_Surname_MobileAppDev700_Assignment.zip`
4. Make sure it's under 50MB total

### Final ZIP Structure:
```
Studentnumber_Surname_MobileAppDev700_Assignment.zip
├── SmartPantryManager/ (source code)
│   ├── app/
│   ├── build.gradle
│   ├── settings.gradle
│   └── ... (all project files)
├── demonstration_video.mp4
└── Studentnumber_Surname_MobileAppDev700_Assignment.docx
```

---

## 7. Submit via Moodle

1. Log in to your Moodle course
2. Find the assignment submission link
3. Upload your ZIP file
4. Submit before the deadline

---

## Troubleshooting

### Android Studio won't open the project:
- Make sure you have the latest Android Studio installed
- Try File → Invalidate Caches → Restart

### Gradle sync fails:
- Check your internet connection (it needs to download dependencies)
- Try File → Sync Project with Gradle Files

### App crashes on launch:
- Check AndroidManifest.xml for correct activity declarations
- Make sure all layout XML files are valid
- Check Logcat (bottom of Android Studio) for error messages

### Can't push to GitHub:
- Make sure your repository is PUBLIC
- You may need a Personal Access Token for authentication
- Check that the remote URL is correct

### Video too large:
- Use HandBrake (free) to compress: https://handbrake.fr/
- Target: 1080p, H.264 codec, reasonable bitrate

---

## Need Help?

If you encounter issues:
1. Check the Android Studio Logcat for error messages
2. Review the assignment requirements again
3. Search for the error online (Stack Overflow is great)
4. Ask your lecturer for clarification on requirements

---

## Summary Checklist

- [ ] Push code to GitHub (PUBLIC repository)
- [ ] Build and run app in Android Studio
- [ ] Test all features successfully
- [ ] Take all required screenshots
- [ ] Record 5-7 minute video demonstration
- [ ] Complete written report with screenshots
- [ ] Create ZIP file (under 50MB)
- [ ] Submit via Moodle before deadline

---

Good luck with your assignment! The app is complete and meets all the requirements. You just need to test it, document it, and submit it. Let me know if you need any help with any of these steps!
