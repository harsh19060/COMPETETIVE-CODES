import csv
import math

# Function to calculate Euclidean distance between two points
def euclidean_distance(p1, p2):
    return math.sqrt((p1[1] - p2[1])**2 + (p1[2] - p2[2])**2)

# Read dataset from CSV
locations = []
with open("points.csv", newline="") as file:
    reader = csv.reader(file)
    next(reader)  # Skip header
    for row in reader:
        name = row[0]
        lat = float(row[1])
        lon = float(row[2])
        locations.append((name, lat, lon))

# Brute Force Algorithm
min_dist = float("inf")
closest_pair = (None, None)

for i in range(len(locations)):
    for j in range(i + 1, len(locations)):
        dist = euclidean_distance(locations[i], locations[j])
        if dist < min_dist:
            min_dist = dist
            closest_pair = (locations[i], locations[j])

# Output
print(f"Closest pair: {closest_pair[0][0]} at ({closest_pair[0][1]}, {closest_pair[0][2]}) "
      f"and {closest_pair[1][0]} at ({closest_pair[1][1]}, {closest_pair[1][2]})")
print(f"Distance: {min_dist:.6f}")
