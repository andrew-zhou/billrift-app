# BillRift Server
The back-end of the BillRift app
### Models
Group:
  - id: 
  - name: string
  - userIds: string[]


### Endpoints
#### Groups
`GET /groups?` - Get the list of group objects.  
`GET /group/:id/balances` - Get the list of balances for a group identified by id.  
`GET /group/:id/transactions` - Get the list of transactions for a group identified by id.  
`POST /group/:id/transaction` - add a new transaction to the group.  
#### Login
`POST /login` - Log in  
