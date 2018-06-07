var request = require('request');
var storage = require('@google-cloud/storage')();
var fs = require('fs');
var async = require('async');

/**
 * Triggered from a message on a Cloud Storage bucket.
 *
 * @param {!Object} event The Cloud Functions event.
 * @param {!Function} The callback function.
 */
exports.processFile = (event, callback) => {
    let filename = event.data.name;
    let bucketname = 'suggest-batch-start';
    console.log('--------start name extract!------' + filename);

    //TODO ファイル読み込み
    let productFile = storage.bucket(bucketname).file(filename);
    productFile.makePublic(function() {
        console.log('start get file');

        getProducts(filename, function(json) {
            console.log(json);

            storage.bucket(bucketname).file(filename).makePrivate(function() {
                console.log('private');
                callback();
            });

            // name抽出
            var productnames = [];
            async.each(json, function(product, callback) {
                productnames.push(product.name);
            });

            let uploadOptions = {
                destination: 'name-' + filename
            };
            storage.bucket('[バケツ名   ]')
                .upload(productnames, uploadOptions, function(err, file) {

                });


            console.log('end');
            callback();
            return;
        });
    });


    //TODO name抽出

    //TODO ファイル化

    // TODO temp1バケットへ
};

function getProducts(name, callback) {
    console.log('in getProducts : ' + 'https://storage.googleapis.com/[バケツ名]/' + name);
    var options = {
        url: 'https://storage.googleapis.com/[バケツ名]/' + name,
        method: 'GET',
        json: true
    };

    request(options, function(error, response, body) {
        console.log('in request');
        callback(body);
    });
};