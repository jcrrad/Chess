space :=
space +=

BIN=./bin
UT=./ut
TESTS=src/test/
SRC=src/main/
JARS=$(subst $(space),:, $(shell find src/jars -name "*.jar" -type f))
FILES=$(shell find $(SRC) -name "*.java" -type f)
TEST_FILES=$(shell find $(TESTS) -name "*.java" -type f)

tfiles=$(subst /,., $(subst $(TESTS)/,,$(basename $(shell find $(TESTS) -name "*.java" -type f))))


show:
	echo $(tfiles)

install: client server

client: main
	@echo "Main-Class: core.client.Runner\n" > $(BIN)/client_manifest.txt
	@cd $(BIN); jar cfm ../Client.jar client_manifest.txt .* 

server: main
	@echo "Main-Class: core.server.Server\n" > $(BIN)/server_manifest.txt
	@cd $(BIN); jar cfm ../Server.jar server_manifest.txt .* 

ut: main tests
	@java -cp src/:src/jars/*:src/main/:src/test/ org.junit.runner.JUnitCore $(tfiles)

clean: clean_app clean_ut


#HELPERS BELOW THIS POINT

main: clean_app
	@mkdir $(BIN)
	@javac -d $(BIN)/ -classpath .:$(SRC):$(JARS) $(FILES)
   
tests: clean_ut
	@mkdir $(UT)
	@javac -d $(UT) -classpath .:$(SRC):$(TESTS):$(JARS) $(TEST_FILES)

clean_app:
	@rm -rf $(BIN)
	@rm -rf *.jar

clean_ut:
	@rm -rf $(UT)
