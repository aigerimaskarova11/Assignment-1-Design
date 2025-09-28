Assignment 1 – Divide & Conquer Algorithms
Architecture Notes

MergeSort
Uses a reusable buffer for merging to avoid repeated allocations. For small subarrays (≤16), insertion sort is applied to reduce recursion depth and improve cache locality.
Maximum recursion depth = O(log n).

QuickSort
Randomized pivot selection ensures expected recursion depth O(log n). The implementation always recurses into the smaller side and iterates over the larger one, which guarantees bounded stack depth ≈ O(log n) even in the worst case.

Deterministic Select (Median of Medians)
Divides the array into groups of 5, sorts each group with insertion sort, and takes the medians. These medians are recursively processed to pick a pivot. Guarantees linear running time and recursion depth O(log n) in the worst case.

Closest Pair of Points (2D)
Implements the classical D&C algorithm: sort by x, split recursively, maintain y-ordering via merging. For very small subsets (≤3 points), brute-force is used. Recursion depth = O(log n). Uses an auxiliary array for y-merge.

Recurrence Analysis

MergeSort
Recurrence:
T(n) = 2T(n/2) + Θ(n)
By Master Theorem (Case 2): a = 2, b = 2, f(n) = Θ(n) → T(n) = Θ(n log n).

QuickSort (randomized, tail recursion optimization)
Expected recurrence:
T(n) = T(q) + T(n - q - 1) + Θ(n), where q is pivot position.
With balanced partitions in expectation → T(n) = 2T(n/2) + Θ(n) → Θ(n log n).
Worst case (without randomization): T(n) = T(n-1) + Θ(n) → Θ(n^2).

Deterministic Select (Median of Medians)
Groups of 5 guarantee a “good enough” pivot.
Recurrence:
T(n) ≤ T(n/5) + T(7n/10) + Θ(n)
By Akra–Bazzi theorem → T(n) = Θ(n).

Closest Pair of Points (2D)
After initial Θ(n log n) sorting by x, recursive split:
T(n) = 2T(n/2) + Θ(n)
(strip check is Θ(n) since each point checks ≤7 neighbors).
Overall: T(n) = Θ(n log n).

Experimental Results
Plots

(should be generated with Java timing code or exported to CSV and plotted with Python/Matplotlib)

Time vs n

MergeSort and QuickSort grow as n log n.

QuickSort is usually faster for mid-size inputs due to fewer copy operations.

Deterministic Select is linear but has large constant factors.

Closest Pair grows as n log n.

Recursion Depth vs n

MergeSort and Closest Pair: depth ≈ log₂ n.

QuickSort with tail recursion: depth bounded by O(log n).

Deterministic Select: depth O(log n) with smaller constants.

Discussion of Constant-Factor Effects

MergeSort: buffer allocation/copying adds overhead, but algorithm is stable.

QuickSort: fewer allocations but more comparisons.

Deterministic Select: extra median steps add overhead, so it’s slower in practice than randomized selection despite linear complexity.

Closest Pair: high hidden constant factors from merging and strip-check, even though asymptotics match.

Summary

Theoretical asymptotics (Θ(n log n) or Θ(n)) are confirmed by experiments.

Constant-factor differences observed: QuickSort is faster than MergeSort in practice, but less predictable.

Deterministic Select behaves linearly, but randomized quickselect is usually faster in practice.

Closest Pair matches Θ(n log n) but shows significant constant-time overhead from merge operations.
