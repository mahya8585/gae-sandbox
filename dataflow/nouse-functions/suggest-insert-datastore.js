/**
 * Triggered from a message on a Cloud Storage bucket.
 *
 * @param {!Object} event The Cloud Functions event.
 * @param {!Function} The callback function.
 */
exports.processFile = (event, callback) => {
  console.log('-----data insert! -------');

  //TODO ファイル読み込み

  //TODO datasoter kind delete

  //TODO datasotore kind insert

  callback();
};
