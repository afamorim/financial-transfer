# financial-transfer
Financial Transfer Task

##This project have been builded using the clean architecture rules
###The Domain package will be the `br.com.oreri.financialTransfer.domain` with all policies needed to build the business rules

###The Infrastructure package will the `br.com.oreri.financialTransfer.infrastructure` has concret classes that will be used to interecat with database frameworks and other stuffs

###The Application package will be the `br.com.oreri.financialTransfer.application` has all classes needed to link the UI with project policies

To build the policies has be used the `chain of responsability` pattern to make clear the segregation and evolution of business rules

To compile this project execute `mvn clean install` 

To execute the code the project run `java -jar target/financialTransfer-0.0.1-SNAPSHOT.jar`

After that you can run `curl --location --request POST 'http://localhost:8080/transfer/v1' \
--header 'Content-Type: application/json' \
--data-raw '{
"originAccount": "XXXX-X",
"destinationAccount": "YYYY-Y",
"transferAmount": 100.00,
"transferDate": "2021-09-25"
}'` to include an test transfer

And you can run `curl --location --request GET 'http://localhost:8080/transfer/v1'` to get back all transfers saved in memory