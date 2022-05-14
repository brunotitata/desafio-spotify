# Album Spotify [![Build Status](https://travis-ci.com/brunotitata/beblue-test.svg?branch=master)](https://travis-ci.com/brunotitata/beblue-test) [![HitCount](http://hits.dwyl.io/brunotitata@gmail.com/beblue-test.svg)](http://hits.dwyl.io/brunotitata@gmail.com/beblue-test)

**Instalação**
<br />
Para executar o projeto, entre na pasta e execute os seguintes comandos:
<br />
```
$ mvn clean install
$ docker build -t desafio:latest .
$ docker run -p 8080:8080 desafio:latest
```
**Após executar o serviço, segue o manual abaixo dos endpoints**
<br />
<br />
Para alimentar o catálogo de discos, o serviço deverá consumir a API do Spotify e retornar os 50 primeiros discos de cada gênero **(POP, ROCK, CLASSIC, MPB)**
<br />
<br />
**Request:**
<br />
`GET: http://localhost:8080/download`
<br />
<br />
**Response: 200(OK)**
<br />

# endpoint's para buscar os catalogos de albums

Após ter alimentado a base de dados, podemos consultar o catalogo de discos de forma paginada e com utilização de filtros
<br />
<br />
Request:
<br />
`GET: http://localhost:8080/buscar?genero=rock&page=0&direction=ASC`
<br />
<br />
**Response: 200(OK)**
<br />
```
{
            "id": "65P84ZBtHARyccEPc4XFrj",
            "nome": "100 Rock",
            "preco": 84.56,
            "genero": "rock"
        },
        {
            "id": "15sQJn13XwnVk7YIVR0Lpb",
            "nome": "Ao Vivo no Rock In Rio",
            "preco": 20.19,
            "genero": "rock"
        },
        {
            "id": "7xe8VI48TxUpU1IIo0RfGi",
            "nome": "Blue Hawaii",
            "preco": 28.19,
            "genero": "rock"
        }
```

Podemos tambem buscar o album pelo seu identificador
<br />
<br />
**Request:**
<br />
`GET: http://localhost:8080/buscar/65P84ZBtHARyccEPc4XFrj`
<br />
<br />
**Response: 200(OK)**
<br />
```
{
    "id": "65P84ZBtHARyccEPc4XFrj",
    "nome": "100 Rock",
    "preco": 98.07,
    "genero": "rock"
}
```


# endpoints para venda de albums

Registrando uma nova venda
<br />
<br />
**Request:**
<br />
`POST: http://localhost:8080/venda/`
<br />
<br />
**Body:**
<br />
```
{
	"idAlbum": "65P84ZBtHARyccEPc4XFrj",
	"diaDaCompra" : "2019-02-18",
	"quantidade": 2
}
```
**Response: 201 (Created)** Location → http://localhost:8080/venda/3971bfad-7be5-4e51-ac66-2a54853cf3c1

Endpoint responsavel por buscar venda pelo seu identificador
<br />
<br />
**Request:**
<br />
`GET: http://localhost:8080/venda/3971bfad-7be5-4e51-ac66-2a54853cf3c1`
<br />
<br />
**Response: 200(OK)**
```
{
    "id": "3971bfad-7be5-4e51-ac66-2a54853cf3c1",
    "nomeAlbum": "'70s Pop #1's",
    "valorAlbum": 32.7,
    "valorCashBack": 0.25,
    "quantidade": 2,
    "valorTotal": 65.4,
    "dataDaCompra": "2019-02-24",
    "valorRetorado": 16.35
}
```
<br />

Endpoint responsavel por buscar vendas com utilização de parametros
<br />
<br />
**Request:**
`GET: http://localhost:8080/venda?startDate=2019-02-12&endDate=2019-02-12`
<br />
**Response: 200(OK)**
<br />
```
{
    "content": [
        {
            "id": "77dec1a9-68b8-4f60-a5d8-f1fbd457ce29",
            "nomeAlbum": "100 Rock",
            "valorAlbum": 98.07,
            "valorCashBack": 0.40,
            "quantidade": 2,
            "valorTotal": 196.14,
            "dataDaCompra": "2019-02-12"
            "valorRetorado": 78.46
        }
    ],
    "last": true,
    "totalPages": 1,
    "totalElements": 1,
    "size": 20,
    "number": 0,
    "sort": [
        {
            "direction": "DESC",
            "property": "dataDaCompra",
            "ignoreCase": false,
            "nullHandling": "NATIVE",
            "descending": true,
            "ascending": false
        }
    ],
    "first": true,
    "numberOfElements": 1
}
```
