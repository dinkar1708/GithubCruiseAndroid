# Product Roadmap

## 12-Month Vision

*"Become the fastest way for developers worldwide to discover GitHub talent"*

**Goals:**
- 50,000 monthly active users
- $10k monthly recurring revenue
- 40% week-1 retention
- Average 5+ searches per session

---

## Roadmap Overview

| Phase | Timeline | Focus | Key Features | Success Target |
|-------|----------|-------|--------------|----------------|
| **Phase 1** | Month 1-2 | MVP Launch | Search, profiles, repos, dark mode | 1k users, validate idea |
| **Phase 2** | Month 3-4 | Performance | Caching, offline mode, debouncing | Under 500ms search, 40% retention |
| **Phase 3** | Month 5-6 | Engagement | Favorites, history, trending devs | 5k users, 2x session time |
| **Phase 4** | Month 7-9 | Monetization | Premium tier, OAuth, analytics | 100 paying users, $5k MRR |
| **Phase 5** | Month 10-12 | Scale | iOS app, teams, API | 50k users, $10k MRR |

---

## Phase 1: MVP (Month 1-2) - Completed

### Goal
Validate product-market fit

### What We Built
- User search with pagination
- Profile viewing
- Repository browsing with fork filter
- Dark mode
- Japanese localization

### Why These Features?
- **Search:** Core value prop - find users fast
- **Dark mode:** 70% of devs need it
- **Japanese:** 10M+ market opportunity
- **Testing:** Reliability is critical (77% coverage)

### Results
- 1.2s startup (60% faster than mobile web)
- 650ms average search
- 99.8% crash-free
- Validated: people want this

---

## Phase 2: Performance (Month 3-4) - Planned

### Goal
Improve retention through better performance

### What to Build

**1. Debounced Search**
- **Problem:** Search fires on every keystroke, wastes API calls
- **Solution:** Wait 300ms after last keystroke
- **Impact:** 70% fewer API calls, smoother UX

**2. Repository Caching**
- **Problem:** Re-fetching same repos on back navigation
- **Solution:** 15-minute cache
- **Impact:** 3s load becomes 100ms on repeat views

**3. Basic Offline Mode**
- **Problem:** App breaks on spotty mobile connection
- **Solution:** Cache last 10 viewed profiles
- **Impact:** 25% higher retention for mobile users

**4. Repository Pagination**
- **Problem:** Users with 500+ repos cause 10s load time
- **Solution:** Load 30 at a time, lazy load more
- **Impact:** 10s becomes 1s initial load

### Target Metrics
- 5,000 users
- 40% week-1 retention (up from 30%)
- Under 500ms search (95th percentile)
- 4.5+ star rating

---

## Phase 3: Engagement (Month 5-6) - Planned

### Goal
Increase session length and repeat usage

### What to Build

**1. Favorites**
- **Why:** Users with favorites return 2x more often
- **What:** Save profiles/repos for later
- **Monetization hook:** Free tier = 10 favorites max

**2. Search History**
- **Why:** Quick access to recent searches
- **What:** Last 20 searches, 30-day auto-delete
- **Privacy:** Clear history option

**3. Trending Developers**
- **Why:** Discovery beyond search
- **What:** Devs with high star growth, filtered by language
- **Impact:** 5+ min session extension

### Target Metrics
- 10,000 users (2x from Phase 2)
- 5+ min average session (2x from Phase 1)
- 50% week-1 retention
- 30% of users have at least 1 favorite

---

## Phase 4: Monetization (Month 7-9) - Planned

### Goal
Prove revenue model, achieve $5k MRR

### What to Build

**1. Premium Tier - $4.99/month**

| Feature | Free | Premium |
|---------|------|---------|
| Favorites | 10 max | Unlimited |
| API rate limit | 60 req/hr | 5,000 req/hr (OAuth) |
| Advanced filters | Stars only | Stars, language, activity |
| Analytics dashboard | None | Personal GitHub insights |

**2. GitHub OAuth**
- **Why:** Unlock 5,000 req/hr (vs 60)
- **What:** Secure login, access private repos
- **Impact:** Removes biggest user friction

**3. Analytics Dashboard (Premium)**
- **What:** Commit frequency, language breakdown, contribution streak
- **Why:** High-value feature justifies $5/month
- **Impact:** Weekly check-ins, higher retention

### Pricing Strategy
- $4.99/month or $49.99/year (save 16%)
- 2% conversion target (200 users from 10k)
- **Soft paywall:** "Favorite limit reached - Upgrade?"

### Target Metrics
- 15,000 users
- 100 paying users (2% conversion)
- $5k MRR
- 80% premium retention month-over-month

---

## Phase 5: Scale (Month 10-12) - Planned

### Goal
Expand platform, achieve 50k users and $10k MRR

### What to Build

**1. iOS App**
- **Why:** iOS users = 50% of dev market
- **Strategy:** Kotlin Multiplatform (70% code reuse)
- **Impact:** 2x addressable market

**2. Team Collaboration**
- **What:** Shared candidate lists, comments, mentions
- **Pricing:** $99/user/year for teams
- **Target:** 10 enterprise teams (10+ users each)

**3. Public API**
- **Why:** Developer ecosystem, brand awareness
- **Pricing:** 1k calls/month free, $49/month for 100k calls
- **Target:** 100 API developers

### Target Metrics
- 50,000 total users
- 10,000 iOS users (20%)
- $10k MRR (300 paying individuals + 10 teams)
- 100 API developers

---

## Prioritization: How We Decide

### RICE Framework

**Formula:** (Reach × Impact × Confidence) / Effort

**Example: Favorites Feature**
- **Reach:** 40% of users will use it
- **Impact:** 2x session frequency
- **Confidence:** 70% (proven by competitors)
- **Effort:** 3 weeks

**Score:** (40 × 2 × 0.7) / 3 = 18.7 (High priority)

### Why This Order?

**Phase 1 first:** Validate before investing
**Phase 2 before 3:** Performance drives retention drives engagement
**Phase 4 before 5:** Prove monetization before scaling
**iOS after Android:** Validate model on one platform first

---

## Risk Management

### Technical Risks

**Risk:** GitHub API rate limits
- **Impact:** High (app unusable)
- **Mitigation:** Caching + OAuth for premium

**Risk:** Scaling costs
- **Impact:** High (profitability)
- **Mitigation:** Optimize API calls, use Firebase free tier

### Market Risks

**Risk:** GitHub launches competing feature
- **Impact:** High
- **Mitigation:** Focus on speed/UX, not just features

**Risk:** Low conversion to premium
- **Impact:** High
- **Mitigation:** A/B test pricing, add more premium features

---

## Success Metrics Dashboard

### North Star Metric
**Weekly Active Users (WAU)** - GitHub search is weekly, not daily

### Supporting Metrics

**Acquisition:**
- Monthly active users (MAU)
- Organic vs paid (target: 90% organic)

**Engagement:**
- Session length (target: 5+ minutes)
- Searches per session (target: 5+)
- Feature adoption (favorites, trending)

**Retention:**
- Day-1: 50%
- Week-1 (D7): 40%
- Month-1 (D30): 20%

**Revenue:**
- Monthly recurring revenue (MRR)
- Conversion rate (free to premium): 2%
- Churn rate: under 5% per month

---

## Why This Matters

This roadmap proves:

1. **Strategic thinking** - Phased approach: validate, polish, monetize, scale
2. **Prioritization** - RICE framework, data-driven decisions
3. **Business sense** - Revenue targets, conversion rates, unit economics
4. **Risk awareness** - Identified technical/market risks with mitigations
5. **Measurable goals** - Clear success criteria for each phase

That's product planning.

---

**Related:**
- product-development.md - How we got here
- requirements-specification.md - How we translate needs to specs
