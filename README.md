# api-forfaits-opt

Une API pour les forfaits telecom de l'OPT-NC, dans le cadre d'un stage avec l'[UNC](https://unc.nc/), et avec comme données celles
disponibles publiquement sur les sites de l'[OPT-NC](https://www.opt.nc/).

## Lancer le projet
### Prérequis : 
- Java Version : 23
- Maven Version : 3.9.9
- Quarkus Version : 3.17.3

### Compiler / lancer

A la racine du dépôt :

- compiler
    ```bash
    ./mvnw clean install
- lancer
    ```bash
    java -jar target/quarkus-app/quarkus-run.jar


- ou lancer en mode dev : 
    ```bash
    ./mvnw quarkus:dev