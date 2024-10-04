#ifndef GRAPH
#define GRAPH
#include <unordered_map>
#include <vector>
#include <string>

class Edge {
    public:
        int from;
        int to;
        std::string property; 
        Edge();
        virtual ~Edge();
};

class Node {
    public:
        int id;
        std::string name;
        Node();
        virtual ~Node();
};

class Graph {
    protected:
        std::unordered_map<int, Node> nodes;
        std::unordered_map<int, Edge> adjs;
    public:
        Graph();
        virtual ~Graph();

};

#endif