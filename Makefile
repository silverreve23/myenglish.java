default:
	@echo 'Preparing app myenglish!'
	-rm -r /opt/myenglish 
	-rm -r ./install
	-rm ~/.config/autostart/custom-command-myenglish.desktop
	-mkdir -m 755 ./install
	-mkdir -m 755 /opt/myenglish 
	tar -xvf ./myenglish.tar.gz -C ./install
	@echo 'Install app myenglish!'
	cp ./install/myenglish.jar /opt/myenglish
	cp -r ./install/db /opt/myenglish
	cp -r ./install/bin /opt/myenglish
	-cp ./install/desktop/myenglish.desktop ~/.config/autostart/custom-command-myenglish.desktop
	chmod 777 ~/.config/autostart/custom-command-myenglish.desktop
	chmod 777 /opt/myenglish/bin/myenglish
	apt-get update 
	apt-get install default-jdk -y
	apt autoremove -y
	rm -r ./install
