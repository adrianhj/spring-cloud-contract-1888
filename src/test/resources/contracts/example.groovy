package contracts

import org.springframework.cloud.contract.spec.Contract

Contract.make {
    request {
        method 'POST'
        url '/example'
        headers {
            contentType('multipart/form-data;boundary=9eae5c22-031b-414f-a328-252a528a93e9')
        }
        multipart(
            file: named(
                name: value(consumer(regex(nonEmpty())), producer("file.txt")),
                content: value(consumer(regex(nonEmpty())), producer(fileAsBytes("file.txt"))),
                contentType: value("text/plain")
            ),
            metadata: value(
                consumer('\\{"value": "example"}'),
                producer('{"value": "example"}')
            ),

            /*
            Below generates a working test but requires clients to now provide a filename when invoking:

            metadata: named(
                name: value(consumer(regex(nonEmpty())), producer("metadata.json")),
                content: value(consumer('\\{"value": "example"}'), producer('{"value": "example"}'),
                contentType: value("application/json")
            )

            Desired would potentially be:

            metadata: part(
                content: value(consumer('\\{"value": "example"}'), producer('{"value": "example"}'),
                contentType: value("application/json"),
                headers:
                    <map of any custom headers for this part, although looks unsupported via Rest Assured affordances>
            )

            */

        )
    }

    response {
        body: null
        status 204
    }
}