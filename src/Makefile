.SILENT: all
.DEFAULT_GOAL=help
.PHONY: clear help
ESPACE_HELP=10

JFLAGS =
JC = javac
JVM= java
DOC = javadoc

run: compile## Lance le projet
	$(JVM) MainLinux

all: gui/OrderGui.class MainLinux.class javadoc## Compile tout le projet et la javadoc

MainLinux.class: MainLinux.java
	$(JC) MainLinux.java

compile: gui MainLinux.class## Compile tout le projet

gui: controller newOrderPanel gui/ThreadLoad.class gui/ModifyPanel.class gui/PreparePanel.class gui/StockPanel.class gui/OrderPanel.class gui/Gui.class gui/DeliveryPanel.class

gui/StockPanel.class: gui/StockPanel.java
	$(JC) gui/StockPanel.java

gui/PreparePanel.class: gui/PreparePanel.java
	$(JC) gui/PreparePanel.java

gui/ModifyPanel.class: gui/ModifyPanel.java
	$(JC) gui/ModifyPanel.java

gui/Gui.class:  gui/Gui.java
	$(JC) gui/Gui.java

gui/OrderPanel.class: gui/OrderPanel.java
	$(JC) gui/OrderPanel.java

gui/DeliveryPanel.class: gui/DeliveryPanel.java
	$(JC) gui/DeliveryPanel.java

gui/ThreadLoad.class: gui/ThreadLoad.java
	$(JC) gui/ThreadLoad.java

newOrderPanel: gui/neworderpanel/OrderAddForm.class gui/neworderpanel/BeerAddForm.class gui/neworderpanel/BeerAdd.class gui/neworderpanel/BeerTable.class

gui/neworderpanel/OrderAddForm.class: gui/neworderpanel/OrderAddForm.java
	$(JC) gui/neworderpanel/OrderAddForm.java

gui/neworderpanel/BeerAdd.class: gui/neworderpanel/BeerAdd.java
	$(JC) gui/neworderpanel/BeerAdd.java

gui/neworderpanel/BeerAddForm.class: gui/neworderpanel/BeerAddForm.java
	$(JC) gui/neworderpanel/BeerAddForm.java

gui/neworderpanel/BeerTable.class: gui/neworderpanel/BeerTable.java
			$(JC) gui/neworderpanel/BeerTable.java

controller: business controller/Controller.class controller/ControllerNewOrder.class controller/ControllerPrepare.class

controller/Controller.class:  controller/Controller.java
		$(JC) controller/Controller.java

controller/ControllerNewOrder.class: controller/ControllerNewOrder.java
		$(JC) controller/ControllerNewOrder.java

controller/ControllerPrepare.class: controller/ControllerPrepare.java
		$(JC) controller/ControllerPrepare.java

business: dataccess business/BusinessInterface.class business/Business.class

business/BusinessInterface.class: business/BusinessInterface.java
	$(JC) business/BusinessInterface.java

business/Business.class:	business/Business.java
	$(JC) business/Business.java

dataccess: composants dataccess/DataMock.class dataccess/SingletonConnection.class dataccess/BeerDBAccess.class dataccess/OrderDBAccess.class dataccess/BusinessDBAccess.class dataccess/LocalityDBAccess.class dataccess/ClientDBAccess.class

dataccess/InterfaceData.class: dataccess/InterfaceData.java
	$(JC) dataccess/InterfaceData.java

dataccess/DataMock.class: dataccess/DataMock.java
	$(JC) dataccess/DataMock.java


dataccess/BeerDBAccess.class: dataccess/BeerDBAccess.java
	$(JC) dataccess/BeerDBAccess.java

dataccess/BusinessDBAccess.class: dataccess/BusinessDBAccess.java
		$(JC) dataccess/BusinessDBAccess.java

dataccess/ClientDBAccess.class: dataccess/ClientDBAccess.java
			$(JC) dataccess/ClientDBAccess.java

dataccess/LocalityDBAccess.class: dataccess/LocalityDBAccess.java
			$(JC) dataccess/LocalityDBAccess.java

dataccess/OrderDBAccess.class: dataccess/OrderDBAccess.java
			$(JC) dataccess/OrderDBAccess.java

dataccess/SingletonConnection.class: dataccess/SingletonConnection.java
			$(JC) dataccess/SingletonConnection.java

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

exceptions: exceptions/ProgramErrorException.class exceptions/UserInputErrorException.class exceptions/ClientException.class exceptions/LocalityException.class exceptions/BusinessUnitException.class exceptions/OrderException.class exceptions/OrderLineException.class

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

exceptions/UserInputErrorException.class: exceptions/UserInputErrorException.java
	$(JC) exceptions/UserInputErrorException.java

exceptions/ProgramErrorException.class: exceptions/ProgramErrorException.java
	$(JC) exceptions/ProgramErrorException.java

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
