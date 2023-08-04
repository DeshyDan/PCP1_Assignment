#!/bin/bash


find src -name "*.java" -print | xargs javac -d bin

if [ $? -eq 0 ]; then

    # java -cp bin serialImplentation.MonteCarloMinimization 10 10 0 10 0 10 1
    java -cp bin serialImplentation.MonteCarloMinimization 6500  6500  -4000 4000 -4000 4000 .4
    java -cp bin serialImplentation.MonteCarloMinimization 6500  6500  -4000 4000 -4000 4000 .5
    java -cp bin serialImplentation.MonteCarloMinimization 6500  6500 -4000 4000 -4000 4000 .1
    java -cp bin serialImplentation.MonteCarloMinimization 6500  6500  -4000 4000 -4000 4000 .05
    # java -cp bin serialImplentation.MonteCarloMinimization 6000 6000 -6000 6000 -6000 6000 1
    

    find bin -name "*.class" -delete
else
    echo "Compilation failed. Check the source code for errors."
fi