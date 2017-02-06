mkdir -p out
javac -d ./out ./application/*.java ./application/*/*.java ./application/*/*/*.java
java -cp "out" application.RunGame
