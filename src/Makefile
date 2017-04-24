all: build

build:
	ant ..
pack:
	jar -cMf projekt2.zip ..
clean:
	if exist "../build" (rd /s /q "../build")
	if exist "../bin" (rd /s /q "../bin")
test:
	echo Pro spusteni JUnit testu je potreba pouzit IDE.
doc: Doxyfile
	doxygen && cd ../doc/latex/ && make && mv refman.pdf ../../dokumentace.pdf
	rm -r ../doc

run:
	if exist "../build" (java -cp "../build" "Calc")
	if exist "../bin" (java -cp "../bin" "Calc")