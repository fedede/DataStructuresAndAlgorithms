# DataStructuresAndAlgorithms
UC3M, Y1Q2

Programming language: Java

Exercises done in the Data Structures and Algorithms class. This README will contain a brief explanation of every exercise.


Exercise 4: SList implementation
  Here, we create the single-linked list data structure, composed of the following:
    
    Interfaces
      IList.java
      
    Classes
      SNode.java
      SList.java
      SListCircular.java
      SListTail.java
      Test.java
      
  Single-linked lists are managed using nodes. These type of lists have a fixed firstNode, which will be the starting point for every other node needed. Every element in the list is retrieved, created or deleted using nodes. Nodes are objects which contain a generic type element, and have a nextNode attribute which points to the following node. This pointer is what allows us to modify the list. A node that points to null is the last element in the list (not the case for circular lists), and a node that is not pointed to by any other node is deleted from the list, as there is no way to access said node. 
  
  Circular lists do not contain a node that points to null. This last element will simply point to the first element.
  
  Tail lists store the first node and the last node of the list.
