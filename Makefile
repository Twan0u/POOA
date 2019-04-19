.SILENT: all
.DEFAULT_GOAL=help
.PHONY: clear help
ESPACE_HELP=10

JFLAGS =
JC = javac
JVM= java
DOC = javadoc

javadoc: Interface.java BrassiGestion.java ##Génère la javadoc
	$(DOC) Interface.java BrassiGestion.java composants composants.exceptions

run: ## Lance le projet
	$(JVM) BrassiGestion

all: exceptions composants## Compile tout le projet
	$(JC) Interface.java
	$(JC)	BrassiGestion.java

composants: exceptions## Compile les composants

	$(JC) composants/Client.java
	$(JC) composants/Locality.java
	$(JC) composants/BusinessUnit.java
	$(JC) composants/Order.java
	$(JC) composants/OrderLine.java

exceptions: ## Compile les exceptions
	$(JC) composants/exceptions/ClientException.java
	$(JC) composants/exceptions/LocalityException.java
	$(JC) composants/exceptions/BusinessUnitException.java
	$(JC) composants/exceptions/OrderException.java
	$(JC) composants/exceptions/OrderLineException.java


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
