# Illumio-Coding-Challenge

## Description
- `Firewall.java`: The class to read `.csv` file, call `acceptPacket` method and test some common and corner cases.
- `NetworkRule.java`: The class which defines the four network rules.
- `Utils.java`: The class which define some helper function.

## Test
I use Junit to test the code. My test case includes all the common and corner test cases and it pass unit test successfully. But I need to implement large dataset of test case if needed in the future in order to put it into production.

## Implementation
After reading the requirement, I find the the most important demand for 
this question is the speed after the dataset has been loaded. I think the tradeoff is to `sacrifice the initializing speed for faster quering and passing speed` 

So I do two important parts for my code:  

- Use hash function to generate a hashcode about the four rules and store it in the hashmap, `Key` is the hashcode, `Value` is whether it exists.
- Break down the initial csv into 4 `HashMap` (since direction and protocol each only take on 2 values) in order to speed up query, each map query's time complexity is `O(1)`, this can then be used to store each Network Rule uniquely on the hash map

## Refinements
If I had more time, I would like to use some other fancy data structure and algorithm to improve my code. 

- I can using consistent hashing to make my hash function more precise because simple hash function may cause hash collision some time.
- With my implementation using a hashmap, the space complexity increases significantly as the more network rules with ranges of ports/ipAddress are added. Some data structure like `Trie tree` or `Interval tree` can be used as initializing the dataset to store port/ip range, it's more efficient than turning it to hashmap.

## Teams
I am interested in the Policy team (first choice) and Data team(second choice).
 
