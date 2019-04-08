.SILENT:
.DEFAULT_GOAL=help
.PHONY: clear help
ESPACE_HELP=10

run:  ## compile et lance le fichier compilé
	make compile
	java BrassiGestion

#threadInit: ThreadInit.java##compile ThreadX
#	javac ThreadInit.java

interface: Interface.java##compile ThreadX
		javac Interface.java

compile: interface BrassiGestion.java  ## Compile le projet

	javac BrassiGestion.java


clear: ## Supprime les fichiers temporaires non indispensables

	rm -f *.class

help: ## Affiche l'aide

	@grep -h -E '^[a-zA-Z_-]+:.*?## .*$$' $(MAKEFILE_LIST) | sort | awk 'BEGIN {FS = ":.*?## "}; {printf "\033[36m%-$(ESPACE_HELP)s\033[0m %s\n", $$1, $$2}'
