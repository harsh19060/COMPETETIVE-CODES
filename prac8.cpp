#include <bits/stdc++.h>
using namespace std;

int n = 6;
set<vector<int>> s;

void helper(vector<vector<int> > &graph, vector<int> color, int vertex, vector<int> colours){
    int paint = 1, found = 0;
    vector<int> available(colours);
    for(int i=0; i<n; i++){
        if(graph[vertex][i] == 1){
            available.erase(remove(available.begin(), available.end(), color[i]), available.end());
        }
    }
    if(available.size() > 0){
        for(int j=0; j<available.size(); j++){
            color[vertex] = available[j];
            for(int i=0; i<n; i++){
                if(color[i] == -1){
                    found = 1;
                    helper(graph, color, i, colours);
                }
            }
            if(found == 0){
                s.insert(color);
            }
        }
        
    }else{
        return;
    }
}

int main(){
    vector<int> colours = {1,2,3};
    vector<vector<int> > graph = {
        {0, 1, 1, 0, 1, 1},
        {1, 0, 1, 0, 0, 0},
        {1, 1, 0, 1, 0, 0},
        {0, 0, 1, 0, 1, 0},
        {1, 0, 0, 1, 0, 1},
        {1, 0, 0, 0, 1, 0}
    };

    vector<int> color(n, -1);
    helper(graph, color, 0, colours);

    for(int i=0; i<n; i++){
        cout << "V" << i+1 << " ";
    }
    cout << endl;

    for (const auto& v : s) {
        for (int x : v)
            cout <<"C"<< x << " ";
        cout << endl;
    }

    if(s.size() == 0){
        cout << "Not Possible";
    }else{
        cout << "Total Combinations: " << s.size();
    }
    
    return 0;
}

