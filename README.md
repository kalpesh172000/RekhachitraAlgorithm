# RekhachitraAlgorithm
we will be accepting source and destination from the user
database will have graph, bus_route, bus_timing. (csv files present in the repository)
we will create a graph from the graph database.
it will be weighted graph.
first we will find all the paths from source to destination. 
these paths needs to be 
	-sorted according to total weight
	-format will be source->(n braching nodes)->destination. 
	-only source and destination can be inner nodes. 
after you get these multiple paths we will apply the calculating algorithm that will choose each path and use each bus route and all their timing to test whether 
	-the bus goes in right direction 
	-the bus is making user reach destination first 
as we are calculating these routes with timing we will be adding them to priority list.
top 5 paths form this list will be shown to the user.
