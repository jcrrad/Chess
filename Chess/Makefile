tfiles=$(shell find . -name "*Test.java" | sed -e 's/.\/src\/test\///' -e 's/\//./g' -e 's/\.java//')
install:
	@find ./src -name "*.java" > source.txt
	@javac -classpath src/:src/jars/* @source.txt 
	@rm -f source.txt

client:	
	cd src/main; java core/client/Runner $(server)

server: 
	cd src/main; java core/server/Server

clean:
	@find . -name "*.class" -exec rm -f {} \;

test:
	@java -cp src/:src/jars/*:src/main/:src/test/ org.junit.runner.JUnitCore $(tfiles)
