cat uiot.txt

function install_prerequisites()
{
	sudo apt-get update
	sudo apt-get install apache2;
	sudo apt-get install git;
	sudo apt-get update;
	sudo apt-get install php7.0;
	sudo apt-get install php7.0-dev;
	sudo apt-get isntall php-pear;
	sudo apt-get install php-curl;
	sudo apt install libapache2-mod-php;
	sudo apt-get install python;
	sudo a2enmod rewrite;
	sudo service apache2 restart;
	install_couchbase;

}

function install_couchbase()
{
	sudo apt-get install libssl-dev;
	sudo wget "https://packages.couchbase.com/releases/4.6.1/couchbase-server-enterprise_4.6.1-ubuntu14.04_amd64.deb";
	sudo dpkg -i couchbase-server-enterprise_4.6.1-ubuntu14.04_amd64.deb;
	install_sdk;
}

function create_cluster()
{
	while true; do
	    read -p "We will now configure the couchbase cluster. Please input the desired Memory: " yn
	  if [ $yn -gt 512 ];
    then
				memory=$yn;
				read -p "Please input the desired username for couchbase: " username
				read -p "Please input the desired password for couchbase: " password

				cd /opt/couchbase/bin/;
				./couchbase-cli cluster-init -c 127.0.0.1:8091 -u $username -p $password --cluster-name='raise' --cluster-ramsize=$((memory+0));
				install_sdk;
				break;


		else
				echo "Memory must be higher than 512MB";
		fi

	done

}

function install_sdk()
{
	sudo wget http://packages.couchbase.com/releases/couchbase-release/couchbase-release-1.0-2-amd64.deb;
	sudo dpkg -i couchbase-release-1.0-2-amd64.deb;
	sudo apt-get update;
	sudo apt-get install libcouchbase-dev build-essential zlib1g-dev;
	sudo pecl install pcs-1.3.3
	sudo cp /var/www/html/Install/20-pcs.ini /etc/php/7.0/conf.d/20-pcs.ini;
	sudo mv /var/www/html/Install/20-pcs.ini /etc/php/7.0/conf.d/20-pcs.ini;
	sudo pecl install couchbase
	sudo cp /var/www/html/Install/30-couchbase.ini /etc/php/7.0/conf.d/30-couchbase.ini;
	sudo mv /var/www/html/Install/30-couchbase.ini /etc/php/7.0/conf.d/30-couchbase.ini;

	cd /var/www/html ; sudo rm index.html ; sudo git init ; sudo git remote add origin https://github.com/UIoT/Apps.git ; sudo git pull origin devel
    
    sudo apt install php7.0-mbstring
    sudo a2dismod mpm_event
    sudo a2enmod mpm_prefork
    sudo service apache2 restart
    cd /var/www/html/Install; sudo php move.php
    sudo service apache2 restart

	sleep 5 &
	PID=$!
	i=1
	sp="/-\|"
	echo -n ' '
	while [ -d /proc/$PID ]
	do
	  printf "\b${sp:i++%${#sp}:1}"
	done
	sudo nano /var/www/html/Install/conf.php
	sudo php /var/www/html/Install/install.php
	
}

while true; do
    read -p "We are going to install RAISe and all it's dependencies. Continue?  [Y/N] " yn
    case $yn in
        [Yy]* ) install_prerequisites; break;;
        [Nn]* ) exit;;
        * ) echo "Please answer yes or no.";;
    esac
done