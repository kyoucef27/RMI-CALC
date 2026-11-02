# TP2 : Calculatrice Distribuée avec Java RMI (Remote Method Invocation)

Ce projet implémente un service de calculatrice distribuée utilisant **Java RMI** (*Remote Method Invocation*). Il permet à un client d'exécuter des opérations mathématiques sur un serveur distant de manière transparente.

---

## 📋 Table des matières
1. [Architecture & Fonctionnement](#-architecture--fonctionnement)
2. [Structure des fichiers](#-structure-des-fichiers)
3. [Opérations disponibles](#-opérations-disponibles)
4. [Prérequis](#-prérequis)
5. [Guide d'exécution étape par étape](#-guide-dexécution-étape-par-étape)
6. [Détail des commandes (PowerShell, CMD, Bash)](#-détail-des-commandes)
7. [Exemple de sortie](#-exemple-de-sortie)

---

## 🏛 Architecture & Fonctionnement

L'application repose sur le modèle classique de Java RMI composé de 3 éléments :
```text
┌──────────────────────┐         1. Lookup ("CalculatorService")        ┌─────────────────────────┐
│   CalculatorClient   │ ─────────────────────────────────────────────> │       RMI Registry      │
│                      │ <───────────────────────────────────────────── │       (Port 1099)       │
└──────────────────────┘         2. Référence du stub distant           └─────────────────────────┘
          │                                                                          ▲
          │                                                                          │ 0. Rebind
          │ 3. Invocation distante (add, modulo, puissance, racineCarree...)          │
          ▼                                                                          │
┌────────────────────────────────────────────────────────────────────────────────────┴────┐
│ CalculatorServer (CalculatorImpl)                                                       │
└─────────────────────────────────────────────────────────────────────────────────────────┘
```

1. **Le Serveur (`CalculatorServer`)** :
   - Démarre le registre RMI sur le port `1099` via `LocateRegistry.createRegistry(1099)`.
   - Crée l'instance de `CalculatorImpl`.
   - Publie l'objet distant dans le registre sous le nom `"CalculatorService"`.

2. **Le Client (`CalculatorClient`)** :
   - Recherche le service distant via `Naming.lookup("rmi://localhost:1099/CalculatorService")`.
   - Reçoit un **stub** implémentant l'interface `Calculator`.
   - Appelle les méthodes distantes comme s'il s'agissait d'un objet local.

---

## 📁 Structure des fichiers

| Fichier | Rôle |
|---|---|
| `Calculator.java` | **Interface RMI** définissant les contrats des méthodes distantes (hérite de `java.rmi.Remote`). |
| `CalculatorImpl.java` | **Implémentation** de l'interface (hérite de `UnicastRemoteObject`). |
| `CalculatorServer.java` | Programme principal du **serveur** qui publie l'objet distant dans le registre RMI. |
| `CalculatorClient.java` | Programme principal du **client** qui appelle les méthodes distantes et affiche les résultats. |

---

## 🧮 Opérations disponibles

Toutes les opérations prennent en charge les types `double` et lèvent une `RemoteException` en cas d'erreur de communication réseau :

| Opération | Méthode(s) | Description / Gestion d'erreur |
|---|---|---|
| **Addition** | `add(a, b)` | Retourne $a + b$ |
| **Soustraction** | `subtract(a, b)` | Retourne $a - b$ |
| **Multiplication** | `multiply(a, b)` | Retourne $a \times b$ |
| **Division** | `divide(a, b)` | Retourne $a / b$. Lève une `ArithmeticException` si $b = 0$. |
| **Modulo** | `modulo(a, b)` / `mod(a, b)` | Retourne $a \pmod b$. Lève une `ArithmeticException` si $b = 0$. |
| **Puissance** | `puissance(a, b)` / `power(a, b)` | Retourne $a^b$ via `Math.pow(a, b)`. |
| **Racine carrée** | `racineCarree(a)` / `sqrt(a)` / `squareRoot(a)` | Retourne $\sqrt{a}$. Lève une `ArithmeticException` si $a < 0$. |

---

## ⚙️ Prérequis

- **Java JDK** installé (version 8 ou supérieure, testé avec OpenJDK 21).
- Vérifiez votre installation dans un terminal :
  ```bash
  javac -version
  java -version
  ```

---

## 🚀 Guide d'exécution étape par étape

### 1. Se positionner dans le dossier du projet
Ouvrez un terminal dans le répertoire contenant les fichiers `.java` :
```powershell
cd "c:\Users\kefif\Desktop\studies\ANS\tp2\RMI"
```

### 2. Compiler les fichiers Java
Puisque les fichiers déclarent le package `package tp2;`, utilisez l'option `-d .` pour que le compilateur génère l'arborescence de package requise :
```powershell
javac -d . *.java
```

### 3. Démarrer le Serveur RMI
Dans un **premier terminal**, lancez le serveur :
```powershell
java -cp . tp2.CalculatorServer
```
Vous devriez voir s'afficher :
```text
Calculator RMI Server is ready.
```
*(Laissez ce terminal ouvert en arrière-plan afin que le serveur reste actif)*.

### 4. Exécuter le Client RMI
Dans un **deuxième terminal**, lancez le client :
```powershell
java -cp . tp2.CalculatorClient
```

---

## 💻 Détail des commandes

### Sous Windows PowerShell :
```powershell
# 1. Compilation
javac -d . *.java

# 2. Lancement du serveur en tâche de fond (ou dans un terminal séparé)
Start-Process -NoNewWindow java -ArgumentList "-cp . tp2.CalculatorServer"

# 3. Lancement du client
java -cp . tp2.CalculatorClient
```

### Sous Windows CMD (Invite de commandes) :
```cmd
REM 1. Compilation
javac -d . *.java

REM 2. Lancer le serveur dans une nouvelle fenêtre
start java -cp . tp2.CalculatorServer

REM 3. Lancer le client
java -cp . tp2.CalculatorClient
```

### Sous Linux / macOS (Bash) :
```bash
# 1. Compilation
javac -d . *.java

# 2. Lancer le serveur en arrière-plan
java -cp . tp2.CalculatorServer &

# 3. Lancer le client
java -cp . tp2.CalculatorClient
```

---

## 📊 Exemple de sortie

Sortie obtenue lors de l'exécution de `CalculatorClient` avec les valeurs $a = 12.5$ et $b = 4.5$ :

```text
Addition: 17.0
Subtraction: 8.0
Multiplication: 56.25
Division: 2.7777777777777777
Modulo (12.5 % 4.5): 3.5
Puissance (12.5 ^ 4.5): 86316.74575031098
Racine carrée (sqrt(12.5)): 3.5355339059327378
```
"# RMI-CALC" 
