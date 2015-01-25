# Archimed
## Projet
Archimed est un manager de configuration pour les architectures de type
mediateur.
### Features
* Edit/Create Configurations
* Full Javascript/HTML interface
* Export configuration in XML format


## Installation de l'env de développement pour le front

Dependencies for build:

* nodejs (http://nodejs.org/)
* How to install Node.js and npm https://docs.npmjs.com/getting-started/installing-node
* You need Grunt-cli `sudo npm install -g grunt-cli`
* You need Bower `sudo npm install -g bower`
* You need Compass for building CSS http://compass-style.org/install/

```
git clone https://github.com/Afalide/esgi_archimed.git
cd esgi_archimed/archimed-front
npm install
bower install
grunt serve
```
## Installation de l'env de dev pour le backend

Dependencies for build:

* Java 1.7 or greater

```
cd archimed-backend
./activator "run 9001"

# Under Windows
/activator.bat "run 9001"

```


