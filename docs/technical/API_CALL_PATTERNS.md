# API Call Patterns: Serial vs Parallel

## Serial API Calls (Currently Active)

**Location:** `UserRepoScreenViewModel.kt` → `loadApiData()`

Calls execute **one after another** - second call waits for first to complete.

**How to Switch:** Both functions (`loadApiData` and `loadApiDataParallel`) are available in the ViewModel. Simply update the `LaunchedEffect` call in `UserRepoScreen.kt`.

```kotlin
viewModelScope.launch {
    loadUserProfile(login)      // Call 1: Wait for completion
    loadUserRepositories()      // Call 2: Then execute
}
// Total time: time1 + time2
```

**Use when:**
- Second API needs data from first API
- Simple implementation
- Order matters

### Real Logs from GithubCruise App:

```
2026-07-24 12:02:54.123  UserRepoScreenViewModel: loadUserProfile - START
2026-07-24 12:02:55.777  UserRepoScreenViewModel: loadUserProfile - END (1654ms)
2026-07-24 12:02:55.778  UserRepoScreenViewModel: loadUserRepositories - START
2026-07-24 12:02:56.096  UserRepoScreenViewModel: loadUserRepositories - END (318ms)

Total Time: 1972ms (1.97 seconds)
```

**Pattern:** Profile API completes first (1654ms), **THEN** repositories API starts (318ms).

---

## Parallel API Calls (Available)

**Location:** `UserRepoScreenViewModel.kt` → `loadApiDataParallel()`

Calls execute **at the same time** - both start immediately.

```kotlin
viewModelScope.launch {
    val profile = async { loadUserProfile(login) }
    val repos = async { loadUserRepositories() }

    profile.await()  // Wait for both
    repos.await()
}
// Total time: max(time1, time2) - 16% faster!
```

**Use when:**
- APIs are independent (don't need each other's data)
- Want faster loading
- Both needed for UI

### ✅ SUCCESS: Parallel Execution Working!

**Test Results with `loadApiDataParallel()`:**

```
2026-07-24 12:55:37.961  UserRepoScreenViewModel: loadUserProfile - START
2026-07-24 12:55:37.963  UserRepoScreenViewModel: loadUserRepositories - START  ← Only 2ms gap! ✅
2026-07-24 12:55:38.221  UserRepoScreenViewModel: loadUserProfile - END (259ms)
2026-07-24 12:55:38.394  UserRepoScreenViewModel: loadUserRepositories - END (431ms)

Total Time: ~433ms (max of 259ms + 431ms = time of slowest API)
```

**✅ Confirmed: TRUE PARALLEL EXECUTION**

- **Both APIs started nearly simultaneously** (only 2ms difference)
- **Profile API:** 259ms
- **Repositories API:** 431ms
- **Total time:** ~433ms (not 690ms!)
- **Speed improvement:** ~40% faster than serial execution

---

## Comparison

| Pattern | Test Results | Total Time | Status |
|---------|-------------|------------|--------|
| **Serial (`loadApiData`)** | Profile (1654ms) → Repos (318ms) | **1972ms** | ✅ Working |
| **Parallel (`loadApiDataParallel`)** | Profile (259ms) \|\| Repos (431ms) | **~433ms** | ✅ **Working** - True parallel execution |
| **Performance Gain** | Both APIs run simultaneously | **~40% faster** | 🎯 433ms vs 1972ms serial |

---

## Why We Use Serial in This Project

**Intentional for demonstration purposes.**

Serial calls are easier to understand and debug. This project shows the **simple pattern first**.

For production apps with independent APIs, use **parallel** for better performance.

---

## How to Switch Between Serial and Parallel

Both functions are available in `UserRepoScreenViewModel.kt`:

**Step 1:** Open `UserRepoScreen.kt`
- Find: `LaunchedEffect(key1 = login) { viewModel.loadApiData(login) }`
- Change to: `LaunchedEffect(key1 = login) { viewModel.loadApiDataParallel(login) }`

**Step 2:** Run the app and check logs
- You should see both API calls start at the same time
- Total time will be ~1654ms instead of ~1972ms

---

## Official Documentation

**Kotlin Coroutines - Concurrent using async:**
https://kotlinlang.org/docs/composing-suspending-functions.html#concurrent-using-async
