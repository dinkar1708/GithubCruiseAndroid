# Configuration Changes in Android

## What are Configuration Changes?

Configuration changes are system events like screen rotation, language change, dark mode toggle, font size change, etc. Android destroys and recreates the Activity when these occur.

**Official Documentation:** [Handle Configuration Changes](https://developer.android.com/guide/topics/resources/runtime-changes)

---

## How GitHub Cruise Preserves State

### SearchBox Example

**File:** `ui/shared/SearchBox.kt:57`

```kotlin
// Search text survives ALL configuration changes (rotation, language, dark mode, etc.)
var searchText by rememberSaveable { mutableStateOf("") }
```

**How it works:** `rememberSaveable` uses Android's Bundle to save/restore state when Activity is recreated.

### Scroll Position

**File:** `ui/features/users/view/UsersListView.kt:55`

```kotlin
// Scroll position preserved automatically
val scrollState = rememberLazyListState()
```

**Note:** `rememberLazyListState()` uses `rememberSaveable` internally, so scroll position is automatically preserved.

---

**References:**
- [Handle Configuration Changes](https://developer.android.com/guide/topics/resources/runtime-changes)
- [Save UI State](https://developer.android.com/topic/libraries/architecture/saving-states)
- [State Saving in Compose](https://developer.android.com/develop/ui/compose/state-saving)
