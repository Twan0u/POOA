.SILENT:
.DEFAULT_GOAL=help
.PHONY: clear help
ESPACE_HELP=10

JFLAGS =
JC = javac
JVM= java

all: ## Compile et lance le projet
	make compile
	make run

run:  ## Lance le projet
	$(JVM) BrassiGestion

compile : ## Compile tout le projet
	$(JC) BrassiGestion.java
	$(JC) BusinessUnit.java
	$(JC) Client.java
	$(JC) Interface.java
	$(JC) Locality.java
	$(JC) Order.java
	$(JC) OrderLine.java

clear: ## Supprime les fichiers temporaires non indispensables

	rm -f *.class

help: ## Affiche l'aide

	@grep -h -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-$(ESPACE_HELP)s\033[0m %s\n", $$1, $$2}'
