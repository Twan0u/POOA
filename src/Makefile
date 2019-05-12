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

all: gui/OrderGui.class Main.class javadoc## Compile tout le projet et la javadoc

Main.class: Main.java
	$(JC) Main.java

compile: gui/OrderGui.class Main.class## Compile tout le projet

gui/OrderGui.class: controller gui/OrderGui.java
	$(JC) gui/OrderGui.java

controller: business controller/Controller.class controller/InterfaceController.class

controller/Controller.class: controller/InterfaceController.class controller/Controller.java
		$(JC) controller/Controller.java

controller/InterfaceController.class: business controller/InterfaceController.java
		$(JC) controller/InterfaceController.java

business: dataccess business/BusinessInterface.class business/Business.class

business/BusinessInterface.class: business/BusinessInterface.java
	$(JC) business/BusinessInterface.java

business/Business.class:	business/Business.java
	$(JC) business/Business.java

dataccess: composants dataccess/InterfaceData.class dataccess/DataMock.class

dataccess/InterfaceData.class: dataccess/InterfaceData.java
	$(JC) dataccess/InterfaceData.java

dataccess/DataMock.class: dataccess/DataMock.java
	$(JC) dataccess/DataMock.java

composants: exceptions composants/Client.class composants/Locality.class composants/BusinessUnit.class composants/Order.class composants/OrderLine.class

composants/Client.class: composants/Client.java
	$(JC) composants/Client.java

composants/Locality.class: composants/Locality.java
	$(JC) composants/Locality.java

composants/BusinessUnit.class: composants/BusinessUnit.java
	$(JC) composants/BusinessUnit.java

composants/Order.class: composants/Order.java
	$(JC) composants/Order.java

composants/OrderLine.class:	composants/OrderLine.java
		$(JC) composants/OrderLine.java

exceptions: exceptions/ClientException.class exceptions/LocalityException.class exceptions/BusinessUnitException.class exceptions/OrderException.class exceptions/OrderLineException.class

exceptions/ClientException.class: exceptions/ClientException.java
	$(JC) exceptions/ClientException.java

exceptions/LocalityException.class: exceptions/LocalityException.java
	$(JC) exceptions/LocalityException.java

exceptions/BusinessUnitException.class: exceptions/BusinessUnitException.java
	$(JC) exceptions/BusinessUnitException.java

exceptions/OrderException.class: exceptions/OrderException.java
	$(JC) exceptions/OrderException.java

exceptions/OrderLineException.class: exceptions/OrderLineException.java
	$(JC) exceptions/OrderLineException.java

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
