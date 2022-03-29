# BE-Epicode-ProgettoSettimana12(Gestione Catalogo Libri)
## Contenuti
* [Introduzione](#introduzione)
* [Tecnologie](#tecnologie)
* [Sicurezza](#sicurezza)
* [Spiegazione](#spiegazione)
* [Link Utili](#link)
* [Testing](#test)

## Introduzione
Questo progetto consiste in un'applicazione  (comprensiva solo di lato Back-End) per la gestione di catalogo di libri informatizzato, collegato ad un DataBase SQL. 

Abbiamo anche aggiunto un sistema di autenticazione basato su token JWT. Bisogna essere autenticati per accedere ai metodi. 
NOTA. Il sistema già presenta alcune entità nel database per eventuali test, oltre che a 2 utenti di default (user e admin).
	
## Tecnologie
Il progetto è stato creato usando:
* Java + Spring Boot
* PostegreSQL
* Spring Security + Token JWT
* JUnit
* Maven 
* Git (GitHub)
* Comprensivo di Swagger e OpenApi per la documentazione 

## Sicurezza
Come già accennato, per accedere al sistema gli utenti/user devono essere autenticati(è possibile effettuare una registrazione utente):

MODEL UTENTE PARAMETRIZZATO
```
{
  "userName": "Lorece",
  "password": "provapassword",
  "nome": "Lorenzo",
  "cognome": "De Ceglie",
  "mail": "strin@string.com",
  "roles": [
    "USER"
  ]
}
```

Anche gli utenti/user vengono salvati sul database, le password vengono criptate usando BCrypt Password Encoder.

Gli utenti sono divisi in Admin e User. Gli utenti con ruolo User possono soltanto accedere ai metodi di visualizzazione, i metodi di modifica/aggiorna/cancella sono disponibili soltanto per gli admin. Un utente può avere entrambi i ruoli contemporanemente.

	
## Spiegazione
L'applicazione viene utilizzata per la gestione delle seguenti entità:
* Autori
* Libri
* Categorie
* User(Autenticazione)

Le entità vengono salvate sul database con un valore numerico(Long) come chiave primaria.

Abbiamo a disposizione tutte le funzionalità di CRUD per tutte le entità.

Gli autori sono comprensivi di diversi dati:

ESEMPIO DI BODY JSON PER INSERIMENTO
```
{
  "id": 0,
  "nome": "Paolo",
  "cognome": "Rossi"
}
```
Alla creazione di un autore bisogna o passare semplicemente nome e cognome tramite stringhe.

I libri per essere creati NON necessitano di autori e categorie(i libri possono avere più autori e più categorie) ma possono essere aggiunti con un eventuale Update.

ESEMPIO DI BODY JSON PER Libro
```
{
  "id": 0,
  "titolo": "string",
  "annoPubblicazione": 0,
  "prezzo": 0,
  "autori": [
    {
      "id": 0,
      "nome": "string",
      "cognome": "string"
    }
  ],
  "categorie": [
    {
      "id": 0,
      "nome": "string"
    }
  ]
}
```
La categoria di un libro è stata creata come entità a sé stante
ESEMPIO DI BODY JSON PER Categoria
```
{
  "id": 0,
  "nome": "string"
}
```


## Link

Esempio di link di accesso a Swagger(Per la documentazione):
http://localhost:8080/swagger-ui/index.html#/

## Test
Sono stati implementati un numero consistente di test effettuati con Junit e MockMvc, presenti nelle apposite classi nel package di test.
