#!/bin/bash


find src -name "*.java" -print | xargs javac -d bin

if [ $? -eq 0 ]; then

java -Xms512m -Xmx32g -cp bin parallelImplementation.MonteCarloMinimizationParallel  700 700 -350  350   -350  350   0.5
java -Xms512m -Xmx32g -cp bin parallelImplementation.MonteCarloMinimizationParallel  1000 1000 -500  500   -500  500   0.5
java -Xms512m -Xmx32g -cp bin parallelImplementation.MonteCarloMinimizationParallel  2000 2000 -1000  1000   -1000  1000   0.3
java -Xms512m -Xmx32g -cp bin parallelImplementation.MonteCarloMinimizationParallel  2500 2500 -1250  1250   -1250  1250   0.2
java -Xms512m -Xmx32g -cp bin parallelImplementation.MonteCarloMinimizationParallel  3000 3000 -1500  1500   -1500  1500   0.9
java -Xms512m -Xmx32g -cp bin parallelImplementation.MonteCarloMinimizationParallel  3500 3500 -1750  1750   -1750  1750   0.5
java -Xms512m -Xmx32g -cp bin parallelImplementation.MonteCarloMinimizationParallel  4200 4200 -2100  2100   -2100  2100   0.8
java -Xms512m -Xmx32g -cp bin parallelImplementation.MonteCarloMinimizationParallel  5000 5000 -2500  2500   -2500  2500   0.1
java -Xms512m -Xmx32g -cp bin parallelImplementation.MonteCarloMinimizationParallel  6000 6000 -3000  3000   -3000  3000   0.05                    
java -Xms512m -Xmx32g -cp bin parallelImplementation.MonteCarloMinimizationParallel  7000 7000 -3500  3500   -3500  3500   0.09
java -Xms512m -Xmx32g -cp bin parallelImplementation.MonteCarloMinimizationParallel  8000 8000 -4000  4000   -4000  4000   0.07
java -Xms512m -Xmx32g -cp bin parallelImplementation.MonteCarloMinimizationParallel  9000 9000 -4500  4500   -4500  4500   0.9
java -Xms512m -Xmx32g -cp bin parallelImplementation.MonteCarloMinimizationParallel  10000 10000 -5000  5000   -5000  5000 .05
java -Xms512m -Xmx32g -cp bin parallelImplementation.MonteCarloMinimizationParallel  11000 11000 -5500  5500   -5500  5500   1
java -Xms512m -Xmx32g -cp bin parallelImplementation.MonteCarloMinimizationParallel  12000 12000 -6000  6000   -6000  6000   0.5
java -Xms512m -Xmx32g -cp bin parallelImplementation.MonteCarloMinimizationParallel  13000 13000 -6500  6500   -6500  6500   0.01
java -Xms512m -Xmx32g -cp bin parallelImplementation.MonteCarloMinimizationParallel  20000 20000 -10000  10000   -10000  10000   0.5
    
else
    echo "Compilation failed. Check the source code for errors."
fi