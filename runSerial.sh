#!/bin/bash


find src -name "*.java" -print | xargs javac -d bin

if [ $? -eq 0 ]; then

java -cp bin serialImplentation.MonteCarloMinimization  50  50  -10   10 -10 10 0.1
java -cp bin serialImplentation.MonteCarloMinimization  100 100 -50   50 -50 50 0.5
java -cp bin serialImplentation.MonteCarloMinimization  200 200 -100  100 -100 100 0.9
java -cp bin serialImplentation.MonteCarloMinimization  150 150 -75   75 -75 75   0.2
java -cp bin serialImplentation.MonteCarloMinimization  250 250 -125  125   -125  125   0.8
java -cp bin serialImplentation.MonteCarloMinimization  500 500 -250  250   -250  250   0.4
java -cp bin serialImplentation.MonteCarloMinimization  450 450 -225  225   -225  225   0.3
java -cp bin serialImplentation.MonteCarloMinimization  600 600 -300  300   -300  300   0.2
java -cp bin serialImplentation.MonteCarloMinimization  550 550 -275  275   -275  275   0.1
java -cp bin serialImplentation.MonteCarloMinimization  700 700 -350  350   -350  350   0.5
java -cp bin serialImplentation.MonteCarloMinimization  1000 1000 -500  500   -500  500   0.5
java -cp bin serialImplentation.MonteCarloMinimization  1500 1500 -750  750   -750  750   0.4
java -cp bin serialImplentation.MonteCarloMinimization  2000 2000 -1000  1000   -1000  1000   0.3
java -cp bin serialImplentation.MonteCarloMinimization  2500 2500 -1250  1250   -1250  1250   0.2
java -cp bin serialImplentation.MonteCarloMinimization  3000 3000 -1500  1500   -1500  1500   0.1
java -cp bin serialImplentation.MonteCarloMinimization  3500 3500 -1750  1750   -1750  1750   0.5
java -cp bin serialImplentation.MonteCarloMinimization  4200 4200 -2100  2100   -2100  2100   0.8
java -cp bin serialImplentation.MonteCarloMinimization  5000 5000 -2500  2500   -2500  2500   0.7
java -cp bin serialImplentation.MonteCarloMinimization  6000 6000 -3000  3000   -3000  3000   0.6
java -cp bin serialImplentation.MonteCarloMinimization  7000 7000 -3500  3500   -3500  3500   0.5

java -cp bin serialImplentation.MonteCarloMinimization  8000 8000 -4000  4000   -4000  4000   0.4
java -cp bin serialImplentation.MonteCarloMinimization  9000 9000 -4500  4500   -4500  4500   0.3
java -cp bin serialImplentation.MonteCarloMinimization  10000 10000 -5000  5000   -5000  5000   0.2
java -cp bin serialImplentation.MonteCarloMinimization  11000 11000 -5500  5500   -5500  5500   0.1
java -cp bin serialImplentation.MonteCarloMinimization  12000 12000 -6000  6000   -6000  6000   0.5
java -cp bin serialImplentation.MonteCarloMinimization  13000 13000 -6500  6500   -6500  6500   0.5
java -cp bin serialImplentation.MonteCarloMinimization  20000 20000 -10000  10000   -10000  10000   0.5
java -cp bin serialImplentation.MonteCarloMinimization  30000 30000 -15000  15000   -15000  15000   0.5
java -cp bin serialImplentation.MonteCarloMinimization  100000 100000 -50000  50000   -50000  50000   0.2

    
else
    echo "Compilation failed. Check the source code for errors."
fi

