.SILENT: all
.DEFAULT_GOAL=help
.PHONY: clear help
ESPACE_HELP=10

JFLAGS =
JC = javac
JVM= java
DOC = javadoc

run:## Lance le projet
	$(JVM) Main

all: Main.java interface javadoc## Compile tout le projet et la javadoc
	$(JC) Main.java

compile: Main.java interface## Compile tout le projet
	$(JC) Main.java

interface: controller gui/OrderGui.java
	$(JC) gui/OrderGui.java

controller: business controller/Controller.java controller/InterfaceController.java
	$(JC) controller/InterfaceController.java
	$(JC) controller/Controller.java

business: dataccess business/Business.java business/BusinessInterface.java
	$(JC) business/BusinessInterface.java
	$(JC) business/Business.java

dataccess: composants dataccess/Data.java dataccess/DataMock.java dataccess/InterfaceData.java
	$(JC) dataccess/InterfaceData.java
	$(JC) dataccess/DataMock.java

composants: exceptions
	$(JC) composants/Client.java
	$(JC) composants/Locality.java
	$(JC) composants/BusinessUnit.java
	$(JC) composants/Order.java
	$(JC) composants/OrderLine.java

exceptions: composants/exceptions/ClientException.java composants/exceptions/LocalityException.java composants/exceptions/BusinessUnitException.java composants/exceptions/OrderException.java composants/exceptions/OrderLineException.java
	$(JC) composants/exceptions/ClientException.java
	$(JC) composants/exceptions/LocalityException.java
	$(JC) composants/exceptions/BusinessUnitException.java
	$(JC) composants/exceptions/OrderException.java
	$(JC) composants/exceptions/OrderLineException.java

javadoc: ##Génère la javadoc
	$(DOC) business controller dataccess composants gui


clear: ## Supprime les fichiers temporaires non indispensables

	rm -rf *.class
	rm -rf *.js
	rm -rf *.html
	rm -rf *.css

	rm -rf */*.class
	rm -rf */*.js
	rm -rf */*.html
	rm -rf */*.css

	rm -rf */*/*.class
	rm -rf */*/*.js
	rm -rf */*/*.html
	rm -rf */*/*.css

help: ## Affiche l'aide

	@grep -h -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-$(ESPACE_HELP)s\033[0m %s\n", $$1, $$2}'
