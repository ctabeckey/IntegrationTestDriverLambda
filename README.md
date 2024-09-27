# IntegrationTestDriverLambda
Allows invocation of a Lambda during CloudFormation deployment (used for integration testing)
Use: sam deploy --parameter-overrides BuildNumber=1
The BuildNumber must change on each deployment, otherwise 'update' requests will not occur.
