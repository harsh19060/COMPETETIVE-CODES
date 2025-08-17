import pandas as pd
import matplotlib.pyplot as plt
import math

# ---- Step 1: Load the CSV ----
df = pd.read_csv("points.csv")  # Change to your file name

# Convert to (lat, lon) tuples
points = list(zip(df['Latitude'], df['Longitude']))

# ---- Step 2: Distance Function ----
def distance(p1, p2):
    return math.sqrt((p1[0] - p2[0])**2 + (p1[1] - p2[1])**2)

# ---- Step 3: Divide and Conquer Closest Pair ----
def closest_pair_dc(points_sorted_x):
    n = len(points_sorted_x)
    if n <= 3:
        min_dist = float('inf')
        pair = None
        for i in range(n):
            for j in range(i+1, n):
                d = distance(points_sorted_x[i], points_sorted_x[j])
                if d < min_dist:
                    min_dist = d
                    pair = (points_sorted_x[i], points_sorted_x[j])
        return pair, min_dist

    mid = n // 2
    left = points_sorted_x[:mid]
    right = points_sorted_x[mid:]

    (pair_left, dist_left) = closest_pair_dc(left)
    (pair_right, dist_right) = closest_pair_dc(right)

    if dist_left < dist_right:
        d = dist_left
        min_pair = pair_left
    else:
        d = dist_right
        min_pair = pair_right

    mid_x = points_sorted_x[mid][0]
    strip = [p for p in points_sorted_x if abs(p[0] - mid_x) < d]
    strip.sort(key=lambda point: point[1])

    for i in range(len(strip)):
        for j in range(i+1, min(i+7, len(strip))):
            dist_strip = distance(strip[i], strip[j])
            if dist_strip < d:
                d = dist_strip
                min_pair = (strip[i], strip[j])

    return min_pair, d

# Sort by latitude before starting
points_sorted = sorted(points, key=lambda p: p[0])
closest_pair, min_dist = closest_pair_dc(points_sorted)

print("Closest pair (lat, lon):", closest_pair)
print("Distance:", min_dist)

# ---- Step 4: Plotting ----
plt.figure(figsize=(8, 6))
plt.scatter(df['Longitude'], df['Latitude'], color='blue', label='Points')

# Highlight closest pair
p1, p2 = closest_pair
plt.scatter([p1[1], p2[1]], [p1[0], p2[0]], color='red', label='Closest Pair')
plt.plot([p1[1], p2[1]], [p1[0], p2[0]], color='red', linestyle='--')

# Add labels
for i, name in enumerate(df['Name']):
    plt.text(df['Longitude'][i] + 0.001, df['Latitude'][i] + 0.001, name, fontsize=8)

plt.xlabel('Longitude')
plt.ylabel('Latitude')
plt.title('Closest Pair of Locations')
plt.legend()
plt.grid(True)
plt.show()
