# Activity Result API Demo

This project demonstrates both the modern and legacy approaches to handling activity results in Android. It includes examples of both the new Activity Result API (introduced with AndroidX) and the traditional `startActivityForResult()` method.

## Project Structure

```
ActivityResultExample/
└── app/
    └── src/
        └── main/
            ├── java/edu/northeastern/activityresultexample/
            │   ├── MainActivity.java           # Main entry point with navigation
            │   ├── ModernResultActivity.java   # Modern Activity Result API demo
            │   ├── LegacyResultActivity.java   # Legacy startActivityForResult demo
            │   └── SecondActivity.java         # Target activity that returns results
            ├── res/
            │   └── layout/
            │       ├── activity_main.xml           # Main navigation layout
            │       ├── activity_modern_result.xml  # Modern approach layout
            │       ├── activity_legacy_result.xml  # Legacy approach layout
            │       └── activity_second.xml         # Result sender layout
            └── AndroidManifest.xml
```

## Implementation Details

### Modern Approach (Activity Result API)
Located in `ModernResultActivity.java`
- Uses `ActivityResultLauncher` and `registerForActivityResult()`
- No need to override `onActivityResult()`
- Type-safe contracts
- Better separation of concerns
- Log tag: "ModernResultActivity"

Key code snippet:
```java
private final ActivityResultLauncher<Intent> secondActivityLauncher = 
    registerForActivityResult(
        new ActivityResultContracts.StartActivityForResult(),
        result -> {
            // Handle result here
        }
    );

// Launch second activity
secondActivityLauncher.launch(intent);
```

### Legacy Approach (startActivityForResult)
Located in `LegacyResultActivity.java`
- Uses `startActivityForResult()` and `onActivityResult()`
- Request codes for identifying results
- All results handled in single method
- Log tag: "LegacyResultActivity"

Key code snippet:
```java
// Start activity
startActivityForResult(intent, REQUEST_CODE);

// Handle result
@Override
protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    // Handle result here
}
```

### Result Provider
Located in `SecondActivity.java`
- Common activity that returns results to both modern and legacy implementations
- Log tag: "SecondActivity"

## Logging and Debugging

Each activity includes comprehensive logging to track the flow of execution:

1. **ModernResultActivity Logs:**
   - Activity start: "Modern Activity Result Demo Started"
   - Launch: "Launching Second Activity using Modern API"
   - Result: "Modern API callback received"

2. **LegacyResultActivity Logs:**
   - Activity start: "Legacy Activity Result Demo Started"
   - Launch: "Launching Second Activity using Legacy startActivityForResult"
   - Result: "Legacy callback received - RequestCode: [code]"

3. **SecondActivity Logs:**
   - Start: "Second Activity Started"
   - Result: "Sending result back: [result]"
   - Cancel: "User canceled the operation"

To view logs in Android Studio:
- Open Logcat window
- Filter using: `TAG:ModernResultActivity|LegacyResultActivity|SecondActivity`

## Navigation

The app provides two main buttons in MainActivity:
1. "Modern ActivityResult API Demo" - Opens ModernResultActivity
2. "Legacy startActivityForResult Demo" - Opens LegacyResultActivity

Both approaches allow you to:
1. Launch a second activity
2. Enter text in the second activity
3. Send the result back
4. View the result in the original activity

## Key Differences Between Approaches

Modern Approach (Recommended):
- More structured and type-safe
- Callback-based approach
- No request codes needed
- Can be registered at any time
- Better lifecycle handling

Legacy Approach (Deprecated):
- Uses request codes
- All results handled in one method
- Less flexible
- Deprecated in newer Android versions