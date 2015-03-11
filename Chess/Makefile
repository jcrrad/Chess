tfiles=$(shell find . -name "*Test.java" | sed -e 's/.\/src\/test\///' -e 's/\//./g' -e 's/\.java//')
install:
	@find ./src -name "*.java" > source.txt
	@javac -classpath src/main/:src/jars/* @source.txt 
	@rm -f source.txt

client:	
	java -classpath src/main/:src/jars/* core.client.Runner $(server)

server: 
	java -classpath src/main/:src/jars/* core.server.Server $(port)

clean:
	@find . -name "*.class" -exec rm -f {} \;

test:
	@java -cp src/:src/jars/*:src/main/:src/test/ org.junit.runner.JUnitCore $(tfiles)
