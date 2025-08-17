import random
import time
import matplotlib.pyplot as plt
import pandas as pd
import math


# ---------------- Brute Force Method ----------------
def brute_force(points):
    min_dist = float('inf')
    n = len(points)
    for i in range(n):
        for j in range(i + 1, n):
            dist = math.dist(points[i], points[j])
            if dist < min_dist:
                min_dist = dist
    return min_dist


# ---------------- Divide and Conquer Method ----------------
def closest_pair_dc(points):
    def closest_pair_recursive(px, py):
        if len(px) <= 3:
            return brute_force(px)

        mid = len(px) // 2
        Qx = px[:mid]
        Rx = px[mid:]

        midpoint = px[mid][0]
        Qy = [p for p in py if p[0] <= midpoint]
        Ry = [p for p in py if p[0] > midpoint]

        d1 = closest_pair_recursive(Qx, Qy)
        d2 = closest_pair_recursive(Rx, Ry)
        d = min(d1, d2)

        strip = [p for p in py if abs(p[0] - midpoint) < d]
        min_dist_strip = d
        for i in range(len(strip)):
            for j in range(i + 1, min(i + 7, len(strip))):
                dist = math.dist(strip[i], strip[j])
                if dist < min_dist_strip:
                    min_dist_strip = dist

        return min(d, min_dist_strip)

    px = sorted(points, key=lambda x: x[0])
    py = sorted(points, key=lambda x: x[1])
    return closest_pair_recursive(px, py)


# ---------------- Performance Measurement ----------------
sizes = [10, 50, 100, 200, 500]
brute_times = []
dc_times = []

for size in sizes:
    points = [(random.uniform(0, 1000), random.uniform(0, 1000)) for _ in range(size)]

    # Brute Force timing
    start = time.perf_counter()
    brute_force(points)
    end = time.perf_counter()
    brute_times.append(end - start)

    # Divide & Conquer timing
    start = time.perf_counter()
    closest_pair_dc(points)
    end = time.perf_counter()
    dc_times.append(end - start)

# ---------------- Dataframe ----------------
df = pd.DataFrame({
    'Size': sizes,
    'Brute Force Time (s)': brute_times,
    'Divide & Conquer Time (s)': dc_times
})
print(df)

# ---------------- Performance Chart ----------------
plt.plot(sizes, brute_times, marker='o', label="Brute Force")
plt.plot(sizes, dc_times, marker='o', label="Divide & Conquer")
plt.xlabel("Number of Points")
plt.ylabel("Time (seconds)")
plt.title("Performance Comparison")
plt.legend()
plt.grid(True)
plt.show()
