# api-forfaits-opt

Une API pour les forfaits telecom de l'OPT-NC, dans le cadre d'un stage avec l'[UNC](https://unc.nc/), et avec comme données celles
disponibles publiquement sur les sites de l'[OPT-NC](https://www.opt.nc/).

## Lancer le projet sans conteneur
### Prérequis : 
- Java Version : 23
- Maven Version : 3.9.9
- Quarkus Version : 3.17.3
### Lancer
A la racine du dépôt :
- lancer
    ```bash
    mvn quarkus:dev
## Lancer le projet dans un conteneur
### Prérequis (sous ubunutu) : 
- Podman : 
    ```bash
        sudo apt-get update
        sudo apt-get -y install podman
### Build l'image : 
- A la racine du dépôt :
    ```bash
        mvn package -Dquarkus.container-image.tag=1.0.0-SNAPSHOT -Dquarkus.container-image.name=forfaits-opt-nc
### Lancer le conteneur :
- ```bash
    podman run --name forfaits-opt -p 8080:8080 forfaits-opt-nc:1.0.0-SNAPSHOT

- vérifier que l'endpoint `/offres` fonctionne :  
    ```bash 
    http GET :8080/offres
## Compiler et lire le rapport 
### Prérequis : 
- Latex : 
    ```bash
        sudo apt update
        sudo apt install texlive-latex-extra
### Compiler et lire le rapport
- compiler en pdf : 
    ```bash
        pdflatex rapport_de_stage.tex
- lire : 
    ```bash 
    xdg-open rapport_de_stage.pdf