install: install_before remove preparing
	@echo -e '\n\e[34mEnter your email*:\e[0m '
	@read username; if [ -z "$${username}" ]; then echo 'Email is required!\n'; exit 1; fi; echo "useremail=$${username}" > /opt/myenglish/config/config.ini
	@echo -e '\e[34mEnter your language for get word [ua, ru], default en:\e[0m '
	@read wordlang; if [ -z "$${wordlang}" ]; then wordlang='en'; fi; echo "wordlang=$${wordlang}" >> /opt/myenglish/config/config.ini
	@echo -e '\e[34mEnter your translate language [en, ru], default ua:\e[0m '
	@read translang; if [ -z "$${translang}" ]; then translang='ua'; fi; echo "translang=$${translang}" >> /opt/myenglish/config/config.ini
	tar -xvf ./myenglish.tar.gz -C ./install
	@echo -e '\n\e[0;32mInstall app myenglish!\n\e[0m'
	cp -r ./install/bin /opt/myenglish
	cp ./install/myenglish.jar /opt/myenglish
	cp -r ./install/drivers/* /usr/lib/myenglish
	-cp ./install/desktop/myenglish.desktop ~/.config/autostart/custom-command-myenglish.desktop
	chmod 777 ~/.config/autostart/custom-command-myenglish.desktop
	chmod 777 /opt/myenglish/bin/myenglish
	-apt-get update 
	apt-get install default-jdk -y
	-apt autoremove -y
	rm -r ./install
	#cd /opt/myenglish/bin && ./myenglish &
	@echo -e '\n\e[0;32mThanks! Your web cabinet http://myenglish.tk\n\e[0m'

uninstall: uninstall_before romove_before remove
	@echo -e '\n\e[0;32mThanks! Your myenglish app and web cabinet removed!\n\e[0m'
	
uninstall_before:
	@echo -e '\n\e[0;32mPreparing app myenglish for uninstall!\n\e[0m'
romove_before: 	
	@echo -e '\n\e[0;31mThis command will delete myenglish app! Are you shure to continue?\e[0m'
	@read tmp;
install_before: 	
	@echo -e '\n\e[0;32mPreparing app myenglish for install!\n\e[0m'

preparing:
	-mkdir -m 755 ./install
	-mkdir -m 755 /opt/myenglish 
	-mkdir -m 755 /usr/lib/myenglish
	-mkdir -m 755 /opt/myenglish/config
	-mkdir -m 777 ~/.config/autostart
	-touch /opt/myenglish/config/config.ini
	-chmod 755 /opt/myenglish/config/config.ini
remove:
	-rm -r /opt/myenglish
	-rm -r /usr/lib/myenglish
	-rm -r ./install
	-rm -i ~/.config/autostart/custom-command-myenglish.desktop


