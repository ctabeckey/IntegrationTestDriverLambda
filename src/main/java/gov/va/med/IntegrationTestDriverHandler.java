package gov.va.med;

import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.events.CloudFormationCustomResourceEvent;
import software.amazon.lambda.powertools.cloudformation.AbstractCustomResourceHandler;
import software.amazon.lambda.powertools.cloudformation.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class IntegrationTestDriverHandler extends AbstractCustomResourceHandler {
    private final static Logger logger = LoggerFactory.getLogger(IntegrationTestDriverHandler.class);

    @Override
    protected Response create(CloudFormationCustomResourceEvent createEvent, Context context) {
        logger.info("create()");

        if (createEvent.getResourceProperties() != null)
            createEvent.getResourceProperties().entrySet().stream()
                    .forEach(entry -> logger.info("{} -> {}", entry.getKey(), entry.getValue()));

        return Response.success();
    }

    @Override
    protected Response update(CloudFormationCustomResourceEvent updateEvent, Context context) {
        logger.info("update()");
        return Response.success();
    }

    @Override
    protected Response delete(CloudFormationCustomResourceEvent deleteEvent, Context context) {
        logger.info("delete()");
        return Response.success();
    }
}


// ================================================================================================

//exports.handler = async (event, context) => {
//        console.log('S3 Client Version:', s3Package.version);
//    console.log('Request type:', event.RequestType);
//
//// Ensure immediate response if event loop remains active
//context.callbackWaitsForEmptyEventLoop = false;
//
//// Extract bucket name
//let bucketName = event.ResourceProperties?.BucketName || event.bucketName;
//    if (!bucketName) {
//        console.log("Bucket name not found in request");
//        return sendResponse(event, context, "FAIL", { message: "Bucket name not found in request" });
//        }
//
//        console.log('bucketName:', bucketName);
//let status = "FAIL";
//let responseMessage = "Unknown status message";
//
//    if (event.RequestType === 'Delete') {
//        try {
//        // List objects in the bucket
//        const listParams = { Bucket: bucketName };
//        const listedObjects = await s3.send(new ListObjectsV2Command(listParams));
//        const objects = listedObjects.Contents;
//
//            console.log('Getting bucket objects');
//
//            if (objects && objects.length > 0) {
//        for (var index = 0; index < objects.length && index < 10; index++) {
//        console.log("Deleting object ", objects[index]);
//                }
//
//                        // Create list of object keys to delete
//                        const deleteParams = {
//Bucket: bucketName,
//Delete: { Objects: objects.map(({ Key }) => ({ Key })) }
//        };
//
//        // Delete the listed objects
//        console.log('Deleting bucket objects');
//await s3.send(new DeleteObjectsCommand(deleteParams));
//        console.log('Deleted bucket objects');
//status = "SUCCESS"
//responseMessage = "Bucket contents have been deleted";
//        } else {
//        console.log(`No objects found in bucket: ${bucketName}`);
//status = "SUCCESS"
//responseMessage = "No objects found in bucket";
//        }
//        } catch (error) {
//        console.error(`Error: ${error.message}`);
//status = "FAIL"
//responseMessage = error.message;
//        }
//                } else {
//                console.log("Request type is not DELETE, ignoring");
//status = "SUCCESS"
//responseMessage = "Request type is not DELETE";
//        }
//
//        return sendResponse(event, context, status, { message: responseMessage });
//
//        };
//
//// Send response to the pre-signed S3 URL
//function sendResponse(event, context, responseStatus, responseData) {
//    const responseBody = JSON.stringify({
//            Status: responseStatus,
//            Reason: `See the details in CloudWatch Log Stream: ${context.logStreamName}`,
//    PhysicalResourceId: context.logStreamName,
//            StackId: event.StackId,
//            RequestId: event.RequestId,
//            LogicalResourceId: event.LogicalResourceId,
//            Data: responseData,
//    });
//
//    console.log("Sending response to:", event.ResponseURL);
//    console.log("RESPONSE BODY:\n", responseBody);
//
//    const https = require("https");
//    const url = require("url");
//
//    const parsedUrl = url.parse(event.ResponseURL);
//    const options = {
//            hostname: parsedUrl.hostname,
//            port: 443,
//            path: parsedUrl.path,
//            method: "PUT",
//            headers: {
//        "content-type": "",
//                "content-length": responseBody.length,
//    },
//    };
//
//    // create request, add callback that will log the response and mark the context
//    // as done
//    const request = https.request(options, function (response) {
//        console.log("STATUS:", response.statusCode);
//        console.log("HEADERS:", JSON.stringify(response.headers));
//    });
//
//    request.on("error", function (error) {
//        console.log("sendResponse Error:", error);
//    });
//
//    request.write(responseBody);
//    request.end();
//}
