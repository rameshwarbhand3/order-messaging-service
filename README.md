### Steps to connect sns-service to aws localstack using commandline

1. How to up localstack in local

```
docker run  --rm -it  -p 4566:4566  -p 4510-4559:4510-4559  localstack/localstack
{
"TopicArn": "arn:aws:sns:ap-south-1:000000000000:order"
}
```

2. Then to create a topic use following command

```agsl

aws sns create-topic --name order --region ap-south-1 --endpoint http://localhost:4566
```

3. To subscribe a topic use following command

```
   aws sns subscribe --topic-arn arn:aws:sns:ap-south-1:000000000000:order --protocol email --notification-endpoint xyz@gmail.com --region ap-south-1 --endppoint http://localhost:4566
   {
   "SubscriptionArn": "arn:aws:sns:ap-south-1:000000000000:order:c775a78f-1e56-44a5-bdb6-7f82bdeb6bc6"
   }
   ```

4. To publish a message on topic use following command

```agsl
aws sns publish --topic-arn arn:aws:sns:ap-south-1:000000000000:order --message meassge-name south-1 --endppoint http://localhost:4566

```