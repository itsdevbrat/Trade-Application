# Trade-Application

Jar is present in the repository so can directly run it and execute below curls to test the behaviour


### Create a trade with maturity date before todays date (Rejected)
`
curl -X POST --location "http://localhost:3000/trade/" \
    -H "Content-Type: application/json" \
    -d "{
          \"tradeId\": \"T1\",
          \"version\": 3,
          \"counterPartyId\": \"p1\",
          \"bookId\": \"b1\",
          \"maturityDate\": \"2000-04-17\"
        }"
        `
        
        
### Create a trade with version as 2 (Accepted)
`
curl -X POST --location "http://localhost:3000/trade/" \
    -H "Content-Type: application/json" \
    -d "{
        \"tradeId\": \"T1\",
        \"version\": 2,
        \"counterPartyId\": \"p1\",
        \"bookId\": \"b2\",
        \"maturityDate\": \"2022-04-17\"
        }"
        `


### Create a trade with version as 3 (Accepted)
`
curl -X POST --location "http://localhost:3000/trade/" \
    -H "Content-Type: application/json" \
    -d "{
          \"tradeId\": \"T1\",
          \"version\": 3,
          \"counterPartyId\": \"p1\",
          \"bookId\": \"b1\",
          \"maturityDate\": \"2022-04-17\"
        }"
        `


### Create a trade with version as 1 (Rejected as version is less than the existing trades)
`
curl -X POST --location "http://localhost:3000/trade/" \
    -H "Content-Type: application/json" \
    -d "{
          \"tradeId\": \"T1\",
          \"version\": 1,
          \"counterPartyId\": \"p1\",
          \"bookId\": \"b21\",
          \"maturityDate\": \"2022-04-17\"
        }"
        `

### List down all trades
`
curl -X GET --location "http://localhost:3000/trade/all" \
    -H "Accept: application/json"
    `

### Update a trade by passing same version number (Accepted and Updates)
`
curl -X POST --location "http://localhost:3000/trade/" \
    -H "Content-Type: application/json" \
    -d "{
        \"tradeId\": \"T1\",
        \"version\": 2,
        \"counterPartyId\": \"p1\",
        \"bookId\": \"updatedBookId\",
        \"maturityDate\": \"2022-04-17\"
        }"
        `

### List down the trades
`
curl -X GET --location "http://localhost:3000/trade/all" \
    -H "Accept: application/json"
    `
