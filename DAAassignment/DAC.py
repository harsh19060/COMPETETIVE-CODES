import csv
import math

# Euclidean distance
def euclidean_distance(p1, p2):
    return math.sqrt((p1[1] - p2[1])**2 + (p1[2] - p2[2])**2)

# Brute force for small sets
def brute_force(points):
    min_dist = float("inf")
    closest_pair = (None, None)
    n = len(points)
    for i in range(n):
        for j in range(i + 1, n):
            dist = euclidean_distance(points[i], points[j])
            if dist < min_dist:
                min_dist = dist
                closest_pair = (points[i], points[j])
    return min_dist, closest_pair

# Closest in strip
def strip_closest(strip, d, best_pair):
    min_dist = d
    n = len(strip)
    for i in range(n):
        j = i + 1
        while j < n and (strip[j][1] - strip[i][1]) < min_dist:
            dist = euclidean_distance(strip[i], strip[j])
            if dist < min_dist:
                min_dist = dist
                best_pair = (strip[i], strip[j])
            j += 1
    return min_dist, best_pair

# Recursive function
def closest_util(px, py):
    n = len(px)
    if n <= 3:
        return brute_force(px)

    mid = n // 2
    mid_point = px[mid]

    pyl = [p for p in py if p[1] <= mid_point[1]]
    pyr = [p for p in py if p[1] > mid_point[1]]

    dl, pair_left = closest_util(px[:mid], pyl)
    dr, pair_right = closest_util(px[mid:], pyr)

    if dl < dr:
        d = dl
        best_pair = pair_left
    else:
        d = dr
        best_pair = pair_right

    strip = [p for p in py if abs(p[1] - mid_point[1]) < d]
    ds, strip_pair = strip_closest(strip, d, best_pair)

    if ds < d:
        return ds, strip_pair
    else:
        return d, best_pair

# Main function
def closest_pair(points):
    px = sorted(points, key=lambda p: p[1])  # Sort by latitude
    py = sorted(points, key=lambda p: p[2])  # Sort by longitude
    return closest_util(px, py)

# Read data
locations = []
with open("points.csv", newline="") as file:
    reader = csv.reader(file)
    next(reader)  # Skip header
    for row in reader:
        name = row[0]
        lat = float(row[1])
        lon = float(row[2])
        locations.append((name, lat, lon))

# Run algorithm
min_dist, pair = closest_pair(locations)

# Output
print(f"Closest pair (Divide & Conquer): {pair[0][0]} at ({pair[0][1]}, {pair[0][2]}) "
      f"and {pair[1][0]} at ({pair[1][1]}, {pair[1][2]})")
print(f"Distance: {min_dist:.6f}")
