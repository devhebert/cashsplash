# Projeto-Cashsplash
Projeto de cash back

<img src="https://cdn.jsdelivr.net/gh/devicons/devicon/icons/java/java-original.svg" width="40" height="40"/>

<h4>Professor</h4>
<ul>
  <li>Jesse Haniel</li>
</ul>

<h4>Turma 1000</h4>
<ul>
<li>Bruno Bacs</li>
<li>Hebert Luiz</li>
<li>Ilessa Lobo</li>
<li>Victor Hugo</li>
</ul>

### Itens Obrigatórios
<ul>
  <li>Construir uma API;</li>
  <li>Persistência em banco de dados (H2 ou Postgres);</li>
  <li>Configuração de segurança: controle de rota e login (jwt opcional);</li>
  <li>Consumo de uma API externa pública;</li>
  <li>Frontend opcional;</li>
</ul>

Algumas citações para facilitar o teste de Requisições mais relevantes:

POST Login:  -> http://localhost:8080/login
```
{
  "username": "admin",
  "password": "Teste"
}
```
GET Users -> {{baseUrl}}/users

POST PRODUCT -> {{baseUrl}}/products/create
```
{
  "name": "Mouse",
  "description": "Mouse logitech",
  "price": "155",
  "amount": "10"
}
```
POST CAMPAIGN -> {{baseUrl}}/campaigns
```
{
  "name": "Natal",
  "description": "Campanha de natal",
  "offValue": "0.05"
}
```

POST SALE -> http://localhost:8080/sales/create
```
{
  "idUser": "1",
  "idCustomer": "1",
  "items": [
    {
        "idProduct": 1,
        "amount": 4
    }
  ],
  "idCampaign": "1"
}
```
GET Calculate SALE -> {{baseUrl}}/39d891bc-6124-4eeb-b2e2-e91c08b3fb67/calculate-totals


