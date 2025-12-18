import tkinter as tk
from tkinter import ttk, messagebox, filedialog
import heapq
import time
import json
from collections import deque
import sv_ttk

# -------------------------------------
# ALGORITHMS
# -------------------------------------
dirs = [(1,0), (-1,0), (0,1), (0,-1)]

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
            if 0<=nx<rows and 0<=ny<cols and maze[nx][ny] != float('inf') and (nx,ny) not in visited:
                visited.add((nx,ny))
                q.append(((nx,ny), path+[(nx,ny)]))
                
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
            if 0<=nx<rows and 0<=ny<cols and maze[nx][ny] != float('inf') and (nx,ny) not in visited:
                stack.append(((nx,ny), path+[(nx,ny)]))
                
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
            if 0<=nx<rows and 0<=ny<cols and maze[nx][ny] != float('inf'):
                if (nx, ny) not in visited:
                    move_cost = maze[nx][ny]
                    new_cost = cost + move_cost
                    heapq.heappush(pq, (new_cost, (nx, ny), path + [(nx, ny)]))
                    
    return None, float('inf'), nodes_visited

def heuristic(a, b):
    return abs(a[0]-b[0]) + abs(a[1]-b[1])

def astar(maze, start, goal):
    rows, cols = len(maze), len(maze[0])
    h_start = heuristic(start, goal)
    pq = [(h_start, 0, start, [start])]
    g_scores = {start: 0}
    nodes_visited = 0

    while pq:
        nodes_visited += 1
        f, g, (x, y), path = heapq.heappop(pq)
        
        if (x, y) == goal:
            return path, g, nodes_visited
            
        if g > g_scores.get((x,y), float('inf')):
            continue
            
        for dx, dy in dirs:
            nx, ny = x+dx, y+dy
            if 0<=nx<rows and 0<=ny<cols and maze[nx][ny] != float('inf'):
                move_cost = maze[nx][ny]
                new_g = g + move_cost
                
                if new_g < g_scores.get((nx, ny), float('inf')):
                    g_scores[(nx, ny)] = new_g
                    new_h = heuristic((nx, ny), goal)
                    new_f = new_g + new_h
                    heapq.heappush(pq, (new_f, new_g, (nx, ny), path + [(nx, ny)]))
                    
    return None, float('inf'), nodes_visited

# -------------------------------------
# ENHANCED UI APPLICATION
# -------------------------------------
class PathfindingApp:
    
    CELL_SIZE = 28
    
    # Enhanced color scheme
    COLOR_EMPTY = "#1a1a1a"
    COLOR_SMOKE = "#FFB84D"
    COLOR_DEBRIS = "#8B4513"
    COLOR_OBSTACLE = "#FF4444"
    COLOR_START = "#00D9FF"
    COLOR_GOAL = "#00FF88"
    COLOR_PATH = "#9D4EDD"
    COLOR_PATH_HIGHLIGHT = "#C77DFF"

    def __init__(self, master):
        self.master = master
        master.title("🚨 Smart Evacuation Route Finder")
        master.geometry("1600x900")
        
        # Keyboard shortcuts
        master.bind('<Control-z>', lambda e: self.undo_last_action())
        master.bind('<Control-s>', lambda e: self.save_scenario())
        master.bind('<Control-o>', lambda e: self.load_scenario())
        master.bind('<space>', lambda e: self.run_all_algorithms())
        
        # Main container
        main_container = ttk.Frame(master)
        main_container.pack(fill=tk.BOTH, expand=True, padx=5, pady=5)
        
        # Left panel (controls) - FIXED HEIGHT
        left_container = ttk.Frame(main_container, width=420)
        left_container.pack(side=tk.LEFT, fill=tk.BOTH, padx=(0, 10))
        left_container.pack_propagate(False)
        
        # Split left panel into top (controls) and bottom (results)
        self.control_frame = ttk.Frame(left_container)
        self.control_frame.pack(side=tk.TOP, fill=tk.BOTH, expand=False)
        
        # Results frame - ALWAYS VISIBLE
        self.results_container = ttk.LabelFrame(left_container, text="📊 Algorithm Results", padding=10)
        self.results_container.pack(side=tk.BOTTOM, fill=tk.BOTH, expand=True, pady=(10, 0))
        
        # Right panel (grid + stats)
        right_panel = ttk.Frame(main_container)
        right_panel.pack(side=tk.RIGHT, fill=tk.BOTH, expand=True)
        
        # Top stats bar
        self.stats_frame = ttk.Frame(right_panel)
        self.stats_frame.pack(fill=tk.X, pady=(0, 10))
        
        # Grid container with scrollbars
        grid_container = ttk.Frame(right_panel)
        grid_container.pack(fill=tk.BOTH, expand=True)
        
        # Canvas for scrollable grid
        self.canvas = tk.Canvas(grid_container, bg="#0d0d0d", highlightthickness=0)
        v_scrollbar = ttk.Scrollbar(grid_container, orient=tk.VERTICAL, command=self.canvas.yview)
        h_scrollbar = ttk.Scrollbar(grid_container, orient=tk.HORIZONTAL, command=self.canvas.xview)
        
        self.grid_frame = ttk.Frame(self.canvas)
        self.canvas_window = self.canvas.create_window((0, 0), window=self.grid_frame, anchor=tk.NW)
        
        self.canvas.configure(yscrollcommand=v_scrollbar.set, xscrollcommand=h_scrollbar.set)
        
        v_scrollbar.pack(side=tk.RIGHT, fill=tk.Y)
        h_scrollbar.pack(side=tk.BOTTOM, fill=tk.X)
        self.canvas.pack(side=tk.LEFT, fill=tk.BOTH, expand=True)
        
        self.grid_frame.bind("<Configure>", lambda e: self.canvas.configure(scrollregion=self.canvas.bbox("all")))
        
        # State variables
        self.rows = 25
        self.cols = 25
        self.start_point = None
        self.goal_points = []
        self.maze_data = [[1 for _ in range(self.cols)] for _ in range(self.rows)]
        self.grid_cells = []
        self.history = []
        self.is_dragging = False
        self.animation_speed = tk.IntVar(value=2)
        self.show_visited = tk.BooleanVar(value=False)
        
        self.draw_mode = tk.StringVar(value="wall")
        self.draw_mode.trace_add("write", self.update_status_label)

        self.setup_ui()
        self.setup_results_ui()
        self.create_stats_display()
        self.create_grid()

    def setup_ui(self):
        # Header - MINIMAL
        header = ttk.Frame(self.control_frame)
        header.pack(fill=tk.X, pady=(0, 5))
        
        title_label = ttk.Label(header, text="🚨 Route Finder", font=("Segoe UI", 11, "bold"))
        title_label.pack(anchor=tk.W)
        
        ttk.Separator(self.control_frame, orient=tk.HORIZONTAL).pack(fill=tk.X, pady=5)
        
        # Grid Setup - MINIMAL
        setup_frame = ttk.LabelFrame(self.control_frame, text="Grid", padding=5)
        setup_frame.pack(fill=tk.X, pady=(0, 5))
        
        size_grid = ttk.Frame(setup_frame)
        size_grid.pack(fill=tk.X)
        
        ttk.Label(size_grid, text="Rows:", width=6, font=("Segoe UI", 8)).grid(row=0, column=0, sticky=tk.W, pady=1)
        self.rows_var = tk.StringVar(value=str(self.rows))
        ttk.Spinbox(size_grid, from_=5, to=50, textvariable=self.rows_var, width=5).grid(row=0, column=1, sticky=tk.W, padx=(3, 0))
        
        ttk.Label(size_grid, text="Cols:", width=6, font=("Segoe UI", 8)).grid(row=1, column=0, sticky=tk.W, pady=1)
        self.cols_var = tk.StringVar(value=str(self.cols))
        ttk.Spinbox(size_grid, from_=5, to=50, textvariable=self.cols_var, width=5).grid(row=1, column=1, sticky=tk.W, padx=(3, 0))
        
        ttk.Button(setup_frame, text="Create", 
                  command=self.create_grid, style="Accent.TButton").pack(fill=tk.X, pady=(5, 0))
        
        # Draw Tools - MINIMAL
        draw_frame = ttk.LabelFrame(self.control_frame, text="Tools", padding=5)
        draw_frame.pack(fill=tk.X, pady=(0, 5))
        
        tools = [
            ("🎯 Start", "start"),
            ("🚪 Exit", "goal"),
            ("🔥 Wall", "wall"),
            ("🪨 Debris", "debris"),
            ("💨 Smoke", "smoke"),
            ("✓ Clear", "empty")
        ]
        
        for text, value in tools:
            ttk.Radiobutton(draw_frame, text=text, variable=self.draw_mode, 
                          value=value).pack(anchor=tk.W, pady=0)
        
        self.status_label = ttk.Label(draw_frame, text="", font=("Segoe UI", 7, "italic"),
                                     foreground="#00D9FF", wraplength=380)
        self.status_label.pack(anchor=tk.W, pady=(3, 0))
        self.update_status_label()
        
        # Settings - MINIMAL
        settings_frame = ttk.LabelFrame(self.control_frame, text="Speed", padding=5)
        settings_frame.pack(fill=tk.X, pady=(0, 5))
        
        speed_frame = ttk.Frame(settings_frame)
        speed_frame.pack(fill=tk.X)
        
        ttk.Scale(speed_frame, from_=1, to=5, variable=self.animation_speed, 
                 orient=tk.HORIZONTAL).pack(side=tk.LEFT, fill=tk.X, expand=True)
        ttk.Label(speed_frame, textvariable=self.animation_speed, width=2, 
                 font=("Segoe UI", 8)).pack(side=tk.LEFT, padx=(3, 0))
        
        # Actions - MINIMAL
        action_frame = ttk.Frame(self.control_frame)
        action_frame.pack(fill=tk.X, pady=(0, 5))

        self.find_path_button = ttk.Button(action_frame, text="▶ Find Route", 
                                          command=self.run_all_algorithms, 
                                          style="Accent.TButton")
        self.find_path_button.pack(fill=tk.X, pady=(0, 3))
        
        btn_grid = ttk.Frame(action_frame)
        btn_grid.pack(fill=tk.X, pady=(0, 3))
        
        ttk.Button(btn_grid, text="Clear Path", 
                  command=self.clear_path_drawing).pack(side=tk.LEFT, fill=tk.X, expand=True, padx=(0, 2))
        ttk.Button(btn_grid, text="Clear All", 
                  command=self.confirm_clear_all).pack(side=tk.LEFT, fill=tk.X, expand=True)
        
        file_frame = ttk.Frame(action_frame)
        file_frame.pack(fill=tk.X)
        
        ttk.Button(file_frame, text="Save", 
                  command=self.save_scenario).pack(side=tk.LEFT, fill=tk.X, expand=True, padx=(0, 2))
        ttk.Button(file_frame, text="Load", 
                  command=self.load_scenario).pack(side=tk.LEFT, fill=tk.X, expand=True)

    def setup_results_ui(self):
        """Setup the results display - always visible at bottom"""
        cols = ('Algorithm', 'Cost', 'Nodes', 'Time')
        self.results_tree = ttk.Treeview(self.results_container, columns=cols, 
                                         show='headings', height=5)
        
        widths = {'Algorithm': 100, 'Cost': 70, 'Nodes': 70, 'Time': 80}
        for col in cols:
            self.results_tree.heading(col, text=col)
            self.results_tree.column(col, width=widths[col], anchor=tk.E if col != 'Algorithm' else tk.W)
        
        scrollbar = ttk.Scrollbar(self.results_container, orient=tk.VERTICAL, 
                                 command=self.results_tree.yview)
        self.results_tree.configure(yscroll=scrollbar.set)
        scrollbar.pack(side=tk.RIGHT, fill=tk.Y)
        self.results_tree.pack(fill=tk.BOTH, expand=True, pady=(0, 8))

        self.result_summary_label = ttk.Label(self.results_container, text="Ready to find paths", 
                                             wraplength=360, justify=tk.LEFT, 
                                             font=("Segoe UI", 9, "bold"),
                                             foreground="#00D9FF")
        self.result_summary_label.pack(fill=tk.X)

    def create_stats_display(self):
        """Create real-time statistics display"""
        stats = [
            ("📍", "start_stat", "Not set"),
            ("🚪", "exits_stat", "0"),
            ("🔥", "obstacles_stat", "0"),
            ("💨", "hazards_stat", "0")
        ]
        
        for label, var_name, default in stats:
            frame = ttk.Frame(self.stats_frame)
            frame.pack(side=tk.LEFT, padx=12)
            
            ttk.Label(frame, text=label, font=("Segoe UI", 11)).pack(side=tk.LEFT)
            stat_var = tk.StringVar(value=default)
            setattr(self, var_name, stat_var)
            ttk.Label(frame, textvariable=stat_var, font=("Segoe UI", 10, "bold"),
                     foreground="#00D9FF").pack(side=tk.LEFT, padx=(3, 0))

    def update_stats(self):
        """Update statistics display"""
        self.start_stat.set("Set" if self.start_point else "None")
        self.exits_stat.set(str(len(self.goal_points)))
        
        obstacles = sum(1 for row in self.maze_data for cell in row if cell == float('inf'))
        hazards = sum(1 for row in self.maze_data for cell in row if cell in [5, 10])
        
        self.obstacles_stat.set(str(obstacles))
        self.hazards_stat.set(str(hazards))

    def get_color_for_cost(self, cost):
        if cost == 1: return self.COLOR_EMPTY
        elif cost == 5: return self.COLOR_SMOKE
        elif cost == 10: return self.COLOR_DEBRIS
        elif cost == float('inf'): return self.COLOR_OBSTACLE
        return self.COLOR_EMPTY

    def update_status_label(self, *args):
        mode_text = {
            "start": "Click to set starting position",
            "goal": "Click to add exit points",
            "wall": "Draw impassable walls",
            "debris": "Draw heavy debris (cost 10)",
            "smoke": "Draw smoke areas (cost 5)",
            "empty": "Clear cells (cost 1)"
        }
        self.status_label.config(text=mode_text.get(self.draw_mode.get(), ''))

    def create_grid(self):
        try:
            new_rows = int(self.rows_var.get())
            new_cols = int(self.cols_var.get())
            if new_rows < 2 or new_cols < 2 or new_rows > 50 or new_cols > 50:
                raise ValueError
            self.rows, self.cols = new_rows, new_cols
        except ValueError:
            messagebox.showerror("Error", "Grid size must be 2-50.")
            return

        for widget in self.grid_frame.winfo_children():
            widget.destroy()
            
        self.start_point = None
        self.goal_points = []
        self.maze_data = [[1 for _ in range(self.cols)] for _ in range(self.rows)]
        self.grid_cells = []
        self.history = []
        
        for r in range(self.rows):
            row_cells = []
            for c in range(self.cols):
                cell = tk.Frame(self.grid_frame,
                               width=self.CELL_SIZE,
                               height=self.CELL_SIZE,
                               bg=self.COLOR_EMPTY,
                               borderwidth=1,
                               relief="solid",
                               highlightbackground="#333",
                               highlightthickness=1)
                
                cell.bind("<Button-1>", lambda e, r=r, c=c: self.on_grid_click(r, c))
                cell.bind("<B1-Motion>", lambda e, r=r, c=c: self.on_grid_drag(r, c))
                cell.bind("<ButtonRelease-1>", lambda e: self.end_drag())
                
                cell.grid(row=r, column=c, padx=1, pady=1)
                row_cells.append(cell)
            self.grid_cells.append(row_cells)
            
        self.clear_results()
        self.update_stats()

    def save_state(self):
        """Save current state for undo"""
        state = {
            'maze': [row[:] for row in self.maze_data],
            'start': self.start_point,
            'goals': self.goal_points[:]
        }
        self.history.append(state)
        if len(self.history) > 50:
            self.history.pop(0)

    def undo_last_action(self):
        """Undo the last action"""
        if not self.history:
            return
            
        state = self.history.pop()
        self.maze_data = state['maze']
        self.start_point = state['start']
        self.goal_points = state['goals']
        
        self.redraw_grid()
        self.update_stats()

    def redraw_grid(self):
        """Redraw entire grid from maze_data"""
        for r in range(self.rows):
            for c in range(self.cols):
                cost = self.maze_data[r][c]
                color = self.get_color_for_cost(cost)
                self.grid_cells[r][c].config(bg=color)
        
        if self.start_point:
            r, c = self.start_point
            self.grid_cells[r][c].config(bg=self.COLOR_START)
        
        for r, c in self.goal_points:
            self.grid_cells[r][c].config(bg=self.COLOR_GOAL)

    def on_grid_click(self, r, c):
        self.save_state()
        self.is_dragging = True
        mode = self.draw_mode.get()
        self.clear_path_drawing()
        
        if (r,c) == self.start_point:
            self.start_point = None
        if (r,c) in self.goal_points:
            self.goal_points.remove((r,c))
        
        if mode == "start":
            if self.start_point:
                old_r, old_c = self.start_point
                old_cost = self.maze_data[old_r][old_c]
                self.grid_cells[old_r][old_c].config(bg=self.get_color_for_cost(old_cost))
            
            self.start_point = (r, c)
            self.maze_data[r][c] = 1
            self.grid_cells[r][c].config(bg=self.COLOR_START)
        
        elif mode == "goal":
            if (r,c) not in self.goal_points:
                self.goal_points.append((r,c))
                self.maze_data[r][c] = 1
                self.grid_cells[r][c].config(bg=self.COLOR_GOAL)
        
        elif mode == "wall":
            self.maze_data[r][c] = float('inf')
            self.grid_cells[r][c].config(bg=self.COLOR_OBSTACLE)
        elif mode == "debris":
            self.maze_data[r][c] = 10
            self.grid_cells[r][c].config(bg=self.COLOR_DEBRIS)
        elif mode == "smoke":
            self.maze_data[r][c] = 5
            self.grid_cells[r][c].config(bg=self.COLOR_SMOKE)
        elif mode == "empty":
            self.maze_data[r][c] = 1
            self.grid_cells[r][c].config(bg=self.COLOR_EMPTY)
        
        self.update_stats()

    def on_grid_drag(self, r, c):
        if not self.is_dragging:
            return
        mode = self.draw_mode.get()
        if mode in ["wall", "debris", "smoke", "empty"]:
            if (r, c) == self.start_point or (r, c) in self.goal_points:
                return
            
            if mode == "wall":
                self.maze_data[r][c] = float('inf')
                self.grid_cells[r][c].config(bg=self.COLOR_OBSTACLE)
            elif mode == "debris":
                self.maze_data[r][c] = 10
                self.grid_cells[r][c].config(bg=self.COLOR_DEBRIS)
            elif mode == "smoke":
                self.maze_data[r][c] = 5
                self.grid_cells[r][c].config(bg=self.COLOR_SMOKE)
            elif mode == "empty":
                self.maze_data[r][c] = 1
                self.grid_cells[r][c].config(bg=self.COLOR_EMPTY)

    def end_drag(self):
        self.is_dragging = False
        self.update_stats()

    def clear_path_drawing(self):
        for r in range(self.rows):
            for c in range(self.cols):
                if (r,c) == self.start_point or (r,c) in self.goal_points:
                    continue
                cost = self.maze_data[r][c]
                color = self.get_color_for_cost(cost)
                self.grid_cells[r][c].config(bg=color)
        
        if self.start_point:
            r, c = self.start_point
            self.grid_cells[r][c].config(bg=self.COLOR_START)
        for (r, c) in self.goal_points:
            self.grid_cells[r][c].config(bg=self.COLOR_GOAL)
        
        self.clear_results()

    def clear_results(self):
        for i in self.results_tree.get_children():
            self.results_tree.delete(i)
        self.result_summary_label.config(text="Ready to find paths")

    def confirm_clear_all(self):
        if messagebox.askyesno("Confirm", "Clear entire grid?"):
            self.create_grid()

    def draw_path(self, path):
        if not path:
            return
        
        speed = self.animation_speed.get()
        base_delay = 300
        delay = max(1, base_delay // (len(path) * speed))
        
        def draw_segment(i):
            if i < len(path):
                (r, c) = path[i]
                if (r,c) != self.start_point and (r,c) not in self.goal_points:
                    intensity = 1 - (i / len(path)) * 0.4
                    color = self.COLOR_PATH_HIGHLIGHT if intensity > 0.7 else self.COLOR_PATH
                    self.grid_cells[r][c].config(bg=color)
                
                self.master.after(delay, draw_segment, i+1)
            else:
                if self.goal_points and path:
                    (r, c) = path[-1]
                    self.grid_cells[r][c].config(bg=self.COLOR_GOAL)

        draw_segment(1)

    def run_all_algorithms(self):
        if not self.start_point or not self.goal_points:
            messagebox.showwarning("Missing Data", "Set a start point and at least one exit.")
            return
            
        self.clear_path_drawing()
        self.find_path_button.config(state=tk.DISABLED)
        self.result_summary_label.config(text="Computing paths...")
        self.master.update()
        
        algos = [
            ("🎯 Dijkstra", dijkstra, True),
            ("⚡ A*", astar, True),
            ("📏 BFS", bfs, False),
            ("🌲 DFS", dfs, False)
        ]
        
        results = []
        
        for name, func, is_cost_aware in algos:
            best_path = None
            best_cost = float('inf')
            best_nodes = 0
            total_time = 0
            
            for goal in self.goal_points:
                start_time = time.perf_counter()
                path, cost, nodes = func(self.maze_data, self.start_point, goal)
                elapsed = (time.perf_counter() - start_time) * 1000
                
                total_time += elapsed
                
                if path and cost < best_cost:
                    best_cost = cost
                    best_path = path
                    best_nodes = nodes
            
            results.append((name, best_path, best_cost, best_nodes, total_time, is_cost_aware))
        
        # Display results
        for name, path, cost, nodes, t, is_cost_aware in results:
            cost_str = f"{cost:.0f}" if cost != float('inf') else "N/A"
            nodes_str = f"{nodes:,}"
            time_str = f"{t:.2f} ms"
            
            # Color code results
            tag = 'found' if path else 'notfound'
            item = self.results_tree.insert('', tk.END, values=(name, cost_str, nodes_str, time_str), tags=(tag,))
        
        # Find best
        valid = [(name, p, c) for name, p, c, n, t, aware in results if p and aware]
        
        if not valid:
            self.result_summary_label.config(text="❌ No path found!", foreground="#FF4444")
        else:
            best_algo, best_path, best_cost = min(valid, key=lambda x: x[2])
            self.result_summary_label.config(
                text=f"✅ {best_algo}: Cost {best_cost:.0f} | {len(best_path)-1} steps",
                foreground="#00FF88"
            )
            self.draw_path(best_path)
        
        self.find_path_button.config(state=tk.NORMAL)

    def save_scenario(self):
        """Save scenario to JSON"""
        if not self.start_point and not self.goal_points:
            messagebox.showinfo("Nothing to Save", "Create a scenario first.")
            return
            
        filename = filedialog.asksaveasfilename(
            defaultextension=".json",
            filetypes=[("JSON files", "*.json")],
            title="Save Scenario"
        )
        
        if filename:
            data = {
                'rows': self.rows,
                'cols': self.cols,
                'maze_data': self.maze_data,
                'start_point': self.start_point,
                'goal_points': self.goal_points
            }
            
            try:
                with open(filename, 'w') as f:
                    json.dump(data, f, indent=2)
                messagebox.showinfo("Success", "Scenario saved!")
            except Exception as e:
                messagebox.showerror("Error", f"Failed to save: {str(e)}")

    def load_scenario(self):
        """Load scenario from JSON"""
        filename = filedialog.askopenfilename(
            filetypes=[("JSON files", "*.json")],
            title="Load Scenario"
        )
        
        if filename:
            try:
                with open(filename, 'r') as f:
                    data = json.load(f)
                
                self.rows = data['rows']
                self.cols = data['cols']
                self.rows_var.set(str(self.rows))
                self.cols_var.set(str(self.cols))
                
                # Recreate grid
                for widget in self.grid_frame.winfo_children():
                    widget.destroy()
                
                self.maze_data = data['maze_data']
                self.start_point = tuple(data['start_point']) if data['start_point'] else None
                self.goal_points = [tuple(p) for p in data['goal_points']]
                self.grid_cells = []
                self.history = []
                
                # Build grid
                for r in range(self.rows):
                    row_cells = []
                    for c in range(self.cols):
                        cell = tk.Frame(self.grid_frame,
                                       width=self.CELL_SIZE,
                                       height=self.CELL_SIZE,
                                       bg=self.COLOR_EMPTY,
                                       borderwidth=1,
                                       relief="solid",
                                       highlightbackground="#333",
                                       highlightthickness=1)
                        
                        cell.bind("<Button-1>", lambda e, r=r, c=c: self.on_grid_click(r, c))
                        cell.bind("<B1-Motion>", lambda e, r=r, c=c: self.on_grid_drag(r, c))
                        cell.bind("<ButtonRelease-1>", lambda e: self.end_drag())
                        
                        cell.grid(row=r, column=c, padx=1, pady=1)
                        row_cells.append(cell)
                    self.grid_cells.append(row_cells)
                
                self.redraw_grid()
                self.update_stats()
                self.clear_results()
                
                messagebox.showinfo("Success", "Scenario loaded!")
            except Exception as e:
                messagebox.showerror("Error", f"Failed to load: {str(e)}")


if __name__ == "__main__":
    root = tk.Tk()
    sv_ttk.set_theme("dark")
    app = PathfindingApp(root)
    root.mainloop()