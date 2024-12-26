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
        podman build -t forfaits-opt-nc .
### Lancer le conteneur :
   - ```bash
        podman run --name forfaits-container -p 8080:8080 forfaits-opt-nc

   - vérifier que l'endpoint `/hello` fonctionne : 
   (capture d'écran)

## Compiler et lire le rapport 
### Prérequis : 
- Latex : 
    ```bash
        sudo apt update
        sudo apt install texlive-latex-extra
### Compiler le rapport
- se rentre dans le repertoire `rapport/` du dépôt :
    ```bash
        cd rapport
- compiler en pdf : 
    ```bash
        pdflatex rapport_de_stage.tex
### Lire le rapport : 
une fois la commande ci-dessus executer un pdf à été généré : 
[capture d'écran]