FROM php:8.4-fpm

RUN apt-get update -y && apt-get upgrade -y && apt-get install unzip git libssl-dev -y

# Install Composer
RUN cd ~
RUN curl -sS https://getcomposer.org/installer -o /tmp/composer-setup.php

RUN HASH=`curl -sS https://composer.github.io/installer.sig`
RUN php -r "if (hash_file('SHA384', '/tmp/composer-setup.php') === '$HASH') { echo 'Installer verified'; } else { echo 'Installer corrupt'; unlink('composer-setup.php'); } echo PHP_EOL;"
RUN php /tmp/composer-setup.php --install-dir=/usr/local/bin --filename=composer

# Install PDO MySQL extension 
RUN docker-php-ext-install pdo pdo_mysql 
