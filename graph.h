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
        std::vector<Edge> links;
        Node();
        virtual ~Node();
};

class Graph {
    protected:
        std::unordered_map<int, Node> nodes;
        std::unordered_map<Node, Edge> edges;
    public:
        Graph();
        virtual ~Graph();

};

#endif