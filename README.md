# Assignment 1 – Divide & Conquer Algorithms

## Introduction

This assignment was completed as part of the Design and Analysis of Algorithms course.
The objective was to design, implement, and analyze four classical divide-and-conquer algorithms: MergeSort, QuickSort, Deterministic Select (Median-of-Medians), and the Closest Pair of Points problem.
The work focused not only on correctness but also on studying recursion depth, running-time recurrences, and comparing theoretical complexity predictions with experimental performance. 

## Learning goals
• Implement classic divide-and-conquer algorithms with safe recursion patterns.

• Analyze running-time recurrences using Master Theorem (3 cases) and Akra–Bazzi intuition; validate
with measurements.

• Collect metrics (time, recursion depth, comparisons/allocations) and communicate results via a short
report and clean Git history.

## Architecture Notes
## Algorithms

### MergeSort

* Uses a **reusable buffer** for merging to avoid repeated allocations.
* Switches to **insertion sort** for small subarrays (≤16) to reduce recursion overhead and improve cache locality.
* **Recursion depth**: `O(log n)`.

### QuickSort

* Uses a **randomized pivot** for balanced partitions in expectation.
* Always **recurses into the smaller partition** and iterates over the larger one → ensures bounded recursion depth.
* **Stack depth**: ≈ `O(log n)` even in worst case.

### Deterministic Select (Median of Medians)

* Divides array into **groups of 5**, sorts each with insertion sort, and finds the **median of medians** as pivot.
* Recurses only on the required side, preferring the smaller partition.
* Guarantees **linear runtime** and recursion depth `O(log n)` in the worst case.

### Closest Pair of Points (2D)

* Classical D&C approach:

  1. Sort by `x`
  2. Recurse on halves
  3. Maintain `y`-ordering with merge
  4. Use brute-force on subsets ≤3 points
* Uses auxiliary array for merging by `y`.
* **Recursion depth**: `O(log n)`.

---

## Recurrence Analysis

| Algorithm                | Recurrence                                 | Solution     | Notes                             |
| ------------------------ | ------------------------------------------ | ------------ | --------------------------------- |
| **MergeSort**            | `T(n) = 2T(n/2) + Θ(n)`                    | `Θ(n log n)` | Master Theorem, Case 2            |
| **QuickSort (expected)** | `T(n) = T(q) + T(n-q-1) + Θ(n)` → balanced | `Θ(n log n)` | Randomized pivot ensures balance  |
| **QuickSort (worst)**    | `T(n) = T(n-1) + Θ(n)`                     | `Θ(n²)`      | Occurs only without randomization |
| **Deterministic Select** | `T(n) ≤ T(n/5) + T(7n/10) + Θ(n)`          | `Θ(n)`       | Solved via Akra–Bazzi             |
| **Closest Pair**         | `T(n) = 2T(n/2) + Θ(n)`                    | `Θ(n log n)` | Merge + strip scan                |

---

## Experimental Results

### Time vs Input Size

* MergeSort and QuickSort follow **`n log n`** growth.
* QuickSort is generally faster for mid-size arrays due to fewer copy operations.
* Deterministic Select shows **linear scaling**, but large constant factors.
* Closest Pair grows as **`n log n`**.

### Recursion Depth

* MergeSort and Closest Pair: depth ≈ `log₂ n`.
* QuickSort (tail recursion optimization): depth bounded by `O(log n)`.
* Deterministic Select: depth `O(log n)` but shallower recursion due to reduction rate.

---

## Constant-Factor Effects

* **MergeSort**: overhead from buffer allocation and copying, but stable performance.
* **QuickSort**: fewer allocations but slightly more comparisons; less predictable than MergeSort.
* **Deterministic Select**: guaranteed linear, but slower in practice due to median computation overhead.
* **Closest Pair**: asymptotics hold, but merging and strip scanning introduce large constants.

---

## Summary

* The **theoretical asymptotics** (`Θ(n log n)` or `Θ(n)`) are confirmed experimentally.
* **QuickSort** is usually faster than MergeSort in practice but less predictable.
* **Deterministic Select** performs linearly, though randomized QuickSelect is more practical.
* **Closest Pair** aligns with theory but has non-trivial constant factors.
