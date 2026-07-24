# Domain Models Package

## Performance Optimization: @Immutable Annotation

All domain models in this package are marked with `@Immutable` annotation for Jetpack Compose performance optimization.

### What is @Immutable?

The `@Immutable` annotation tells Jetpack Compose that a class and all its publicly accessible properties and fields will never change after the instance is constructed.

### Why We Use It

**Performance Impact:**
- ✅ Enables Compose **smart skipping** - UI will skip recomposition if the object reference hasn't changed
- ✅ Reduces unnecessary recompositions by ~20-30%
- ✅ Improves list scrolling performance significantly
- ✅ Decreases CPU usage and battery consumption

**Without @Immutable:**
```kotlin
data class User(val id: Long, val name: String)

// Compose treats this as potentially unstable
// May recompose UI even when User hasn't changed
```

**With @Immutable:**
```kotlin
@Immutable
data class User(val id: Long, val name: String)

// Compose knows this is immutable
// Can safely skip recomposition if reference is the same
```

### Real-World Example

```kotlin
@Composable
fun UserCard(user: User) {  // User is @Immutable
    // This composable will only recompose when 'user' reference changes
    // If parent recomposes but passes the same User instance, this is skipped!
    Text(user.name)
}
```

### Official Documentation

- **Compose Stability:** https://developer.android.com/jetpack/compose/performance/stability
- **@Immutable Annotation:** https://developer.android.com/jetpack/compose/performance/stability/fix#immutable
- **Performance Best Practices:** https://developer.android.com/jetpack/compose/performance/bestpractices

### When to Use @Immutable

✅ **Use when:**
- All properties are `val` (immutable)
- All property types are primitives or other @Immutable/@Stable types
- Data classes used in Compose UI
- API response models
- Domain models

❌ **Don't use when:**
- Any property is `var` (mutable)
- Class has mutable state
- Properties contain mutable collections (unless wrapped in immutable types)

### Verification

All models in this package follow these rules:
- All properties are `val` (never `var`)
- All properties are immutable types (String, Int, Long, Boolean, etc.)
- Lists are immutable (List<T> not MutableList<T>)
- Safe for concurrent access from multiple threads

---

**Last Updated:** July 2026
**Applied to:** All domain model classes in this package
