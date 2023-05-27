all: run

clean:
	rm -f out/Solver.jar out/SentimentAnalyzerParcs.jar

out/Solver.jar: out/parcs.jar src/Solver.java src/Input.java src/Result.java
	@javac -cp out/parcs.jar src/Solver.java src/Input.java src/Result.java
	@jar cf out/Solver.jar -C src Solver.class -C src Input.class -C src Result.class
	@rm -f src/Solver.class src/Input.class src/Result.class

out/SentimentAnalyzerParcs.jar: out/parcs.jar src/SentimentAnalyzerParcs.java src/Input.java src/Result.java
	@javac -cp out/parcs.jar src/SentimentAnalyzerParcs.java src/Input.java src/Result.java
	@jar cf out/SentimentAnalyzerParcs.jar -C src SentimentAnalyzerParcs.class -C src Input.class -C src Result.class
	@rm -f src/SentimentAnalyzerParcs.class src/Input.class src/Result.class

build: out/Solver.jar out/SentimentAnalyzerParcs.jar

run: out/Solver.jar out/SentimentAnalyzerParcs.jar
	@cd out && java -cp 'parcs.jar:Solver.jar' Solver
