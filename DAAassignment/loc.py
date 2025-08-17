import random
import csv

# Number of points
num_points = 50  

# Example facility types 
facility_types = ["Ambulance", "Warehouse", "Delivery Van", "Police Car", "Fire Truck"]

# Generate random coordinates (latitude & longitude)
data = []
for i in range(num_points):
    name = f"{random.choice(facility_types)} {i+1}"
    lat = round(random.uniform(21.10, 21.20), 6)  # Latitude
    lon = round(random.uniform(79.05, 79.15), 6)  # Longitude
    data.append((name, lat, lon))

# Save in CSV file
# generate new csv and save the generated points
with open("points.csv", mode="w", newline="") as file:
    writer = csv.writer(file)
    writer.writerow(["Name", "Latitude", "Longitude"])
    writer.writerows(data)

print(f"Dataset with {num_points} facilities created and saved to locations.csv")
