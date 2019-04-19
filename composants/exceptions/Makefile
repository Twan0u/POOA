.SILENT: all
.DEFAULT_GOAL=all
.PHONY: clear help
ESPACE_HELP=10

JFLAGS =
JC = javac
JVM= java
DOC = javadoc

EXCEPT = LocalityException.java BeerException.java BusinessUnitException.java ClientException.java OrderException.java OrderLineException.java

javadoc: $(EXCEPT) ##Génère la javadoc
	javadoc $(EXCEPT)

all: $(EXCEPT)## compile les exceptions
	$(JC) $(EXCEPT)

clear: ## Supprime les fichiers temporaires non indispensables

	rm -rf *.class
	rm -rf *.js
	rm -rf *.html
	rm -rf *.css

help: ## Affiche l'aide

	@grep -h -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-$(ESPACE_HELP)s\033[0m %s\n", $$1, $$2}'
