JAVAC = javac
JFLAGS = -d bin -sourcepath src

PACKAGE_SOURCES := $(wildcard src/*/*.java)
CLASSES = $(PACKAGE_SOURCES:src/%.java=bin/%.class)

all: $(CLASSES)

bin/%.class: src/%.java
	@mkdir -p $(@D)
	$(JAVAC) $(JFLAGS) $<

clean:
	rm -rf bin/*

.PHONY: runSerial
runSerial: $(CLASSES)
	bash runSerial.sh

.PHONY: runParallel
runParallel: $(CLASSES)
	bash runParallel.sh