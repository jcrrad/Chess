space :=
space +=

BIN=./bin
UT=./ut
TESTS=src/test/
SRC=src/main/
JARS=$(subst $(space),:, $(shell find src/jars -name "*.jar" -type f))
FILES=$(shell find $(SRC) -name "*.java" -type f)
TEST_FILES=$(shell find $(TESTS) -name "*.java" -type f)

#tfiles=$(shell find . -name "*Test.java" | sed -e 's/.\/src\/test\///' -e
's/\//./g' -e 's/\.java//')
tfiles=$(subst /,., $(subst $(TESTS)/,,$(basename $(shell find $(TESTS)
-name "*.java" -type f))))


install: client server
   @find ./src -name "*.java" > source.txt
   @javac -classpath src/:src/jars/* @source.txt 
   @rm -f source.txt

client: main
   @echo "Main-Class: core.client.Runner\n" > $(BIN)/client_manifest.txt
   @cd $(BIN); jar cfm ../Client.jar client_manifest.txt .* 

server: main
   @echo "Main-Class: core.server.Server\n" > $(BIN)/server_manifest.txt
   @cd $(BIN); jar cfm ../Server.jar server_manifest.txt .* 

test: main tests
   @java -cp src/:src/jars/*:src/main/:src/test/ org.junit.runner.JUnitCore
$(tfiles)

main: clean_app
   @mkdir $(BIN)
   @javac -d $(BIN)/ -classpath .:$(SRC):$(JARS) $(FILES)
   
tests: clean_ut
   @mkdir $(UT)
   @javac -d $(UT) -classpath .:$(SRC):$(TESTS):$(JARS) $(TEST_FILES)

clean: clean_app clean_ut

clean_app:
	@rm -rf $(BIN)
	@rm -rf *.jar

clean_ut:
	@rm -rf $(UT)
