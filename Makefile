# author	:  Rajan Khullar
# created	:  11/28/16
# updated	:  11/28/16

rebuild: clean compile

clean:
	@rm -rf *.class *~

compile: Node.java Queue.java Process.java TimeBlock.java RoundRobin1.java Demo.java
	@javac Demo.java

test: rebuild
	@java Demo
