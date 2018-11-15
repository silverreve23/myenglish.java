default:
	@echo 'Preparing app myenglish!'
	-rm -r /opt/myenglish
	-rm -r /usr/lib/myenglish
	-rm -r ./install
	-rm ~/.config/autostart/custom-command-myenglish.desktop
	-mkdir -m 755 ./install
	-mkdir -m 755 /opt/myenglish 
	-mkdir -m 755 /usr/lib/myenglish
	tar -xvf ./myenglish.tar.gz -C ./install
	@echo 'Install app myenglish!'
	cp -r ./install/bin /opt/myenglish
	cp -r ./install/db /opt/myenglish
	cp ./install/myenglish.jar /opt/myenglish
	cp -r ./install/drivers/* /usr/lib/myenglish
	-cp ./install/desktop/myenglish.desktop ~/.config/autostart/custom-command-myenglish.desktop
	chmod 777 ~/.config/autostart/custom-command-myenglish.desktop
	chmod 777 /opt/myenglish/bin/myenglish
	apt-get update 
	apt-get install default-jdk -y
	apt autoremove -y
	rm -r ./install
