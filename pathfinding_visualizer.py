import tkinter as tk
from tkinter import ttk, messagebox
import heapq, time
from collections import deque


# ALGORITHMS
dirs = [(1,0), (-1,0), (0,1), (0,-1)]

def bfs_visual(maze, start, goal, update_callback):
    rows, cols = len(maze), len(maze[0])
    q = deque([(start, [start])])
    visited = {start}
    while q:
        (x, y), path = q.popleft()
        update_callback((x, y))
        if (x, y) == goal:
            return path
        for dx, dy in dirs:
            nx, ny = x+dx, y+dy
            if 0 <= nx < rows and 0 <= ny < cols and maze[nx][ny] != 0 and (nx, ny) not in visited:
                visited.add((nx, ny))
                q.append(((nx, ny), path + [(nx, ny)]))
    return None

def dfs_visual(maze, start, goal, update_callback):
    rows, cols = len(maze), len(maze[0])
    stack = [(start, [start])]
    visited = set()
    while stack:
        (x, y), path = stack.pop()
        if (x, y) in visited:
            continue
        visited.add((x, y))
        update_callback((x, y))
        if (x, y) == goal:
            return path
        for dx, dy in dirs:
            nx, ny = x+dx, y+dy
            if 0 <= nx < rows and 0 <= ny < cols and maze[nx][ny] != 0:
                stack.append(((nx, ny), path + [(nx, ny)]))
    return None

def dijkstra_visual(maze, start, goal, update_callback):
    rows, cols = len(maze), len(maze[0])
    pq = [(0, start, [start])]
    visited = set()
    while pq:
        cost, (x, y), path = heapq.heappop(pq)
        if (x, y) in visited:
            continue
        visited.add((x, y))
        update_callback((x, y))
        if (x, y) == goal:
            return path
        for dx, dy in dirs:
            nx, ny = x+dx, y+dy
            if 0 <= nx < rows and 0 <= ny < cols and maze[nx][ny] != 0:
                heapq.heappush(pq, (cost + 1, (nx, ny), path + [(nx, ny)]))
    return None

def heuristic(a, b):
    return abs(a[0]-b[0]) + abs(a[1]-b[1])

def astar_visual(maze, start, goal, update_callback):
    rows, cols = len(maze), len(maze[0])
    pq = [(heuristic(start, goal), 0, start, [start])]
    visited = set()
    g_scores = {start: 0}
    while pq:
        f, g, (x, y), path = heapq.heappop(pq)
        if (x, y) in visited:
            continue
        visited.add((x, y))
        update_callback((x, y))
        if (x, y) == goal:
            return path
        for dx, dy in dirs:
            nx, ny = x+dx, y+dy
            if 0 <= nx < rows and 0 <= ny < cols and maze[nx][ny] != 0:
                new_g = g + 1
                if (nx, ny) not in g_scores or new_g < g_scores[(nx, ny)]:
                    g_scores[(nx, ny)] = new_g
                    new_f = new_g + heuristic((nx, ny), goal)
                    heapq.heappush(pq, (new_f, new_g, (nx, ny), path + [(nx, ny)]))
    return None

# Non-visual versions (for comparative table)
def bfs(maze, start, goal):
    rows, cols = len(maze), len(maze[0])
    q = deque([(start, [start])])
    visited = {start}
    nodes_visited = 0 
    while q:
        nodes_visited += 1
        (x, y), path = q.popleft()
        if (x, y) == goal:
            return path, len(path)-1, nodes_visited 
        for dx, dy in dirs:
            nx, ny = x+dx, y+dy
            if 0 <= nx < rows and 0 <= ny < cols and maze[nx][ny] != 0 and (nx, ny) not in visited:
                visited.add((nx, ny))
                q.append(((nx, ny), path + [(nx, ny)]))
    return None, float('inf'), nodes_visited

def dfs(maze, start, goal):
    rows, cols = len(maze), len(maze[0])
    stack = [(start, [start])]
    visited = set()
    nodes_visited = 0
    while stack:
        nodes_visited += 1
        (x, y), path = stack.pop()
        if (x, y) == goal:
            return path, len(path)-1, nodes_visited
        if (x, y) in visited:
            continue
        visited.add((x, y))
        for dx, dy in dirs:
            nx, ny = x+dx, y+dy
            if 0 <= nx < rows and 0 <= ny < cols and maze[nx][ny] != 0 and (nx, ny) not in visited:
                stack.append(((nx, ny), path + [(nx, ny)]))
    return None, float('inf'), nodes_visited

def dijkstra(maze, start, goal):
    rows, cols = len(maze), len(maze[0])
    pq = [(0, start, [start])]
    visited = set()
    nodes_visited = 0
    while pq:
        nodes_visited += 1
        cost, (x, y), path = heapq.heappop(pq)
        if (x, y) == goal:
            return path, cost, nodes_visited
        if (x, y) in visited:
            continue
        visited.add((x, y))
        for dx, dy in dirs:
            nx, ny = x+dx, y+dy
            if 0 <= nx < rows and 0 <= ny < cols and maze[nx][ny] != 0:
                if (nx, ny) not in visited:
                    new_cost = cost + 1
                    heapq.heappush(pq, (new_cost, (nx, ny), path + [(nx, ny)]))
    return None, float('inf'), nodes_visited

def astar(maze, start, goal):
    rows, cols = len(maze), len(maze[0])
    h_start = heuristic(start, goal)
    pq = [(h_start, 0, start, [start])]
    visited = set()
    g_scores = {start: 0}
    nodes_visited = 0
    while pq:
        nodes_visited += 1
        f, g, (x, y), path = heapq.heappop(pq)
        if (x, y) in visited:
            continue
        if (x, y) == goal:
            return path, g, nodes_visited
        visited.add((x, y))
        for dx, dy in dirs:
            nx, ny = x+dx, y+dy
            if 0 <= nx < rows and 0 <= ny < cols and maze[nx][ny] != 0:
                new_g = g + 1
                if (nx, ny) not in g_scores or new_g < g_scores.get((nx, ny), float('inf')):
                    g_scores[(nx, ny)] = new_g
                    new_h = heuristic((nx, ny), goal)
                    new_f = new_g + new_h
                    heapq.heappush(pq, (new_f, new_g, (nx, ny), path + [(nx, ny)]))
    return None, float('inf'), nodes_visited


# UI APPLICATION

class PathfindingApp:
    CELL_SIZE = 25
    COLOR_EMPTY = "white"
    COLOR_OBSTACLE = "black"
    COLOR_START = "green"
    COLOR_GOAL = "red"
    COLOR_PATH = "blue"
    COLOR_VISITED = "#87CEFA"  # light sky blue for visited

    def __init__(self, master):
        self.master = master
        master.title("Emergency Evacuation Route Finder")

        self.control_frame = ttk.Frame(master, padding=10)
        self.control_frame.pack(side=tk.LEFT, fill=tk.Y)

        self.grid_frame = ttk.Frame(master, padding=10)
        self.grid_frame.pack(side=tk.RIGHT, fill=tk.BOTH, expand=True)

        self.rows = 10
        self.cols = 10
        self.start_point = None
        self.goal_point = None
        self.maze_data = [[1 for _ in range(self.cols)] for _ in range(self.rows)]
        self.grid_cells = []
        self.draw_mode = tk.StringVar(value="obstacle")

        ttk.Label(self.control_frame, text="Grid Setup", font=("Arial", 14, "bold")).pack(pady=10)
        size_frame = ttk.Frame(self.control_frame)
        ttk.Label(size_frame, text="Rows:").pack(side=tk.LEFT)
        self.rows_var = tk.StringVar(value=str(self.rows))
        ttk.Entry(size_frame, width=5, textvariable=self.rows_var).pack(side=tk.LEFT, padx=5)
        ttk.Label(size_frame, text="Cols:").pack(side=tk.LEFT)
        self.cols_var = tk.StringVar(value=str(self.cols))
        ttk.Entry(size_frame, width=5, textvariable=self.cols_var).pack(side=tk.LEFT, padx=5)
        size_frame.pack(pady=5)

        ttk.Button(self.control_frame, text="Create Grid", command=self.create_grid).pack(fill=tk.X, pady=5)
        ttk.Separator(self.control_frame, orient='horizontal').pack(fill=tk.X, pady=10)

        ttk.Label(self.control_frame, text="Draw Tool", font=("Arial", 14, "bold")).pack(pady=10)
        for text, value in [("Start Point (Click)", "start"), ("Exit Point (Click)", "goal"),
                            ("Obstacle (Click/Drag)", "obstacle"), ("Eraser (Click/Drag)", "empty")]:
            ttk.Radiobutton(self.control_frame, text=text, variable=self.draw_mode, value=value).pack(anchor=tk.W)

        ttk.Separator(self.control_frame, orient='horizontal').pack(fill=tk.X, pady=10)

        ttk.Label(self.control_frame, text="Actions", font=("Arial", 14, "bold")).pack(pady=10)
        ttk.Button(self.control_frame, text="Find Path (Compare All)", command=self.run_all_algorithms).pack(fill=tk.X, pady=5)

        # Dropdown for visualization
        ttk.Label(self.control_frame, text="Visualize Algorithm:", font=("Arial", 12, "bold")).pack(pady=5)
        self.algo_var = tk.StringVar(value="BFS")
        algo_options = ["BFS", "DFS", "Dijkstra", "A*"]
        self.algo_menu = ttk.Combobox(self.control_frame, textvariable=self.algo_var, values=algo_options, state="readonly")
        self.algo_menu.pack(fill=tk.X, pady=5)
        ttk.Button(self.control_frame, text="Visualize", command=self.visualize_selected).pack(fill=tk.X, pady=5)

        ttk.Button(self.control_frame, text="Clear Path", command=self.clear_path_drawing).pack(fill=tk.X, pady=5)
        ttk.Button(self.control_frame, text="Clear All", command=self.create_grid).pack(fill=tk.X, pady=5)

        ttk.Separator(self.control_frame, orient='horizontal').pack(fill=tk.X, pady=10)
        ttk.Label(self.control_frame, text="Results", font=("Arial", 14, "bold")).pack(pady=10)
        self.results_text = tk.Text(self.control_frame, height=15, width=40, state=tk.DISABLED, font=("Courier", 9))
        self.results_text.pack(fill=tk.X)

        self.create_grid()

    def create_grid(self):
        try:
            self.rows = int(self.rows_var.get())
            self.cols = int(self.cols_var.get())
        except ValueError:
            messagebox.showerror("Error", "Rows and Columns must be integers.")
            return
        for widget in self.grid_frame.winfo_children():
            widget.destroy()
        self.start_point = None
        self.goal_point = None
        self.maze_data = [[1 for _ in range(self.cols)] for _ in range(self.rows)]
        self.grid_cells = []
        for r in range(self.rows):
            row_cells = []
            for c in range(self.cols):
                cell = tk.Frame(self.grid_frame, width=self.CELL_SIZE, height=self.CELL_SIZE,
                                bg=self.COLOR_EMPTY, borderwidth=1, relief="solid")
                cell.bind("<Button-1>", lambda e, r=r, c=c: self.on_grid_click(r, c))
                cell.bind("<B1-Motion>", lambda e, r=r, c=c: self.on_grid_drag(r, c))
                cell.grid(row=r, column=c)
                row_cells.append(cell)
            self.grid_cells.append(row_cells)

    def on_grid_click(self, r, c):
        mode = self.draw_mode.get()
        self.clear_path_drawing()
        if mode == "start":
            if self.start_point:
                old_r, old_c = self.start_point
                self.grid_cells[old_r][old_c].config(bg=self.COLOR_EMPTY)
            self.start_point = (r, c)
            self.maze_data[r][c] = 1
            self.grid_cells[r][c].config(bg=self.COLOR_START)
        elif mode == "goal":
            if self.goal_point:
                old_r, old_c = self.goal_point
                self.grid_cells[old_r][old_c].config(bg=self.COLOR_EMPTY)
            self.goal_point = (r, c)
            self.maze_data[r][c] = 1
            self.grid_cells[r][c].config(bg=self.COLOR_GOAL)
        elif mode == "obstacle":
            self.grid_cells[r][c].config(bg=self.COLOR_OBSTACLE)
            self.maze_data[r][c] = 0
        elif mode == "empty":
            self.grid_cells[r][c].config(bg=self.COLOR_EMPTY)
            self.maze_data[r][c] = 1
            if (r, c) == self.start_point:
                self.start_point = None
            if (r, c) == self.goal_point:
                self.goal_point = None

    def on_grid_drag(self, r, c):
        mode = self.draw_mode.get()
        if mode in ("obstacle", "empty"):
            self.on_grid_click(r, c)

    def clear_path_drawing(self):
        for r in range(self.rows):
            for c in range(self.cols):
                if self.maze_data[r][c] == 1:
                    self.grid_cells[r][c].config(bg=self.COLOR_EMPTY)
        if self.start_point:
            r, c = self.start_point
            self.grid_cells[r][c].config(bg=self.COLOR_START)
        if self.goal_point:
            r, c = self.goal_point
            self.grid_cells[r][c].config(bg=self.COLOR_GOAL)

    # ANIMATED PATH DRAWING
  
    def animate_path(self, path, index=1, delay=80):
        if not path:
            return
        if index >= len(path) - 1:
            return
        r, c = path[index]
        if (r, c) not in [self.start_point, self.goal_point]:
            self.grid_cells[r][c].config(bg=self.COLOR_PATH)
        self.master.after(delay, self.animate_path, path, index + 1, delay)

    def animate_path_instant(self, path):
        # immediate draw (no animation)
        if not path:
            return
        for r, c in path:
            if (r, c) not in [self.start_point, self.goal_point]:
                self.grid_cells[r][c].config(bg=self.COLOR_PATH)

    # RUN ALL (non-visual comparison)
    def run_all_algorithms(self):
        if not self.start_point or not self.goal_point:
            messagebox.showerror("Error", "Please set Start and Goal points.")
            return
        self.clear_path_drawing()
        algos = [("BFS", bfs), ("DFS", dfs), ("Dijkstra", dijkstra), ("A*", astar)]
        output = f"{'Algorithm':<10}{'Path':<8}{'Steps':<8}{'Nodes':<8}{'Time (µs)':<12}\n" + "-" * 60 + "\n"
        results = []
        for name, func in algos:
            start_t = time.perf_counter()
            path, cost, nodes = func(self.maze_data, self.start_point, self.goal_point)
            end_t = time.perf_counter()
            t_us = (end_t - start_t) * 1_000_000
            found = "Yes" if path else "No"
            cost_str = str(cost) if cost != float('inf') else "N/A"
            output += f"{name:<10}{found:<8}{cost_str:<8}{nodes:<8}{t_us:<12.2f}\n"
            results.append((name, path, cost))
        valid = [(n, p, c) for n, p, c in results if p and c != float('inf')]
        if valid:
            best_algo, best_path, best_cost = min(valid, key=lambda x: x[2])
            output += f"\n✅ Shortest path found by {best_algo} ({best_cost} steps)."
            # animate best path for clarity
            self.animate_path(best_path, index=1, delay=60)
        else:
            output += "\nNo path found."
        self.results_text.config(state=tk.NORMAL)
        self.results_text.delete(1.0, tk.END)
        self.results_text.insert(tk.END, output)
        self.results_text.config(state=tk.DISABLED)

    # VISUALIZATION
  
    def visualize_selected(self):
        if not self.start_point or not self.goal_point:
            messagebox.showerror("Error", "Please set Start and Goal points.")
            return
        self.clear_path_drawing()
        algo_name = self.algo_var.get()
        algo_funcs = {
            "BFS": bfs_visual,
            "DFS": dfs_visual,
            "Dijkstra": dijkstra_visual,
            "A*": astar_visual
        }
        func = algo_funcs.get(algo_name)
        if not func:
            messagebox.showerror("Error", "Unknown algorithm selected.")
            return

        visited_cells = []
        def update_callback(cell):
            visited_cells.append(cell)

        # run the algorithm (collect visited order)
        start_t = time.perf_counter()
        path = func(self.maze_data, self.start_point, self.goal_point, update_callback)
        end_t = time.perf_counter()
        t_us = (end_t - start_t) * 1_000_000
        nodes_visited = len(visited_cells)

        # animate visited cells sequentially, then draw path
        def animate_visits(index=0, visit_delay=40):
            if index < len(visited_cells):
                r, c = visited_cells[index]
                if (r, c) not in [self.start_point, self.goal_point]:
                    self.grid_cells[r][c].config(bg=self.COLOR_VISITED)
                self.master.after(visit_delay, animate_visits, index + 1, visit_delay)
            else:
                # after visiting, show metrics and draw path
                self.results_text.config(state=tk.NORMAL)
                self.results_text.delete(1.0, tk.END)
                found = "Yes" if path else "No"
                cost = len(path)-1 if path else float('inf')
                cost_str = str(cost) if cost != float('inf') else "N/A"
                out = (f"Algorithm: {algo_name}\n"
                       f"Path found: {found}\n"
                       f"Steps (cost): {cost_str}\n"
                       f"Nodes visited: {nodes_visited}\n"
                       f"Time taken (visual run): {t_us:.2f} µs\n")
                self.results_text.insert(tk.END, out)
                self.results_text.config(state=tk.DISABLED)
                if path:
           
                    self.animate_path(path, index=1, delay=80)

        animate_visits(0, visit_delay=40)

# MAIN EXECUTION

if __name__ == "__main__":
    root = tk.Tk()
    app = PathfindingApp(root)
    root.mainloop()
