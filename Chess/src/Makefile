install:
	@clear
	@find -name "*.java" > source.txt
	@javac -classpath .:jars/* @source.txt 
	@rm -f source.txt

client:	
	cd main; java core/client/Runner

server: 
	cd main;java core/server/Server

clean:
	@rm -rf *.class
