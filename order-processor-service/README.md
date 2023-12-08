# Order Producer Service

-----

# Tech Stack

- Java 17
- Spring Boot
- Maven
- AWS SNS
- AWS SQS
- Localstack
- Testcontainers
- Junit5

# local setup

## Steps to connect sns-service to aws localstack using commandline

1. How to up localstack in local

```
docker run  --rm -it  -p 4566:4566  -p 4510-4559:4510-4559  localstack/localstack
```

2. Then to create a topic use following command

```shell
  aws sns create-topic --name order --region ap-south-1 --endpoint http://localhost:4566 
  {
     TopicArn: arn:aws:sns:ap-south-1:000000000000:order
  }
```

3. Let's create a quque.This quque can be subscriber of sns topic.

```
aws --endpoint http://localhost:4566 sqs create-queue --queue-name order  --region ap-south-1
```

4. To subscribe a topic use following command

```
aws --region ap-south-1 --endpoint-url=http://localhost:4566 sns subscribe --topic-arn   arn:aws:sns:ap-south-1:000000000000:order  --protocol sqs --notification-endpoint arn:aws:sqs:ap-south-1:000000000000:order  --output table
   {
   "SubscriptionArn": "arn:aws:sns:ap-south-1:000000000000:order:c775a78f-1e56-44a5-bdb6-7f82bdeb6bc6"
   }
   ```

5. To publish a message on topic use following command

```
aws sns publish --topic-arn arn:aws:sns:ap-south-1:000000000000:order --message meassge-name south-1 --endppoint http://localhost:4566

```

6. To view the messages that is sent to the SNS topic, lets subscribe SQS queue to the topic.

```
aws --region ap-south-1 --endpoint-url http://localhost:4566 sns subscribe --topic-arn   arn:aws:sns:ap-south-1:000000000000:order   --protocol sqs --notification-endpoint arn:aws:sqs:ap-south-1:000000000000:order --output table
```

Note : please use sqs arn instead of sqs endpoint

7. To check attribute return by sqs.

```
aws sqs get-queue-attributes --queue-url http://localhost:4566/000000000000/order --attribute-names All --endpoint-url http://localhost:4566 --region ap-south-1
```

8. To publish message to sns use following command.

```
aws sns publish --endpoint-url=http://localhost:4566 --topic-arn arn:aws:sns:ap-south-1:000000000000:order --message "Hello World"  --region ap-south-1
```

9. To receive a message(event) to sqs use following command

```
aws --region 'ap-south-1' --endpoint-url=http://localhost:4566 sqs receive-message --visibility-timeout=5 --max-number-of-messages=5 --queue-url "http://localhost:4566/000000000000/order"
```

10. To subscribe your endpoint in local by sns topic use following command:
    Note: use your ip address of your local machine endpoint

```shell
aws --region ap-south-1 --endpoint-url=http://localhost:4566 sns subscribe --topic-arn   arn:aws:sns:ap-south-1:000000000000:order  --protocol http --notification-endpoint http://`hostname -I | awk '{print $1}'`:8081/api/orders/notify  --output table
```