.SILENT:
.DEFAULT_GOAL=help
.PHONY: clear help
ESPACE_HELP=10

JFLAGS =
JC = javac
JVM= java

BrassiGestion: ## Compile et lance le projet
	make compile
	make run

run:  ## Lance le projet
	$(JVM) BrassiGestion

compile: BrassiGestion.java composants
	$(JC) BrassiGestion.java

composants: exceptions## Compile tout le projet
	$(JC) composants/Client.java
	$(JC) composants/Locality.java
	$(JC) composants/BusinessUnit.java
	$(JC) composants/Order.java
	$(JC) composants/OrderLine.java

exceptions: ## compile les exceptions
	$(JC) composants/exceptions/LocalityException.java

clear: ## Supprime les fichiers temporaires non indispensables

	rm -f *.class

help: ## Affiche l'aide

	@grep -h -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-$(ESPACE_HELP)s\033[0m %s\n", $$1, $$2}'
