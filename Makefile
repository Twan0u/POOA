.SILENT:
.DEFAULT_GOAL=help
.PHONY: clear help
ESPACE_HELP=10

JFLAGS =
JC = javac
JVM= java
DOC = javadoc

Interface.class: Interface.java
	$(JC) Interface.java

javadoc: Interface.java BrassiGestion.java ##Génère la javadoc
	javadoc Interface.java BrassiGestion.java composants composants.exceptions

run: Brassigestion.class ## Lance le projet
	$(JVM) BrassiGestion

Brassigestion.class: BrassiGestion.java Interface.class composants
	$(JC) BrassiGestion.java

composants: exceptions## Compile tout le projet
	$(JC) composants/Client.java
	$(JC) composants/Locality.java
	$(JC) composants/BusinessUnit.java
	$(JC) composants/Order.java
	$(JC) composants/OrderLine.java

exceptions: ## compile les exceptions
	$(JC) composants/exceptions/LocalityException.java
	$(JC) composants/exceptions/BeerException.java
	$(JC) composants/exceptions/BusinessUnitException.java
	$(JC) composants/exceptions/ClientException.java
	$(JC) composants/exceptions/OrderException.java
	$(JC) composants/exceptions/OrderLineException.java

clear: ## Supprime les fichiers temporaires non indispensables

	rm -f *.class

help: ## Affiche l'aide

	@grep -h -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-$(ESPACE_HELP)s\033[0m %s\n", $$1, $$2}'
